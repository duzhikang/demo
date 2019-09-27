package middle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>ClassName: MyAtoi</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/15
 * @since JDK 1.8
 */
public class MyAtoi {

    public static int myAtoi(String str) {
        char[] chars = str.trim().toCharArray();
        if (chars.length <=0) {
            return 0;
        }
        boolean flag = false;
        int a = 0;
        int b = 0;
        StringBuilder builder = new StringBuilder();
        for (char var : chars) {
            if (var == '+' || var == '-' || (var >= 48 && var <= 57)) {
                if(a > 0 && (var =='+' || var=='-')) {
                    break;
                }
                if (a == 1 && var == '0' && builder.substring(0).equals("0")) {
                    continue;
                }
                if (a == 1 && builder.indexOf("-") == 0 && var == '0') {
                    continue;
                }
                builder.append(var);
                flag = true;
                a++;
                if (a > 13) {
                    break;
                }
            }else {
                if (a == 0) {
                    return 0;
                }
            }

            if (flag) {
                b++;
                if (a != b) {
                    break;
                }
            }
        }
        str = builder.toString();
        if (str.isEmpty() || str.equals("-") || str.equals("+")) {
            return 0;
        }
        Long result = Long.valueOf(str);
        if (result >= 2147483647) {
            result = 2147483647L;
        }
        if (result <= -2147483648) {
            result = -2147483648L;
        }
        return Math.toIntExact(result);
    }


    public static void main(String[] args) {
        System.out.println(myAtoi("00000000000000012345678"));
    }
}
