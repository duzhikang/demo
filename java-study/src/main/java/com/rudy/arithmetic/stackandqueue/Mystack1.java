package com.rudy.arithmetic.stackandqueue;

import java.util.Stack;

/**
 * <p>ClassName: Mystack1</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/13
 * @since JDK 1.8
 */
// 方案一中stackMin压入时稍省空间，但是弹出操作稍费时间
public class Mystack1 {

    private Stack<Integer> stackData;

    private Stack<Integer> stackMin;

    public Mystack1() {
        this.stackData = new Stack<Integer>();
        this.stackMin = new Stack<Integer>();
    }

    public void push(int newNum) throws Exception {
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum <= this.getmin()) {
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);
    }

    // poll：Queue(队列)的一个方法，获取并移除此队列的头，如果此队列为空，则返回null。
    // 返回栈顶的值 ；会把栈顶的值删除。
    public int pop() throws Exception {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("your stack is empty");
        }
        int value = this.stackData.pop();
        if (value == this.getmin()) {
            this.stackMin.pop();
        }
        return value;
    }


    public int getmin() throws Exception {
        if (stackMin.isEmpty()) {
            throw new Exception("your stack is empty");
        }
        // 返回栈顶的值 ；不改变栈的值，查看栈顶的对象而不移除它。
        return stackMin.peek();
    }

    // 方案二中stackMin压入时稍费空间，但是弹出操作稍省时间。
    static class MyStack2 {
        private Stack<Integer> stackData;

        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) throws Exception {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                Integer peek = this.stackMin.peek();
                this.stackMin.push(peek);
            }
            this.stackData.push(newNum);
        }

        public int pop() throws Exception {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("your stack is empty");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getmin() throws Exception {
            if (stackMin.isEmpty()) {
                throw new Exception("your stack is empty");
            }
            // ：返回栈顶的值 ；不改变栈的值，查看栈顶的对象而不移除它。
            return stackMin.peek();
        }
    }
}
