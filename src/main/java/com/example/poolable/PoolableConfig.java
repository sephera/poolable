package com.example.poolable;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.AbstractPoolingTargetSource;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PoolableConfig {
    private static final String BOOK_TARGET_NAME = "bookTarget";

    /**
     * Returns the pooled bean.
     *
     * @return the pooled bean
     */
    @Bean(BOOK_TARGET_NAME)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Book bookTarget() {
        return new Book();
    }

    /**
     * Returns the pool.
     *
     * @return the pool
     */
    @Bean
    public TargetSource bookTargetSource(@Value("2") int maxSize) {
        final AbstractPoolingTargetSource poolingConfig = new CommonsPool2TargetSource();
        poolingConfig.setMaxSize(maxSize);
        poolingConfig.setTargetBeanName(BOOK_TARGET_NAME);
        return poolingConfig;
    }

    /**
     * Returns a ProxyFactoryBean that is correctly pooled.
     *
     * @return the proxy we will call
     */
    @Bean
    public ProxyFactoryBean bookProxyFactoryBean(TargetSource bookTargetSource) {
        ProxyFactoryBean proxyBean = new ProxyFactoryBean();
        proxyBean.setTargetSource(bookTargetSource);
        return proxyBean;
    }
}
