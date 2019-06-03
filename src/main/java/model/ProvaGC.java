package model;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

import exceptions.*;

/**
 * @author bruno
 */
public class ProvaGC {
    
    public static int geraPrioridadeAleatoria() {
		int tipoDeUsuario = (int) ((Math.random() * 3) + 1);
		
        return tipoDeUsuario;
    }
    
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {

            Impressora pretoBranco = new Impressora();
            Impressora colorida = new Impressora();
            GerenciadorDeImpressao gerenciador = new GerenciadorDeImpressao(pretoBranco, colorida);
        
			menuPrincipal(gerenciador);
			
        } catch (UnderflowException ue) {

			System.out.println("Erro! Lista ou Fila Vazias");
			
        } catch (IOException ex) {

			System.out.println("Erro de I/O");
			
        }
        
    }
    
    public static void menuPrincipal(GerenciadorDeImpressao gerenciador) throws UnderflowException, IOException {
        int opcao;
		
		do {

            System.out.println("\nDigite a opção desejada: ");
            System.out.println(" 1 - Enviar Documento");
            System.out.println(" 2 - Imprimir Documento");
            System.out.println(" 3 - Cancelar Impressão");
            System.out.println(" 0 - Sair");
                
            opcao = scanner.nextInt();
			
			switch (opcao) {
                case 1:
                    gerenciador.adicionarArquivoAListaDeImpressoesPendentes(enviarDocumento(geraPrioridadeAleatoria()));
					break;
					
                case 2:
					imprimeArquivo(gerenciador);
					
                case 3:
                    cancelarImpressao(gerenciador);
					break;
					
                case 0:
                    System.exit(0);
					break;
					
                default:
                    System.out.println("Opção Inválida!");
			}
			
        } while (opcao != 0);
    }
    
    public static Arquivo enviarDocumento(int prioridade) {
        int opcao;
		Arquivo arquivo = null;
		
        do {

            System.out.println("Prioridade Atual: " + prioridade);
            System.out.println("\nEscolha o tipo de arquivo desejado: ");
            System.out.println(" 1 - Preto e Branco");
            System.out.println(" 2 - Colorido");
            System.out.println(" 0 - Cancelar Envio");
                  
            opcao = scanner.nextInt();
			
			switch (opcao) {
                case 1:
                    arquivo = criaArquivo(prioridade, false);
					break;
					
                case 2:
                    arquivo = criaArquivo(prioridade, true);
					break;
					
                case 0:
                    System.out.println("Operação de Envio Cancelada");
					break;
					
                default:
                    System.out.println("Opção Inválida!");
			}

		} while (opcao != 0);
		
        return arquivo;
    }
    
    public static Arquivo criaArquivo(int prioridade, boolean colorido) {
        String filename = "";
		
		while (filename.isEmpty()) {
            filename = JOptionPane.showInputDialog(null, "Digite o nome do arquivo: ", "Enviar Documento", JOptionPane.PLAIN_MESSAGE);
        }
        
        Arquivo arquivo = new Arquivo(filename, colorido);
        arquivo.setPrioridade(prioridade);
		
		return arquivo;
    }
    
    public static void imprimeArquivo(GerenciadorDeImpressao gerenciador) throws UnderflowException, IOException {
        int opcao;
		
		do {

            System.out.println("\nEscolha a impressora que deve imprimir: ");
            System.out.println(" 1 - Preto e Branco");
            System.out.println(" 2 - Colorido");
            System.out.println(" 0 - Cancelar Impressão");
                  
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    gerenciador.getPretoBranco().imprimirArquivo();
                    break;
                case 2:
                    gerenciador.getColorida().imprimirArquivo();
                    break;
                case 0:
                    System.out.println("Operação de Impressão Cancelada");
                    break;
                default:
                    System.out.println("Opção Inválida!");
			}
			
        } while (opcao != 0);
    }
    
    public static void cancelarImpressao(GerenciadorDeImpressao gerenciador) {
        String filename = "";
		
		while (filename.isEmpty()) {
            filename = JOptionPane.showInputDialog(null, "Digite o nome do arquivo: ", "Cancelar Documento", JOptionPane.PLAIN_MESSAGE);
		}
		
		try {
			gerenciador.removeArquivoDaFila(filename);
		} catch(Exception e) {
			e.printStackTrace();
		}
        
    }
    
}
