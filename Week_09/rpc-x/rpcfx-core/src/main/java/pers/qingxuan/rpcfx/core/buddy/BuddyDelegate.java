package pers.qingxuan.rpcfx.core.buddy;

import com.google.gson.Gson;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import pers.qingxuan.rpcfx.core.api.RpcfxException;
import pers.qingxuan.rpcfx.core.api.RpcfxRequest;
import pers.qingxuan.rpcfx.core.api.RpcfxResponse;
import pers.qingxuan.rpcfx.core.json.ParameterizedTypeImpl;
import pers.qingxuan.rpcfx.core.net.HttpClient;
import pers.qingxuan.rpcfx.core.net.NettyHttpClient;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * <p> byte-buddy 增强
 *
 * @author : QingXuan
 * @since Created in 下午7:44 2020/12/17
 */
public class BuddyDelegate {
    private static final HttpClient HTTPCLIENT;
    private static final Gson GSON;
    private static final ThreadLocal<String> urlLocal;

    static {
        HTTPCLIENT = new NettyHttpClient();
        GSON = new Gson();
        urlLocal = new ThreadLocal<>();
    }

    @IgnoreForBinding
    public static void setUrl(String url) {
        urlLocal.set(url);
    }

    @IgnoreForBinding
    public static void delUrl() {
        urlLocal.remove();
    }

    @RuntimeType
    public static Object invoke(@AllArguments Object[] params, @Origin Method method) throws Exception {
        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(method.getDeclaringClass().getName());
        request.setMethod(method.getName());
        request.setParams(params);
        if (params != null) {
            String[] paramTypes = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                paramTypes[i] = params[i].getClass().getName();
            }
            request.setParamTypes(paramTypes);
        } else {
            request.setParamTypes(new String[0]);
        }

        String result = HTTPCLIENT.call(urlLocal.get(), request);
        Type type = new ParameterizedTypeImpl(RpcfxResponse.class, new Type[]{method.getReturnType()});

        RpcfxResponse<?> data = GSON.fromJson(result, type);
        if (!data.isStatus()) {
            throw new RpcfxException(data.getException());
        }
        return data.getResult();
    }
}
