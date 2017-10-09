package android.usuario.rudigital;

/**
 * Created by Pedro Junio on 29/08/2017.
 */

public class Usuario {

    private String matriculasiape;
    private String nome;
    private String email;
    private String senha;
    private String rg;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getMatriculaSiape() {
        return matriculasiape;
    }

    public void setMatriculaSiape(String matriculasiape) {
        this.matriculasiape = matriculasiape;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
