 package com.example.imprimirticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button btnCorte;
    Button btnSurtir;
    Button btnSurtir2;
    Button btnHistTickets;

    MyImpresora myImpresora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.usuarios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinnerTicket = (Spinner) findViewById(R.id.spinnerTicket);
        ArrayAdapter<CharSequence> adapterTicket = ArrayAdapter.createFromResource(this,
                R.array.tickets, android.R.layout.simple_spinner_item);
        adapterTicket.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTicket.setAdapter(adapterTicket);

        myImpresora = new MyImpresora(MainActivity.this);
        myImpresora.mUsbReceiver.onReceive(MainActivity.this,new Intent(USB_SERVICE));

        btn = (Button)findViewById(R.id.btnImprimir);
        btnCorte = (Button)findViewById(R.id.btnImprimirCorte);
        btnSurtir = (Button)findViewById(R.id.btnSuritr);
        btnSurtir2 = (Button)findViewById(R.id.btnSurtir2);
        btnHistTickets = (Button)findViewById(R.id.btnATickets);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String ticket = "";

                ticket+="\nTotal: " + "ACA\n";
                ticket+="Gracias por su preferencia!\n";
                ticket+="Pedidos al: \n";
                ticket+="www.fb.com/CerealesSocorrito\n";
                ticket+="Whatsapp: 311-394-42-33\n";

                myImpresora.call(ticket);
                */
                /*ImprimirTicketWeb tick = new ImprimirTicketWeb(){
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        Log.e("ERROR TICKET: ", s);
                    }
                };
                 */
                String usuario = spinner.getSelectedItem().toString();
                String ticketNum = spinnerTicket.getSelectedItem().toString();
                //Toast.makeText(MainActivity.this, "Usuario: " + usuario, Toast.LENGTH_SHORT).show();
                ImprimirTicketWeb tick = new ImprimirTicketWeb(usuario, ticketNum);

                tick.myImpresora = myImpresora;
                tick.execute();
                //myImpresora.call(ticket);
            }
        });


        btnCorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = spinner.getSelectedItem().toString();
                //Toast.makeText(MainActivity.this, "Usuario: " + usuario, Toast.LENGTH_SHORT).show();
                ImprimirCorteWeb tick = new ImprimirCorteWeb(usuario);

                tick.myImpresora = myImpresora;
                tick.execute();
                //myImpresora.call(ticket);
            }
        });

        btnSurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = spinner.getSelectedItem().toString();
                //Toast.makeText(MainActivity.this, "Usuario: " + usuario, Toast.LENGTH_SHORT).show();
                ImprimirSurtirDiarios tick = new ImprimirSurtirDiarios();

                tick.myImpresora = myImpresora;
                tick.execute();
                //myImpresora.call(ticket);
            }
        });

        btnSurtir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = spinner.getSelectedItem().toString();
                //Toast.makeText(MainActivity.this, "Usuario: " + usuario, Toast.LENGTH_SHORT).show();
                ImprimirSurtirDiarios2 tick = new ImprimirSurtirDiarios2();

                tick.myImpresora = myImpresora;
                tick.execute();
                //myImpresora.call(ticket);
            }
        });

        btnHistTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TicketsActivity.class);
                /*
                EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
                String message = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                */
                startActivity(intent);
            }
        });

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},0);
        }
/*
        wbSistema = (WebView)findViewById(R.id.wbSistema);
        wbSistema.loadUrl("https://www.fb.com");

 */

    }
}