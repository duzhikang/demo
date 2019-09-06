package collection.array;

/**
 * <p>ClassName: BubbleSort</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/6
 * @since JDK 1.8
 */
public class BubbleSort {

    /**
     * 冒泡排序
     * @param a
     * @return
     */
    public static int[] bubbleSort(int[] a) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int tmp = a[j];
                    a[j] = a[i];
                    a[i] = tmp;
                }
            }
        }
        System.out.println("bubbleSort:" + (System.currentTimeMillis() -start));
        for (int i : a) {
            //System.out.print(i + ",");
        }
        return a;
    }

    public static void main(String[] args) {
        int[] clone = bubbleSort(new int[]{1, 2, 6, 3, 2, 5}).clone();
        for (int i : clone) {
            System.out.println(i);
        }
    }
}
