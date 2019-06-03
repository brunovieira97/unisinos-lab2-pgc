package model;

import javax.swing.JOptionPane;

import types.DoublyLinkedList;

/**
 *
 * @author bruno
 */
public class GerenciadorDeImpressao {
    private DoublyLinkedList<Arquivo> listaDeImpressoesPendentes;
    private Impressora pretoBranco, colorida;
    
    
    public GerenciadorDeImpressao(Impressora pretoBranco, Impressora colorida) {
        this.listaDeImpressoesPendentes = new DoublyLinkedList<>();
        this.pretoBranco = pretoBranco;
        this.colorida = colorida;
    }
    
    // * Test-only method
	/*
    public void visualizarListaDeImpressao(){
        for (int i = 0; i < listaDeImpressoesPendentes.numElements(); i++) {
            System.out.println(listaDeImpressoesPendentes.get(i));
        }
    }
	*/

    public DoublyLinkedList<Arquivo> getListaDeImpressoesPendentes() {
        return listaDeImpressoesPendentes;
    }

    public void setListaDeImpressoesPendentes(DoublyLinkedList<Arquivo> listaDeImpressoesPendentes) {
        this.listaDeImpressoesPendentes = listaDeImpressoesPendentes;
    }

    public Impressora getPretoBranco() {
        return pretoBranco;
    }

    public void setPretoBranco(Impressora pretoBranco) {
        this.pretoBranco = pretoBranco;
    }

    public Impressora getColorida() {
        return colorida;
    }

    public void setColorida(Impressora colorida) {
        this.colorida = colorida;
    }
    
    public void adicionarArquivoAListaDeImpressoesPendentes(Arquivo arquivo) {
        if (arquivo != null) {
            int prioridadeAtual = arquivo.getPrioridade();
            int posicao = 0;
			
			for (int i = 0; i < listaDeImpressoesPendentes.numElements(); i++) {
                if (!(listaDeImpressoesPendentes.get(i).getPrioridade() > prioridadeAtual)) {
                    posicao = i;
                }
            }
            
            listaDeImpressoesPendentes.insert(arquivo, posicao);
            this.enviaArquivoParaImpressao();
        }
    }
    
    public void enviaArquivoParaImpressao() {
        Arquivo arquivo = listaDeImpressoesPendentes.get(0);
		
		if (arquivo.isColorido()) {
            if (this.colorida.getFilaDeImpressao().isFull()) {
                JOptionPane.showMessageDialog(null, "A fila de impressão da Impressora Colorida está cheia. Por favor, realize as impressões pendentes e tente novamente.", "Erro!", JOptionPane.ERROR_MESSAGE);
            } else {
                this.colorida.adicionarArquivoAFila(this.listaDeImpressoesPendentes.removeFirst());
            }
        } else {
            if (this.pretoBranco.getFilaDeImpressao().isFull()) {
                JOptionPane.showMessageDialog(null, "A fila de impressão da Impressora P/B está cheia. Por favor, realize as impressões pendentes e tente novamente.", "Erro!", JOptionPane.ERROR_MESSAGE);
            } else {
                this.pretoBranco.adicionarArquivoAFila(this.listaDeImpressoesPendentes.removeFirst());
            }
        }
    }
    
    public void removeArquivoDaFila(String filename) throws Exception {
		boolean removedSomeFile = false;

        for (int i = 0; i < this.listaDeImpressoesPendentes.numElements(); i++) {
            if(listaDeImpressoesPendentes.get(i).getNome() == filename) {
				listaDeImpressoesPendentes.remove(i);
				
				removedSomeFile = true;
            }
		}
		
		if (!removedSomeFile) {
			throw new Exception("O arquivo informado não foi encontrado na fila de impressão!");
		}
    }
}
