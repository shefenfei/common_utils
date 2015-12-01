package com.johnson.commonlibs.common_utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.johnson.commonlibs.common_utils.annotations.InjectView;

import java.lang.reflect.Field;

/**
 * Created by shefenfei on 15/10/9.<br/>
 * Version 1.0 <br/>
 * Description project base Activity
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * setting layout if you wanna use anno
     *
     * @param layoutId layoutId
     */
    public void setInjectContentView(int layoutId) {
        setContentView(layoutId);
        injectView();
    }

    private void injectView() {
        Class<? extends BaseActivity> aClass = getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView injectView = field.getAnnotation(InjectView.class);
                int viewId = injectView.id();
                if (viewId > 0) {
                    try {
                        field.setAccessible(true);
                        field.set(this, findViewById(viewId));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
