package com.micro.services.supplier.svc.facade;

import com.micro.services.event.bus.event.ProductAvailabilityUpdated;
import com.micro.services.event.bus.event.ProductCreated;
import com.micro.services.event.bus.event.model.ProductAvailability;
import com.micro.services.event.bus.event.model.ProductContent;
import com.micro.services.event.bus.publisher.EventPublisher;
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

@Service
public class ProductFacade {

    private final Logger logger = LoggerFactory.getLogger(ProductFacade.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private EventPublisher eventPublisher;

    public ProductApiModel createProduct(CreateProductRequest request) {
        ProductApiModel productApiModel = productService
                .create(request)
                .orElseThrow(() -> new RuntimeException("Failed to create new product"));

        if (request.isPublish()) {
            try {
                eventPublisher.publish(new ProductCreated(getProductContent(productApiModel)));
            } catch (IOException ex) {
                logger.error("Failed to publish product content(product code:)" + productApiModel.getProductCode());
            }

            if (CollectionUtils.isNotEmpty(request.getProductAvailabilities())) {
                try {
                    eventPublisher.publish(new ProductAvailabilityUpdated(getProductAvailability(request, productApiModel.getProductCode())));
                } catch (IOException ex) {
                    logger.error("Failed to publish product availabilities(product code:)" + productApiModel.getProductCode());
                }
            }
        }

        return productApiModel;
    }

    private ProductContent getProductContent(ProductApiModel productApiModel) {
        return new ProductContent.Builder()
                .withProductCode(productApiModel.getProductCode())
                .withProductName(productApiModel.getProductName())
                .withProductDescription(productApiModel.getProductDescription().orElse(""))
                .build();
    }

    private ProductAvailability getProductAvailability(CreateProductRequest request, String productCode) {
        return new ProductAvailability.Builder()
                .withProductCode(productCode)
                .withAvailabilities(request.getProductAvailabilities())
                .withUnavailabilities(Collections.emptyList())
                .build();
    }

}
