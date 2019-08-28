package com.servi.cloud.consumer.socket.nio;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.service();
    }

    private Selector selector = null;

    private ServerSocketChannel serverSocketChannel = null;
    private int port = 8000;
    private Charset charset = Charset.forName("utf-8");

    public NIOServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        ServiLogger.log("NIO服务器启动");
    }

    public void service() throws IOException {
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Set readyKeys = selector.keys();
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = null;
                try {
                    key = (SelectionKey) it.next();
                    it.remove();

                    if (key.isAcceptable()) {

                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = ssc.accept();
                        ServiLogger.log("接收到来自客户端的请求：" + socketChannel.socket().getInetAddress() + ":" + socketChannel.socket().getPort());
                        socketChannel.configureBlocking(false);
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, buffer);
                    }

                    if (key.isReadable()) {
                        receive(key);
                    }

                    if (key.isWritable()) {
                        send(key);
                    }
                } catch (Exception e) {
                    try {
                        if (key != null) {
                            key.cancel();
                            key.channel().close();
                        }
                    } catch (Exception ee) {
                    }
                }
            }
        }
    }

    public void send(SelectionKey key) throws IOException {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.flip();
        String data = decode(buffer);
        if (data.indexOf("\r\n") == -1) {
            return;
        }
        String outputdata = data.substring(0, data.indexOf("\n") + 1);
        ServiLogger.log("threaid:" + Thread.currentThread().getId() + " outputdata:" + outputdata);
        ByteBuffer outputbuffer = encode(outputdata);
        buffer.position(outputbuffer.limit());
        buffer.compact();
        if (outputdata.equals("bye\r\n")) {
            key.cancel();
            socketChannel.close();
            ServiLogger.log("关闭与客户端的链接。");
        }
    }

    public void receive(SelectionKey key) throws IOException {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer readerBuff = ByteBuffer.allocate(32);
        socketChannel.read(readerBuff);
        readerBuff.flip();
        buffer.limit(buffer.capacity());
        buffer.put(readerBuff);

    }

    private String decode(ByteBuffer buffer) {
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }

    private ByteBuffer encode(String str) {
        return charset.encode(str);
    }
}
