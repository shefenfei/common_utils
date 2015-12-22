package com.johnson.commonlibs.common_utils.animations;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by shefenfei on 15/12/22.<br/>
 *
 * @author shefenfei
 * @version 1.0
 * @date 15/12/22
 */
public class AnimUtils {

    /**
     * 产生旋转动画
     *
     * @param view
     */
    public static void rotation3D(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", 0f, 180f);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.start();
    }
}
