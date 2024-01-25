package com.svitsmachnogo.api.service.file.upload;

import com.google.cloud.storage.Storage;
import com.svitsmachnogo.api.exceptions.FileExtensionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;

/**
 * Concrete implementation of {@link GCPFileUploader} for uploading product-related files to Google Cloud Storage.
 * This class specializes in uploading files to a specific directory related to products.
 *
 * @see com.svitsmachnogo.api.service.file.upload.GCPFileUploader
 */
@Component
public class GCPProductUploader extends GCPFileUploader {

    @Value("${gcp.dir.product.name}")
    String gcpDirProduct;

    public GCPProductUploader(Storage storage) {
        super(storage);
    }

    /**
     * Gets the type of upload operation (PRODUCT).
     *
     * @return The upload type.
     */
    @Override
    public UploadType getUploadType() {
        return UploadType.PRODUCT;
    }


    /**
     * Uploads a file to the specific product directory in Google Cloud Storage.
     *
     * @param file The file to be uploaded.
     * @return The link to the uploaded file.
     */
    @Override
    public String uploadFile(MultipartFile file) {
        this.gcpDir = gcpDirProduct;
        return super.uploadFile(file);
    }

    /**
     * Checks the file extension to ensure it is a valid format for product-related files.
     *
     * @param fileName The name of the file to check.
     * @throws FileExtensionException if the file extension is not valid.
     */
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
