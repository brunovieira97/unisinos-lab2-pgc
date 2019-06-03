package model;

/**
 *
 * @author bruno
 */
public class Arquivo {
    private String nome, usuario;
    private int prioridade;
    private boolean colorido;

    public Arquivo(String nome, boolean colorido) {
        this.nome = nome;
        this.colorido = colorido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        if (prioridade <= 0 || prioridade > 3) {
            System.out.println("Nível de Prioridade Inválido!");
        } else {
            this.prioridade = prioridade;
		   
			if (getPrioridade() == 1) {
                setUsuario("Estagiário");
            } else if (getPrioridade() == 2) {
                setUsuario("Funcionário");
            } else {
                setUsuario("Gerente");
            }
        }
    }

    public boolean isColorido() {
        return colorido;
    }

    public void setColorido(boolean colorido) {
        this.colorido = colorido;
    }

    @Override
    public String toString() {
        return "Arquivo {" + "nome=" + nome + ", usuario=" + usuario + ", prioridade=" + prioridade + ", colorido=" + colorido + "}";
    }
    
    
    
    
}
