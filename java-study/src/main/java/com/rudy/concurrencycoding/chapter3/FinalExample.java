package com.rudy.concurrencycoding.chapter3;

/**
 * <p>ClassName: FinalExample</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/20
 * @since JDK 1.8
 */
public class FinalExample {

    int i;
    final int j;
    static FinalExample obj;
    public FinalExample() {
        i =1;
        j = 2;
    }

    public static void writer() {
        obj = new FinalExample();
    }

    public static void reader() {
        FinalExample object = obj;
        int a = object.i;
        int b = object.j;
    }


}
