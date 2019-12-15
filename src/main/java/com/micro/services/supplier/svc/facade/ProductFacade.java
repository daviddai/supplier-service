package com.micro.services.supplier.svc.facade;

import com.micro.services.event.bus.event.ProductAvailabilityUpdated;
import com.micro.services.event.bus.event.ProductCreated;
import com.micro.services.event.bus.event.model.ProductAvailability;
import com.micro.services.event.bus.event.model.ProductAvailablePeriod;
import com.micro.services.event.bus.event.model.ProductContent;
import com.micro.services.event.bus.publisher.EventPublisher;
import com.micro.services.supplier.svc.dao.model.ProductAvailabilityRuleDTO;
import com.micro.services.supplier.svc.dao.model.ProductDetailDTO;
import com.micro.services.supplier.svc.exception.SupplierServiceException;
import com.micro.services.supplier.svc.model.request.CreateProductRequest;
import com.micro.services.supplier.svc.model.request.UpdateProductAvailabilityRequest;
import com.micro.services.supplier.svc.model.request.UpdateProductDetailRequest;
import com.micro.services.supplier.svc.model.response.ProductApiModel;
import com.micro.services.supplier.svc.model.response.ProductAvailabilityRule;
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
import java.sql.Date;
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
        ProductDetailDTO newAddedProductDetailDTO = productDetailService.addDetail(request);

        productAvailabilityRuleService.addAvailabilityRules(
                newAddedProductDetailDTO.getProductCode(), request.getNewProductAvailabilityRules());

        List<ProductAvailabilityRuleDTO> productAvailabilityRuleDTOs =
                productAvailabilityRuleService.getProductAvailabilityRules(newAddedProductDetailDTO.getProductCode());

        if (request.isPublish()) {
            ProductCreated productCreated = constructProductCreatedEvent(constructProductContent(newAddedProductDetailDTO));
            publishProduct(productCreated);

            if (CollectionUtils.isNotEmpty(request.getNewProductAvailabilityRules())) {
                ProductAvailability productAvailability = constructProductAvailability(
                        newAddedProductDetailDTO.getProductCode(),
                        constructProductAvailablePeriods(productAvailabilityRuleDTOs));

                ProductAvailabilityUpdated productAvailabilityUpdated = constructProductAvailabilityUpdated(productAvailability);

                publishProductAvailability(productAvailabilityUpdated);
            }
        }

        ProductDetailApiModel productDetailApiModel = constructProductDetailApiModel(newAddedProductDetailDTO);
        ProductAvailabilityRuleApiModel productAvailabilityRuleApiModel =
                constructProductAvailabilityApiModel(newAddedProductDetailDTO.getProductCode(), productAvailabilityRuleDTOs);
        return new ProductApiModel(productDetailApiModel, productAvailabilityRuleApiModel);
    }

    public ProductDetailApiModel findProduct(String productCode) throws SupplierServiceException {
        ProductDetailDTO product = productDetailService.findDetailByProductCode(productCode);
        return constructProductDetailApiModel(product);
    }

    public ProductDetailApiModel updateProductDetail(UpdateProductDetailRequest request) {
        ProductDetailDTO productDetailDto = new ProductDetailDTO(
                request.getProductCode(), request.getProductName(), request.getProductDescription());
        productDetailService.updateDetail(productDetailDto);
        return constructProductDetailApiModel(productDetailDto);
    }

    public ProductAvailabilityRuleApiModel updateProductAvailability(UpdateProductAvailabilityRequest request) {
        if (CollectionUtils.isNotEmpty(request.getNewProductAvailabilityRules())) {
            productAvailabilityRuleService.addAvailabilityRules(request.getProductCode(), request.getNewProductAvailabilityRules());
        }

        if (CollectionUtils.isNotEmpty(request.getUpdatedProductAvailabilityRules())) {
            List<ProductAvailabilityRuleDTO> productAvailabilityRuleDTOs = request.getUpdatedProductAvailabilityRules()
                    .stream()
                    .map(rule -> new ProductAvailabilityRuleDTO(
                            rule.getId(), request.getProductCode(), Date.valueOf(rule.getStartDate()), Date.valueOf(rule.getEndDate())))
                    .collect(Collectors.toList());
            productAvailabilityRuleService.updateAvailabilityRules(productAvailabilityRuleDTOs);
        }

        if (CollectionUtils.isNotEmpty(request.getRemovedProductAvailabilityRuleIds())) {
            productAvailabilityRuleService.removeAvailabilityRues(request.getProductCode(), request.getRemovedProductAvailabilityRuleIds());
        }

        List<ProductAvailabilityRuleDTO> productAvailabilityRules =
                productAvailabilityRuleService.getProductAvailabilityRules(request.getProductCode());

        if (request.isPublish()) {
            // todo: Optimise this to avoid access to database again
            publishProductAvailablePeriods(request.getProductCode());
        }


        return constructProductAvailabilityApiModel(request.getProductCode(), productAvailabilityRules);
    }

    public void publishProductContent(String productCode) throws SupplierServiceException {
        ProductDetailDTO product = productDetailService.findDetailByProductCode(productCode);
        ProductCreated productCreatedEvent = constructProductCreatedEvent(constructProductContent(product));
        publishProduct(productCreatedEvent);
	}

	public void publishProductAvailablePeriods(String productCode) {
        List<ProductAvailabilityRuleDTO> productAvailabilityRuleDTOs = productAvailabilityRuleService.getProductAvailabilityRules(productCode);
        List<ProductAvailablePeriod> productAvailablePeriods = constructProductAvailablePeriods(productAvailabilityRuleDTOs);
        ProductAvailability productAvailability = constructProductAvailability(productCode, productAvailablePeriods);
        ProductAvailabilityUpdated productAvailabilityUpdated = constructProductAvailabilityUpdated(productAvailability);
        publishProductAvailability(productAvailabilityUpdated);
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
                    + productAvailabilityUpdated.getProductAvailability().getProductCode());
        }
    }

    private ProductCreated constructProductCreatedEvent(ProductContent productContent) {
        return new ProductCreated(productContent);
    }

    private ProductAvailabilityUpdated constructProductAvailabilityUpdated(ProductAvailability productAvailability) {
        return new ProductAvailabilityUpdated(productAvailability);
    }

    private ProductContent constructProductContent(ProductDetailDTO productDetailDto) {
        return new ProductContent.Builder()
                .withProductCode(productDetailDto.getProductCode())
                .withProductName(productDetailDto.getProductName())
                .withProductDescription(productDetailDto.getProductDescription())
                .build();
    }

    private ProductAvailability constructProductAvailability(String productCode,
                                                             List<ProductAvailablePeriod> productAvailablePeriods) {
        return new ProductAvailability.Builder()
                .withProductCode(productCode)
                .withProductAvailablePeriods(productAvailablePeriods)
                .build();
    }

    private ProductDetailApiModel constructProductDetailApiModel(ProductDetailDTO productDetailDto) {
        return new ProductDetailApiModel(
            productDetailDto.getProductCode(),
            productDetailDto.getProductName(),
            productDetailDto.getProductDescription()
        );
    }

    private List<ProductAvailablePeriod> constructProductAvailablePeriods(List<ProductAvailabilityRuleDTO> productAvailabilityRuleDTOs) {
        return productAvailabilityRuleDTOs.stream()
                .map(productAvailabilityRuleDTO -> new ProductAvailablePeriod(
                        productAvailabilityRuleDTO.getStartDate().toLocalDate(),
                        productAvailabilityRuleDTO.getEndDate().toLocalDate()))
                .collect(Collectors.toList());
    }

    private ProductAvailabilityRuleApiModel constructProductAvailabilityApiModel(String productCode,
                                                                                 List<ProductAvailabilityRuleDTO> productAvailabilityRuleDTOs) {
        List<ProductAvailabilityRule> productAvailabilityRules = productAvailabilityRuleDTOs
                .stream()
                .map(this::constructProductAvailabilityRule)
                .collect(Collectors.toList());
        return new ProductAvailabilityRuleApiModel(productCode, productAvailabilityRules);
    }

    private ProductAvailabilityRule constructProductAvailabilityRule(ProductAvailabilityRuleDTO productAvailabilityRuleDTO) {
        return new ProductAvailabilityRule(
                productAvailabilityRuleDTO.getStartDate().toLocalDate(),
                productAvailabilityRuleDTO.getEndDate().toLocalDate(),
                productAvailabilityRuleDTO.getId(),
                productAvailabilityRuleDTO.getProductCode()
        );
    }
}
