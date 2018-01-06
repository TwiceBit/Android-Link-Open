package de.TwiceBitDev.AndroidLinkOpen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kevin.androidlinkopen.R;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.button2);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    send();
                }
            });




    }

    private void send(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                EditText ip = findViewById(R.id.ip_feld);
                EditText port = findViewById(R.id.port_feld);
                EditText url = findViewById(R.id.link_feld);
               if(!ip.getText().toString().isEmpty() && !port.getText().toString().isEmpty() && !url.getText().toString().isEmpty()){
                    String addr = ip.getText().toString();
                    int addrport = Integer.valueOf(port.getText().toString());
                    String link = url.getText().toString();
                    try {

                        Socket  client = new Socket(addr, addrport);
                        PrintWriter w = new PrintWriter(client.getOutputStream());
                        w.write(link);
                        w.flush();
                        w.close();
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }


}
