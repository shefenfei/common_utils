package com.johnson.commonlibs.common_utils.api;

/**
 * Created by shefenfei on 15/12/1.<br/>
 * Error信息
 *
 * @author shefenfei
 * @version 1.0
 * @date 15/12/1
 */
public class APIError {
    private int statusCode;
    private String message;

    public APIError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
