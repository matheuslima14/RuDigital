package android.usuario.rudigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaAvisarComer extends AppCompatActivity {

    private Button btn_voltar;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_avisar_comer);

        usuario = new Usuario();
        Intent intent = getIntent();

        usuario.setMatriculaSiape(intent.getStringExtra("matriculasiape"));
        usuario.setNome(intent.getStringExtra("nome"));
        usuario.setEmail(intent.getStringExtra("email"));
        usuario.setSenha(intent.getStringExtra("senha"));
        usuario.setRg(intent.getStringExtra("rg"));

        btn_voltar = (Button) findViewById(R.id.btn_voltar);

        this.btn_voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent TelaMenu = new Intent(TelaAvisarComer.this, TelaMenu.class);

                TelaMenu.putExtra("matriculasiape", usuario.getMatriculaSiape());
                TelaMenu.putExtra("nome", usuario.getNome());
                TelaMenu.putExtra("email", usuario.getEmail());
                TelaMenu.putExtra("senha", usuario.getSenha());
                TelaMenu.putExtra("rg", usuario.getRg());

                TelaAvisarComer.this.startActivity(TelaMenu);
                TelaAvisarComer.this.finish();
            }
        });
    }
}