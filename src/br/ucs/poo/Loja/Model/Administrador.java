package br.ucs.poo.Loja.Model;


@SuppressWarnings("serial")
public class Administrador extends PessoaFisica {
    private String usuario;
   

    public Administrador(String nome, String numero, String cpf, String usuario, String senha,Endereco end) {
        super(nome, numero, cpf,senha,end);
        this.usuario = usuario;
    }

    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
