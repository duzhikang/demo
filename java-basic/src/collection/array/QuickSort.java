package collection.array;

/**
 * <p>ClassName: QuickSort</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/6
 * @since JDK 1.8
 */
public class QuickSort {

    public static void quickSort(int[] a, int head, int tail) {
        int i = head, j = tail, pivot = a[(tail + head)/2];
        while (i <= j) {
            while (a[i] < pivot) {
                i ++;
            }
            while (a[j] > pivot) {
                j --;
            }
            if (i < j) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i ++;
                j --;
            }
        }

    }
}
