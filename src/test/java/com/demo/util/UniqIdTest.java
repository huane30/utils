package com.demo.util;

import java.net.UnknownHostException;

/**
 * Created by hoo on 2017/3/10.
 */
public class UniqIdTest {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(UniqId.getInstance().getUniqID());
        System.out.println("test".hashCode());
    }
}
