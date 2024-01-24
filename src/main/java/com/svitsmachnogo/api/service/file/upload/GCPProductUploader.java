package com.svitsmachnogo.api.service.file.upload;

import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GCPProductUploader extends GCPFileUploader{

    @Value("${gcp.dir.product.name}")
    private String gcpDirProduct;

    public GCPProductUploader(Storage storage){
        super(storage);
        gcpDir = gcpDirProduct;
    }

    @Override
    public UploadType getUploadType() {
        return UploadType.PRODUCT;
    }

}
