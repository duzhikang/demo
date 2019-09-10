package string;

/**
 * <p>ClassName: StringTest</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/7
 * @since JDK 1.8
 */
public class StringTest {

    public static void compare() {
        String a = "a";
        String b = "b";
        String c = "ab";
        String d = "a" + "b";
        String e = a + b;
        String f = new String("ab");
        System.out.println(d == c);
        // 编译期不能确定e的值，运行时才能计算出e的值
        System.out.println(a + b == c);
    }

    public static void main(String[] args) {
        compare();
    }
}
