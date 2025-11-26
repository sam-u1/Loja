package br.ucs.poo.Loja.Model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Cliente extends PessoaFisica {
    private List<Pedidos> pedidos = new ArrayList<>();

    public Cliente(String nome, String numero, String cpf,String senha,Endereco end) {
        super(nome, numero, cpf,senha,end);
        
    }

    public List<Pedidos> getPedidosData(int dI, int mI, int aI, int dF, int mF, int aF) {
        List<Pedidos> lista = new ArrayList<>();

        for (Pedidos pedido : pedidos) {
            int dia = pedido.getData().getDia();
            int mes = pedido.getData().getMes();
            int ano = pedido.getData().getAno();

            
            boolean depoisOuIgualDataInicial = 
                (ano > aI) || 
                (ano == aI && mes > mI) || 
                (ano == aI && mes == mI && dia >= dI);

            boolean antesOuIgualDataFinal = 
                (ano < aF) || 
                (ano == aF && mes < mF) || 
                (ano == aF && mes == mF && dia <= dF);

            if (depoisOuIgualDataInicial && antesOuIgualDataFinal) {
                lista.add(pedido);
            }
        }

        return lista;
    }
	public List<Pedidos> getPedidos() {
		return pedidos;
	}
	public void adicionarPedidos(Pedidos pedido) {
		pedidos.add(pedido);
	}

	public void setPedidos(List<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}

    
}
