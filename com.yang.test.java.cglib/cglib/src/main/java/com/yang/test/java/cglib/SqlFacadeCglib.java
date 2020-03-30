package com.yang.test.java.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class SqlFacadeCglib implements MethodInterceptor {

    /**
     * 被代理的对象
     */
    private Object target;

    public SqlFacadeCglib(Object target) {
        this.target = target;
    }

    /**
     *  实现回调方法
     * @param obj 代理的对象
     * @param method 被代理对象的方法
     * @param args  参数集合
     * @param proxy 生成的代理类的方法
     * @return
     * @throws Throwable
     */
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 开始执行时间
        Long startTime = System.currentTimeMillis();
        // //调用业务类（父类中）的方法
        Object result = proxy.invokeSuper(obj, args);
        // 执行结束
        Long endTime = System.currentTimeMillis();
        System.out.println(target.getClass().getName()+"执行executeSql耗时"+(endTime-startTime)+"ms");
        return result;
    }
}