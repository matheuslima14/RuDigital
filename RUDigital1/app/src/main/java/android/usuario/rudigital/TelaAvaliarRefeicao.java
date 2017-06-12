package android.usuario.rudigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaAvaliarRefeicao extends AppCompatActivity {

    public Button btn_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_avaliar_refeicao);

        btn_voltar = (Button) findViewById(R.id.btn_voltar);

        this.btn_voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent TelaMenu = new Intent(TelaAvaliarRefeicao.this, TelaMenu.class);
                TelaAvaliarRefeicao.this.startActivity(TelaMenu);
                TelaAvaliarRefeicao.this.finish();
            }
        });
    }
}