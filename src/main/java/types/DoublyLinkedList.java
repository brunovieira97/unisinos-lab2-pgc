package types;

import exceptions.*;

/**
 * Implementa��o de uma lista linear com armazenamento din�mico,
 * baseado em n�s duplamente encadeados (encadeamento nos dois
 * sentidos).
 */
public class DoublyLinkedList<E> implements List<E> {
	DNode<E> head;
	DNode<E> tail;
	int numElements;

	/**
	 * Constr�i uma lista inicialmente vazia.
	 */
	public DoublyLinkedList() {
		head = tail = null;
		numElements = 0;
	}

	/* (non-Javadoc)
	 * @see br.unisinos.prog2lab2.List#numElements()
	 */
	public int numElements() {
		return numElements;
	}

	/* (non-Javadoc)
	 * @see br.unisinos.prog2lab2.List#isEmpty()
	 */
	public boolean isEmpty() {
		return numElements == 0;
	}

	/* (non-Javadoc)
	 * @see br.unisinos.prog2lab2.List#isFull()
	 */
	public boolean isFull() {
		// uma lista com aloca��o din�mica nunca estar� cheia!
		return false;
	}
	
	/**
	 * Insere um novo elemento na posi��o inicial da lista.
	 * @param element O elemento a ser inserido
	 */
	public void insertFirst(E element) {
		// cria um novo n� e o torna o novo "head"
		DNode<E> newNode = new DNode<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
		}

		// ajusta o total de elementos
		numElements++;
	}
		
	/**
	 * Insere um novo elemento no final da lista.
	 * @param element O elemento a ser inserido
	 */
	public void insertLast(E element) {
		// cria um novo n� e o torna o novo "tail"
		DNode<E> newNode = new DNode<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail = newNode;
		}

		// ajusta o total de elementos
		numElements++;
	}

	/* (non-Javadoc)
	 * @see br.unisinos.prog2lab2.List#insert(java.lang.Object, int)
	 */
	public void insert(E element, int pos) {
		// verifica se a posi��o � v�lida
		if (pos < 0  ||  pos > numElements)
			throw new IndexOutOfBoundsException();
		
		// casos especiais: inser��o no in�cio...
		if (pos == 0)
			insertFirst(element);
		else if (pos == numElements) // ... ou inser��o no final
			insertLast(element);
		else { // caso geral: inser��o no meio da lista
			// localiza o n� imediatamente anterior � posi��o
			// onde o novo ser� inserido
			DNode<E> prev = head;
			for (int i = 0; i < pos-1; i++)
				prev = prev.getNext();
			
			// cria um novo n� e o posiciona logo ap�s "prev",
			// ajustando os apontamentos e o total de elementos
			DNode<E> newNode = new DNode<E>(element);
			newNode.setPrevious(prev);
			newNode.setNext(prev.getNext());
			prev.getNext().setPrevious(newNode);
			prev.setNext(newNode);
			numElements++;
		}
	}

	/**
	 * Remove o primeiro elemento da lista.
	 * @return O elemento removido
	 */
	public E removeFirst() {
		// verifica se h� pelo menos um elemento a ser removido
		if (isEmpty())
			throw new UnderflowException();

		// guarda uma refer�ncia tempor�ria ao elemento sendo removido
		E element = head.getElement();

		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else {
			// ...sen�o, o segundo elemento passa a ser o "head"
			head = head.getNext();
			head.setPrevious(null);
		}

		// ajusta o total de elementos e retorna o removido
		numElements--;
		return element;
	}
	
	/**
	 * Remove o �ltimo elemento da lista.
	 * @return O elemento removido
	 */
	public E removeLast() {
		// verifica se h� pelo menos um elemento a ser removido
		if (isEmpty())
			throw new UnderflowException();

		// guarda uma refer�ncia tempor�ria ao elemento sendo removido
		E element = tail.getElement();
		
		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else {
			// ...sen�o, o pen�ltimo elemento passa a ser o "tail"
			tail = tail.getPrevious();
			tail.setNext(null);
		}

		// ajusta o total de elementos e retorna o removido
		numElements--;
		return element;
	}

	/* (non-Javadoc)
	 * @see br.unisinos.prog2lab2.List#remove(int)
	 */
	public E remove(int pos) {
		// verifica se a posi��o � v�lida
		if (pos < 0  ||  pos >= numElements)
			throw new IndexOutOfBoundsException();
		
		// casos especiais: remo��o do in�cio...
		if (pos == 0)
			return removeFirst();
		else if (pos == numElements-1) // ... ou remo��o do final
			return removeLast();
		else { // caso geral: remo��o do meio da lista
			// localiza o n� imediatamente anterior � posi��o
			// de onde o elemento ser� removido
			DNode<E> prev = head;
			for (int i = 0; i < pos-1; i++)
				prev = prev.getNext();
			
			// guarda uma ref. tempor�ria ao elemento sendo removido
			E element = prev.getNext().getElement();

			// ajusta o encadeamento "pulando" o n� sendo removido
			prev.setNext(prev.getNext().getNext());
			prev.getNext().setPrevious(prev);

			// ajusta o total de elementos e retorna o removido
			numElements--;
			return element;
		}
	}

	/* (non-Javadoc)
	 * @see br.unisinos.prog2lab2.List#get(int)
	 */
	public E get(int pos) {
		// verifica se a posi��o � v�lida
		if (pos < 0  ||  pos >= numElements)
			throw new IndexOutOfBoundsException();

		// percorre o encadeamento at� chegar ao elemento
		DNode<E> current = head;
		for (int i = 0; i < pos; i++)
			current = current.getNext();
		
		return current.getElement();
	}
	
	/* (non-Javadoc)
	 * @see br.unisinos.prog2lab2.List#search(java.lang.Object)
	 */
	public int search(E element) {
		// percorre o encadeamento at� encontrar o elemento
		DNode<E> current = head;
		int i = 0;
		while (current != null) {
			if (element.equals(current.getElement()))
					return i;
			i++;
			current = current.getNext();
		}
		
		// se chegar at� aqui, � porque n�o encontrou
		return -1;
	}
	
	/**
	 * Retorna uma representa��o String da lista.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "";

		DNode<E> current = head;
		while (current != null) {
			s += current.getElement().toString() + " ";
			current = current.getNext();
		}
		return s;
	}
}
