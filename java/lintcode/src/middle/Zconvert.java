package middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>ClassName: Zconvert</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/6
 * @since JDK 1.8
 */
public class Zconvert {

    public static String convert(String s, int numRows) {
        if (s.isEmpty() || numRows<=0) {
            return s;
        }
        Map<Integer, StringBuilder> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int cycle = numRows ==1 ? 1 : 2*numRows - 2;
        int sLength = chars.length;
        int count = sLength%cycle == 0 ? sLength/cycle : sLength/cycle + 1;
        for (int i = 0; i < count; i++) {
            int tmp = 2;
            for (int j = 0; j < cycle; j++) {
                if ((i*cycle + j) >= sLength) {
                    break;
                }
                if (j < numRows) {
                    if (map.containsKey(j)) {
                        StringBuilder builder = map.get(j);
                        builder.append(chars[i*cycle + j]);
                    }else {
                        StringBuilder builder = new StringBuilder();
                        builder.append(chars[i*cycle + j]);
                        map.put(j, builder);
                    }
                }else {
                    StringBuilder builder = map.get(j - tmp);
                    builder.append(chars[i*cycle + j]);
                    tmp += 2;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        int cycleCount = sLength < numRows ? sLength : numRows;
        for (int i = 0; i < cycleCount; i++) {
            StringBuilder tmp = map.get(i);
            builder.append(tmp);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 4));
        int arr[] = new int[10];
    }
}
