package com.micro.services.supplier.svc.exception;

public class SupplierServiceException extends Exception {

    public enum ErrorCode {
        SUPPLIER_FAILED_TO_ADD_PRODUCT_TO_DB,
    }

    private ErrorCode errorCode;

    public SupplierServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
