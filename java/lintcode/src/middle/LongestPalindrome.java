package middle;

/**
 * <p>ClassName: LongestPalindrome</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/31
 * @since JDK 1.8
 */
public class LongestPalindrome {

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * 中心扩展法
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int l = 0;
        int r = 0;
        char[] chars = s.toCharArray();
        int length = s.length();
        char[] fillArr = new char[2*length - 1];
        for (int i = 0; i < fillArr.length; i++) {
            if (i%2 == 0) {
                fillArr[i] = chars[i/2];
            }else {
                fillArr[i] = '#';
            }
        }
        for (int i = 0; i < fillArr.length; i++) {
            int left = 0;
            int right = 0;
            while (left >= 0 && right < fillArr.length) {
                if ((i - left) < 0 || i + right >= fillArr.length) {
                    break;
                }
                if (fillArr[i - left] == fillArr[i + right]) {
                    if ((right + left) >= (l-r + 1)) {
                        l=i-left ;
                        r=i+right;
                    }
                    left ++;
                    right ++;
                }else {
                    break;
                }
            }
        }
        return fillArr.toString().substring(l, r + 1).replaceAll("#", "");
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("sbbghj"));
    }

}
