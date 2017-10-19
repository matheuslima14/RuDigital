package android.usuario.rudigital;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TelaAvaliarRefeicao extends AppCompatActivity {

    public Button btn_voltar;
    private Button btnAvaliar;
    private OkHttpClient client;
    private RatingBar nota;
    String notaAtribuida;
    String dataNota;
    Usuario usuario;
    Avaliacao avaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_avaliar_refeicao);

        usuario = new Usuario();
        Intent intent = getIntent();

        usuario.setMatriculaSiape(intent.getStringExtra("matriculasiape"));
        usuario.setNome(intent.getStringExtra("nome"));
        usuario.setEmail(intent.getStringExtra("email"));
        usuario.setSenha(intent.getStringExtra("senha"));
        usuario.setRg(intent.getStringExtra("rg"));

        nota = (RatingBar) findViewById(R.id.nota);
        btnAvaliar = (Button) findViewById(R.id.btnAvaliar);
        btn_voltar = (Button) findViewById(R.id.btn_voltar);

        this.btnAvaliar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (nota.getRating() == 0.0) {
                    Toast.makeText(getBaseContext(), "Faça uma Avaliação!", Toast.LENGTH_SHORT).show();
                } else {
                    client = new OkHttpClient();
                    getWebService();
                }

            }
        });

        this.btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TelaMenu = new Intent(TelaAvaliarRefeicao.this, TelaMenu.class);
                TelaMenu.putExtra("matriculasiape", usuario.getMatriculaSiape());
                TelaMenu.putExtra("nome", usuario.getNome());
                TelaMenu.putExtra("email", usuario.getEmail());
                TelaMenu.putExtra("senha", usuario.getSenha());
                TelaMenu.putExtra("rg", usuario.getRg());
                TelaAvaliarRefeicao.this.startActivity(TelaMenu);
                TelaAvaliarRefeicao.this.finish();
            }
        });
    }

    private void getWebService() {

        notaAtribuida = String.valueOf(nota.getRating());
        dataNota = DateFormat.getDateInstance().format(new Date());
        String matriculasiape = usuario.getMatriculaSiape();

        float notaconvertida = nota.getRating();

        final Request request = new Request.Builder().url("http://172.19.7.33:802/appRUDigital/AvaliarServico.php?matriculasiape=" + matriculasiape + "&notaAtribuida=" + notaconvertida + "&dataNota=" + dataNota).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //falho
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

                            avaliacao = json.fromJson(response.body().string(), Avaliacao.class);

                            if (avaliacao != null) {
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
                Intent telaMenu = new Intent(TelaAvaliarRefeicao.this, TelaMenu.class);
                startActivity(telaMenu);
                Toast.makeText(getBaseContext(), "\tNota: " + notaAtribuida + " \n\tAtribuída com Sucesso!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(getBaseContext(), "O Serviço de hoje já foi avaliado!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                Toast.makeText(getBaseContext(), "Erro ao Fazer Avaliação!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}