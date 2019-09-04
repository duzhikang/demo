package middle;

/**
 * <p>ClassName: AddTwoNumbers</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/28
 * @since JDK 1.8
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode nodenext = node;
        while (l1 != null || l2 != null ) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int temp = a + b;
            if (temp >= 10) {
                l1 = l1.next;
                if (l1 != null) {
                    l1.val = l1.val + 1;
                }else {
                    l1 = new ListNode(1);
                }
                nodenext.val = temp - 10;
                if (l2 != null) {
                    l2 = l2.next;
                }
                if (l1 != null || l2 != null) {
                    nodenext.next = new ListNode(0);
                    nodenext = node.next;
                }
            }else {
                nodenext.val = temp;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
                if (l1 != null || l2 != null) {
                    nodenext.next = new ListNode(0);
                    nodenext = node.next;
                }
            }
        }
        return node;
    }

    public static void main(String[] args) {
       Integer a = new Integer(555);
       Integer b = a;
       b = new Integer(333);
       System.out.println(a);
    }
}
