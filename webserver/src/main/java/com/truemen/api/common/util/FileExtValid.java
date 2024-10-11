package com.truemen.api.common.util;

import com.truemen.api.common.exception.ErrorCode;
import com.truemen.api.common.exception.ServerException;

public class FileExtValid {

    // 定义文件类型对应的扩展名集合
    private static final String[] PHOTO_EXTENSIONS = {"jpg", "jpeg", "png", "bmp", "gif", "tiff"};
    private static final String[] AUDIO_EXTENSIONS = {"mp3", "wav", "aac", "flac", "m4a"};
    private static final String[] VIDEO_EXTENSIONS = {"mp4", "avi", "mkv", "mov", "wmv", "flv"};

    private static final String[] ALLOWED_FILE_TYPES;

    public enum FileType {
        PHOTO, AUDIO, VIDEO
    }

    static {
        int totalLength = PHOTO_EXTENSIONS.length + AUDIO_EXTENSIONS.length + VIDEO_EXTENSIONS.length;
        ALLOWED_FILE_TYPES = new String[totalLength];
        System.arraycopy(PHOTO_EXTENSIONS, 0, ALLOWED_FILE_TYPES, 0, PHOTO_EXTENSIONS.length);
        System.arraycopy(AUDIO_EXTENSIONS, 0, ALLOWED_FILE_TYPES, PHOTO_EXTENSIONS.length, AUDIO_EXTENSIONS.length);
        System.arraycopy(VIDEO_EXTENSIONS, 0, ALLOWED_FILE_TYPES, PHOTO_EXTENSIONS.length + AUDIO_EXTENSIONS.length, VIDEO_EXTENSIONS.length);
    }

    public static void validateFileType(String fileName) {
        String fileExtension = getFileExtension(fileName);
        for (String allowedType : ALLOWED_FILE_TYPES) {
            if (allowedType.equalsIgnoreCase(fileExtension)) {
                return;
            }
        }
        throw new IllegalArgumentException("Invalid file type: " + fileExtension);
    }

    public static String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return null;
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }



    // 根据文件扩展名返回文件类型
    public static FileType classifyFile(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > -1 && dotIndex < fileName.length() - 1) {
            String extension = fileName.substring(dotIndex + 1).toLowerCase();
            for (String photoExt : PHOTO_EXTENSIONS) {
                if (photoExt.equals(extension)) return FileType.PHOTO;
            }
            for (String audioExt : AUDIO_EXTENSIONS) {
                if (audioExt.equals(extension)) return FileType.AUDIO;
            }
            for (String videoExt : VIDEO_EXTENSIONS) {
                if (videoExt.equals(extension)) return FileType.VIDEO;
            }
        }
        throw new ServerException("文件拓展名不合要求"); // 如果没有匹配到，则认为是其他类型的文件
    }
}
