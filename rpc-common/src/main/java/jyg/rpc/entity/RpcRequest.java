package jyg.rpc.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RpcRequest implements Serializable {

    /**
     *请求内容包括：调用接口名称；调用方法名称；调用方法参数；调用方法的参数类型
     */
    private String interfaceName;

    private String methodName;

    private Object[] parameters;

    private Class<?>[] paramTypes;
}
