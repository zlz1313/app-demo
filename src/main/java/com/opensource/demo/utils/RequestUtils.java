package com.opensource.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public final class RequestUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> getBodyMap(HttpServletRequest request) {
        Map<String, Object> bodyMap = new HashMap<>();
        try (BufferedReader reader = request.getReader()) {
            StringBuffer stringBuffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                stringBuffer.append(line);
                line = reader.readLine();
            }
            String bodyStr = stringBuffer.toString();
            if (StringUtils.isNotEmpty(bodyStr)) {
                bodyMap = objectMapper.readValue(bodyStr, Map.class);
            }
        } catch (Exception ignore) {

        }
        return bodyMap;
    }

    public static String filterSize(String content) {
        if (content != null && content.length() > 1024) {
            return "(skip too large message)";
        }
        return content;
    }
}
