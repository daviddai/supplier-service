package com.micro.services.supplier.svc.exception;

public class SupplierServiceException extends Exception {

    private static final long serialVersionUID = -1074881423330549068L;

    public enum ErrorCode {
        SUPPLIER_FAILED_TO_ADD_PRODUCT_TO_DB,
        SUPPLIER_FAILED_TO_FIND_NON_EXISTING_PRODUCT_CODE,
    }

    private ErrorCode errorCode;

    public SupplierServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
