package com.svitsmachnogo.api.service.file.upload;

import com.google.cloud.storage.Storage;
import com.svitsmachnogo.api.exceptions.FileExtensionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


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

    @Override
    public void checkFileExtension(String fileName) {
        Objects.requireNonNull(fileName);

        if (!fileName.endsWith(".svg")) {
            throw new FileExtensionException("Incorrect file format. Allowed only .svg files");
        }
    }
}
