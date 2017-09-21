package com.example.lhadj.timecounter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.widget.Button;

/**
 * Created by lhadj on 11/21/2016.
 */

public class Couneter extends Thread {
    int sec,min,hr;
    static int hours,minuts,seconds;
    Button counter;
    ConstraintLayout ctl;
    Handler hn;
    public Couneter(int houers,int minuts,int seconds, Button counter,ConstraintLayout ctl) {
        sec=seconds;
        min=minuts;
        hr=houers;
        this.ctl = ctl;
        this.counter=counter;
        hn= new Handler();
    }
    @Override
    public void run() {
        try {
        for(hours=hr;hours>=0;hours--){
            for(minuts=Math.abs(60-(min+60));minuts>=0;minuts--){
                for(seconds=Math.abs(60-(sec+60));seconds>=0;seconds--){
                    sleep(970);
                    hn.post(new Runnable() {
                        @Override
                        public void run() {
                            if(hours<0){
                                hours=0;
                            }
                            if(minuts<0){
                                minuts=0;
                            }
                            if(seconds<0){
                                seconds=0;
                            }
                            String timeToDisplay=(hours)+" : "+(minuts)+" : "+(seconds);
                            counter.setText(timeToDisplay);
                            if(hours==0 && minuts==0 && seconds<10){
                 //  ctl.setSupportButtonTintList(ContextCompat.getColorStateList(Activity.this, R.color.colorPrimary));
                                ObjectAnimator colorFade = ObjectAnimator.ofObject(ctl, "setSupportButtonTintList", new ArgbEvaluator(), 0xffFFFFFF, 0xffFA947D);
                                    colorFade.setDuration(450);
                                    colorFade.start();
                                    colorFade = ObjectAnimator.ofObject(ctl, "backgroundColor", new ArgbEvaluator(), 0xffFA947D, 0xffFFFFFF);
                                    colorFade.setDuration(450);
                                    colorFade.start();
                            }
                        }
                    });
                }
                sec=59;
            }
            min=59;
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
