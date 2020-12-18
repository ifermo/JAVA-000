package pers.qingxuan.rpcfx.core.buddy;

import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;

/**
 * <p> 代理类命名
 *
 * @author : QingXuan
 * @since Created in 上午11:28 2020/12/18
 */
public class RpcNamingStrategy extends NamingStrategy.AbstractBase {
    /**
     * Determines a new name when creating a new type that subclasses the provided type.
     *
     * @param superClass The super type of the created type.
     * @return The name of the dynamic type.
     */
    @Override
    protected String name(TypeDescription superClass) {
        return superClass.getName() + "Rpc";
    }
}
