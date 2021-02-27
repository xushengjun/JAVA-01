package org.example.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 文件工具类
 *
 * @author Wiener
 * @date 2020/10/22
 */
public class FileTools {
    private static Logger logger = LoggerFactory.getLogger(FileTools.class);

    /**
     * 使用第三方jar包 org.apache.commons.io.FileUtils 简捷地下载网络文件
     *
     * @param urlStr   资源URL
     * @param dir      存储目录
     * @param fileName 存储文件名
     * @return
     */
    public static void downloadHttpResource(String urlStr, String fileName, String dir) {
        try {
            URL httpUrl = new URL(urlStr);
            fileName = getFileName(httpUrl, fileName);
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            FileUtils.copyURLToFile(httpUrl, new File(dir + File.separator + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载网络文件，常规写法，不多说，直接上代码
     *
     * @param urlStr   资源URL
     * @param dir      保存目录
     * @param fileName 保存后的文件名，不包括后缀
     * @return void
     */
    public static void downloadNetResource(String urlStr, String fileName, String dir) {
        // 下载网络文件
        int byteSum = 0;
        int byteRead = 0;
        InputStream inStream = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            fileName = getFileName(url, fileName);
            // 设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            //模拟浏览器访问,防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36");
            // 拿到输入流就相当于拿到了文件
            inStream = conn.getInputStream();
            // 文件保存位置
            File saveDir = new File(dir);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            fos = new FileOutputStream(file);
            byte[] buffer = new byte[1204];
            while ((byteRead = inStream.read(buffer)) != -1) {
                byteSum += byteRead;
                fos.write(buffer, 0, byteRead);
            }
            logger.info("文件 {} 的大小为 {}", fileName, byteSum);
        } catch (Exception e) {
            logger.error("下载网络资源 {} 失败，请及时处理，", fileName, e);
        } finally {
            IOUtils.closeQuietly(inStream, null);
            IOUtils.closeQuietly(fos, null);
        }
    }

    private static String getFileName(URL url, String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            fileName = Long.toString(System.currentTimeMillis());
        }
        String urlFileName = url.getFile();
        logger.info("网络资源原始名称：{}", urlFileName);
        if (!StringUtils.isEmpty(urlFileName)) {
            String subfix = urlFileName.substring(urlFileName.lastIndexOf("."));
            fileName = fileName + subfix;
        }
        return fileName;
    }

    public static void main(String[] args) {
        // 可以是其它类型的文件，此处以PDF文件为例
        String urlStr = "http://dl.cncounter.com/temp/README.pdf";
        String fileName = "我是文件名";
        String netFileName = "电子表格简明进阶教程";
        String savePath = "/Users/xushengjun/Documents/书籍";
//        downloadHttpUrl(urlStr, fileName, savePath);
        downloadNetResource(urlStr, null, savePath);
    }
}