package br.ucs.poo.Loja.Model;

import java.util.ArrayList;
import java.util.List;

import br.ucs.poo.Loja.Excessoes.FornecedorNaoEncontradoException;
import br.ucs.poo.Loja.Excessoes.PessoaNaoEncontradaException;
import br.ucs.poo.Loja.Excessoes.ProdutoNaoEncontradoException;

@SuppressWarnings("serial")
public class Loja extends PessoaJuridica {
	
	private List<Pedidos> pedidos = new ArrayList<>();
	private List<PessoaFisica> pessoas = new ArrayList<>();
	private List<Fornecedor> fornecedores = new ArrayList<>();
	private List<Produto> produtos = new ArrayList<>();

	public Loja(String nome, String numero, String cnpj, Endereco end) {
		super(nome, numero, cnpj, end);
	}
	public Loja(PessoaJuridica pessJu) {
		super(pessJu.getNome(),pessJu.getNumero(),pessJu.getCnpj(),pessJu.getEndereco());
	}
	public Loja() {
		
	}

	public int adicionarPessoa(PessoaFisica pessoa) {
		if (pessoa != null) {
			pessoa.setIdConta();
			pessoas.add(pessoa);
			return 1;
		} else {
			return 0;
		}

	}
	public void excluirFornecedorComProdutos(int idFornecedor) throws FornecedorNaoEncontradoException {
	    Fornecedor fornecedor = buscarPorId(idFornecedor);
	    if (fornecedor == null) {
	        throw new FornecedorNaoEncontradoException("Fornecedor com ID '" + idFornecedor + "' não encontrado.");
	    }

	    // Remove os produtos associados ao fornecedor
	    List<Produto> produtosParaRemover = new ArrayList<>();
	    for (Produto produto : produtos) {
	        if (produto.getFornecedor().getIdFornecedor() == idFornecedor) {
	            produtosParaRemover.add(produto);
	        }
	    }
	    produtos.removeAll(produtosParaRemover);
	    fornecedores.remove(fornecedor);
	}

	public ArrayList<PessoaFisica> listarPessoas() {
		List<PessoaFisica> pess = new ArrayList<>();
		for (PessoaFisica pessoa : pessoas) {
			pess.add(pessoa);
		}
		return (ArrayList<PessoaFisica>) pessoas;
	}

	public PessoaFisica buscarPessoa(String senha, String nome) {
		for (PessoaFisica pessoa : pessoas) {
			if (pessoa.getSenha().equals(senha) && pessoa.getNome().equalsIgnoreCase(nome)) {
				return pessoa;
			}
		}
		return null;
	}

	public boolean excluirPessoa(String senha, String nome) throws PessoaNaoEncontradaException {
		for (PessoaFisica pessoa : pessoas) {
			if (pessoa.getSenha().equals(senha) && pessoa.getNome().equals(nome)) {
				pessoas.remove(pessoa);
				return true;
			}
		}
		return false;

	}

	public String listarPessoas(int escolha) {
		StringBuilder sc = new StringBuilder();

		for (PessoaFisica pessoa : pessoas) {
			if (escolha == 4) {

				if (pessoa instanceof Cliente)
					sc.append("Id: " + pessoa.getId() + " - Senha: " + pessoa.getSenha() + " - Nome: "
							+ pessoa.getNome() + " - Endereço " + pessoa.dadosEndereco());
			} else {
				if (pessoa instanceof Administrador)
					sc.append("Id: " + pessoa.getId() + " - Senha: " + pessoa.getSenha() + " - Nome: "
							+ pessoa.getNome() + " - Endereço " + pessoa.dadosEndereco());
			}
		}
		if (sc.isEmpty()) {
			return ("Lista vazia");
		}
		return sc.toString();
	}

	public boolean adicionarFornecedor(Fornecedor fornecedor) {
		if (fornecedor != null) {
			fornecedores.add(fornecedor);
			return true;
		}
		return false;
	}

