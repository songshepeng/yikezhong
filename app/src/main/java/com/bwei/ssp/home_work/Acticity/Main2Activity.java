package com.bwei.ssp.home_work.Acticity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bwei.ssp.home_work.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class Main2Activity extends AppCompatActivity {

    @InjectView(R.id.img_donghua)
    ImageView img_dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this);
        ObjectAnimator a1 = ObjectAnimator.ofFloat(img_dh, "translationX", 0f, 200f);
        ObjectAnimator a2 = ObjectAnimator.ofFloat(img_dh, "translationY", 0f, 200f);
        ObjectAnimator a3 = ObjectAnimator.ofFloat(img_dh, "translation", 0f, 200f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(a1, a2, a3);//同时执行
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


}
