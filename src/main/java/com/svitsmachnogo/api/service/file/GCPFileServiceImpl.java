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

/**
 * Implementation of the {@link GCPFileService} interface that handles the uploading of files to Google Cloud Storage.
 * This service manages a list of {@link GCPFileUploader} instances, each responsible for a specific file type or category.
 */
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

    /**
     * Uploads a file of a specific type using the corresponding GCPFileUploader.
     *
     * @param file The file to be uploaded.
     * @param type The type of file indicating the specific uploader to use.
     * @return The link to the uploaded file.
     */
    public String uploadFile(MultipartFile file, UploadType type) {
        return uploadersMap
                .get(type)
                .uploadFile(file);
    }
}
