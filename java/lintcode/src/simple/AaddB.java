package simple;

/**
 * <p>ClassName: AaddB</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/26
 * @since JDK 1.8
 */

/**
 * 给出两个整数 a和 b , 求他们的和。
 */
public class AaddB {

    /**
     * 1.异或运算有一个别名叫做：不进位加法
     * 2.那么a ^ b就是a和b相加之后，该进位的地方不进位的结果
     * 3. a & b就是a和b里都是1的那些位置，a & b << 1 就是进位
     * 4.个过程是在模拟加法的运算过程，进位不可能
     * 一直持续，所以b最终会变为0。因此重复做上述操作就可以
     * @param a
     * @param b
     * @return
     */
    public static int aplusb(int a, int b) {
        while (b!=0) {
            int _a = a ^ b;
            int _b = (a&b) << 1;
            a = _a;
            b = _b;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(aplusb(11,22));
    }
}
