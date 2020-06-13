package com.rudy.arithmetic.stackandqueue;

import java.util.Stack;

/**
 * <p>ClassName: TwoStacksQueue</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/13
 * @since JDK 1.8
 */
public class TwoStacksQueue {

    private Stack<Integer> stackPush;

    private Stack<Integer> stackPop;

    public TwoStacksQueue() {
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    // push栈向pop栈倒入数据
    private void pushToPop() {
        if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void add(int pushInt) {
        stackPush.push(pushInt);
        pushToPop();
    }

    public int poll() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("queue is empty!");
        }
        pushToPop();
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("queue is empty!");
        }
        pushToPop();
        return stackPop.peek();
    }

    public static void main(String[] args) {
        TwoStacksQueue queue = new TwoStacksQueue();
        queue.add(1);
        queue.add(2);
        queue.poll();
        queue.add(3);
    }
}
