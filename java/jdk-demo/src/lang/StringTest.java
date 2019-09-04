package lang;

import java.util.Arrays;

/**
 * <p>ClassName: StringTest</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/26
 * @since JDK 1.8
 */
public class StringTest {

    public void nullorempty() {
        //String str = new String();
        //String str = null;
        //String str = "";
        String str = " ";
        System.out.println(str.length());
        System.out.println(str.isEmpty());
    }


    public static void main(String[] args) {
        String str = "abcdef";
        String a = "sssssss";
        //ASCII值 0~127 ,char 对应的ASCII值的映射值
        System.out.println(str.codePointAt(0));
        System.out.println(a.charAt(0) - str.charAt(0));
        int i = 7;
        int j = 7;
        System.out.println(--i);
        System.out.println(j++);

        String[] arrs = new String[]{"a","a","a","a","a","a","a","a","a","a"};
        System.out.println(Arrays.copyOf(args, 2).length);
        System.out.println(arrs.length);

        String[] arrA = new String[10];
        System.out.println(arrA.length);

        String cn = "汉字";
        System.out.println(cn.length());
        System.out.println(cn.charAt(0));
    }
}
