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

    String segunda1;
    String segunda2;
    String terca1;
    String terca2;
    String quarta1;
    String quarta2;
    String quinta1;
    String quinta2;
    String sexta1;
    String sexta2;


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

        btn_voltar = (Button) findViewById(R.id.btn_voltar);
        btnSalvarAgenda = (Button) findViewById(R.id.btnSalvarAgenda);

        this.btnSalvarAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (almocoSegunda.isChecked()) {
                    segunda1 = "true";
                } else {
                    segunda1 = "false";
                }

                if (almocoTerca.isChecked()) {
                    terca1 = "true";
                } else {
                    terca1 = "false";
                }

                if (almocoQuarta.isChecked()) {
                    quarta1 = "true";
                } else {
                    quarta1 = "false";
                }

                if (almocoQuinta.isChecked()) {
                    quinta1 = "true";
                } else {
                    quinta1 = "false";
                }

                if (almocoSexta.isChecked()) {
                    sexta1 = "true";
                } else {
                    sexta2 = "false";
                }

                if (jantaSegunda.isChecked()) {
                    segunda2 = "true";
                } else {
                    segunda2 = "false";
                }

                if (jantaTerca.isChecked()) {
                    terca2 = "true";
                } else {
                    terca2 = "false";
                }

                if (jantaQuarta.isChecked()) {
                    quarta2 = "true";
                } else {
                    quarta2 = "false";
                }

                if (jantaQuinta.isChecked()) {
                    quinta2 = "true";
                } else {
                    quinta2 = "false";
                }
                if (jantaSexta.isChecked()) {
                    sexta2 = "true";
                } else {
                    sexta2 = "false";
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

        final Request request = new Request.Builder().url("http://172.50.0.178:802/appRUDigital/AgendarRefeicao.php?matriculasiape=" + usuario.getMatriculaSiape().toString() /*+ "&agenda=" + agenda.getId()*/ + "&almocoSegunda=" + segunda1 + "&almocoTerca=" + terca1 + "&almocoQuarta=" + quarta1 + "&almocoQuinta=" + quinta1 + "&almocoSexta=" + sexta1 + "&jantaSegunda=" + segunda2 + "&jantaTerca=" + terca2 + "&jantaQuarta=" + quarta2 + "&jantaQuinta=" + quinta2 + "&jantaSexta=" + sexta2).build();
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