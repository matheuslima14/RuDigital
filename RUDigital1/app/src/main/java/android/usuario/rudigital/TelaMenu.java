package android.usuario.rudigital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.*;

public class TelaMenu extends AppCompatActivity {

    private Button btn_votarCardapio, btnConfiguracoes;
    private Button btn_avisarComer;
    private Button btn_avaliarRefeicao;
    private Button btn_Sair;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_menu);

        usuario = new Usuario();
        Intent intent = getIntent();

        usuario.setMatriculaSiape(intent.getStringExtra("matriculasiape"));
        usuario.setNome(intent.getStringExtra("nome"));
        usuario.setEmail(intent.getStringExtra("email"));
        usuario.setSenha(intent.getStringExtra("senha"));
        usuario.setRg(intent.getStringExtra("rg"));

        this.btn_votarCardapio = (Button) findViewById(R.id.btn_votarCardapio);
        this.btn_avisarComer = (Button) findViewById(R.id.btn_avisarComer);
        this.btn_avaliarRefeicao = (Button) findViewById(R.id.btn_avaliarRefeicao);
        this.btn_Sair = (Button) findViewById(R.id.btn_Sair);
        this.btnConfiguracoes = (Button) findViewById(R.id.btnConfiguracoes);

        this.btnConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irTelaConfiguracoes = new Intent(TelaMenu.this, TelaConfiguracoes.class);
                irTelaConfiguracoes.putExtra("matriculasiape", usuario.getMatriculaSiape());
                irTelaConfiguracoes.putExtra("nome", usuario.getNome());
                irTelaConfiguracoes.putExtra("email", usuario.getEmail());
                irTelaConfiguracoes.putExtra("senha", usuario.getSenha());
                irTelaConfiguracoes.putExtra("rg", usuario.getRg());
                TelaMenu.this.startActivity(irTelaConfiguracoes);
                TelaMenu.this.finish();
            }
        });

        this.btn_votarCardapio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irTelaVotarCardapio = new Intent(TelaMenu.this, TelaVotarCardapio.class);
                irTelaVotarCardapio.putExtra("matriculasiape", usuario.getMatriculaSiape());
                irTelaVotarCardapio.putExtra("nome", usuario.getNome());
                irTelaVotarCardapio.putExtra("email", usuario.getEmail());
                irTelaVotarCardapio.putExtra("senha", usuario.getSenha());
                irTelaVotarCardapio.putExtra("rg", usuario.getRg());
                TelaMenu.this.startActivity(irTelaVotarCardapio);
                TelaMenu.this.finish();
            }
        });

        this.btn_avisarComer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irTelaAvisarComer = new Intent(TelaMenu.this, TelaAvisarComer.class);
                irTelaAvisarComer.putExtra("matriculasiape", usuario.getMatriculaSiape());
                irTelaAvisarComer.putExtra("nome", usuario.getNome());
                irTelaAvisarComer.putExtra("email", usuario.getEmail());
                irTelaAvisarComer.putExtra("senha", usuario.getSenha());
                irTelaAvisarComer.putExtra("rg", usuario.getRg());
                TelaMenu.this.startActivity(irTelaAvisarComer);
                TelaMenu.this.finish();
            }
        });

        this.btn_avaliarRefeicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irTelaAvaliarRefeicao = new Intent(TelaMenu.this, TelaAvaliarRefeicao.class);
                irTelaAvaliarRefeicao.putExtra("matriculasiape", usuario.getMatriculaSiape());
                irTelaAvaliarRefeicao.putExtra("nome", usuario.getNome());
                irTelaAvaliarRefeicao.putExtra("email", usuario.getEmail());
                irTelaAvaliarRefeicao.putExtra("senha", usuario.getSenha());
                irTelaAvaliarRefeicao.putExtra("rg", usuario.getRg());
                TelaMenu.this.startActivity(irTelaAvaliarRefeicao);
                TelaMenu.this.finish();
            }
        });

        this.btn_Sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irTelaLogin = new Intent(TelaMenu.this, TelaLogin.class);
                TelaMenu.this.startActivity(irTelaLogin);
                TelaMenu.this.finish();
            }
        });
    }
}