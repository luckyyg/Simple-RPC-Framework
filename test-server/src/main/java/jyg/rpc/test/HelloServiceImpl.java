package jyg.rpc.test;

import jyg.rpc.api.HelloObject;
import jyg.rpc.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceImpl implements HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    private String res = ":方法回调,执行结果为res";
    @Override
    public String hello(HelloObject object) {
        Integer id = object.getId();
        String message = object.getMessage();
        logger.info("接收：到{}",message);
        System.out.println(id+"----"+message);
        return id+res;
    }
}
