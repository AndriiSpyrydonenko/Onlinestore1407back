package com.svitsmachnogo.api.service.file.upload;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class GCPFileUploader {

    final Storage storage;

    UploadType uploadType;

    String gcpDir;

    @Value("${gcp.bucket.name}")
    protected String gcpBucketName;

    @Value("${gcp.cloud.storage.link}")
    protected String gcpStorageLink;

    public String uploadFile(MultipartFile file) {
        try {

            BlobId blobId = BlobId.of(gcpBucketName, gcpDir + file.getOriginalFilename());

            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();

            Blob blob = storage.create(blobInfo, file.getBytes());

            return gcpStorageLink + blob.getName();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