	// adicionar função para remover todos os produtos com o fornecedor removido
	public void excluirFornecedor(int idFornecedor) throws FornecedorNaoEncontradoException {
		for (Fornecedor fornecedor : fornecedores) {
			if (fornecedor.getIdFornecedor() == idFornecedor) {
				fornecedores.remove(fornecedor);
				return;
			}
		}

	}

	

	public Fornecedor buscarPorId(int idFornecedor) {
		for (Fornecedor fornecedor : fornecedores) {
			if (fornecedor.getIdFornecedor() == idFornecedor) {
				return fornecedor;
			}
		}
		return null;
	}

	public ArrayList<Fornecedor> buscarPorNomeFornecedor(String nome) {
		List<Fornecedor> pesquisados = new ArrayList<>();
		for (Fornecedor fornecedor : fornecedores) {
			int contem = 0;
			for (int pesquisa = 0; pesquisa < nome.length(); pesquisa++) {
				for (int palavra = 0; palavra < fornecedor.getNome().length(); palavra++) {
					if (fornecedor.getNome().toLowerCase().indexOf(nome.toLowerCase().charAt(pesquisa)) > -1) {
						contem++;
						break;
					}
				}
			}
			if (contem == nome.length()) {
				pesquisados.add(fornecedor);
			}
		}
		return (ArrayList<Fornecedor>) pesquisados;
	}

	public String listarFornecedores() {
		StringBuilder sc = new StringBuilder();
		for (Fornecedor fornecedor : fornecedores) {
			sc.append("Id: " + fornecedor.getIdFornecedor() + " - CNPJ: " + fornecedor.getCnpj() + " - Nome: "
					+ fornecedor.getNome());
			sc.append("\n");
		}
		return sc.toString();
	}

	public String listarEndFornecedores() {
		StringBuilder sc = new StringBuilder();

		for (Fornecedor fornecedor : fornecedores) {
			sc.append("Id: " + fornecedor.getIdFornecedor() + " - CNPJ: " + fornecedor.getCnpj() + " - Nome: "
					+ fornecedor.getNome() + " - Endereço " + fornecedor.dadosEndereco());
		}
		return sc.toString();
		
	}

	public boolean adicionarProduto(Produto produto) {
		if (produto != null) {
			produtos.add(produto);
			return true;
		}
		return false;
	}

	public void excluirProduto(int codigo) throws ProdutoNaoEncontradoException {
		for (Produto produto : produtos) {
			if (produto.getCodigo() == codigo) {
				produtos.remove(produto);
				return;
			}
		}

	}
	// adicionar opção de alterar só alguns dados ex: só nome, só cnpj, só endereço,
	// só número e adicionar função alterar fornecedor

	public Produto buscarPorCodigo(int codigo) {
		for (Produto produto : produtos) {
			if (produto.getCodigo() == codigo) {
				return produto;
			}
		}
		return null;
	}
	public List<Produto> listaProdutos(){
		return (List<Produto>) produtos;
	}

	public List<Produto> buscarPorNome(String nome) {
		List<Produto> pesquisados = new ArrayList<>();
		for (Produto produto : produtos) {
			int contem = 0;
			for (int pesquisa = 0; pesquisa < nome.length(); pesquisa++) {
				for (int palavra = 0; palavra < produto.getNome().length(); palavra++) {
					if (produto.getNome().toLowerCase().indexOf(nome.toLowerCase().charAt(pesquisa)) > -1) {
						contem++;
						break;
					}
				}
			}
			if (contem == nome.length()) {
				pesquisados.add(produto);
			}
		}
		return (ArrayList<Produto>) pesquisados;
	}
	public void adicionarPedido(Pedidos pedido) {
	    if (pedido != null) {
	        pedidos.add(pedido);
	    }
	}

	public List<Pedidos> getPedidos() {
	    return pedidos;
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
	public Pedidos getPedidosPorCod(int cod) {
		for(Pedidos pedido:pedidos) {
			if(pedido.getCod()==cod) {
				return pedido;
			}
		}
		return null;
	}
}
