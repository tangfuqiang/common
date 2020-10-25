package com.tfq.common.util;


import lombok.extern.slf4j.Slf4j;
import com.tfq.common.exception.MyExcetion;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ZipUtil {


    /**
     * 解压zip文件
     *
     * @param filePath 文件路径
     * @param descDir  解压文件保存路径
     * @param encode   编码格式
     */
    public static void unZip(String filePath, String descDir, String encode) throws IOException {

        int filePathLen = filePath.length();
        String format = filePath.substring(filePathLen - 4, filePathLen);
        if (!".zip".equals(format)) {
            throw new MyExcetion("203", "文件不是zip文件");
        }
        File zipFile = new File(filePath);
        unZip(zipFile, descDir, encode);
    }

    /**
     * @param zipFile 待解压文件
     * @param descDir 解压文件保存路径
     * @param encode  编码格式
     * @throws IOException
     */
    public static void unZip(File zipFile, String descDir, String encode) throws IOException {
        long startTime = System.currentTimeMillis();
        if (!zipFile.exists()) {
            throw new MyExcetion("202", "找不到文件，路径可能出错");
        }
        try {
            ZipFile zip = new ZipFile(zipFile, Charset.forName(encode));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + File.separator + zipEntryName);
                // 判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                // 输出文件路径信息
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();

            }
        } catch (IOException e) {
            throw new MyExcetion("401", "解压异常，解压文件" + zipFile.getCanonicalPath() +
                    ",解压路径" + descDir + ",共耗时：" + String.valueOf(startTime - System.currentTimeMillis()));
        }
        long endTime = System.currentTimeMillis();
        log.info("共耗时{}毫秒", endTime - startTime);
    }

    /**
     * 文件压缩
     *
     * @param sourcePath 压缩文件路径
     * @param savePath   保存压缩文件路径
     * @throws IOException
     */
    public static void ratioFile(String sourcePath, String savePath) throws IOException {
        File file = new File(sourcePath);
        ratioFile(file, savePath);
    }

    /**
     * 文件压缩
     *
     * @param sourceFile 待压缩文件
     * @param savePath   压缩文件保存路径
     */
    public static void ratioFile(File sourceFile, String savePath) throws IOException {
        long startTime = System.currentTimeMillis();
        if (!sourceFile.exists()) {
            throw new MyExcetion("202", "找不到文件，路径可能出错");
        }
        FileOutputStream outputStream = new FileOutputStream(savePath + File.separator + sourceFile.getName() + ".zip");
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);
        try {
            compress(zipOutputStream, bufferedOutputStream, sourceFile, sourceFile.getName());
        } catch (IOException e) {
            throw new MyExcetion("401", "压缩异常，压缩文件" + sourceFile.getCanonicalPath() +
                    ",保存路径" + savePath + ",共耗时：" + String.valueOf(startTime - System.currentTimeMillis()));
        } finally {
            bufferedOutputStream.close();
            zipOutputStream.close();
            outputStream.close();
        }


    }

    /**
     * @param zipOutputStream      zip输出流
     * @param bufferedOutputStream 缓存输出流
     * @param sourceFile           压缩文件
     * @param base                 压缩文件在压缩包中路径
     * @throws Exception
     */
    private static void compress(ZipOutputStream zipOutputStream, BufferedOutputStream bufferedOutputStream, File sourceFile, String base) throws IOException {
        //如果路径为目录（文件夹）
        if (sourceFile.isDirectory()) {
            //取出文件夹中的文件（或子文件夹）
            File[] flist = sourceFile.listFiles();
            //如果文件夹为空，则只需在目的地zip文件中写入一个目录
            if (flist.length == 0) {
                zipOutputStream.putNextEntry(new ZipEntry(base));
            } else//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
            {
                for (int i = 0; i < flist.length; i++) {
                    compress(zipOutputStream, bufferedOutputStream, flist[i], base + File.separator + flist[i].getName());
                }
            }
        } else//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
        {
            zipOutputStream.putNextEntry(new ZipEntry(base));
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            int tag = 0;
            //将源文件写入到zip文件中
            while ((tag = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(tag);
            }
            fileInputStream.close();
            bufferedInputStream.close();
        }
    }
}


