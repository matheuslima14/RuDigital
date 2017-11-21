package android.usuario.rudigital;

/**
 * Created by Pedro Junio on 21/11/2017.
 */

public class Prato {

    private String dia;
    private String dataPrato;
    private String descricao;
    private int cardapio;


    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDataPrato() {
        return dataPrato;
    }

    public void setDataPrato(String dataPrato) {
        this.dataPrato = dataPrato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCardapio() {
        return cardapio;
    }

    public void setCardapio(int cardapio) {
        this.cardapio = cardapio;
    }

    public String toString() {
        return dia + " - " + dataPrato + "\n" + descricao+"\n\n";
    }

}
