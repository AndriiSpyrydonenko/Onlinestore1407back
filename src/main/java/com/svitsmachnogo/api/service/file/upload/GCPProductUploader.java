package com.svitsmachnogo.api.service.file.upload;

import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class GCPProductUploader extends GCPFileUploader {

    @Value("${gcp.dir.product.name}")
    String gcpDirProduct;

    public GCPProductUploader(Storage storage) {
        super(storage);
    }

    @Override
    public UploadType getUploadType() {
        return UploadType.PRODUCT;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        this.gcpDir = gcpDirProduct;
        return super.uploadFile(file);
    }
}
