package jyg.rpc.server;

import jyg.rpc.entity.RpcRequest;
import jyg.rpc.entity.RpcResponse;
import jyg.rpc.enumeration.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class WorkThread implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(WorkThread.class);
    private Socket socket;
    private Object service;

    public  WorkThread(Socket socket,Object service){
        this.socket = socket;
        this.service = service;
    }
    @Override
    public void run() {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest)objectInputStream.readObject();
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            Object res = method.invoke(service, rpcRequest.getParameters());
            RpcResponse<Object> rpcResponse = RpcResponse.success(res);
            objectOutputStream.writeObject(rpcResponse);
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException|InvocationTargetException | IllegalAccessException e) {
            logger.error("线程执行错误");
            e.printStackTrace();
        }
    }
}
