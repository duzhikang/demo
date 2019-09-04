package difficult;

import middle.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>ClassName: FindMedianSortedArrays</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/30
 * @since JDK 1.8
 */
public class FindMedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0.0;
        DNode node = null;
        int size = nums1.length + nums2.length;
        int count = 0;
        Integer[] all = new Integer[size];
        for (int i : nums1) {
            all[count] = i;
            count ++;
        }
        for (int i : nums2) {
            all[count] = i;
            count ++;
        }
        
        for (int i = 0; i < all.length; i++) {
            if (node == null) {
                node = new DNode(all[i]);
                continue;
            }

            while (node.val > all[i]) {
                if (node.pre != null) {
                    node = node.pre;
                }else {
                    break;
                }
            }

            while (node.val < all[i]) {
                if (node.suf != null) {
                    node = node.suf;
                }else {
                    break;
                }
            }

            if (node.val > all[i]) {
                DNode add = new DNode(all[i]);
                add.pre = node.pre;
                add.suf = node;
                if (node.pre != null) {
                    node.pre.suf = add;
                }
                node.pre = add;
            }else {
                DNode add = new DNode(all[i]);
                add.pre = node;
                add.suf = node.suf;
                if (node.suf != null) {
                    node.suf.pre = add;
                }
                node.suf = add;
            }

        }
        while (node.suf != null) {
            node = node.suf;
        }
        count=0;
        while (count < all.length) {
            all[count] = node.val;
            count ++;
            node = node.pre;
        }
        for (Integer integer : all) {
            System.out.println(integer);
        }
        if (all.length%2 == 0) {
            result = (double) (all[all.length/2] + all[all.length/2 -1])/2;
        }else {
            System.out.println("idx:" + (all.length/2));
            result = (double) all[all.length/2];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 3};
        int[] b = new int[] {2};
        System.out.println(findMedianSortedArrays(a, b));
    }

    static class DNode{
        int val;

        DNode pre;

        DNode suf;

        DNode(int x) { val = x; }
    }
}
