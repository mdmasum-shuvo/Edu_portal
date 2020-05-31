package com.masum.edu_portal.utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;

import com.masum.edu_portal.R;

public class AnimationUtility {

    public static Animation getFadeInAnimation(Context context){
        if (context != null){
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
            animation.setDuration(1300);
            return animation;
        }
        return null;
    }

    public static Animation getSlideLefAnimation(Context context){
        if (context != null){
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_right);
            animation.setDuration(1300);
            return animation;
        }
        return null;
    }

    public static Animation getSlideRightAnimation(Context context){
        if (context != null){
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_left);
            animation.setDuration(1300);
            return animation;
        }
        return null;
    }

    public static Animation getSlideUpAnimation(Context context){
        if (context != null){
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            animation.setDuration(1300);
            return animation;
        }
        return null;
    }

    public static Animation getSlideBottomAnimation(Context context){
        if (context != null){
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_bottom);
            animation.setDuration(1300);
            return animation;
        }
        return null;
    }
    public static ScaleAnimation getBubbleAnimation(){
        ScaleAnimation bubble = new ScaleAnimation(0, 1, 0, 1
                , ScaleAnimation.RELATIVE_TO_SELF
                , .5f, ScaleAnimation.RELATIVE_TO_SELF
                , .5f);
        bubble.setDuration(400);
        bubble.setInterpolator(new OvershootInterpolator());
        return bubble;
    }
}
