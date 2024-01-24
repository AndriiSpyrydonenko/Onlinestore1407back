package com.svitsmachnogo.api.service.file.upload;

import com.google.cloud.storage.Storage;
import com.svitsmachnogo.api.exceptions.FileExtensionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;


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

    @Override
    public void checkFileExtension(String fileName) {
        Objects.requireNonNull(fileName);

        String[] correctExtensions = {".png", ".jpeg", ".jpg"};

        boolean match = Arrays
                .stream(correctExtensions)
                .noneMatch(fileName::endsWith);

        if (match) {
            throw new FileExtensionException("Incorrect file format. Allowable formats: "
                                             + Arrays.toString(correctExtensions));
        }
    }
}
