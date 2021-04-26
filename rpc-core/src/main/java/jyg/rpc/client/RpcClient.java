package jyg.rpc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jyg.rpc.entity.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * rpc（远程方法调用）客户端
 * @author luckyyg
 */
public class RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    public Object sendRequest(RpcRequest rpcRequest,String host,int port){
        //放入try括号中，临时变量，自动关闭，快速回收
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("发送时出错",e);
            e.printStackTrace();
            return null;
        }
    }
}
