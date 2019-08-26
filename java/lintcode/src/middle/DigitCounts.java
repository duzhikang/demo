package middle;

/**
 * <p>ClassName: DigitCounts</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/26
 * @since JDK 1.8
 */
public class DigitCounts {

    public static int digitCounts(int k, int n) {
        int count = 0;
        String strK = String.valueOf(k);
        for (int i = 0; i <= n; i++) {
            String[] split = String.valueOf(i).split("");
            for (String s : split) {
                if (strK.equals(s)) {
                    count += 1;
                }
            }
        }
        return count;
    }

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
        System.out.println(digitCounts(1, 12));
    }


}
