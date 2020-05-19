package com.coderusk.speedcodesampleapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.coderusk.speedcode.app.BaseActivity;
import com.coderusk.speedcode.app.Fexter;
import com.coderusk.speedcode.app.Flowster;
import com.coderusk.speedcode.app.Loger;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends BaseActivity {
    Flowster.Action actionA = this::functionA;
    Flowster.Action actionB = this::functionB;
    Flowster.Action actionBy = this::functionBy;
    Flowster.Action actionBn = this::functionBn;
    Flowster.Action actionC = this::functionC;
    Flowster.Action actionD = this::functionD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onSetContentView() {
        setContentView(R.layout.activity_main);
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



    @Override
    public int getLoader_id() {
        return R.id.l_loader;
    }


    private void functionA(Flowster flowster)
    {
        Log.d("flowster_testing","from function A");
        Flowster.flowCase("next",flowster);
    }
    private int random(int min, int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    private void functionB(Flowster flowster)
    {
        Log.d("flowster_testing","from function B");
        int val = rand(10,99);
        Log.d("flowster_testing","val="+val);
        if(val%2==0)
        {
            Flowster.flowCase("yes",flowster);
        }
        else
        {
            Flowster.flowCase("no",flowster);
        }

    }

    private void functionBy(Flowster flowster)
    {
        Log.d("flowster_testing","from function By");
        Flowster.flowCase("next",flowster);
    }

    private void functionBn(Flowster flowster)
    {
        Log.d("flowster_testing","from function Bn");
        Flowster.flowCase("next",flowster);
    }

    private void functionC(Flowster flowster)
    {
        Log.d("flowster_testing","from function C");
        Flowster.flowCase("next",flowster);
    }

    private void functionD(Flowster flowster)
    {
        Log.d("flowster_testing","from function D");
        Flowster.flowCase("next",flowster);
    }

    @Override
    protected void onCreateTasks() {



        findViewById(R.id.bt_execute).setOnClickListener(v -> Flowster
                .create()
                .add(actionA).onNext(actionB)
                .add(actionB).onYes(actionBy).onNo(actionBn)
                .add(actionBy).onNext(actionC)
                .add(actionC).onNext(actionBn)
                .add(actionBn).onNext(actionD)
                .execute());
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
