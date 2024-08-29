package com.sysu.verto.common.util;

import java.util.Arrays;
import java.util.List;

public class ConVertUrlList {
    public static String convertMediaUrlsToString(List<String> mediaUrls) {
        if (mediaUrls == null || mediaUrls.isEmpty()) {
            return null;
        }
        return String.join(",", mediaUrls);
    }

    public static List<String> convertStringToUrlList(String str) {
        if (str == null) {
            return null;
        }
        String[] urls = str.split(",");
        return Arrays.stream(urls).toList();
    }
}
