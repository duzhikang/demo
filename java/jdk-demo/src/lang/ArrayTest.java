package lang;

/**
 * <p>ClassName: ArrayTest</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/26
 * @since JDK 1.8
 */
public class ArrayTest {

    public static void main(String[] args) {
        int count = 0;
        String[] a = new String[4];
        a[count++] = "a";
        System.out.println(count);
        a[count++] = "b";
        System.out.println(count);
        a[count++] = "c";
        System.out.println(count);
        a[count++] = "d";
        System.out.println(count);
        System.out.println(a[0]);
    }
}
