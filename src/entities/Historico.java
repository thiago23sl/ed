package entities;
import java.util.*;
public class Historico {
    private String nomePessoa;
    private Stack<String> historicoLivros;

    public Historico(String nomePessoa){
        this.nomePessoa = nomePessoa;
        this.historicoLivros = new Stack<>();
    }

    public String getNomePessoa(){
        return nomePessoa;
    }

    public void addLivro(String titulo){
        historicoLivros.push(titulo);
    }

    public void mostrarHistorico(){
        if(historicoLivros.isEmpty()){
            System.out.printf("%s AINDA NÃO PROCUROU POR NENHUM LIVRO", nomePessoa);
        } else{
            System.out.printf("%s PROCUROU POR ESSES LIVROS:\n", nomePessoa);
            for (String livro : historicoLivros) {
                System.out.println(livro);
            }
        }
    }
}
