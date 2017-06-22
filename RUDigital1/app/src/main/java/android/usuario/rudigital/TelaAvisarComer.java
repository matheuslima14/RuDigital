package android.usuario.rudigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaAvisarComer extends AppCompatActivity {

    private Button btn_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_avisar_comer);

        btn_voltar = (Button) findViewById(R.id.btn_voltar);

        this.btn_voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent TelaMenu = new Intent(TelaAvisarComer.this, TelaMenu.class);
                TelaAvisarComer.this.startActivity(TelaMenu);
                TelaAvisarComer.this.finish();
            }
        });
    }
}