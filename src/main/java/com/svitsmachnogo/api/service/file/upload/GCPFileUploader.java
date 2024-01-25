package com.svitsmachnogo.api.service.file.upload;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.svitsmachnogo.api.exceptions.FileExtensionException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * Abstract class representing a part of the Command Pattern for uploading files to Google Cloud Storage.
 * This class defines a common class for concrete file upload commands (implementations) to follow.
 * Child classes should extend this class and define the 'gcpDir' field to specify the
 * directory into which the file will be uploaded. Additionally, child classes must
 * implement the 'checkFileExtension' method to customize file extension checking.
 *
 * @author Vanya Demydenko
 * @see com.svitsmachnogo.api.service.file.upload.GCPProductUploader
 * @see com.svitsmachnogo.api.service.file.upload.GCPCategoryUploader
 */
@Getter
@Setter
@RequiredArgsConstructor
public abstract class GCPFileUploader {

    protected final Storage storage;

    /**
     * Specific directory into which the file will be uploaded.
     * Child classes should define this field.
     */
    protected String gcpDir;

    protected UploadType uploadType;

    @Value("${gcp.bucket.name}")
    protected String gcpBucketName;

    @Value("${gcp.cloud.storage.link}")
    protected String gcpStorageLink;

    /**
     * Uploads a file to Google Cloud Storage and returns the file link.
     *
     * @param file The file to be uploaded.
     * @return The link to the uploaded file.
     */
    public String uploadFile(MultipartFile file) {

        String fileName = prepareFile(file);

        try {
            BlobId blobId = BlobId.of(gcpBucketName, gcpDir + fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
            Blob blob = storage.create(blobInfo, file.getBytes());

            return gcpStorageLink + blob.getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Abstract method to be implemented by child classes for custom file extension checking.
     *
     * @param fileName The name of the file to check.
     * @throws FileExtensionException if the file extension is not valid.
     */
    public abstract void checkFileExtension(String fileName) throws FileExtensionException;


    private String prepareFile(MultipartFile file) {
        Objects.requireNonNull(file);
        String fileName = Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
        checkFileExtension(fileName);
        return fileName;
    }
}
