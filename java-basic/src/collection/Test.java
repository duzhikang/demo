package collection;

import collection.array.BubbleSort;
import collection.array.InsertionSort;
import collection.array.QuickSort;
import collection.array.SelectSort;

import java.util.Arrays;

/**
 * <p>ClassName: Test</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/6
 * @since JDK 1.8
 */
public class Test {

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,
                5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,2,5,4,8,7,9,1,2,78,54,32,11,1,78,2,5,4,8,7,9,1,2,78,54,32,11,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                1,2,3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,32,11,1,2,3,2,5,4,85,45,16,6,
                1,9,1,2,78,54,4,32,11,1,2,3,2,5,4,85,45,16,6};
        BubbleSort.bubbleSort(a);
        int[] b = new int[]{1,2,3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,
                5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,2,5,4,8,7,9,1,2,78,54,32,11,1,78,2,5,4,8,7,9,1,2,78,54,32,11,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                1,2,3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,32,11,1,2,3,2,5,4,85,45,16,6,
                1,9,1,2,78,54,4,32,11,1,2,3,2,5,4,85,45,16,6};
        SelectSort.selectSort(b);
        int[] c = new int[]{1,2,3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,
                5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,2,5,4,8,7,9,1,2,78,54,32,11,1,78,2,5,4,8,7,9,1,2,78,54,32,11,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                1,2,3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,32,11,1,2,3,2,5,4,85,45,16,6,
                1,9,1,2,78,54,4,32,11,1,2,3,2,5,4,85,45,16,6};
        InsertionSort.insertionSort(c);
        int[] d = new int[]{1,2,3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,
                5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,2,5,4,8,7,9,1,2,78,54,32,11,1,78,2,5,4,8,7,9,1,2,78,54,32,11,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,
                1,2,3,2,5,4,8,7,9,1,2,78,54,32,11,1,2,3,2,5,4,85,45,16,65,8,7,9,14,2,5,4,8,7,9,1,9,1,2,78,54,4,32,11,1,2,3,2,5,4,85,45,16,6,
                1,9,1,2,78,54,4,32,11,1,2,3,2,5,4,85,45,16,6};
        long l = System.currentTimeMillis();
        QuickSort.quickSort(d, 0,a.length -1);
        System.out.println("quickSort:" + (System.currentTimeMillis() - l));
        System.out.println(Arrays.toString(a));

    }
}
