package android.usuario.rudigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class TelaVotarCardapio extends Activity {

    private Button btn_voltar, btn_votarDia, btn_VotarSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_votar_cardapio);

        btn_voltar = (Button) findViewById(R.id.btn_voltar);
        btn_votarDia = (Button) findViewById(R.id.btn_votarDia);
        btn_VotarSemana = (Button) findViewById(R.id.btn_votarSemana);

        this.btn_voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent TelaMenu = new Intent(TelaVotarCardapio.this, TelaMenu.class);
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