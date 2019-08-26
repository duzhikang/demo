package nio_day01;

/**
 * <p>ClassName: TestChannel</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/20
 * @since JDK 1.8
 */

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 1.通道（Channel）：由 java.nio.channels 包定义的。Channel 表示 IO 源与目标打开的连接。
 * Channel 类似于传统的“流”。只不过 Channel本身不能直接访问数据，Channel 只能与Buffer 进行交互。
 *
 * 2.Java 为 Channel 接口提供的最主要实现类如下：
 * •FileChannel：用于读取、写入、映射和操作文件的通道。
 * •DatagramChannel：通过 UDP 读写网络中的数据通道。
 * •SocketChannel：通过 TCP 读写网络中的数据。
 * •ServerSocketChannel：可以监听新进来的 TCP 连接，对每一个新进来的连接都会创建一个 SocketChannel。
 *
 * 3.获取通道
 * java 针对支持通道的类提供了getChannel()方法
 * 本地IO:
 * FileInputStream/FileOutputStream
 * RandomAccessFille
 *
 * 网络IO
 * Socket
 * ServerSocket
 * DatagramSocket
 *
 * jdk1.7 中的NIO.2 针对各个通道提供了静态方法open
 * jdk1.7 中的NIO.2 的Files 工具类的newByteChannel()
 *
 * 通道之间的数据传输
 *
 * 分散（scatter）与聚集（gather）
 * 分散读取（scattering Reads）: 将通道中的数据分散到多个缓冲区中
 * 聚焦写入（gathering writes）： 将多个缓冲区中的数据聚集到通道中
 *
 * 字符集：charset
 * 编码：字符串-》字节数组
 * 解码：字节数组-》字符串
 */
public class TestChannel {

    public static void test05() throws CharacterCodingException {
        SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = availableCharsets.entrySet();
        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println(entry.getKey() + entry.getValue());
        }

        Charset cs1 = Charset.forName("GBK");
        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();
        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("可笑的样子");
        cBuf.flip();
        //编码
        ByteBuffer encode = ce.encode(cBuf);
        System.out.println(encode.capacity());

        encode.flip();
        CharBuffer decode = cd.decode(encode);
        System.out.println(decode.get(1));

    }

    /**
     * 分散和聚集
     */
    public static void test04() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("1.txt",
                "rw");

        // 1. 获取通道
        FileChannel channel1 = raf1.getChannel();
        // 2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(1024);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        // 3. 分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip();
        }
        System.out.println(channel1.size());
        // 聚焦写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);
    }

    /**
     * 通道之间的数据传输(直接缓冲区)
     */
    public void test03() {
        try {
            FileChannel inChannel = FileChannel
                    .open(Paths.get("D:\\picture\\lm1.jpg"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("D:\\picture\\1003.jpg"), StandardOpenOption.WRITE,
                    StandardOpenOption.READ, StandardOpenOption.CREATE);

            inChannel.transferTo(0, inChannel.size(), outChannel);

            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1.获取通道
    public static void test01() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fisChannel = null;
        FileChannel fosChannel = null;
        try {
            fis = new FileInputStream("D:\\picture\\lm1.jpg");
            fos = new FileOutputStream("D:\\picture\\2.jpg");

            // 1. 获取通道
            fisChannel = fis.getChannel();
            fosChannel = fos.getChannel();

            // 2.分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // 3.将通道中数据放到缓冲区中去
            while (fisChannel.read(buf) != -1) {
                buf.flip(); // 切换模式
                fosChannel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fosChannel != null) {
                try {
                    fosChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fisChannel != null) {
                try {
                    fisChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void test02() {
        try {
            FileChannel inChannel = FileChannel
                    .open(Paths.get("D:\\picture\\lm1.jpg"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("D:\\picture\\1003.jpg"), StandardOpenOption.WRITE,
                    StandardOpenOption.READ, StandardOpenOption.CREATE);

            // 内存映射文件
            MappedByteBuffer inMappedBuf = inChannel
                    .map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf = outChannel
                    .map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            // 直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);

            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        test05();
    }
}
