package collection.array;

/**
 * <p>ClassName: InsertionSort</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/6
 * @since JDK 1.8
 */
public class InsertionSort {

    /**
     * 插入排序
     */
    public static void insertionSort(int[] a) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < a.length -1; i++) {
            int tmp = a[i+1];
            for (int j = i; j >= 0; j--) {
                if (tmp < a[j]) {
                    a[j + 1] = a[j];
                    a[j] = tmp;
                }
            }
        }
        System.out.println("insertionSort:" + (System.currentTimeMillis() -start));
        for (int i : a) {
            //System.out.print(i + ",");
        }
    }
}
