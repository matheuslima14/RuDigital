package android.usuario.rudigital;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private RatingBar notaAlmoco;
    private RatingBar notaJanta;
    String notaatribuidaalmoco;
    String notaatribuidajanta;
    String datanota;
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

        notaAlmoco = (RatingBar) findViewById(R.id.notaAlmoco);
        notaJanta = (RatingBar) findViewById(R.id.notaJanta);
        btnAvaliar = (Button) findViewById(R.id.btnAvaliar);
        btn_voltar = (Button) findViewById(R.id.btn_voltar);

        getWebService2();

        this.btnAvaliar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (notaAlmoco.getRating() == 0.0 && notaJanta.getRating() == 0.0) {
                    Toast.makeText(getBaseContext(), "Faça sua Avaliação!", Toast.LENGTH_SHORT).show();
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

        notaatribuidaalmoco = String.valueOf(notaAlmoco.getRating());
        notaatribuidajanta = String.valueOf(notaJanta.getRating());
        datanota = DateFormat.getDateInstance().format(new Date());
        String matriculasiape = usuario.getMatriculaSiape();

        final Request request = new Request.Builder().url("http://192.168.1.3:802/appRUDigital/AvaliarServico.php?matriculasiape=" + matriculasiape + "&notaAtribuidaAlmoco=" + notaatribuidaalmoco + "&notaAtribuidaJanta=" + notaatribuidajanta + "&dataNota=" + datanota).build();
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
                Toast.makeText(getBaseContext(), "Nota atribuída com Sucesso!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(getBaseContext(), "Nota de hoje atualizada com Sucesso!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                Toast.makeText(getBaseContext(), "Erro ao Fazer Avaliação!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 3) {
                notaAlmoco.setRating(Float.parseFloat(avaliacao.getNotaAlmoco()));
                notaJanta.setRating(Float.parseFloat(avaliacao.getNotaJanta()));
            } else if (msg.what == 4) {

            } else if (msg.what == 5) {
                Toast.makeText(getBaseContext(), "Falha de Comunicação com Servidor!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void getWebService2() {

        datanota = DateFormat.getDateInstance().format(new Date());
        String matriculasiape = usuario.getMatriculaSiape();

        final Request request = new Request.Builder().url("http://192.168.1.3:802/appRUDigital/ExibirAvaliacao.php?matriculasiape=" + matriculasiape + "&datanota=" + datanota).build();
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
                                handler.sendEmptyMessage(3);
                            } else {
                                handler.sendEmptyMessage(4);
                            }

                        } catch (IOException ioe) {
                            handler.sendEmptyMessage(5);
                        }
                    }
                });
            }
        });
    }

}