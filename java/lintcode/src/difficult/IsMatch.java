package difficult;

import java.util.regex.Pattern;

/**
 * <p>ClassName: IsMatch</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/16
 * @since JDK 1.8
 */
public class IsMatch {
    public static boolean isMatch(String s, String p) {
        boolean matches = Pattern.matches(p, s);
        return matches;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("mississippi", "mis*is*p*."));
    }

}
