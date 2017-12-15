package android.usuario.rudigital;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TelaAvisarComer extends AppCompatActivity {

    private Button btn_voltar;
    private Button btnSalvarAgenda;

    private TextView txtDataAgenda;

    private CheckBox almocoSegunda;
    private CheckBox almocoTerca;
    private CheckBox almocoQuarta;
    private CheckBox almocoQuinta;
    private CheckBox almocoSexta;
    private CheckBox jantaSegunda;
    private CheckBox jantaTerca;
    private CheckBox jantaQuarta;
    private CheckBox jantaQuinta;
    private CheckBox jantaSexta;
    private OkHttpClient client;
    Usuario usuario;
    Agenda agenda;
    AgendaRefeicao agendaRefeicao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_avisar_comer);

        usuario = new Usuario();
        Intent intent = getIntent();

        txtDataAgenda = (TextView) findViewById(R.id.txtDataAgenda);

        almocoSegunda = (CheckBox) findViewById(R.id.almocoSegunda);
        almocoTerca = (CheckBox) findViewById(R.id.almocoTerca);
        almocoQuarta = (CheckBox) findViewById(R.id.almocoQuarta);
        almocoQuinta = (CheckBox) findViewById(R.id.almocoQuinta);
        almocoSexta = (CheckBox) findViewById(R.id.almocoSexta);
        jantaSegunda = (CheckBox) findViewById(R.id.jantaSegunda);
        jantaTerca = (CheckBox) findViewById(R.id.jantaTerca);
        jantaQuarta = (CheckBox) findViewById(R.id.jantaQuarta);
        jantaQuinta = (CheckBox) findViewById(R.id.jantaQuinta);
        jantaSexta = (CheckBox) findViewById(R.id.jantaSexta);

        usuario.setMatriculaSiape(intent.getStringExtra("matriculasiape"));
        usuario.setNome(intent.getStringExtra("nome"));
        usuario.setEmail(intent.getStringExtra("email"));
        usuario.setSenha(intent.getStringExtra("senha"));
        usuario.setRg(intent.getStringExtra("rg"));

        btn_voltar = (Button) findViewById(R.id.btn_voltar);
        btnSalvarAgenda = (Button) findViewById(R.id.btnSalvarAgenda);

        this.btnSalvarAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (almocoSegunda.isChecked()) {
                    almocoSegunda.setText("true");
                } else {
                    almocoSegunda.setText("false");
                }
                if (almocoTerca.isChecked()) {
                    almocoTerca.setText("true");
                } else {
                    almocoTerca.setText("false");
                }
                if (almocoQuarta.isChecked()) {
                    almocoQuarta.setText("true");
                } else {
                    almocoQuarta.setText("false");
                }
                if (almocoQuinta.isChecked()) {
                    almocoQuinta.setText("true");
                } else {
                    almocoQuinta.setText("false");
                }
                if (almocoSexta.isChecked()) {
                    almocoSexta.setText("true");
                } else {
                    almocoSexta.setText("false");
                }

                if (jantaSegunda.isChecked()) {
                    jantaSegunda.setText("true");
                } else {
                    jantaSegunda.setText("false");
                }
                if (jantaTerca.isChecked()) {
                    jantaTerca.setText("true");
                } else {
                    jantaTerca.setText("false");
                }
                if (jantaQuarta.isChecked()) {
                    jantaQuarta.setText("true");
                } else {
                    jantaQuarta.setText("false");
                }
                if (jantaQuinta.isChecked()) {
                    jantaQuinta.setText("true");
                } else {
                    jantaQuinta.setText("false");
                }
                if (jantaSexta.isChecked()) {
                    jantaSexta.setText("true");
                } else {
                    jantaSexta.setText("false");
                }

                client = new OkHttpClient();
                getWebService();
            }
        });

        this.btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void getWebService() {

        final Request request = new Request.Builder().url("http://192.168.0.101:802/appRUDigital/AgendaRefeicao.php?matriculasiape=" + usuario.getMatriculaSiape().toString() + "&agenda=" + agenda.getId() + "&almocoSegunda=" + almocoSegunda.getText().toString() + "&almocoTerca=" + almocoTerca.getText().toString() + "&almocoQuarta=" + almocoQuarta.getText().toString() + "&almocoQuinta=" + almocoQuinta.getText().toString() + "&almocoSexta=" + almocoSexta.getText().toString() + "&jantaSegunda=" + jantaSegunda.getText().toString() + "&jantaTerca=" + jantaTerca.getText().toString() + "&jantaQuarta=" + jantaQuarta.getText().toString() + "&jantaQuinta=" + jantaQuinta.getText().toString() + "&jantaSexta=" + jantaSexta.getText().toString()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            Gson json = new Gson();

                            agendaRefeicao = json.fromJson(response.body().string(), AgendaRefeicao.class);

                            if (agendaRefeicao != null) {
                                handler.sendEmptyMessage(0);
                            } else {
                                handler.sendEmptyMessage(1);
                            }


                        } catch (IOException ioe) {
                            handler.sendEmptyMessage(2);
                        }
                    }
                });
            }
        });
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                Intent telaMenu = new Intent(TelaAvisarComer.this, TelaMenu.class);
                telaMenu.putExtra("matriculasiape", usuario.getMatriculaSiape());
                telaMenu.putExtra("nome", usuario.getNome());
                telaMenu.putExtra("email", usuario.getEmail());
                telaMenu.putExtra("senha", usuario.getSenha());
                telaMenu.putExtra("rg", usuario.getRg());
                startActivity(telaMenu);
                Toast.makeText(getBaseContext(), "Agenda Atualizada com Sucesso!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(getBaseContext(), "Não foi possível atulizar a agenda!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                Toast.makeText(getBaseContext(), "Erro ao Fazer Agendamento!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}