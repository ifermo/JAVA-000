package pers.qingxuan.rpcfx.core.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import pers.qingxuan.rpcfx.core.api.RpcfxException;
import pers.qingxuan.rpcfx.core.api.RpcfxRequest;
import pers.qingxuan.rpcfx.core.api.RpcfxResolver;
import pers.qingxuan.rpcfx.core.api.RpcfxResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver) {
        this.resolver = resolver;
    }

    public RpcfxResponse<Object> invoke(RpcfxRequest request) {
        RpcfxResponse<Object> response = new RpcfxResponse<>();
        try {
            Class<?> clazz = Class.forName(request.getServiceClass());
            Object service = resolver.resolve(clazz);
            Method method = getDeclaredMethod(clazz, request);
            // Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams());
            response.setResult(result);
            response.setStatus(true);
            return response;
        } catch (Exception e) {
            // 3.Xstream
            e.printStackTrace();
            response.setException(new RpcfxException(e));
            response.setStatus(false);
            return response;
        }
    }

    private Method getDeclaredMethod(Class<?> clazz, RpcfxRequest request) throws ClassNotFoundException, NoSuchMethodException {
        Class<?>[] parameterTypes = getParameterTypes(request.getParamTypes());
        return clazz.getDeclaredMethod(request.getMethod(), parameterTypes);
    }

    private Class<?>[] getParameterTypes(String... paramTypeNames) throws ClassNotFoundException {
        Class<?>[] types = new Class<?>[paramTypeNames.length];
        for (int i = 0; i < paramTypeNames.length; i++) {
            types[i] = Class.forName(paramTypeNames[i]);
        }
        return types;
    }
}
