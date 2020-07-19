package com.starriverdata.threadpool;

import java.io.*;

public class JvmTest {

    private static final int _1MB = 100 * 1024 * 1024;


    public static void main(String[] args) {
        File file = new File("/Users/wyr/Desktop/csdn.txt");
        try (InputStream inputStream = new FileInputStream(file)) {
            int fileSize = inputStream.available();
            System.out.println("文件大小为:" + fileSize +"byte");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, len));
                System.out.println("-------------------------");
            }

        } catch (FileNotFoundException e) {
            System.out.println("目录不存在:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
