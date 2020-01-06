package com.clt.springbootssm;

/**
 * @author ：clt
 * @Date ：Created in 21:52 2019/10/17
 */
public class Test1 {

    public static void main(String[] args) {
        int  a =1;
        Integer a1 = 1;
        Integer a2 = new Integer("1");
        Integer b = 1;
        Integer c = 128;
        Integer d = 128;

        String s1 = "abc";
        String s2 = "abc";
        String s4 = "ab" + "c";
        String s3 = new String("abc");

        System.out.println(a1 == b);
        System.out.println(c == d);

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
        System.out.println(s2 == s4);
    }
}
