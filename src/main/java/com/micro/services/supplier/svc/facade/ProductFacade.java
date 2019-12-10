package com.micro.services.supplier.svc.facade;

import com.micro.services.event.bus.event.ProductAvailabilityUpdated;
import com.micro.services.event.bus.event.ProductCreated;
import com.micro.services.event.bus.event.model.ProductAvailability;
import com.micro.services.event.bus.event.model.ProductContent;
import com.micro.services.event.bus.publisher.EventPublisher;
import com.micro.services.supplier.svc.dao.model.Product;
import com.micro.services.supplier.svc.exception.SupplierServiceException;
import com.micro.services.supplier.svc.model.request.CreateProductRequest;
import com.micro.services.supplier.svc.model.response.ProductApiModel;
import com.micro.services.supplier.svc.service.ProductService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Service
public class ProductFacade {

    private final Logger logger = LoggerFactory.getLogger(ProductFacade.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private EventPublisher eventPublisher;

    public ProductApiModel createProduct(CreateProductRequest request) throws SupplierServiceException {
        Product newAddedProduct = productService.create(request);

        if (request.isPublish()) {
            ProductCreated productCreated = constructProductCreatedEvent(constructProductContent(newAddedProduct));
            publishProduct(productCreated);

            if (CollectionUtils.isNotEmpty(request.getProductAvailabilityRules())) {
                ProductAvailabilityUpdated productAvailabilityUpdated = constructProductAvailabilityUpdated(
                        constructProductAvailability(request, newAddedProduct.getProductCode()));
                publishProductAvailability(productAvailabilityUpdated);
            }
        }

        return constructProductApiModel(newAddedProduct);
    }

    public ProductApiModel findProduct(String productCode) throws SupplierServiceException {
        Product product = productService.findByProductCode(productCode);
        return constructProductApiModel(product);
    }

    public void publishExistingProduct(String productCode) throws SupplierServiceException {
        Product product = productService.findByProductCode(productCode);
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
                    + productAvailabilityUpdated.getProductAvailability().getProductCode());
        }
    }

    private ProductCreated constructProductCreatedEvent(ProductContent productContent) {
        return new ProductCreated(productContent);
    }

    private ProductAvailabilityUpdated constructProductAvailabilityUpdated(ProductAvailability productAvailability) {
        return new ProductAvailabilityUpdated(productAvailability);
    }

    private ProductContent constructProductContent(Product product) {
        return new ProductContent.Builder()
                .withProductCode(product.getProductCode())
                .withProductName(product.getProductName())
                .withProductDescription(product.getProductDescription())
                .build();
    }

    private ProductAvailability constructProductAvailability(CreateProductRequest request, String productCode) {
        return new ProductAvailability.Builder()
                .withProductCode(productCode)
                .withAvailabilityRules(request.getProductAvailabilityRules())
                .withAvailabilityRules(Collections.emptyList())
                .build();
    }

    private ProductApiModel constructProductApiModel(Product product) {
        return new ProductApiModel(
            product.getProductCode(),
            product.getProductName(),
            Optional.ofNullable(product.getProductDescription()),
            Collections.emptyList()
        );
    }
}
