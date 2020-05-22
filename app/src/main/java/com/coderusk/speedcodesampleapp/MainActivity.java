package com.coderusk.speedcodesampleapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.coderusk.speedcode.app.BaseActivity;
import com.coderusk.speedcode.app.Fexter;
import com.coderusk.speedcode.app.Flower;
import com.coderusk.speedcode.app.Loger;
import com.coderusk.speedcode.app.Mav;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends BaseActivity {
    Flower.Action actionA = this::functionA;
    Flower.Action actionB = this::functionB;
    Flower.Action actionBy = this::functionBy;
    Flower.Action actionBn = this::functionBn;
    Flower.Action actionC = this::functionC;
    Flower.Action actionD = this::functionD;
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


    private void functionA(Flower Flower,Object ...args)
    {
        Log.d("Flower_testing","from function A");
        Flower.flowCase("next",Flower);
    }
    private int random(int min, int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    private void functionB(Flower Flower,Object ...args)
    {
        Log.d("Flower_testing","from function B");
        int val = rand(10,99);
        Log.d("Flower_testing","val="+val);
        if(val%2==0)
        {
            Flower.flowCase("yes",Flower,val);
        }
        else
        {
            Flower.flowCase("no",Flower,val);
        }

    }

    private void functionBy(Flower Flower,Object ...args)
    {
        int val = 0;
        if(args!=null)
        {
            if(args.length>0)
            {
                Object val1 = args[0];
                if(val1 instanceof Integer)
                {
                    val = (int)val1;
                }
            }
        }
        Log.d("Flower_testing","from function By"+" and the value is "+val);
        Flower.flowCase("next",Flower);
    }

    private void functionBn(Flower Flower,Object ...args)
    {
        Log.d("Flower_testing","from function Bn");
        Flower.flowCase("next",Flower);
    }

    private void functionC(Flower Flower,Object ...args)
    {
        Log.d("Flower_testing","from function C");
        Flower.flowCase("next",Flower);
    }

    private void functionD(Flower Flower,Object ...args)
    {
        Log.d("Flower_testing","from function D");
        Flower.flowCase("next",Flower);
    }

    @Override
    protected void onCreateTasks() {



        findViewById(R.id.bt_execute).setOnClickListener(v -> Flower
                .create()
                .add(actionA).onNext(actionB)
                .add(actionB).onYes(actionBy).onNo(actionBn)
                .add(actionBy).onNext(actionC)
                .add(actionC).onNext(actionBn)
                .add(actionBn).onNext(actionD)
                .execute());

        /*findViewById(R.id.bt_execute).setOnClickListener(
                v ->
        {
            int a = rand(10, 99);
            int b = rand(10, 99);
            int c = rand(10, 99);
            int d = rand(10, 99);

            boolean ret = Mav
                    .create()
                    .add(() -> b > a, () -> error("error1"))
                    .add(() -> c > b, () -> error("error2"))
                    .add(() -> d > c, () -> error("error3"))
                    .execute();

            Log.d("Mav_print",a+","+b+","+c+","+d+" " + (ret ? "true" : "false"));
        }
        );*/
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
