package android.usuario.rudigital;

import android.provider.ContactsContract;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Pedro Junio on 12/10/2017.
 */

public class Avaliacao {


    private String id;
    private String dataVoto;
    private String notaAlmoco;
    private String notaJanta;
    private String matriculasiape;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataVoto() {
        return dataVoto;
    }

    public void setDataVoto(String dataVoto) {
        this.dataVoto = dataVoto;
    }

    public String getNotaAlmoco() {
        return notaAlmoco;
    }

    public void setNotaAlmoco(String notaAlmoco) {
        this.notaAlmoco = notaAlmoco;
    }

    public String getNotaJanta() {
        return notaJanta;
    }

    public void setNotaJanta(String notaJanta) {
        this.notaJanta = notaJanta;
    }

    public String getMatriculasiape() {
        return matriculasiape;
    }

    public void setMatriculasiape(String matriculasiape) {
        this.matriculasiape = matriculasiape;
    }
}
