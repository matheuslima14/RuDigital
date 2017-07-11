package android.usuario.rudigital;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

        if (!(editSenha2.getText().toString().equals(editConfirmaSenha.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "Senhas não conferem!", Toast.LENGTH_SHORT).show();
        }

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

                    url = "HTTP://192.168.0.101:802/appRUDigital/CadastroUsuario.php?nome=" + nome + "&email=" + email + "&senha=" + senha + "&matriculasiape=" + matriculasiape + "&rg=" + rg;

                    okHttpClient = new OkHttpClient();
                    request = new Request.Builder().url(url).build();

                    Response response = okHttpClient.newCall(request).execute();
                    Log.i("MSG", response.body().string());

                    //Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                    //AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    //dlg.setMessage("Cadastrado com Sucesso!");
                    //dlg.show();


                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "Erro no Cadastro!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        Thread threadCadastrar = new Thread(r);
        threadCadastrar.start();
    }


    public void telaLogin(View v) {
        Intent abreLogin = new Intent(this, TelaLogin.class);
        startActivity(abreLogin);
    }
}





