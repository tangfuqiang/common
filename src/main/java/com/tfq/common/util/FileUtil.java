package com.tfq.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileUtil {

    /**
     * 文件读取（按字节）
     *
     * @param file 待读取文件
     * @return
     * @throws IOException
     */
    public static byte[] getFileDataByByte(File file) throws IOException {
        FileInputStream inputStream = null;
        byte[] bytes = null;
        try {
            inputStream = new FileInputStream(file);
            int size = inputStream.available();
            bytes = new byte[size];
            inputStream.read(bytes);
        } catch (IOException e) {
            log.error("文件读取出错：" + file.getAbsolutePath());
        } finally {
            inputStream.close();
        }
        return bytes;
    }

    /**
     * 文件读取（按字符）
     *
     * @param file 待读取文件
     * @return
     * @throws IOException
     */
    public static String getFileDataByChar(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Reader reader = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            reader = new InputStreamReader(inputStream);
            char[] chars = new char[1024];
            while (reader.read(chars) != -1) {
                stringBuilder.append(chars);
                chars = new char[1024];
            }
        } catch (IOException e) {
            log.error("文件读取出错：" + file.getAbsolutePath());
        } finally {
            reader.close();
            inputStream.close();
        }
        return stringBuilder.toString();
    }

}
