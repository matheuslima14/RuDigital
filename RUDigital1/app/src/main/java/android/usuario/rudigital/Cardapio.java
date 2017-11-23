package android.usuario.rudigital;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro Junio on 21/11/2017.
 */

public class Cardapio {

    private int id;
    private String nome;
    private String dataInicio;
    private String dataFinal;
    private List<Prato> pratos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    @Override
    public String toString() {
        String resultado = "";

        for (Prato p : pratos) {
           resultado = resultado +  p.getDia() + " - " + p.getDataPrato() + "\n" + p.getDescricao() + "\n\n";
        }
        return resultado;
    }
}



