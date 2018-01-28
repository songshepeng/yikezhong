package com.bwei.ssp.myopen;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        ObjectAnimator a1= ObjectAnimator.ofFloat(img, "translationX", 0f,200f);
        ObjectAnimator a2=ObjectAnimator.ofFloat(img, "translationY", 0f,200f);
        ObjectAnimator a3=ObjectAnimator.ofFloat(img, "translation", 0f,200f);
        AnimatorSet set=new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(a1,a2,a3);//同时执行
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
//repeat=1 实际执行2次
       // anim.setRepeatCount(1);

//        anim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Toast.makeText(MainActivity.this,"结束了",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }
}
