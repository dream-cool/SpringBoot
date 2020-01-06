package com.springbootshiro.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @ Author   ：clt.
 * @ Date     ：Created in 19:12 2019/8/7
 */
public class GenSalt {
    public static void main(String[] args) {
        int hashIterations = 1024;
        //加密的次数
        Object salt = "谈雨";
        //盐值
        Object credentials = "123456789";
        //密码
        String hashAlgorithmName = "MD5";
        //加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
                salt, hashIterations);
        System.out.println("加密后的值----->" + simpleHash);
    }
}
