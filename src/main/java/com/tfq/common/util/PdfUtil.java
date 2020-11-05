package com.tfq.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

@Slf4j
public class PdfUtil {

    /**
     *
     * @param pdfFile pdf文件
     * @param savePath 转化出word保存路径
     */
    public static void pdfToWord(File pdfFile,String savePath) throws IOException {
        Writer writer = null;
        FileOutputStream outputStream = null;
        File wordFile = null;
        try {
            PDDocument document = PDDocument.load(pdfFile);
            //获取pdf页数
            int page = document.getNumberOfPages();
            String pdfName = pdfFile.getName();
            savePath = savePath + File.separator + pdfName.substring(0,pdfName.length()-4) + ".doc";
            wordFile = new File(savePath);
            if(wordFile.exists()){
                wordFile.delete();
            }
            wordFile.createNewFile();
            outputStream = new FileOutputStream(wordFile);
            writer = new OutputStreamWriter(outputStream, "UTF-8");
            PDFTextStripper stripper = new PDFTextStripper();
            //排序
            stripper.setSortByPosition(true);
            // 设置转换的开始页
            stripper.setStartPage(1);
            // 设置转换的结束页
            stripper.setEndPage(page);
            stripper.writeText(document,writer);
        }catch (IOException e){
            log.error("pdf转图片失败，pdf文件："+pdfFile.getAbsolutePath()+",word文件保存地址："+savePath);
            if(wordFile.exists()){
                wordFile.delete();
            }
        }finally {
            if(writer != null){
                writer.close();
            }
            if(outputStream != null){
                outputStream.close();
            }
        }

    }

    /**
     *
     * @param pdfPath pdf文件路径
     * @param savePath 转化出word保存路径
     */
    public static void pdfToWord(String pdfPath,String savePath){

    }

    /**
     *
     * @param wordFile word文件
     * @param savePath 转化出pdf保存文件
     */
    public static void wordToPdf(File wordFile,String savePath){

    }

    /**
     *
     * @param wordPath word路径
     * @param savePath 转化出pdf保存文件
     */
    public static void wordToPdf(String wordPath,String savePath){

    }


    public static void main(String[] args) throws IOException {
        File file = new File("/Users/tangfuqiang/Downloads/集合.pdf");
        PdfUtil.pdfToWord(file,"/Users/tangfuqiang/Downloads");
    }
}
