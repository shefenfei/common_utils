package com.johnson.commonlibs.common_utils.api;

import com.squareup.okhttp.ResponseBody;
import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Created by shefenfei on 15/12/1.<br/>
 * Error处理工具
 *
 * @author shefenfei
 * @version 1.0
 * @date 15/12/1
 */
public class ErrorUtils {

    public static APIError parseError(Response<?> response, Retrofit retrofit) {
        Converter<ResponseBody, APIError> converter = retrofit.responseConverter(APIError.class, new Annotation[0]);
        APIError apiError;
        try {
            apiError = converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
            return new APIError();
        }
        return apiError;
    }
}
