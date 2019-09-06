package collection.array;

/**
 * <p>ClassName: SelectSort</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/6
 * @since JDK 1.8
 */
public class SelectSort {

    /**
     * 选择排序
     * @param a
     * @return
     */
    public static void selectSort(int[] a) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < a.length; i++) {
            int flag = i;
            for (int j = i+ 1; j < a.length; j++) {
                if (a[flag] > a[j]) {
                    flag = j;
                }
            }
            if (flag != i) {
                int tmp = a[i];
                a[i] = a[flag];
                a[flag] = tmp;
            }
        }
        System.out.println("selectSort:" + (System.currentTimeMillis() -start));
        for (int i : a) {
            //System.out.print(i + ",");
        }
    }
}
