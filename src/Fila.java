import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Fila<E> {
    
    private Celula<E> prim;
    private Celula<E> ult;

    public Fila() {
        Celula<E> sentinela = new Celula<E>();
        prim = sentinela;
        ult = sentinela;
    }

    public void enfileirar(E item) {
        Celula<E> novo = new Celula<E>(item);
        ult.setProximo(novo);
        ult = novo;
    }

    public E desenfileirar() {
        if (vazia()) {
            throw new NoSuchElementException("Não tem nenhum item na fila! Bobão >:(");
        }
        prim = prim.getProximo();
        E item = prim.getItem();
        return item;
    }

    public boolean vazia() {
        return prim == ult;
    }

    public double calcularValorMedio(Function<E, Double> extrator, int quantidade) {
        if (vazia() || quantidade <= 0) {
            return 0.0;
        }
        double soma = 0.0;
        int contador = 0;
        Celula<E> atual = prim.getProximo();
        while (atual != null && contador < quantidade) {
            soma += extrator.apply(atual.getItem());
            contador++;
            atual = atual.getProximo();
        }
        return contador > 0 ? soma / contador : 0.0;
    }

    public Fila<E> filtrar(Predicate<E> condicional, int quantidade) {
        Fila<E> novaFila = new Fila<E>();
        if (vazia() || quantidade <= 0) {
            return novaFila;
        }
        int contador = 0;
        Celula<E> atual = prim.getProximo();
        while (atual != null && contador < quantidade) {
            E elemento = atual.getItem();

            if (condicional.test(elemento)) {
                novaFila.enfileirar(elemento);
            }

            contador++;
            atual = atual.getProximo();
        }
        return novaFila;
    }
}
