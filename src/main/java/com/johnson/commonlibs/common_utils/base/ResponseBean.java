package com.johnson.commonlibs.common_utils.base;

import java.io.Serializable;

/**
 * Created by shefenfei on 15/12/22.<br/>
 * 实体bean的基类
 *
 * @author shefenfei
 * @version 1.0
 * @date 15/12/22
 */
public class ResponseBean<T extends BaseBean & Serializable> {
    T result;
}
