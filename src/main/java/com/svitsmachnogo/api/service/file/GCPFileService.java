package com.svitsmachnogo.api.service.file;


import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class GCPFileService {

    private final Storage storage;

    @Value("${gcp.bucket.name}")
    private String gcpBucketName;

    @Value("${gcp.dir.category.name}")
    private String gcpDirCategory;

    @Value("${gcp.dir.product.name}")
    private String gcpDirProduct;

    @Value("${gcp.cloud.storage.link}")
    private String gcpStorageLink;

    public String uploadFile(MultipartFile file) {
           try {

               BlobId blobId = BlobId.of(gcpBucketName, gcpDirCategory + file.getOriginalFilename());

               BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();

               Blob blob = storage.create(blobInfo,file.getBytes());

               return gcpStorageLink + gcpDirCategory + blob.getName();

           }catch(Exception e){
               throw new RuntimeException(e);
           }
    }
}
