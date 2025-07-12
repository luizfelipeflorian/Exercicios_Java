package biblioteca;
//testar depois import java.util.LinkedList;   LinkedList<String> minhaLista = new LinkedList<>();




// pesquiseri na internet, tinha duvidas sobre como fazer o armazenamento
// Nó genérico para lista encadeada
class Node<T> { // T é um tipo genérico, o que significa que posso criar listas de qualquer tipo: String, Integer, Aluno, etc.
    T data; // declara o tipo do dado sendo igual o da lista
    Node<T> next;
    Node(T data) { this.data = data; } //inicializa o nó com o valor recebido
}

class ListaEncadeada<T> { // Lista encadeada genérica
    Node<T> head;
    void adicionar(T data) { // Adiciona um elemento no final da lista
        Node<T> novo = new Node<>(data);
        if (head == null) head = novo;
        else {
            Node<T> atual = head; // nó temporario
            while (atual.next != null) atual = atual.next; // percorre os nós da lista até o último nó
            atual.next = novo;
        }
    }
    T buscar(java.util.function.Predicate<T> pred) { // Busca um valor usando uma condição tipo: n -> n == {dado}
        Node<T> atual = head;
        while (atual != null) {
            if (pred.test(atual.data)) return atual.data; // verifica se ele satisfaz uma condição usando pred.test(atual.data)
            atual = atual.next;
        }
        return null;
    }
}