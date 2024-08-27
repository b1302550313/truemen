package com.sysu.verto.framework.util;

import java.util.List;

public class ConVertUrlListToString {
    public static String convertMediaUrlsToString(List<String> mediaUrls) {
        if (mediaUrls == null || mediaUrls.isEmpty()) {
            return null;
        }
        return String.join(",", mediaUrls);
    }
}
