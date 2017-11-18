package android.usuario.rudigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaRecuperarSenha extends AppCompatActivity {

    private Button btnRecuperar;
    private Button btnCancelar;
    private EditText edtEmail;
    private TextView txtRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_recuperar_senha);

        btnRecuperar = (Button) findViewById(R.id.btnRecuperar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        txtRecuperar = (TextView) findViewById(R.id.txtRecuperar);

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(edtEmail.getText().toString().isEmpty()){
                   Toast.makeText(getApplicationContext(), "Insira seu Email!", Toast.LENGTH_SHORT).show();
               }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreLogin = new Intent(TelaRecuperarSenha.this, TelaLogin.class);
                startActivity(abreLogin);
            }
        });

    }
}
