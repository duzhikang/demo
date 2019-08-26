package simple;

/**
 * <p>ClassName: TailZeroCount</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 * 设计一个算法，计算出n阶乘中尾部零的个数
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/26
 * @since JDK 1.8
 */
public class TailZeroCount {

    public static long trailingZeros(long n) {
        long tmp = 1;
        for (int i = 1; i <= n; i++) {
            tmp = tmp*i;
        }

        return tmp;
    }

    /**
     *
     * @param n
     * @return
     */
    public static long other(long n) {
        long sum=0;
        while(n/5!=0){
            sum+=n/5;
            n/=5;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(other(105));
    }
}
