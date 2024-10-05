package com.truemen.api.common.storage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Value("${file.base-dir}")
    private String baseDir;

    private static final String[] ALLOWED_FILE_TYPES = { "png", "jpg", "jpeg", "gif", "mp3", "wav", "ogg", "mp4", "avi",
            "mov" };

    public String saveFile(String uid, String module, String fileName, byte[] fileContent) throws IOException {
        validateFileType(fileName);
        Path userModuleDir = Paths.get(baseDir,module,uid).toAbsolutePath().normalize();
        Path targetLocation = userModuleDir.resolve(fileName);
        System.out.println("Saving file to: " + targetLocation.toString());
        Files.createDirectories(targetLocation.getParent());
        Files.write(targetLocation, fileContent);
        System.out.println("File saved successfully to: " + targetLocation.toString());
        return Paths.get(module,uid,fileName).toString();
    }

    public byte[] getFile(String fileName) throws IOException {
        Path userModuleDir = Paths.get(baseDir).toAbsolutePath().normalize();
        Path filePath = userModuleDir.resolve(fileName);
        System.out.println("Reading file from: " + filePath.toString());
        return Files.readAllBytes(filePath);
    }

    public void deleteFile(String userId, String module, String fileName) throws IOException {
        Path userModuleDir = Paths.get(baseDir, userId, module).toAbsolutePath().normalize();
        Path filePath = userModuleDir.resolve(fileName);
        System.out.println("Deleting file from: " + filePath.toString());
        Files.deleteIfExists(filePath);
    }

    public List<String> listFiles(String userId, String module) throws IOException {
        Path userModuleDir = Paths.get(baseDir, module, userId).toAbsolutePath().normalize();
        try (Stream<Path> stream = Files.walk(userModuleDir, 1)) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }
    }

    private void validateFileType(String fileName) {
        String fileExtension = getFileExtension(fileName);
        for (String allowedType : ALLOWED_FILE_TYPES) {
            if (allowedType.equalsIgnoreCase(fileExtension)) {
                return;
            }
        }
        throw new IllegalArgumentException("Invalid file type: " + fileExtension);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return null;
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
}
