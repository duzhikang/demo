package com.dzk.threadpool.interview;/**
 * Created by dzk on 2020/2/8.
 */

/**
 * @ClassName Test
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/8
 **/
public class Test {

    public static void main(String[] args) {
        System.out.println(Math.round(-1.5));
        String a = "a";
        String b = "b";
        String c = "ab";
        String d = "a" + "b";
        System.out.println( c== a + b);
        System.out.println(c == d);
    }
}
