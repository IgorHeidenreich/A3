public class Livro {
    private String id;
    private String titulo;
    private String autor;
    private String anoDePublicacao;
    private String isbn;
    private boolean disponibilidade = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAnoDePublicacao(String anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


}
