package android.usuario.rudigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class TelaVotarCardapio extends Activity {

    private Button btn_voltar, btn_votarDia, btn_VotarSemana;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_votar_cardapio);

        usuario = new Usuario();
        Intent intent = getIntent();

        usuario.setMatriculaSiape(intent.getStringExtra("matriculasiape"));
        usuario.setNome(intent.getStringExtra("nome"));
        usuario.setEmail(intent.getStringExtra("email"));
        usuario.setSenha(intent.getStringExtra("senha"));
        usuario.setRg(intent.getStringExtra("rg"));

        btn_voltar = (Button) findViewById(R.id.btn_voltar);
        btn_votarDia = (Button) findViewById(R.id.btn_votarDia);
        btn_VotarSemana = (Button) findViewById(R.id.btn_votarSemana);

        this.btn_voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent TelaMenu = new Intent(TelaVotarCardapio.this, TelaMenu.class);

                TelaMenu.putExtra("matriculasiape", usuario.getMatriculaSiape());
                TelaMenu.putExtra("nome", usuario.getNome());
                TelaMenu.putExtra("email", usuario.getEmail());
                TelaMenu.putExtra("senha", usuario.getSenha());
                TelaMenu.putExtra("rg", usuario.getRg());

                TelaVotarCardapio.this.startActivity(TelaMenu);
                TelaVotarCardapio.this.finish();
            }
        });

        /*this.btnVotarDiaaDia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent telaCardapios_diarios = new Intent(Votar_cardapio.this, Cardapios_diarios.class);
                Votar_cardapio.this.startActivity(telaCardapios_diarios);
                Votar_cardapio.this.finish();
            }
        });*/

        /*this.btnVotarSemanCompleta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent telaRUDigital = new Intent(Votar_cardapio.this, RUDigital.class);
                Votar_cardapio.this.startActivity(telaRUDigital);
                Votar_cardapio.this.finish();
            }
        });*/
    }
}