package android.usuario.rudigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaApagarConta extends AppCompatActivity {

    private EditText edtSenha;
    private Button btnExcluir;
    private Button btnVoltar;
    Usuario usuario;

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
                }
            }
        });
    }
}
