package com.truemen.api.common.storage.controller;

import com.truemen.api.common.exception.ErrorCode;
import com.truemen.api.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.truemen.api.common.storage.service.FileStorageService;
import com.truemen.api.common.util.RandomNumber;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/storage")
public class FileStorageController {
    @Autowired
    private FileStorageService storageService;

    @PostMapping("/upload/{uid}/{module}")
    public Result<HashMap<String,String>> uploadFile(
            @PathVariable("uid") String uid,
            @PathVariable("module") String module,
            @RequestParam(value = "random", required = true) Boolean random,
            @RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            int dotIndex = fileName.lastIndexOf('.');
            String extName = fileName.substring(dotIndex+1);
            if (random){
                fileName = RandomNumber.randomUUID().toString()+"."+extName;
            }
            String relativePath = storageService.saveFile(uid, module, fileName, file.getBytes());
            HashMap<String,String> p = new HashMap<String,String>();
            p.put("relativePath",relativePath);
            Result<HashMap<String,String>> result = Result.ok(p);
            result.setMsg("File uploaded successfully: " + fileName);
            return result;
        } catch (IOException e) {
            Result result = Result.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(),"Could not store the file: " + e.getMessage());
            return result;
        }
    }

    @GetMapping("/download")
    public ResponseEntity downloadFile(@RequestParam("fileName") String filePath) {
        try {
            byte[] fileContent = storageService.getFile(filePath);
            HttpHeaders headers = new HttpHeaders();
            List<String> fileNames = Arrays.stream(filePath.split("/")).toList();
            String fileName = fileNames.get(fileNames.size()-1);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileContent);
        } catch (IOException e) {
            return ResponseEntity.notFound()
                    .build();
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

    @GetMapping("/list/{module}/{uid}")
    public Result listFiles(
            @PathVariable("uid") String uid,
            @PathVariable("module") String module) {
        try {
            List<String> files = storageService.listFiles(uid, module);
            return Result.ok(files);
        } catch (IOException e) {
            return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
