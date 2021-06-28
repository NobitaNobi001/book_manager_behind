package com.design.book.util.singleton;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.endpoint.EndpointImplFactory;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.support.JaxWsEndpointImplFactory;

import java.io.File;
import java.util.List;

/**
 * @author ezuy
 * @date 21/6/5 17:14
 */
public class JaxWsDynamicClientFactory extends DynamicClientFactory {

    private static volatile JaxWsDynamicClientFactory instance;

    protected JaxWsDynamicClientFactory(Bus bus) {
        super(bus);
    }

    @Override
    protected EndpointImplFactory getEndpointImplFactory() {
        return JaxWsEndpointImplFactory.getSingleton();
    }

    @Override
    protected boolean allowWrapperOps() {
        return true;
    }

    /**
     * Create a new instance using a default <tt>Bus</tt>.
     *
     * @return the new instance
     * @see CXFBusFactory#getDefaultBus()
     */
    public static JaxWsDynamicClientFactory newInstance() {

        if (instance == null) {
            synchronized (JaxWsDynamicClientFactory.class) {
                if (instance == null) {
                    Bus bus = CXFBusFactory.getThreadDefaultBus();
                    instance = new JaxWsDynamicClientFactory(bus);
                    return instance;
                }
            }
        }
        return instance;
    }

    /**
     * 覆写父类的该方法<br/>
     * 注：解决此（错误：编码GBK的不可映射字符）问题
     *
     * @return
     */
    @Override
    protected boolean compileJavaSrc(String classPath, List<File> srcList, String dest) {
        org.apache.cxf.common.util.Compiler javaCompiler
                = new org.apache.cxf.common.util.Compiler();

        // 设置编译编码格式（此处为新增代码）
        javaCompiler.setEncoding("UTF-8");

        javaCompiler.setClassPath(classPath);
        javaCompiler.setOutputDir(dest);
        javaCompiler.setTarget("1.8");

        return javaCompiler.compileFiles(srcList);
    }

}