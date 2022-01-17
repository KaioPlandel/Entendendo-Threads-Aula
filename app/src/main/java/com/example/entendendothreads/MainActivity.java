package com.example.entendendothreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   private Button buttonIniciar;
   private int contador = 0;
   private Handler handler = new Handler();
   private boolean pararExecucao = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonIniciar = findViewById(R.id.buttonIniciar);

    }

    public void iniciarThread(View view){
//        MyThread myThread = new MyThread();
//        myThread.start();

        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            pararExecucao = false;
            for(int i = 0; i <= 15; i++){
                if (pararExecucao)
                    return;


                contador = i;
                Log.d("TAG", "iniciarThread: " + contador);


//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        buttonIniciar.setText("Contador " + contador);
//                    }
//                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        buttonIniciar.setText("Contador " + contador);
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void pararThread(View view){
        pararExecucao = true;
    }

    class MyThread extends Thread{
//        @Override
//        public void run() {
//            super.run();
//            for(int i = 0; i <= 15; i++){
//                Log.d("TAG", "iniciarThread: " + i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }


}