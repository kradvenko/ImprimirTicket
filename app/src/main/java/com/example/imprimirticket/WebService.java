package com.example.imprimirticket;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WebService extends AsyncTask<Void, Void, String> {

    ProgressDialog progressDialog;
    Context context;
    String url="https://fbdb69cd5e14.ngrok.io/graphql";
    //String url="http://192.168.0.101:3003/graphql";
    String postData;

    int bandera;
    int u2;
    public WebService(Context context, String postData,int bandera) {
        this.context = context;
        this.postData = postData;

        this.bandera=bandera;
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
        try {
            if(bandera==1){
                progressDialog = progressDialog.show(context, "Iniciando Sesion", "Por favor espere...");
            }
            if(bandera==3){
                progressDialog = progressDialog.show(context, "Abriendo caja", "Por favor espere...");
            }
            if(bandera==4){
                progressDialog = progressDialog.show(context, "Obteniendo sus productos", "Por favor espere...");
            }
            if(bandera==8){
                progressDialog = progressDialog.show(context, "Cerrando caja", "Por favor espere...");
            }
        }catch (Exception e){

        }




    }

    @Override
    protected String doInBackground(Void... voids) {
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("query", postData);


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            // Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        // 11. return result
        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(bandera==1 || bandera==3 || bandera==4 || bandera==8){
            progressDialog.dismiss();
            //Toast.makeText(context,"bandera: "+bandera,Toast.LENGTH_LONG).show();

        }

        //Toast.makeText(context,"res:\n"+s,Toast.LENGTH_LONG).show();
        try {

            if (bandera==1){//login
                JSONObject jsonObject = new JSONObject(s);
                JSONObject data = new JSONObject(jsonObject.getString("data"));
                JSONObject data2 = new JSONObject(data.getString("login"));
                JSONObject data3=new JSONObject(data2.getString("IdSucursal"));
                String usu=null;
                usu=data2.getString("Nombre");
                if(usu.equals("") || usu==null){
                    Toast.makeText(context,"Usuario o Contraseña incorrectos",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"Bienvenido "+data2.getString("Nombre"),Toast.LENGTH_LONG).show();
                    SharedPreferences preferences=context.getSharedPreferences("loguin",context.MODE_PRIVATE);
                    preferences.edit().putString("_id",data2.getString("_id")).apply();
                    preferences.edit().putString("nombre_del_cajero",data2.getString("Nombre")).apply();
                    preferences.edit().putString("_id_de_sucursal",data3.getString("_id")).apply();
                    preferences.edit().putString("nombre_de_sucursal",data3.getString("Nombre")).apply();


                    //Intent intent =new Intent(context,AbrirCaja.class);

                    //context.startActivity(intent);

                }
            }
            if (bandera==2){//numero de cantidad de abrir caja
                JSONObject jsonObject = new JSONObject(s);
                JSONObject data = new JSONObject(jsonObject.getString("data"));
                JSONArray pv_abrir_caja = new JSONArray(data.getString("pv_abrir_caja"));
                int ul=pv_abrir_caja.length();

                SharedPreferences preferences=context.getSharedPreferences("loguin",context.MODE_PRIVATE);
                preferences.edit().putString("abrcaja",ul+"").apply();
                //Toast.makeText(context,ul+"",Toast.LENGTH_LONG).show();
                //Toast.makeText(context,"entro :) bandera=2",Toast.LENGTH_LONG).show();
            }
            if(bandera==3){//abrir caja
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject data = new JSONObject(jsonObject.getString("data"));
                    JSONObject datos = new JSONObject(data.getString("insert_pv_abrir_caja"));
                    //Toast.makeText(context,datos.toString(),Toast.LENGTH_LONG).show();
                    SharedPreferences preferences=context.getSharedPreferences("loguin",context.MODE_PRIVATE);
                    preferences.edit().putString("_id_del_numero_abierto_de_caja",datos.getString("_id")).apply();
                }catch (Exception eee){
                    Toast.makeText(context,eee.getMessage(),Toast.LENGTH_LONG).show();
                }
                //Intent intent =new Intent(context,Vender.class);
                //context.startActivity(intent);


                /*
                String mid=datos.getString("_id");
                SharedPreferences preferences=context.getSharedPreferences("loguin",context.MODE_PRIVATE);
                preferences.edit().putString("idventa",mid).apply();
                this.storage.set('totaldeventas', this.efectivo);
                this.storage.set('turno',this.turno);
                this.storage.set('idventa',vec._id)
                */
            }

            if(bandera==5){
                Toast.makeText(context,"Pedido realizado con exito",Toast.LENGTH_LONG).show();
            }

            if(bandera==8){
                Intent intent =new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }


        }catch (JSONException err){
            Toast.makeText(context,err.getMessage(),Toast.LENGTH_LONG).show();
        }

    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}