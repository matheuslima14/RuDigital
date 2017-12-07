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

public class TelaConfiguracoes extends AppCompatActivity {

    private Button btnAtualizar;
    private Button btnVoltar;
    private Button btnApagarConta;
    private EditText editNome;
    private EditText editEmail;
    private EditText editRG;
    private EditText editMatriculaSiape;
    private EditText editSenhaNova;
    private EditText editConfirmaSenhaNova;
    private EditText edtSenha;
    private OkHttpClient client;
    Usuario usuario;
    Usuario usuarioretornado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_configuracoes);

        usuario = new Usuario();
        Intent intent = getIntent();

        usuario.setMatriculaSiape(intent.getStringExtra("matriculasiape"));
        usuario.setNome(intent.getStringExtra("nome"));
        usuario.setEmail(intent.getStringExtra("email"));
        usuario.setSenha(intent.getStringExtra("senha"));
        usuario.setRg(intent.getStringExtra("rg"));

        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnApagarConta = (Button) findViewById(R.id.btnApagarConta);
        editNome = (EditText) findViewById(R.id.editNome);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editRG = (EditText) findViewById(R.id.editRG);
        editMatriculaSiape = (EditText) findViewById(R.id.editMatriculaSiape);
        editSenhaNova = (EditText) findViewById(R.id.editSenhaNova);
        editConfirmaSenhaNova = (EditText) findViewById(R.id.editConfirmaSenhaNova);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        editNome.setText(usuario.getNome());
        editEmail.setText(usuario.getEmail());
        editRG.setText(usuario.getRg());
        editMatriculaSiape.setText(usuario.getMatriculaSiape());

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editNome.getText().toString().isEmpty() || editEmail.getText().toString().isEmpty() || editMatriculaSiape.getText().toString().isEmpty() || edtSenha.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Preencha os campos corretamente!", Toast.LENGTH_SHORT).show();
                } else if (editSenhaNova.length() > 0 || editConfirmaSenhaNova.length() > 0) {
                    if (!(editSenhaNova.getText().toString().equals(editConfirmaSenhaNova.getText().toString()))) {
                        Toast.makeText(getApplicationContext(), "Senhas não conferem!", Toast.LENGTH_SHORT).show();
                    } else if (editSenhaNova.length() < 8) {
                        Toast.makeText(getApplicationContext(), "Senha tem que ter no minímo 8 digitos!", Toast.LENGTH_SHORT).show();
                    } else {
                        client = new OkHttpClient();
                        getWebService();
                    }
                } else {
                    client = new OkHttpClient();
                    getWebService();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent abreMenu = new Intent(TelaConfiguracoes.this, TelaMenu.class);
                abreMenu.putExtra("matriculasiape", usuario.getMatriculaSiape());
                abreMenu.putExtra("nome", usuario.getNome());
                abreMenu.putExtra("email", usuario.getEmail());
                abreMenu.putExtra("senha", usuario.getSenha());
                abreMenu.putExtra("rg", usuario.getRg());
                TelaConfiguracoes.this.startActivity(abreMenu);
                TelaConfiguracoes.this.finish();
            }
        });

        btnApagarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreApagarConta = new Intent(TelaConfiguracoes.this, TelaApagarConta.class);
                abreApagarConta.putExtra("matriculasiape", usuario.getMatriculaSiape());
                abreApagarConta.putExtra("nome", usuario.getNome());
                abreApagarConta.putExtra("email", usuario.getEmail());
                abreApagarConta.putExtra("senha", usuario.getSenha());
                abreApagarConta.putExtra("rg", usuario.getRg());
                TelaConfiguracoes.this.startActivity(abreApagarConta);
                TelaConfiguracoes.this.finish();
            }
        });
    }

    private void getWebService() {

        final Request request = new Request.Builder().url("http://pedrofjduarte.000webhostapp.com/appRUDigital/AlterarUsuario.php?nome=" + editNome.getText().toString() + "&email=" + editEmail.getText().toString() + "&matriculasiape=" + editMatriculaSiape.getText().toString() + "&senha=" + edtSenha.getText().toString() + "&novasenha=" + editSenhaNova.getText().toString() + "&matriculasiapeatual=" + usuario.getMatriculaSiape().toString()).build();
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
                Intent telaMenu = new Intent(TelaConfiguracoes.this, TelaMenu.class);
                telaMenu.putExtra("matriculasiape", usuarioretornado.getMatriculaSiape());
                telaMenu.putExtra("nome", usuarioretornado.getNome());
                telaMenu.putExtra("email", usuarioretornado.getEmail());
                telaMenu.putExtra("senha", usuarioretornado.getSenha());
                telaMenu.putExtra("rg", usuario.getRg());
                startActivity(telaMenu);
                Toast.makeText(getBaseContext(), "Dados Alterados!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(getBaseContext(), "Não foi possível alterar os dados!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                Toast.makeText(getBaseContext(), "Erro de comunicação!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
