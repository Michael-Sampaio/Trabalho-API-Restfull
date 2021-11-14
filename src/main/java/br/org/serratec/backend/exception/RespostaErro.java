package br.org.serratec.backend.exception;

public class RespostaErro {
    private String titulo;
    private Integer status;
    private String classe;
    private String mensagem;
    private String dataHora;

    public RespostaErro(String titulo, Integer status, String mensagem, String classe, String dataHora) {
        this.titulo = titulo;
        this.classe = classe;
        this.mensagem = mensagem;
        this.status = status;
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getClasse() {
        return this.classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

}
