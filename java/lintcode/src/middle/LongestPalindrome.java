package middle;

import java.util.Queue;

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
        if (s.trim().isEmpty()) {
            return s;
        }
        char[] strs = s.toCharArray();
        char[] change = new char[s.length()*2 -1];
        for (int i = 0; i < change.length; i++) {
            if (i%2 == 0) {
                change[i] = strs[i/2];
            }else {
                change[i] = '#';
            }
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < change.length; i++) {
            int left = 0;
            int right = 0;
            while ((i -left >= 0) && i+ right < change.length) {
                if (change[i-left] == change[i + right]) {
                    if (new String(change).substring(i-left, i + right + 1).replaceAll("#", "").length() <= (start-end + 1)) {
                        left ++;
                        right ++;
                        continue;
                    }
                    if (((i + right) - (i-left) + 1) > (end -start) + 1) {
                        start = i-left;
                        end = i + right;
                    }
                    left ++;
                    right ++;
                }else {
                    break;
                }
            }
        }
        return new String(change).substring(start, end + 1).replaceAll("#", "");
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

}
