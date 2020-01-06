package com.clt.springbootssm;


import org.junit.Test;

import java.io.*;
import java.util.*;

public class IOTest {
    public static void main(String[] args) {
        char test = 'a';
        String str = "a";
        System.out.println(str.equals(test));
        System.out.println(str.equals('a'));
        System.out.println(str instanceof String );
//        System.out.println(test instanceof String );
        System.out.println(str.hashCode()==test);
    }

    @Test
    public void test(){
        String test = "我ihidsa讴ffss歌红树林看后感";
        int h=test.hashCode();
        System.out.println(h);
        System.out.println(h^(h >>> 16));
    }
    @Test
    public void test1(){
        List<String> list=new ArrayList<>(10);
        for(int i = 0 ; i<20 ; i++){
            list.add(String.valueOf(i));
        }
        Iterator<String> it=list.iterator();
        while (it.hasNext()){
            String temp=it.next();
            if ("10".equals(temp)) {
                it.remove();
            }
            System.out.print(temp+" --");
        }
        for (String  s : list
             ) {
            System.out.print(s+"--");
        }
    }

    @Test
    public void test2(){
        String msg="法 ";
        byte[] bytes=msg.getBytes();
        for (byte b:bytes
             ) {
            System.out.print(b+"**");
        }
        System.out.println();
        byte[] bytes1=new byte[]{97,98,99,100,101};
        for (byte b:bytes1
        ) {
            System.out.print(b+"**");
        }
        System.out.println();
        String ms=new String(bytes1);
        System.out.println(ms);
    }

    @Test
    public void test3(){
        File file=new File("C:/Users/Mrchen/Desktop/test.txt");
        InputStream is=null;
        if (file.exists()){
            try {
                is=new FileInputStream(file);
                int temp;
                while ((temp=is.read())!=-1){
                    System.out.print((char) temp);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void test31(){
        File file=new File("C:/Users/Mrchen/Desktop/test.txt");
        InputStream is=null;
        if (file.exists()){
            try {
                String str="";
                is=new BufferedInputStream(new FileInputStream(file));
                byte[] flush =new byte[3];
                while ((is.read(flush))!=-1){
                     str+=new String(flush);
                    System.out.println(str);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test4(){
        OutputStream os=null;
        String msg="hello world 陈留涛 十五年";
        byte[] bytes=msg.getBytes();
        File file=new File("C:/Users/Mrchen/Desktop/test.txt");
        if (file.exists()){
            try {
                os= new BufferedOutputStream(new FileOutputStream(file,true));
                os.write(bytes);
            } catch (IOException e) {
                if (os!=null){
                    try {
                        os.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            }finally {
                if (os!=null){
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Test
    public void test5(){
        InputStream is=null;
        OutputStream os=null;
        File filefrom=new File("C:/Users/Mrchen/Desktop/test.txt");
        File fileto=new File("C:/Users/Mrchen/Desktop/testto.txt");
        try {
            fileto.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileto.exists()){
            try {
                is=new BufferedInputStream(new FileInputStream(filefrom));
                os=new BufferedOutputStream(new FileOutputStream(fileto));
                byte[] flush = new byte[1024];
                int len = -1;
                while ((len=is.read(flush))!=-1){
                    os.write(flush,0,len);
                }
            } catch (IOException e) {
                if (os!=null){
                    try {
                        os.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (is!=null){
                    try {
                        is.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            }finally {
                if (os!=null){
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Test
    public void test6(){
        Reader reader=null;
        Writer writer=null;
        File filefrom=new File("C:/Users/Mrchen/Desktop/test.txt");
        File fileto=new File("C:/Users/Mrchen/Desktop/testto.txt");
        if (!fileto.exists()){
            System.out.println("########");
            try {
                fileto.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileto.exists()){
            try {
                reader=new BufferedReader(new FileReader(filefrom));
                writer=new BufferedWriter(new FileWriter(fileto,true));
                char[] flush = new char[1024];
                String line = null;
                while ((line=((BufferedReader) reader).readLine())!=null){
                    writer.write(line);
                    ((BufferedWriter) writer).newLine();
                    ((BufferedWriter) writer).newLine();
                }
            } catch (IOException e) {
                if (writer!=null){
                    try {
                        writer.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            }finally {
                if (writer!=null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Test
    public void test7(){
        InputStream is=null;
        OutputStream os=null;
        try {
            String msg="风飒飒付付付付";
            os=new ByteArrayOutputStream();
            os.write(msg.getBytes());
            is=new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray());
            byte[] flush = new byte[1024];
            int len = -1;
            while ((len=is.read(flush))!=-1){
                System.out.println(new String(flush,0,((ByteArrayOutputStream) os).size()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test08(){
        System.out.println(225887%1024);
    }


    public String testString(String src){
        if (src != null){
            char[] temp = src.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = temp.length-1; i >= 0; i--){
                sb.append(temp[i]);
            }
            return sb.toString();
        }
        return null;
    }

    public String testString1(String src){
        if (src != null){
            StringBuilder sb = new StringBuilder();
            for (int i = src.length(); i>0; i--){
                sb.append(src.substring(i-1,i));
            }
            return sb.toString();
        }
        return null;
    }


    @Test
    public void testConverst(){
        String s = "hello world";
        System.out.println(testString1(s));
        Set set = new HashSet();
        set.add(null);
        System.out.println(set);
    }


}
