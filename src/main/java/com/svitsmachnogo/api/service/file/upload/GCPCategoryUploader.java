package com.svitsmachnogo.api.service.file.upload;

import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GCPCategoryUploader extends GCPFileUploader{

    @Value("${gcp.dir.category.name}")
    private String gcpDirCategory;

    public GCPCategoryUploader(Storage storage){
        super(storage);
        gcpDir = gcpDirCategory;
    }

    @Override
    public UploadType getUploadType() {
        return UploadType.CATEGORY;
    }
}
