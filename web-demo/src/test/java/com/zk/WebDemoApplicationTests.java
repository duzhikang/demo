package com.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebDemoApplicationTests {


	// 客户端
	@Test
	public void contextLoads() throws IOException {
		//1.获取通道
		SocketChannel socketChannel = SocketChannel
				.open(new InetSocketAddress("127.0.0.1", 8099));


		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

		//2.分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//3.读取本地文件，并发送到服务器
		while (inChannel.read(buffer) != -1) {
			buffer.flip();
			socketChannel.write(buffer);
			buffer.clear();
		}

		//关闭通道
		inChannel.close();
		socketChannel.close();

	}

	// 服务端
	@Test
	public void service() throws IOException {
		// 1.获取通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"),
				StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		//2.绑定端口号
		serverSocketChannel.bind(new InetSocketAddress(8099));

		System.out.println("*****start");
		//3.获取客户端连接的通道
		SocketChannel socketChannel = serverSocketChannel.accept();
		System.out.println("*****end");
		ByteBuffer buf = ByteBuffer.allocate(1024);
		//4.接受客户端的数据，并保存到本地
		while(socketChannel.read(buf) != -1) {
			buf.flip();
			outChannel.write(buf);
			buf.clear();
		}

		//5.关闭
		socketChannel.close();
		outChannel.close();
		serverSocketChannel.close();
	}

	@Test
	public void client02() throws IOException {
		//1.获取通道
		SocketChannel socketChannel = SocketChannel
				.open(new InetSocketAddress("127.0.0.1", 8099));


		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

		//2.分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//3.读取本地文件，并发送到服务器
		while (inChannel.read(buffer) != -1) {
			buffer.flip();
			socketChannel.write(buffer);
			buffer.clear();
		}

		socketChannel.shutdownOutput();

		// 接受响应
		int len = 0;
		while ( (len = socketChannel.read(buffer)) != -1) {
			buffer.flip();
			System.out.println(new String(buffer.array(), 0, len));
			buffer.clear();
		}

		//关闭通道
		inChannel.close();
		socketChannel.close();

	}

	@Test
	public void service02() throws IOException {
		// 1.获取通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"),
				StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		//2.绑定端口号
		serverSocketChannel.bind(new InetSocketAddress(8099));

		System.out.println("*****start");
		//3.获取客户端连接的通道
		SocketChannel socketChannel = serverSocketChannel.accept();
		System.out.println("*****end");
		ByteBuffer buf = ByteBuffer.allocate(1024);
		//4.接受客户端的数据，并保存到本地
		while(socketChannel.read(buf) != -1) {
			buf.flip();
			outChannel.write(buf);
			buf.clear();
		}
		
		//5.发送数据给客户端
		buf.put("success!!!".getBytes());
		buf.flip();
		socketChannel.write(buf);

		//6.关闭
		socketChannel.close();
		outChannel.close();
		serverSocketChannel.close();
	}

	@Test
	public void test() {
		Scanner scanner = new Scanner(System.in);
		scanner.hasNext();
		System.out.println(scanner.next());
	}

	//非阻塞是io
	@Test
	public void client03() throws IOException, InterruptedException {
		//1.获取通道
		SocketChannel socketChannel = SocketChannel
				.open(new InetSocketAddress("127.0.0.1", 8099));
		//2.切换为非阻塞模式
		socketChannel.configureBlocking(false);
		//3.分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		//4.发送信息到服务端
		Scanner scanner = new Scanner(System.in);
		int i = 0;
		while (scanner.hasNext()) {
			Thread.sleep(1000);
			buf.put((new Date().toString() + "\n" + i + scanner.next()).getBytes());
			buf.flip();
			socketChannel.write(buf);
			buf.clear();
		}
		//5.关闭
		socketChannel.close();
	}

	@Test
	public void server03() throws IOException {
        // 1.获取通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		//2.切换为非阻塞模式
		serverSocketChannel.configureBlocking(false);
		//3.绑定端口号
		serverSocketChannel.bind(new InetSocketAddress(8099));
		//4.获取选择器
		Selector selector = Selector.open();
		//5.将通道注册到选择器上,ops:事件类型,指定监听事件
		//1 读 4 写 8 连接 16 接受
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		//6.顺序式获取选择器上已经准备就绪的事件
		while (selector.select()>0) {
			System.out.println("select:" + selector.select());
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			//7.获取当前选择器中所有注册的“选择键（监听事件）”
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				System.out.println("selectionKey:" + selectionKey);
				//8.获取准备“就绪”的事件
				if (selectionKey.isAcceptable()) {
					// 9.接受就绪,获取客户端连接
					SocketChannel socketChannel = serverSocketChannel.accept();
					// 10切换非阻塞模式
					socketChannel.configureBlocking(false);
					// 11.将该通道注册到选择器上
					socketChannel.register(selector, SelectionKey.OP_READ);
				}else if (selectionKey.isReadable()) {
					//12.获取当前选择器上“读就绪”状态的通道
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					//13.读取数据
					ByteBuffer buf = ByteBuffer.allocate(1024);
					int len = 0;
					while ((len = socketChannel.read(buf)) != -1) {
						buf.flip();
						System.out.println(new String(buf.array(), 0 , len));
					    buf.clear();
					}
				}
				// 取消选择键selectKey

				iterator.remove();
			}
		}
	}

}
