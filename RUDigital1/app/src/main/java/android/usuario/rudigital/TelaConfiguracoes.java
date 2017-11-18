package android.usuario.rudigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_configuracoes);

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

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreMenu = new Intent(TelaConfiguracoes.this, TelaMenu.class);
                startActivity(abreMenu);
            }
        });

        btnApagarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreApagarConta = new Intent(TelaConfiguracoes.this, TelaApagarConta.class);
                startActivity(abreApagarConta);
            }
        });
    }
}
