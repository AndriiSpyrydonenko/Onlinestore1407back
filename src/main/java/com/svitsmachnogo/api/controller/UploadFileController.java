package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.service.file.GCPFileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/upload")
@Tag(name = "Upload files controller")
public class UploadFileController {

    private final GCPFileService fileService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<String>> addFile(@RequestParam("files")MultipartFile[] files){
        List<String> res = Arrays.stream(files)
                .map(fileService::uploadFile)
                .collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }
}
