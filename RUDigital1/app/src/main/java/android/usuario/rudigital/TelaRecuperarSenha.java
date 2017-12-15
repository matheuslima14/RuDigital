package android.usuario.rudigital;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TelaRecuperarSenha extends AppCompatActivity {

    private Button btnRecuperar;
    private Button btnCancelar;
    private EditText edtEmail;
    private TextView txtRecuperar;
    private OkHttpClient client;
    Usuario usuario;
    Usuario usuarioRetornado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_recuperar_senha);

        btnRecuperar = (Button) findViewById(R.id.btnRecuperar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        txtRecuperar = (TextView) findViewById(R.id.txtRecuperar);

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(edtEmail.getText().toString().isEmpty()){
                   Toast.makeText(getApplicationContext(), "Insira seu Email!", Toast.LENGTH_SHORT).show();
               }else {
                   client = new OkHttpClient();
                   getWebService();
               }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreLogin = new Intent(TelaRecuperarSenha.this, TelaLogin.class);
                startActivity(abreLogin);
            }
        });

    }

    private void getWebService() {

        final Request request = new Request.Builder().url("http://192.168.0.101:802/appRUDigital/RedefinirSenha.php?email=" + edtEmail.getText().toString()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            Gson json = new Gson();

                            usuarioRetornado = json.fromJson(response.body().string(), Usuario.class);

                            if (usuarioRetornado != null) {
                                handler.sendEmptyMessage(0);
                            } else {
                                handler.sendEmptyMessage(1);
                            }


                        } catch (IOException ioe) {
                            handler.sendEmptyMessage(2);
                        }
                    }
                });
            }
        });
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                edtEmail.setText("");
                Toast.makeText(getBaseContext(), "Email de recuperação enviado!", Toast.LENGTH_SHORT).show();
                Intent telaLogin = new Intent(TelaRecuperarSenha.this, TelaLogin.class);
                startActivity(telaLogin);
            } else if (msg.what == 1) {
                Toast.makeText(getBaseContext(), "Email não encontrado!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                Toast.makeText(getBaseContext(), "Falha de Comunicação!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
