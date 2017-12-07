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

public class TelaCadastro extends AppCompatActivity {

    private EditText editNome, editEmail2, editRG, editMatriculaSiape, editSenha2, editConfirmaSenha;
    private Button btnRegistar, btnCancelar;
    private OkHttpClient client;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        editNome = (EditText) findViewById(R.id.editNome);
        editEmail2 = (EditText) findViewById(R.id.editEmail2);
        editRG = (EditText) findViewById(R.id.editRG);
        editMatriculaSiape = (EditText) findViewById(R.id.editMatriculaSiape);
        editSenha2 = (EditText) findViewById(R.id.editSenha2);
        editConfirmaSenha = (EditText) findViewById(R.id.editConfirmaSenha);

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnRegistar = (Button) findViewById(R.id.btnRegistrar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreLogin = new Intent(TelaCadastro.this, TelaLogin.class);
                startActivity(abreLogin);
            }
        });

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editNome.getText().toString().isEmpty() || editEmail2.getText().toString().isEmpty() || editRG.getText().toString().isEmpty() || editMatriculaSiape.getText().toString().isEmpty() || editSenha2.getText().toString().isEmpty() || editConfirmaSenha.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Todos os campos são obrigatorios!", Toast.LENGTH_SHORT).show();
                } else if (!(editSenha2.getText().toString().equals(editConfirmaSenha.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Senhas não conferem!", Toast.LENGTH_SHORT).show();
                } else if (editSenha2.length() < 8 || editConfirmaSenha.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Senha tem que ter no minímo 8 digitos!", Toast.LENGTH_SHORT).show();
                } else {
                    client = new OkHttpClient();
                    getWebService();
                }
            }
        });
    }

    private void getWebService() {

        String nome = editNome.getText().toString();
        String email = editEmail2.getText().toString();
        String rg = editRG.getText().toString();
        String matriculasiape = editMatriculaSiape.getText().toString();
        String senha = editSenha2.getText().toString();
        String confirmaSenha = editConfirmaSenha.getText().toString();

        final Request request = new Request.Builder().url("http://pedrofjduarte.000webhostapp.com/appRUDigital/CadastroUsuario.php?nome=" + nome + "&email=" + email + "&senha=" + senha + "&matriculasiape=" + matriculasiape + "&rg=" + rg + "&confirmaSenha" + confirmaSenha).build();
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
                editNome.setText("");
                editEmail2.setText("");
                editRG.setText("");
                editMatriculaSiape.setText("");
                editSenha2.setText("");
                editConfirmaSenha.setText("");
                Toast.makeText(getBaseContext(), "Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(getBaseContext(), "Pessoa não encontrada no sistema!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                Toast.makeText(getBaseContext(), "Erro no Cadastro!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void telaLogin(View v) {
        Intent abreLogin = new Intent(this, TelaLogin.class);
        startActivity(abreLogin);
    }
}
