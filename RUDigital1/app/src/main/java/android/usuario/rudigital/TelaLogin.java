package android.usuario.rudigital;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.lang.reflect.Type;
import java.util.List;

public class TelaLogin extends AppCompatActivity {

    private EditText editMatriculaSiape, editSenha1;
    private Button btnLogar;
    private TextView txtCadastro;

    private OkHttpClient okHttpClient;
    private Request request;
    private String url;
    List<Usuario> usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        editMatriculaSiape = (EditText) findViewById(R.id.editMatriculaSiape);
        editSenha1 = (EditText) findViewById(R.id.editSenha1);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        txtCadastro = (TextView) findViewById(R.id.txtCadastro);

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreCadastro = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(abreCadastro);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editMatriculaSiape.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "O campo matrícula/siape está vazio!", Toast.LENGTH_SHORT).show();
                } else if (editSenha1.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "O campo senha está vazio!", Toast.LENGTH_SHORT).show();
                } else {

                    Runnable r = new Runnable() {
                        @Override
                        public void run() {

                            try {

                                String matriculasiape = editMatriculaSiape.getText().toString();
                                String senha = editSenha1.getText().toString();

                                url = "HTTP://172.19.5.2:802/appRUDigital/LoginUsuario.php?matriculasiape=" + matriculasiape + "&senha=" + senha;

                                okHttpClient = new OkHttpClient();
                                request = new Request.Builder().url(url).build();

                                Response response = okHttpClient.newCall(request).execute();
                                Log.i("MSG", response.body().string());

                                Gson json = new Gson();

                                Type collectionType = new TypeToken<List<Usuario>>() {
                                }.getType();

                                usuario = json.fromJson(response.body().string(), collectionType);
                                handler.sendEmptyMessage(0);

                                if (usuario.get(0) != null) {
                                    Intent telaMenu = new Intent(TelaLogin.this, TelaMenu.class);
                                    startActivity(telaMenu);
                                } else {
                                    handler.sendEmptyMessage(0);
                                }

                            } catch (Exception e) {
                                handler.sendEmptyMessage(0);
                                e.printStackTrace();
                            }
                        }
                    };
                    Thread threadCadastrar = new Thread(r);
                    threadCadastrar.start();
                }
            }
        });
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getBaseContext(), "Erro ao Logar!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}