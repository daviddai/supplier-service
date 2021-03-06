package com.micro.services.supplier.svc.service;

import com.micro.services.supplier.svc.dao.ProductDetailDao;
import com.micro.services.supplier.svc.dao.model.ProductDetailDTO;
import com.micro.services.supplier.svc.exception.SupplierServiceException;
import com.micro.services.supplier.svc.exception.SupplierServiceException.ErrorCode;
import com.micro.services.supplier.svc.model.request.CreateProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductDetailService {

    private final Logger logger = LoggerFactory.getLogger(ProductDetailService.class);

    @Autowired
    private ProductDetailDao productDetailDao;

    public ProductDetailDTO addDetail(CreateProductRequest request) throws SupplierServiceException {
        try {
            ProductDetailDTO newProductDetailDTO = constructProductDetail(request);
            productDetailDao.save(newProductDetailDTO);
            return newProductDetailDTO;
        } catch (DataAccessException ex) {
            logger.error("Failed to add product detail to database");
            throw new SupplierServiceException(ErrorCode.SUPPLIER_FAILED_TO_ADD_PRODUCT_DETAIL_TO_DB);
        }
    }

    public ProductDetailDTO findDetailByProductCode(String productCode) throws SupplierServiceException {
        return productDetailDao.find(productCode)
                .orElseThrow(() -> new SupplierServiceException(ErrorCode.SUPPLIER_FAILED_TO_FIND_NON_EXISTING_PRODUCT_CODE));
    }

    public void updateDetail(ProductDetailDTO productDetailDto) {
        productDetailDao.update(productDetailDto);
    }

    private ProductDetailDTO constructProductDetail(CreateProductRequest request) {
        return new ProductDetailDTO(
            generateProductCode(request.getProductName()),
            request.getProductName(),
            request.getProductDescription()
        );
    }

    private String generateProductCode(String productName) {
        // todo: find a better way to generate product code
        return UUID.randomUUID().toString();
    }

}
