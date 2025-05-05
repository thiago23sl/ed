package entities;

import java.util.*;

public class Biblioteca {
    private List<Livro> livros;
    private Map<String, Queue<String>> filaEspera;
    private Map<String, Stack<Livro>> historico;
    private Grafo grafo;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.filaEspera = new HashMap<>();
        this.historico = new HashMap<>();
        this.grafo = new Grafo();
    }

    public void adicionarLivros(String nome, String autor, int anoPublicacao) {
        Livro livro = new Livro(nome, autor, anoPublicacao);
        livros.add(livro);
        grafo.adicionarLivro(livro);
        System.out.println("LIVRO ADICIONADO COM SUCESSO!");
    }

    public void removerLivro(String nome) {
        Livro livro = procurarlivros(nome);
        if (livro != null) {
            livros.remove(livro);
            grafo.removerLivro(livro);
            filaEspera.remove(nome);
            System.out.println("LIVRO REMOVIDO COM SUCESSO!");
        } else {
            System.out.println("LIVRO NÃO ENCONTRADO.");
        }
    }

    public Livro procurarlivros(String nome) {
        for (Livro l : livros) {
            if (l.getnomeLivro().equalsIgnoreCase(nome)) {
                return l;
            }
        }
        return null;
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("NENHUM LIVRO CADASTRADO.");
            return;
        }
        for (Livro l : livros) {
            System.out.println(l);
        }
    }

    public void adicionarListaEspera(String titulo, String usuario) {
        filaEspera.putIfAbsent(titulo, new LinkedList<>());
        filaEspera.get(titulo).add(usuario);
    }

    public void liberarLivro(String titulo) {
        Queue<String> fila = filaEspera.get(titulo);
        if (fila == null || fila.isEmpty()) {
            System.out.println("NÃO HÁ USUÁRIOS NA FILA PARA ESTE LIVRO.");
        } else {
            String proximoUsuario = fila.poll();
            System.out.println("LIVRO LIBERADO PARA: " + proximoUsuario);
        }
    }

    public void registrarConsulta(String usuario, String tituloLivro) {
        Livro livro = procurarlivros(tituloLivro);
        if (livro == null) {
            System.out.println("LIVRO NÃO ENCONTRADO.");
            return;
        }

        historico.putIfAbsent(usuario, new Stack<>());
        historico.get(usuario).push(livro);
        System.out.println("CONSULTA REGISTRADA.");
    }

    public void exibirHistorico(String usuario) {
        Stack<Livro> historicoUsuario = historico.get(usuario);
        if (historicoUsuario == null || historicoUsuario.isEmpty()) {
            System.out.println("NENHUMA CONSULTA REGISTRADA.");
            return;
        }

        System.out.println("HISTÓRICO DE CONSULTA DE " + usuario + ":");
        for (Livro l : historicoUsuario) {
            System.out.println("- " + l.getnomeLivro());
        }
    }

    public boolean recomendarLivroComPeso(String tituloOrigem, String tituloDestino, int peso) {
        Livro origem = procurarlivros(tituloOrigem);
        Livro destino = procurarlivros(tituloDestino);

        if (origem == null || destino == null) {
            return false;
        }

        grafo.adicionarRecomendacaoComPeso(origem, destino, peso);
        return true;
    }

    public void exibirRecomendacoes() {
        grafo.exibirRecomendacoes();
    }

    public Grafo getGrafo() {
        return grafo;
    }
}
