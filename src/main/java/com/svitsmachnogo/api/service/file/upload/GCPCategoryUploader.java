package com.svitsmachnogo.api.service.file.upload;

import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class GCPCategoryUploader extends GCPFileUploader {

    @Value("${gcp.dir.category.name}")
    String gcpDirCategory;

    public GCPCategoryUploader(Storage storage) {
        super(storage);
    }

    @Override
    public UploadType getUploadType() {
        return UploadType.CATEGORY;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        this.gcpDir = gcpDirCategory;
        return super.uploadFile(file);
    }
}
