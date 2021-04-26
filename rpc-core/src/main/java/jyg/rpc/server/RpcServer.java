package jyg.rpc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class RpcServer {
    private final ExecutorService threadPool;
    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);
    public RpcServer() {
        int corePoolSize = 5;
        int maximumPoolSize = 50;
        long keepAliveTime = 60;
        ArrayBlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit.SECONDS,workingQueue);
    }

    public void register(Object service,int port){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("服务器已启动");
            Socket socket;
            while((socket = serverSocket.accept())!=null){
                logger.info("客户端连接。ip："+socket.getInetAddress());
                System.out.println(socket.getInetAddress());
                threadPool.execute(new WorkThread(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


