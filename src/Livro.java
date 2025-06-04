import java.io.FileWriter;
import java.io.IOException;

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
        switch (disponibilidade ? "sim" : "não") {
        }
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

    public String toString() {
        return id + ";" + titulo + ";" + autor + ";" + anoDePublicacao + ";" + isbn + ";" + (disponibilidade ? "s" : "n");
    }

    public void salvarLivro(Livro livro) {
        try {
            FileWriter escritorL = new FileWriter("livros.txt", true);
            escritorL.write(livro.toString() + "\n");

            escritorL.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar livro.");
            e.printStackTrace();

        }
    }
}