package main.com.tfq.common.util;


import lombok.extern.slf4j.Slf4j;
import main.com.tfq.common.exception.MyExcetion;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Slf4j
public class FileUtil {


    /**
     * 解压zip文件
     * @param filePath 文件路径
     * @param descDir 解压文件保存路径
     * @param encod 编码格式
     */
    public static void unZip(String filePath,String descDir,String encod){
        long startTime = System.currentTimeMillis();
        int filePathLen = filePath.length();
        String format = filePath.substring(filePathLen-4,filePathLen);
        if(!".zip".equals(format)){
            throw new MyExcetion("203","文件不是zip文件");
        }
        File zipFile = new File(filePath);
        if(!zipFile.exists()){
            throw new MyExcetion("202","找不到文件，路径可能出错");
        }

        try {
            ZipFile zip = new ZipFile(zipFile, Charset.forName(encod));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
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
            throw new MyExcetion("401","解压异常，解压文件"+filePath+
                    ",解压路径"+descDir+",共耗时："+String.valueOf(startTime-System.currentTimeMillis()));
        }
        long endTime = System.currentTimeMillis();
        log.info("共耗时{}毫秒",endTime-startTime);
    }

    public static void main(String[] args) {
        FileUtil.unZip("/Users/tangfuqiang/Downloads/天彦反诈(重庆)V1.0.5.zip","/Users/tangfuqiang/Downloads","gbk");
    }
}
