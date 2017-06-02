package android.usuario.rudigital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class  TelaInicial extends AppCompatActivity {

    TextView txtNome, txtId;

    String nomeUsuario, idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        txtNome = (TextView)findViewById(R.id.txtNome);
        txtId = (TextView)findViewById(R.id.txtId);

        nomeUsuario = getIntent().getExtras().getString("nome_usuario");
        idUsuario = getIntent().getExtras().getString("id_usuario");

        txtNome.setText(nomeUsuario);
        txtId.setText(idUsuario);

    }
}
