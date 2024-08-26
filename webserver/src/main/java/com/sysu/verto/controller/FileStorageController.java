package com.sysu.verto.controller;

import com.sysu.verto.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileStorageController {
    @Autowired
    private FileStorageService storageService;

    @PostMapping("/upload/{userId}/{module}")
    public ResponseEntity<String> uploadFile(@PathVariable String userId, @PathVariable String module,
            @RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            storageService.saveFile(userId, module, fileName, file.getBytes());
            return ResponseEntity.ok().body("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Could not store the file: " + e.getMessage());
        }
    }

    @GetMapping("/download/{userId}/{module}/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String userId, @PathVariable String module,
            @PathVariable String fileName) {
        try {
            byte[] fileContent = storageService.getFile(userId, module, fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileContent);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{userId}/{module}/{fileName:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable String userId, @PathVariable String module,
            @PathVariable String fileName) {
        try {
            storageService.deleteFile(userId, module, fileName);
            return ResponseEntity.ok().body("File deleted successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Could not delete the file: " + e.getMessage());
        }
    }

    @GetMapping("/list/{userId}/{module}")
    public ResponseEntity<List<String>> listFiles(@PathVariable String userId, @PathVariable String module) {
        try {
            List<String> files = storageService.listFiles(userId, module);
            return ResponseEntity.ok().body(files);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
