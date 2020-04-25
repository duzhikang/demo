import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>ClassName: Test</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/10/19
 * @since JDK 1.8
 */
public class Test {

    public static void main(String[] args) {
        /*String a = new String("adc");
        String b = a;
        a = "def";
        System.out.println(a);
        System.out.println(b);*/
        Pattern p = Pattern.compile("/sdk/selectUnit.do");
        Matcher matcher = p.matcher("/pay/zf/sdk/selectUnit.do");
        boolean b = matcher.find();
        System.out.println(b);
    }
}
