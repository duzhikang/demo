package simple;

/**
 * <p>ClassName: IsPalindrome</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/16
 * @since JDK 1.8
 */
public class IsPalindrome {
    public static boolean isPalindrome(int x) {
        int a = x;
        int flag = 1,tmp = 0;
        if (x <0) {
            return false;
        }
        while (x > 0) {
            tmp = tmp *10 + x%10;
            x /= 10;
        }
        if (a == tmp) {
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }
}
