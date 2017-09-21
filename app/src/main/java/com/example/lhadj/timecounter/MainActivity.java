package com.example.lhadj.timecounter;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText minuts;
    EditText houers;
    EditText seconds;
    Button timer;
    Button start;
    Button stop;
    Couneter cnt=null;
    ConstraintLayout main;
    Runnable rn;
    int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minuts = (EditText)findViewById(R.id.Minutes);
        houers = (EditText)findViewById(R.id.Hours);
        seconds = (EditText)findViewById(R.id.Seconds);
        start = (Button)findViewById(R.id.start);
        timer = (Button)findViewById(R.id.Timer);
        stop = (Button)findViewById(R.id.pause);
        main =(ConstraintLayout)findViewById(R.id.activity_main);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Shours=Integer.parseInt(houers.getText().toString().equals("")?"0":houers.getText().toString());
                int Sminuts=Integer.parseInt(minuts.getText().toString().equals("")?"0":minuts.getText().toString());
                int Sseconds=Integer.parseInt(seconds.getText().toString().equals("")?"0":seconds.getText().toString());
                if(Shours==0 && Sminuts==0 && Sseconds==0){
                    Toast.makeText(MainActivity.this,"No  Time added !",Toast.LENGTH_SHORT).show();
                }
                if(Sseconds>59){
                    Sseconds=60;
                    seconds.setText("59");
                }
                if(Sminuts>59){
                    Sminuts=60;
                    seconds.setText("59");
                }
                if (cnt != null) {
                    cnt.interrupt();
                }
                    cnt = new Couneter(Shours,Sminuts,Sseconds,timer,main);
                    cnt.setPriority(Thread.MAX_PRIORITY);
                    cnt.start();
    }
});
    }

}
