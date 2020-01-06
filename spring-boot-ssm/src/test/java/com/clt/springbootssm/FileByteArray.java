package com.clt.springbootssm;

import java.io.*;

public class FileByteArray {
    public static void main(String[] args) {
        byte[] bytes=FileByteArray.fileToByteArray("C:/Users/Mrchen/Desktop/注册背景图.jpg");
        FileByteArray.byteArrayTofile(bytes,"C:/Users/Mrchen/Desktop/注册.jpg");
        System.out.println(bytes.length);
    }

    public static byte[] fileToByteArray(String srcPath){
        File file = new File(srcPath);
        InputStream is= null;
        ByteArrayOutputStream baos=null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            baos=new ByteArrayOutputStream();
            byte[] bytes=new byte[1024*10];
            int len = -1;
            while ((len=is.read(bytes))!=-1){
                baos.write(bytes,0,len);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void byteArrayTofile(byte[] bytes,String toPath){
        File file = new File(toPath);
        OutputStream os=null;
        ByteArrayInputStream bais=null;
        try {
            os=new BufferedOutputStream(new FileOutputStream(file));
            bais=new ByteArrayInputStream(bytes);
            byte[] flush =new byte[1024];
            int len = -1;
            while ((len=bais.read(flush))!=-1){
                os.write(flush,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
