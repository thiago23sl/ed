package entities;
public class Livro { //classe livro com seus atributos, construtor e getters
    private String nomeLivro;
    private String autor;
    private int anoPublicacao;

    public Livro(String nomeLivro, String autor, int anoPublicacao){
        this.nomeLivro = nomeLivro;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public String getnomeLivro(){
        return nomeLivro;
    }
    public String getautor(){
        return autor;
    }
    public int getanoPublicacao(){
        return anoPublicacao;
    }

    @Override
    public String toString(){
        return String.format("\nLIVRO: %s\nAUTOR: %s\nANO DA PUBLICAÇÃO: %d", nomeLivro, autor, anoPublicacao);
    }
}