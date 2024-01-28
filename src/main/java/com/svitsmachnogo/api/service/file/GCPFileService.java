package com.svitsmachnogo.api.service.file;

import com.svitsmachnogo.api.service.file.upload.UploadType;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for the GCP (Google Cloud Platform) File Service, defining methods for uploading files to Google Cloud Storage.
 *
 * <p>Implementing classes, such as {@link GCPFileServiceImpl}, provide concrete implementations for handling file uploads.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * // Injected instance of GCPFileService (e.g., through constructor injection)
 * GCPFileService gcpFileService = ...;
 *
 * // Upload a file of a specific type
 * MultipartFile file = ...;
 * UploadType type = UploadType.PRODUCT; // or UploadType.CATEGORY
 * String fileLink = gcpFileService.uploadFile(file, type);
 * }
 * </pre>
 *
 * @see com.svitsmachnogo.api.service.file.GCPFileServiceImpl
 * @see com.svitsmachnogo.api.service.file.upload.UploadType
 */
public interface GCPFileService {

    /**
     * Uploads a file of a specific type to Google Cloud Storage.
     *
     * @param file The file to be uploaded.
     * @param type The type of file indicating the specific uploader to use.
     * @return The link to the uploaded file.
     */
    String uploadFile(MultipartFile file, UploadType type);
}
