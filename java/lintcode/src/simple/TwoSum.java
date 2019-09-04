package simple;

/**
 * <p>ClassName: TwoSum</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/28
 * @since JDK 1.8
 */
public class TwoSum {


    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length ; j++) {
                if ((nums[i] + nums[j]) == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{2, 7, 11, 15, 3}, 5);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
