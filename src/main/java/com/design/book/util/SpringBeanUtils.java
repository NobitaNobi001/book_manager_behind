package com.design.book.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author ezuy
 * @date 21/6/3 20:55
 */
@Service
public class SpringBeanUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {

        if (context == null) {
            throw new IllegalStateException("spring 环境没有启动！");
        }
        return context.getBean(requiredType);
    }
}