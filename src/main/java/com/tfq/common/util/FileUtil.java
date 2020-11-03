package com.tfq.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileUtil {

    /**
     * 写文件
     * @param file
     * @param bytes
     * @throws IOException
     */
    public static void writeFile(File file,byte[] bytes) throws IOException {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
        }catch (IOException e){
            log.error("写文件失败："+file.getAbsolutePath());
        }finally {
            outputStream.close();
        }

    }

    /**
     * 写文件
     * @param path
     * @param bytes
     * @throws IOException
     */
    public static void writeFile(String path,byte[] bytes) throws IOException {
        File file = new File(path);
        writeFile(file,bytes);
    }

    /**
     * 追写文件
     * @param path
     * @param bytes
     * @throws IOException
     */
    public static void appendFile(String path,byte[] bytes) throws IOException {
       File file = new File(path);
       appendFile(file,bytes);
    }

    /**
     * 追写文件
     * @param file
     * @param bytes
     * @throws IOException
     */
    public static void appendFile(File file,byte[] bytes) throws IOException {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file,true);
            outputStream.write(bytes);
        }catch (IOException e){
            log.error("文件追写失败："+file.getAbsolutePath());
        }finally {
            outputStream.close();
        }

    }

    /**
     * 文件读取（按字节）
     *
     * @param file 待读取文件
     * @return
     * @throws IOException
     */
    public static byte[] readDataByByte(File file) throws IOException {
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
    public static String readFileByChar(File file) throws IOException {
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


    public static void main(String[] args) throws IOException {
        File file = new File("/Users/tangfuqiang/Downloads/配置文件的副本.txt");
        byte[] bytes = readDataByByte(file);
        File file1 = new File("/Users/tangfuqiang/Downloads/配置文件的副本2.txt");

        appendFile(file1,bytes);
    }
}
