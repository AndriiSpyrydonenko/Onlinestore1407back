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

@Getter
@Setter
@RequiredArgsConstructor
public abstract class GCPFileUploader {

    protected final Storage storage;

    protected UploadType uploadType;

    protected String gcpDir;

    @Value("${gcp.bucket.name}")
    protected String gcpBucketName;

    @Value("${gcp.cloud.storage.link}")
    protected String gcpStorageLink;

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

    private String prepareFile(MultipartFile file) {
        Objects.requireNonNull(file);
        String fileName = Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
        checkFileExtension(fileName);
        return fileName;
    }

    public abstract void checkFileExtension(String fileName) throws FileExtensionException;

}
