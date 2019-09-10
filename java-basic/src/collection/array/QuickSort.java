package collection.array;

import java.util.Arrays;

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
        if (head >= tail || a == null || a.length <= 1) {
            return;
        }
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
            }else if (i == j) {
                i++;
            }
        }
        quickSort(a, head, j);
        quickSort(a, i, tail);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 8, 2, 55, 3};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
