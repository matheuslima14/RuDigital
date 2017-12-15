package android.usuario.rudigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TelaVotarCardapio extends AppCompatActivity {

    private ListView listaCardapios;
    private OkHttpClient client;
    private ArrayList<Cardapio> cardapios;
    private ArrayList<Prato> pratos;
    ArrayAdapter itemsAdaptercardapios;
    private Button btn_voltar;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_votar_cardapio);

        usuario = new Usuario();
        Intent intent = getIntent();

        usuario.setMatriculaSiape(intent.getStringExtra("matriculasiape"));
        usuario.setNome(intent.getStringExtra("nome"));
        usuario.setEmail(intent.getStringExtra("email"));
        usuario.setSenha(intent.getStringExtra("senha"));
        usuario.setRg(intent.getStringExtra("rg"));


        btn_voltar = (Button) findViewById(R.id.btn_voltar);

        listaCardapios = (ListView) findViewById(R.id.listaCardapios);

        client = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).build();
        getWebServiceCardapios();

        /*listaCardapios.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public  void onItemClick(AdapterView<?> paret, View view, int position, long id){
                Toast.makeText(getBaseContext(), cardapios.get(position).toString(), Toast.LENGTH_SHORT).show();
            }

        });*/

        this.btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TelaMenu = new Intent(TelaVotarCardapio.this, TelaMenu.class);

                TelaMenu.putExtra("matriculasiape", usuario.getMatriculaSiape());
                TelaMenu.putExtra("nome", usuario.getNome());
                TelaMenu.putExtra("email", usuario.getEmail());
                TelaMenu.putExtra("senha", usuario.getSenha());
                TelaMenu.putExtra("rg", usuario.getRg());

                TelaVotarCardapio.this.startActivity(TelaMenu);
                TelaVotarCardapio.this.finish();
            }
        });
    }

    private void getWebServiceCardapios() {
        final Request request = new Request.Builder().url("http://192.168.0.101:802/appRUDigital/ExibirCardapios.php").build();
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

                            Type collectionType = new TypeToken<List<Cardapio>>() {
                            }.getType();

                            cardapios = json.fromJson(response.body().string(), collectionType);

                            itemsAdaptercardapios = new ListaAdapterItem(getBaseContext(),cardapios);

                            if (cardapios == null) {
                                handler.sendEmptyMessage(0);
                            } else {
                                handler.sendEmptyMessage(1);
                            }

                        } catch (IOException ioe) {
                            //Erro
                        }
                    }
                });
            }
        });
    }


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getBaseContext(), "Nenhuma Cardápio Cadastrado!", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                listaCardapios.setAdapter(itemsAdaptercardapios);
            }
        }
    };
}