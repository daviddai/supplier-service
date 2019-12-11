package com.micro.services.supplier.svc.model.request;

public abstract class ProductRequest {

    private boolean publish;

    public ProductRequest(boolean publish) {
        this.publish = publish;
    }

    public boolean isPublish() {
        return publish;
    }
}
