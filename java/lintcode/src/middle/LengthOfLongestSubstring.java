package middle;


import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>ClassName: LengthOfLongestSubstring</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/29
 * @since JDK 1.8
 */
public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Queue<Character> queue = new LinkedList<Character>();
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            while (queue.contains(chars[i])) {
                ((LinkedList<Character>) queue).removeFirst();
            }
            queue.offer(chars[i]);
            if (queue.size() > max) {
                max = queue.size();
            }
        }
        return max;

    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
