package com.sws.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mengyuan
 * @date 2022/8/25/3:16 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 自定义注解，声明使用：@interface
 * ----------------------
 * Retention():该注解保留到什么时候
 * RetentionPolicy.SOURCE：编译的时候使用
 * RetentionPolicy.CLASS：会保存到字节码中，但是运行的时候看不到
 * RetentionPolicy.RUNTIME：运行的时候也可以看到
 * ----------------------
 * Target()：该注解可以用在什么地方
 * ElementType.FIELD：可以用到变量上
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface MyBindView {

    int value();
}
