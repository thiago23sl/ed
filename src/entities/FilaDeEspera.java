package entities;
import java.util.*;
public class FilaDeEspera {
    private String titulo;
    private Queue<String> filaDeEspera;

    public FilaDeEspera(String titulo){
        this.titulo = titulo;
        this.filaDeEspera = new LinkedList<>();
    }

    public String getTitulo(){
        return titulo;
    }

    public void adicionarPessoa(String pessoa){
        filaDeEspera.add(pessoa);
        System.out.printf("%s FOI ADICIONADO A LISTA DE ESPERA\n", pessoa);
    }

    public String proximaPessoa(){
        return filaDeEspera.poll();
    }

    public boolean pessoaEmEspera(){
        return !filaDeEspera.isEmpty();
    }
}