package com.coderusk.speedcodesampleapp;

import android.os.Bundle;

import com.coderusk.speedcode.app.BaseActivity;
import com.coderusk.speedcode.app.Fexter;
import com.coderusk.speedcode.app.Loger;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fexter
                .create()
                .add("A",this::functionA,"B")
                .add("B",this::functionB,"By","Bn")
                .add("By",this::functionBy,"C")
                .add("Bn",this::functionBn,"D")
                .add("C",this::functionC,"Bn")
                .add("D",this::functionD)
                .execute();


    }
    /**
        D
        |
        Bn--
        |   \
        n    \
        |     \
      A-B-y-By-C
     **/

    private int rand(int min, int max)
    {
        return (int)(Math.random() * (max - min + 1) + min);
    }

    private void functionA(Fexter fexter)
    {
        Loger.log("fexter_testing=functionA");
        Fexter.fexec(fexter);
    }

    private void functionB(Fexter fexter)
    {
        int value = rand(10,99);
        Loger.log("fexter_testing=functionB,value="+value);
        if(value%2==0)
        {
            Fexter.fexec_y(fexter);
        }
        else
        {
            Fexter.fexec_n(fexter);
        }
    }

    private void functionBy(Fexter fexter)
    {
        Loger.log("fexter_testing=functionBy");
        Fexter.fexec(fexter);
    }

    private void functionBn(Fexter fexter)
    {
        Loger.log("fexter_testing=functionBn");
        Fexter.fexec(fexter);
    }

    private void functionC(Fexter fexter)
    {
        Loger.log("fexter_testing=functionC");
        Fexter.fexec(fexter);
    }

    private void functionD(Fexter fexter)
    {
        Loger.log("fexter_testing=functionD");
        Fexter.fexec(fexter);
    }

    @Override
    public int getLoader_id() {
        return R.id.l_loader;
    }

    @Override
    protected void onCreateActions() {

    }

    @Override
    protected void onCreateTasks() {

    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setViewsData() {

    }

    @Override
    protected void setViewActions() {

    }

    @Override
    protected void onStartTasks() {

    }
}
