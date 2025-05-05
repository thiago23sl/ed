package entities;

import java.util.*;

public class Grafo {
    private Map<Livro, Map<Livro, Integer>> grafo;

    public Grafo() {
        this.grafo = new HashMap<>();
    }

    public void adicionarLivro(Livro livro) {
        grafo.putIfAbsent(livro, new HashMap<>());
    }

    public void adicionarRecomendacaoComPeso(Livro origem, Livro destino, int peso) {
        grafo.putIfAbsent(origem, new HashMap<>());
        grafo.putIfAbsent(destino, new HashMap<>());
        grafo.get(origem).put(destino, peso);
    }

    public void exibirRecomendacoes() {
        if (grafo.isEmpty()) {
            System.out.println("NÃO HÁ LIVROS CADASTRADOS, PORTANTO NÃO HÁ NADA PARA EXIBIR.");
            return;
        }

        for (Livro livro : grafo.keySet()) {
            System.out.println("SE VOCÊ GOSTOU DE \"" + livro.getnomeLivro() + "\", TALVEZ GOSTE DE:");

            Map<Livro, Integer> recomendados = grafo.get(livro);

            if (recomendados != null && !recomendados.isEmpty()) {
                for (Map.Entry<Livro, Integer> entry : recomendados.entrySet()) {
                    System.out.println("- " + entry.getKey().getnomeLivro() + " (peso: " + entry.getValue() + ")");
                }
            } else {
                System.out.println("- NENHUMA RECOMENDAÇÃO DISPONÍVEL.");
            }

            System.out.println();
        }
    }

    public Map<Livro, Integer> dijkstra(Livro origem) {
        Map<Livro, Integer> distancias = new HashMap<>();
        PriorityQueue<LivroDistancia> fila = new PriorityQueue<>(Comparator.comparingInt(ld -> ld.distancia));
        Set<Livro> visitados = new HashSet<>();

        for (Livro livro : grafo.keySet()) {
            distancias.put(livro, Integer.MAX_VALUE);
        }

        distancias.put(origem, 0);
        fila.add(new LivroDistancia(origem, 0));

        while (!fila.isEmpty()) {
            LivroDistancia atual = fila.poll();
            Livro livroAtual = atual.livro;

            if (visitados.contains(livroAtual)) continue;
            visitados.add(livroAtual);

            Map<Livro, Integer> vizinhos = grafo.getOrDefault(livroAtual, new HashMap<>());

            for (Map.Entry<Livro, Integer> vizinho : vizinhos.entrySet()) {
                int novaDistancia = distancias.get(livroAtual) + vizinho.getValue();

                if (novaDistancia < distancias.get(vizinho.getKey())) {
                    distancias.put(vizinho.getKey(), novaDistancia);
                    fila.add(new LivroDistancia(vizinho.getKey(), novaDistancia));
                }
            }
        }

        return distancias;
    }

    void adicionarRecomendado(Livro base, Livro recomendado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void removerLivro(Livro livro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class LivroDistancia {
        Livro livro;
        int distancia;

        public LivroDistancia(Livro livro, int distancia) {
            this.livro = livro;
            this.distancia = distancia;
        }
    }
}

   // public void mostrarGrafo(){
       // for (Livro l : grafo.keySet()) {
            //System.out.println("O LIVRO: " + l.getnomeLivro() + "\n" + "\nRECOMENDA\n");
            //for (Livro r : grafo.get(l)) {
                //System.out.println("ESSE LIVRO: " + r.getnomeLivro());
           // }
           // System.out.println();
       // }
//}
