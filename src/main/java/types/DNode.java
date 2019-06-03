package types;


public class DNode<E> {
	protected E element;
	protected DNode<E> prev;
	protected DNode<E> next;

	public DNode(E e) {
		element = e;
		prev = next = null;
	}

	public E getElement() {
		return element; 
	}

	public DNode<E> getPrevious() { 
		return prev;
	}

	public DNode<E> getNext() { 
		return next;
	}

	public void setElement(E e) { 
		element = e; 
	}

	public void setPrevious(DNode<E> p) {
		prev = p; 
	}

	public void setNext(DNode<E> n) {
		next = n; 
	}
}

