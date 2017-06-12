package android.usuario.rudigital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.*;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

public class TelaMenu extends AppCompatActivity{

    private Button btn_votarCardapio;
    private Button btn_avisarComer;
    private Button btn_avaliarRefeicao;
    private Button btn_Sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_menu);

        this.btn_votarCardapio = (Button) findViewById(R.id.btn_votarCardapio);
        this.btn_avisarComer = (Button) findViewById(R.id.btn_avisarComer);
        this.btn_avaliarRefeicao = (Button) findViewById(R.id.btn_avaliarRefeicao);
        this.btn_Sair = (Button) findViewById(R.id.btn_Sair);

        this.btn_votarCardapio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent irTelaVotarCardapio = new Intent(TelaMenu.this, TelaVotarCardapio.class);
                TelaMenu.this.startActivity(irTelaVotarCardapio);
                TelaMenu.this.finish();
            }
        });

        this.btn_avisarComer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent irTelaAvisarComer = new Intent(TelaMenu.this, TelaAvisarComer.class);
                TelaMenu.this.startActivity(irTelaAvisarComer);
                TelaMenu.this.finish();
            }
        });

        this.btn_avaliarRefeicao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent irTelaAvaliarRefeicao = new Intent(TelaMenu.this, TelaAvaliarRefeicao.class);
                TelaMenu.this.startActivity(irTelaAvaliarRefeicao);
                TelaMenu.this.finish();
            }
        });

        this.btn_votarCardapio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent irTelaLogin = new Intent(TelaMenu.this, TelaLogin.class);
                TelaMenu.this.startActivity(irTelaLogin);
                TelaMenu.this.finish();
            }
        });
    }
}