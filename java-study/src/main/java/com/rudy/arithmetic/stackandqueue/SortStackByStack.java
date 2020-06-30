package com.rudy.arithmetic.stackandqueue;

import java.util.Stack;

/**
 * <p>ClassName: SortStackByStack</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/28
 * @since JDK 1.8
 */
public class SortStackByStack {

    public static Stack<Integer> sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            while (!help.isEmpty() && help.peek() < cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
        return stack;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(8);
        stack.push(3);
        stack.push(7);
        stack.push(1);
        Stack<Integer> stack1 = sortStackByStack(stack);
        while (!stack1.isEmpty()) {
            System.out.println(stack1.pop());
        }
    }
}
