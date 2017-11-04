package android.usuario.rudigital;


public class Avaliacao {


    private int id;
    private String dataAvaliacao;
    private float notaAtribuidaAlmoco;
    private float notaAtribuidaJanta;
    private int matriculasiape;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public float getNotaAtribuidaAlmoco() {
        return notaAtribuidaAlmoco;
    }

    public void setNotaAtribuidaAlmoco(float notaAtribuidaAlmoco) {
        this.notaAtribuidaAlmoco = notaAtribuidaAlmoco;
    }

    public float getNotaAtribuidaJanta() {
        return notaAtribuidaJanta;
    }

    public void setNotaAtribuidaJanta(float notaAtribuidaJanta) {
        this.notaAtribuidaJanta = notaAtribuidaJanta;
    }

    public int getMatriculasiape() {
        return matriculasiape;
    }

    public void setMatriculasiape(int matriculasiape) {
        this.matriculasiape = matriculasiape;
    }
}
