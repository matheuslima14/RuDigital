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

public class TelaLogin extends AppCompatActivity {

    private EditText editMatriculaSiape, editSenha1;
    private Button btnLogar, btnRegistrar;
    private TextView txtCadastro;
    private OkHttpClient client;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        editMatriculaSiape = (EditText) findViewById(R.id.editMatriculaSiape);
        editSenha1 = (EditText) findViewById(R.id.editSenha1);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        txtCadastro = (TextView) findViewById(R.id.txtCadastro);

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreRecuperarSenha = new Intent(TelaLogin.this, TelaRecuperarSenha.class);
                startActivity(abreRecuperarSenha);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
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
                    client = new OkHttpClient();
                    getWebService();
                }
            }
        });
    }

    private void getWebService() {

        String matriculasiape = editMatriculaSiape.getText().toString();
        String senha = editSenha1.getText().toString();

        final Request request = new Request.Builder().url("http://192.168.0.101:802/appRUDigital/LoginUsuario.php?matriculasiape=" + matriculasiape + "&senha=" + senha).build();
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

                            usuario = json.fromJson(response.body().string(), Usuario.class);

                            if (usuario != null) {
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
                editMatriculaSiape.setText("");
                editSenha1.setText("");
                Intent telaMenu = new Intent(TelaLogin.this, TelaMenu.class);
                telaMenu.putExtra("matriculasiape", usuario.getMatriculaSiape());
                telaMenu.putExtra("nome", usuario.getNome());
                telaMenu.putExtra("email", usuario.getEmail());
                telaMenu.putExtra("senha", usuario.getSenha());
                telaMenu.putExtra("rg", usuario.getRg());
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
