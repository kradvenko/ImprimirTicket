package com.example.imprimirticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
    WebView wbSistema;

    MyImpresora myImpresora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImpresora=new MyImpresora(MainActivity.this);
        myImpresora.mUsbReceiver.onReceive(MainActivity.this,new Intent(USB_SERVICE));
        btn=(Button)findViewById(R.id.btnImprimir);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ticket = "";

                ticket+="\nTotal: " + "ACA\n";
                ticket+="Gracias por su preferencia!\n";
                ticket+="Pedidos al: \n";
                ticket+="www.fb.com/CerealesSocorrito\n";
                ticket+="Whatsapp: 311-394-42-33\n";

                myImpresora.call(ticket);
            }
        });

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},0);
        }

        wbSistema = (WebView)findViewById(R.id.wbSistema);
        wbSistema.loadUrl("https://www.fb.com");
    }
}