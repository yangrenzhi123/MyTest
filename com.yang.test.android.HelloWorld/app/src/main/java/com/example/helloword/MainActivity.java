package com.example.helloword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private  TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= (TextView) findViewById(R.id.tv1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("被点击了2");
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Socket request = new Socket("192.168.19.111", 8080);

                    SocketUtil.writeStr2Stream("F0001", request.getOutputStream());

                    while(true){
                        String got = SocketUtil.readStrFromStream(request.getInputStream());
                        System.out.print("Server111:"+got);
                    }
                }catch(Exception e){
                    System.out.print("异常了");
                }
            }
        }, "指令接收器").start();
    }
}
