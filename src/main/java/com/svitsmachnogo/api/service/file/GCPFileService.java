package com.svitsmachnogo.api.service.file;

import com.svitsmachnogo.api.service.file.upload.UploadType;
import org.springframework.web.multipart.MultipartFile;

public interface GCPFileService {

    String uploadFile(MultipartFile file, UploadType type);

}
