package android.usuario.rudigital;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class TelaCadastro extends AppCompatActivity implements View.OnClickListener {

    private EditText editNome, editEmail2, editRG, editMatriculaSiape, editSenha2, editConfirmaSenha;
    private Button btnRegistar, btnCancelar;

    private OkHttpClient okHttpClient;
    private Request request;
    private String url;

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
        btnRegistar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if (editNome.getText().toString().isEmpty() || editEmail2.getText().toString().isEmpty() || editRG.getText().toString().isEmpty() || editMatriculaSiape.getText().toString().isEmpty() || editSenha2.getText().toString().isEmpty() || editConfirmaSenha.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Todos os campos são obrigatorios!", Toast.LENGTH_SHORT).show();
        } else if (!(editSenha2.getText().toString().equals(editConfirmaSenha.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "Senhas não conferem!", Toast.LENGTH_SHORT).show();
        } else if (editSenha2.length() < 8 || editConfirmaSenha.length() < 8) {
            Toast.makeText(getApplicationContext(), "Senha tem que ter no minímo 8 digitos!", Toast.LENGTH_SHORT).show();
        } else {

            Runnable r = new Runnable() {
                @Override
                public void run() {

                    try {

                        String nome = editNome.getText().toString();
                        String email = editEmail2.getText().toString();
                        String rg = editRG.getText().toString();
                        String matriculasiape = editMatriculaSiape.getText().toString();
                        String senha = editSenha2.getText().toString();
                        String confirmaSenha = editConfirmaSenha.getText().toString();

                        url = "HTTP://172.19.6.31:802/appRUDigital/CadastroUsuario.php?nome=" + nome + "&email=" + email + "&senha=" + senha + "&matriculasiape=" + matriculasiape + "&rg=" + rg + "&confirmaSenha" + confirmaSenha;

                        okHttpClient = new OkHttpClient();
                        request = new Request.Builder().url(url).build();

                        Response response = okHttpClient.newCall(request).execute();
                        Log.i("MSG", response.body().string());
                        handler.sendEmptyMessage(1);

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

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {

                editNome.setText("");
                editEmail2.setText("");
                editRG.setText("");
                editMatriculaSiape.setText("");
                editSenha2.setText("");
                editConfirmaSenha.setText("");
                Toast.makeText(getBaseContext(), "Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 0) {
                Toast.makeText(getBaseContext(), "Erro no Cadastro!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void telaLogin(View v) {
        Intent abreLogin = new Intent(this, TelaLogin.class);
        startActivity(abreLogin);
    }
}