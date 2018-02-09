package android.usuario.rudigital;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TelaApagarConta extends AppCompatActivity {

    private EditText edtSenha;
    private Button btnExcluir;
    private Button btnVoltar;
    private OkHttpClient client;
    Usuario usuario;
    Usuario usuarioretornado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_apagar_conta);

        usuario = new Usuario();
        Intent intent = getIntent();

        usuario.setMatriculaSiape(intent.getStringExtra("matriculasiape"));
        usuario.setNome(intent.getStringExtra("nome"));
        usuario.setEmail(intent.getStringExtra("email"));
        usuario.setSenha(intent.getStringExtra("senha"));
        usuario.setRg(intent.getStringExtra("rg"));

        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreConfiguracoes = new Intent(TelaApagarConta.this, TelaConfiguracoes.class);
                abreConfiguracoes.putExtra("matriculasiape", usuario.getMatriculaSiape());
                abreConfiguracoes.putExtra("nome", usuario.getNome());
                abreConfiguracoes.putExtra("email", usuario.getEmail());
                abreConfiguracoes.putExtra("senha", usuario.getSenha());
                abreConfiguracoes.putExtra("rg", usuario.getRg());
                TelaApagarConta.this.startActivity(abreConfiguracoes);
                TelaApagarConta.this.finish();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtSenha.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Insira sua Senha!", Toast.LENGTH_SHORT).show();
                }else{
                    client = new OkHttpClient();
                    getWebService();
                }
            }
        });
    }

    private void getWebService() {

        String matriculasiape = usuario.getMatriculaSiape().toString();
        String senha = edtSenha.getText().toString();

        final Request request = new Request.Builder().url("http://172.50.0.178:802/appRUDigital/ExcluirUsuario.php?matriculasiape=" + matriculasiape + "&senha=" + senha).build();
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

                            usuarioretornado = json.fromJson(response.body().string(), Usuario.class);

                            if (usuarioretornado != null) {
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
                edtSenha.setText("");
                Intent telaLogin = new Intent(TelaApagarConta.this, TelaLogin.class);
                startActivity(telaLogin);
                Toast.makeText(getBaseContext(), "Usuário deletado!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(getBaseContext(), "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                Toast.makeText(getBaseContext(), "Erro de comunicação!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
