package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exceptions.*;
import types.StaticQueue;

/**
 *
 * @author bruno
 */
public class Impressora {
    private StaticQueue<Arquivo> filaDeImpressao;

    public Impressora() {
        this.filaDeImpressao = new StaticQueue<>(2);
    }

    public StaticQueue<Arquivo> getFilaDeImpressao() {
        return filaDeImpressao;
    }

    public void adicionarArquivoAFila(Arquivo arquivo) throws OverflowException{
        if(filaDeImpressao.isFull()) {
            throw new OverflowException();
		}
		
        filaDeImpressao.enqueue(arquivo);
    }
    
    public void imprimirArquivo() throws UnderflowException, FileNotFoundException, IOException{
        if (filaDeImpressao.isEmpty()) {
            throw new UnderflowException();
		}
		
        Arquivo arquivo = filaDeImpressao.dequeue();
        FileReader fr = new FileReader(arquivo.getNome());
        BufferedReader br = new BufferedReader(fr);
		String linha;
		
		try {
			while ((linha = br.readLine()) != null) {
				if (!linha.equals("")) {
					System.out.println(linha);
				}
	
				continue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
			fr.close();
		}
    }
    
    
    
}
