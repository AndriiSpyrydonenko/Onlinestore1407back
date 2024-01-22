package com.svitsmachnogo.api.service.file;


import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Service
@Getter
@Setter
public class GCPFileService {

    @Autowired
    private Storage storage;

    @Value("${gcp.bucket.name}")
    private String gcpBucketName;

    @Value("${gcp.dir.category.name}")
    private String gcpDirCategory;

    @Value("${gcp.dir.product.name}")
    private String gcpDirProduct;

    public String uploadFile(MultipartFile file) {
           try {
               BlobId blobId = BlobId.of(gcpBucketName, file.getOriginalFilename());
               BlobInfo blobInfo = BlobInfo.newBuilder(blobId).
                       setContentType(file.getContentType()).build();

               Blob blob = storage.create(blobInfo,file.getBytes());

               return blob.getMediaLink();

           }catch(Exception e){
               throw new RuntimeException(e);
           }
    }

    private File convertToFile(MultipartFile multipartFile) {
        try {
            if (multipartFile.getOriginalFilename() == null) {
                throw new RuntimeException("Original file name is null !!!");
            }
            File convertedFile = new File(multipartFile.getName());
            FileOutputStream outputStream = new FileOutputStream(convertedFile);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();
            return convertedFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
