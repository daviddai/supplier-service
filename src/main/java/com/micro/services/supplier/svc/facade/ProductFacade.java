package com.micro.services.supplier.svc.facade;

import com.micro.services.event.bus.event.ProductAvailabilityUpdated;
import com.micro.services.event.bus.event.ProductCreated;
import com.micro.services.event.bus.event.model.ProductAccessibility;
import com.micro.services.event.bus.event.model.ProductAccessibilityDateRange;
import com.micro.services.event.bus.event.model.ProductContent;
import com.micro.services.event.bus.publisher.EventPublisher;
import com.micro.services.supplier.svc.dao.model.ProductAvailabilityRuleDto;
import com.micro.services.supplier.svc.dao.model.ProductDetailDto;
import com.micro.services.supplier.svc.exception.SupplierServiceException;
import com.micro.services.supplier.svc.model.request.CreateProductRequest;
import com.micro.services.supplier.svc.model.request.ProductAvailabilityRule;
import com.micro.services.supplier.svc.model.request.UpdateProductDetailRequest;
import com.micro.services.supplier.svc.model.response.ProductApiModel;
import com.micro.services.supplier.svc.model.response.ProductAvailabilityRuleApiModel;
import com.micro.services.supplier.svc.model.response.ProductDetailApiModel;
import com.micro.services.supplier.svc.service.ProductAvailabilityRuleService;
import com.micro.services.supplier.svc.service.ProductDetailService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductFacade {

    private final Logger logger = LoggerFactory.getLogger(ProductFacade.class);

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductAvailabilityRuleService productAvailabilityRuleService;

    @Autowired
    private EventPublisher eventPublisher;

    public ProductApiModel createProduct(CreateProductRequest request) throws SupplierServiceException {
        ProductDetailDto newAddedProductDetailDto = productDetailService.addDetail(request);
        List<ProductAvailabilityRuleDto> productAvailabilityRuleDtos = productAvailabilityRuleService.addAvailabilityRules(
                newAddedProductDetailDto.getProductCode(), request.getProductAvailabilityRules());

        if (request.isPublish()) {
            ProductCreated productCreated = constructProductCreatedEvent(constructProductContent(newAddedProductDetailDto));
            publishProduct(productCreated);

            if (CollectionUtils.isNotEmpty(request.getProductAvailabilityRules())) {
                ProductAvailabilityUpdated productAvailabilityUpdated =
                        new ProductAvailabilityUpdated(constructProductAccessibility(
                                newAddedProductDetailDto.getProductCode(),
                                constructProductAccessibilityDateRanges(productAvailabilityRuleDtos)
                        ));
                publishProductAvailability(productAvailabilityUpdated);
            }
        }

        ProductDetailApiModel productDetailApiModel = constructProductDetailApiModel(newAddedProductDetailDto);
        ProductAvailabilityRuleApiModel productAvailabilityRuleApiModel = constructProductAvailabilityApiModel();
        return new ProductApiModel(productDetailApiModel, productAvailabilityRuleApiModel);
    }

    public ProductDetailApiModel findProduct(String productCode) throws SupplierServiceException {
        ProductDetailDto product = productDetailService.findDetailByProductCode(productCode);
        return constructProductDetailApiModel(product);
    }

    public ProductDetailApiModel updateProduct(UpdateProductDetailRequest request) {
        ProductDetailDto productDetailDto = new ProductDetailDto(request.getProductCode(), request.getProductName(), request.getProductDescription());
        productDetailService.updateDetail(productDetailDto);
        return constructProductDetailApiModel(productDetailDto);
    }

    public void publishExistingProduct(String productCode) throws SupplierServiceException {
        ProductDetailDto product = productDetailService.findDetailByProductCode(productCode);
        ProductCreated productCreatedEvent = constructProductCreatedEvent(constructProductContent(product));
        publishProduct(productCreatedEvent);
	}
 
    private void publishProduct(ProductCreated productCreatedEvent) {
        try {
            eventPublisher.publish(productCreatedEvent);
        } catch (IOException ex) {
            logger.error("Failed to publish product content(product code:)" + productCreatedEvent.getProductContent().getProductCode());
        }
    }

    private void publishProductAvailability(ProductAvailabilityUpdated productAvailabilityUpdated) {
        try {
            eventPublisher.publish(productAvailabilityUpdated);
        } catch (IOException ex) {
            logger.error("Failed to publish product availabilities(product code:)"
                    + productAvailabilityUpdated.getProductAccessibility().getProductCode());
        }
    }

    private ProductCreated constructProductCreatedEvent(ProductContent productContent) {
        return new ProductCreated(productContent);
    }

    private ProductAvailabilityUpdated constructProductAvailabilityUpdated(ProductAccessibility productAccessibility) {
        return new ProductAvailabilityUpdated(productAccessibility);
    }

    private ProductContent constructProductContent(ProductDetailDto productDetailDto) {
        return new ProductContent.Builder()
                .withProductCode(productDetailDto.getProductCode())
                .withProductName(productDetailDto.getProductName())
                .withProductDescription(productDetailDto.getProductDescription())
                .build();
    }

    private ProductAccessibility constructProductAccessibility(String productCode,
                                                               List<ProductAccessibilityDateRange> productAccessibilityDateRanges) {
        return new ProductAccessibility.Builder()
                .withProductCode(productCode)
                .withProductAccessibilityDataRanges(productAccessibilityDateRanges)
                .build();
    }

    private ProductDetailApiModel constructProductDetailApiModel(ProductDetailDto productDetailDto) {
        return new ProductDetailApiModel(
            productDetailDto.getProductCode(),
            productDetailDto.getProductName(),
            productDetailDto.getProductDescription()
        );
    }

    private List<ProductAccessibilityDateRange> constructProductAccessibilityDateRanges(List<ProductAvailabilityRuleDto> productAvailabilityRuleDtos) {
        return productAvailabilityRuleDtos.stream()
                .map(productAvailabilityRuleDto -> new ProductAccessibilityDateRange(
                        productAvailabilityRuleDto.getStartDate(), productAvailabilityRuleDto.getEndDate()))
                .collect(Collectors.toList());
    }

    private ProductAvailabilityRuleApiModel constructProductAvailabilityApiModel() {
        // todo: might need supplier service ProductAvailabilityRule
        return null;
    }
}
