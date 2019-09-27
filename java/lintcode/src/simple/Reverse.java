package simple;

/**
 * <p>ClassName: Reverse</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *  -2147483648~2147483647
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/12
 * @since JDK 1.8
 */
public class Reverse {

    public static int reverse(int x) {
        long flag = 1, temp = 0;
        if (x < 0) {
            flag = -1;
            x = -x;
        }
        while (x > 0) {
            temp *= 10;
            temp += x%10;
            x /= 10;
        }
        if (temp*flag > 2147483647 || temp*flag < -2147483648) {
            return 0;
        }
        temp *= flag;
        return (int)temp;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-21483647));
    }
}
