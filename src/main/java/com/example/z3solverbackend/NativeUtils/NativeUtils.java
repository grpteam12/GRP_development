package com.example.z3solverbackend.NativeUtils;

import java.io.*;

public class NativeUtils {

    public static void loadLibraryFromJar(String path) throws IOException {
        // 检查路径以确保以 '/' 开头
        if (!path.startsWith("/")) {
            throw new IllegalArgumentException("The path has to be absolute (start with '/').");
        }

        // 提取文件名
        String[] parts = path.split("/");
        String filename = (parts.length > 1) ? parts[parts.length - 1] : null;

        // 创建临时文件
        File temp = File.createTempFile(filename, null);
        temp.deleteOnExit();

        try (InputStream is = NativeUtils.class.getResourceAsStream(path);
             FileOutputStream os = new FileOutputStream(temp)) {

            if (is == null) {
                throw new FileNotFoundException("File " + path + " was not found inside JAR.");
            }

            byte[] buffer = new byte[1024];
            int readBytes;

            while ((readBytes = is.read(buffer)) != -1) {
                os.write(buffer, 0, readBytes);
            }
        }

        // 加载库
        System.load(temp.getAbsolutePath());
    }
}
