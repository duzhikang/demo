package nio_day01;

/**
 * <p>ClassName: TestBuffer</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/19
 * @since JDK 1.8
 */

import java.nio.ByteBuffer;

/**
 * Buffer 就像一个数组，可以保存多个相同类型的数据。根
 * 据数据类型不同(boolean 除外) ，有以下 Buffer 常用子类：
 *  ByteBuffer
 *  CharBuffer
 *  ShortBuffer
 *  IntBuffer
 *  LongBuffer
 *  FloatBuffer
 *  DoubleBuffer
 *
 * 通过allocate()获取缓存区
 *
 * Buffer 中的重要概念：
 * - 容量 (capacity) ：表示 Buffer 最大数据容量，缓冲区容量不能为负，并且创
 * 建后不能更改。
 * - 限制 (limit)：第一个不应该读取或写入的数据的索引，即位于 limit 后的数据
 * 不可读写。缓冲区的限制不能为负，并且不能大于其容量。
 * - 位置 (position)：下一个要读取或写入的数据的索引。缓冲区的位置不能为
 * 负，并且不能大于其限制
 * - 标记 (mark)与重置 (reset)：标记是一个索引，通过 Buffer 中的 mark() 方法
 * 指定 Buffer 中一个特定的 position，之后可以通过调用 reset() 方法恢复到这
 * 个 position.
 *
 * 0 <= mark <= position <= limit <= capacity
 */
public class TestBuffer {

    public static void main(String[] args) {
        test03();
    }

    public static void test01() {
        // 1.分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println(buf.capacity());
        System.out.println(buf.position());
        System.out.println(buf.limit());
        buf.put("hello".getBytes());
        System.out.println("=========put==========");
        System.out.println(buf.capacity());
        System.out.println(buf.position());
        System.out.println(buf.limit());

        // 读取数据，切换读取数据的模式
        System.out.println("=========read==========");
        buf.flip();
        System.out.println(buf.capacity());
        System.out.println(buf.position());
        System.out.println(buf.limit());
        // get读取数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));
        // 可重复读
        buf.rewind();
        // 清空缓冲区,但缓存区中数据依然存在，但处于遗忘状态
        buf.clear();
    }

    public static void test2() {
        String str = "abcdefg";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buf.position());
        // 标记
        buf.mark();
        buf.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buf.position());
        // 恢复到mark位置
        buf.reset();
        System.out.println(buf.position());
    }

    public static void test03() {
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        System.out.println(buf.isDirect());
    }

}
