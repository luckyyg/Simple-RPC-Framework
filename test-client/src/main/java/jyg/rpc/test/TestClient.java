package jyg.rpc.test;

import jyg.rpc.api.HelloObject;
import jyg.rpc.api.HelloService;
import jyg.rpc.client.RpcClientProxy;

public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(14, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
