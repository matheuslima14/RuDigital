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
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class TelaLogin extends AppCompatActivity {

    private EditText editMatriculaSiape, editSenha1;
    private Button btnLogar;
    private TextView txtCadastro;

    private OkHttpClient okHttpClient;
    private Request request;
    private String url;
    Usuario usuario;

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

                                url = "HTTP://172.19.6.31:802/appRUDigital/LoginUsuario.php?matriculasiape=" + matriculasiape + "&senha=" + senha;

                                okHttpClient = new OkHttpClient();
                                request = new Request.Builder().url(url).build();

                                Runnable r = new Runnable() {
                                    @Override
                                    public void run() {
                                        try {

                                            Response response = okHttpClient.newCall(request).execute();
                                            Log.i("MSG", response.body().string());

                                            Gson json = new Gson();
                                            usuario = json.fromJson(response.body().string(), Usuario.class);

                                            if (usuario != null) {
                                                handler.sendEmptyMessage(0);
                                            } else {
                                                handler.sendEmptyMessage(1);
                                            }

                                        } catch (Exception e) {
                                            handler.sendEmptyMessage(2);
                                        }
                                    }
                                };
                                Thread threadresposta = new Thread(r);
                                threadresposta.start();

                            } catch (Exception e) {
                                //e.toString();
                                handler.sendEmptyMessage(2);
                                //e.printStackTrace();
                            }
                        }
                    };
                    Thread threadLogar = new Thread(r);
                    threadLogar.start();
                }
            }
        });
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                Intent telaMenu = new Intent(TelaLogin.this, TelaMenu.class);
                startActivity(telaMenu);
                Toast.makeText(getBaseContext(), "Logado com Sucesso!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(getBaseContext(), "Usuário não Encontrado!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                Toast.makeText(getBaseContext(), "Erro ao Fazer Login!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}