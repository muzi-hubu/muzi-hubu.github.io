package com.company.exception;

/**
 * 自定义异常类:数字超出范围
 */
public class OutNumberBoundException extends Throwable {
    public OutNumberBoundException(String s) {
        super(s);
    }
}
