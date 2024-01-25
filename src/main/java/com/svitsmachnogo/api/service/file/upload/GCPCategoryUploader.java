package com.svitsmachnogo.api.service.file.upload;

import com.google.cloud.storage.Storage;
import com.svitsmachnogo.api.exceptions.FileExtensionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * Concrete implementation of {@link GCPFileUploader} for uploading category-related files to Google Cloud Storage.
 * This class specializes in uploading files to a specific directory related to categories.
 *
 * @see com.svitsmachnogo.api.service.file.upload.GCPFileUploader
 */
@Component
public class GCPCategoryUploader extends GCPFileUploader {

    @Value("${gcp.dir.category.name}")
    String gcpDirCategory;

    public GCPCategoryUploader(Storage storage) {
        super(storage);
    }

    /**
     * Gets the type of upload operation (CATEGORY).
     *
     * @return The upload type.
     */
    @Override
    public UploadType getUploadType() {
        return UploadType.CATEGORY;
    }

    /**
     * Uploads a file to the specific category directory in Google Cloud Storage.
     *
     * @param file The file to be uploaded.
     * @return The link to the uploaded file.
     */
    @Override
    public String uploadFile(MultipartFile file) {
        this.gcpDir = gcpDirCategory;
        return super.uploadFile(file);
    }

    /**
     * Checks the file extension to ensure it is a valid format for category-related files.
     *
     * @param fileName The name of the file to check.
     * @throws FileExtensionException if the file extension is not valid.
     */
    @Override
    public void checkFileExtension(String fileName) {
        Objects.requireNonNull(fileName);

        if (!fileName.endsWith(".svg")) {
            throw new FileExtensionException("Incorrect file format. Allowed only .svg files");
        }
    }
}
