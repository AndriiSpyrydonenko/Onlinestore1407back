package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.service.file.GCPFileService;
import com.svitsmachnogo.api.service.file.upload.UploadType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/upload") // todo: eventually the correct route will be added as "/api/admin/files"
@Tag(name = "Upload files controller")
public class UploadFileController {

    private final GCPFileService fileService;

    @PostMapping(
            value = "/products",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<String>> addPicturesForProduct(@RequestParam("files")MultipartFile[] files){
        List<String> res = Arrays.stream(files)
                .map(f -> fileService.uploadFile(f, UploadType.PRODUCT))
                .collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }

    @PostMapping(
            value = "/categories",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Map<String,String>> addPicturesForCategories(@RequestParam("hover")MultipartFile hover,
                                                                @RequestParam("fill")MultipartFile fill){
        Map<String,String> pictures = new HashMap<>();
        pictures.put("hover", fileService.uploadFile(hover,UploadType.CATEGORY));
        pictures.put("fill", fileService.uploadFile(fill,UploadType.CATEGORY));

        return ResponseEntity.ok(pictures);
    }
}
