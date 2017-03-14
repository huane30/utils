package com.demo.util;

/**
 * Created by hoo on 2017/3/10.
 */
public class ConfigLoaderTest {

    public static void main(String[] args){
        int tt=ConfigLoader.getInstance().getIntValue("test");
        System.out.print(tt);
    }
}
