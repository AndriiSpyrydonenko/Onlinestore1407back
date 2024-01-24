package com.svitsmachnogo.api.service.file;


import com.svitsmachnogo.api.service.file.upload.GCPFileUploader;
import com.svitsmachnogo.api.service.file.upload.UploadType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class GCPFileServiceImpl implements GCPFileService {

    private final List<GCPFileUploader> uploadersList;

    private Map<UploadType, GCPFileUploader> uploadersMap;

    public GCPFileServiceImpl(List<GCPFileUploader> uploadersList) {
        this.uploadersList = uploadersList;
        this.uploadersMap = uploadersList
                .stream()
                .collect(Collectors.toMap(GCPFileUploader::getUploadType, Function.identity()));
    }

    public String uploadFile(MultipartFile file, UploadType type) {
        return uploadersMap
                .get(type)
                .uploadFile(file);
    }
}
