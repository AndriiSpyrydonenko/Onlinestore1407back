package com.svitsmachnogo.api.service.file.upload;

/**
 * Enum representing different types of file upload operations in the GCP storage.
 * This enum is typically used in conjunction with the GCPFileService to specify the
 * type of file being uploaded, such as a PRODUCT or CATEGORY.
 */
public enum UploadType {
    PRODUCT,
    CATEGORY,
}
