package br.ucs.poo.Loja.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.ucs.poo.Loja.Excessoes.FornecedorNaoEncontradoException;
import br.ucs.poo.Loja.Excessoes.PessoaNaoEncontradaException;
import br.ucs.poo.Loja.Excessoes.ProdutoNaoEncontradoException;
import br.ucs.poo.Loja.Model.Administrador;
import br.ucs.poo.Loja.Model.Cliente;
import br.ucs.poo.Loja.Model.Data;
import br.ucs.poo.Loja.Model.DataEnvio;
import br.ucs.poo.Loja.Model.Endereco;
import br.ucs.poo.Loja.Model.Estoque;
import br.ucs.poo.Loja.Model.Fornecedor;
import br.ucs.poo.Loja.Model.Loja;
import br.ucs.poo.Loja.Model.Pedidos;
import br.ucs.poo.Loja.Model.PessoaFisica;
import br.ucs.poo.Loja.Model.Produto;

public class InterfaceUsuario {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Loja loja = new Loja();
		// Loja loja = new Loja("Loja de verduras","99512-4512","612386124",new
		// Endereco("Vitoria","Bela Vista","Caxias do Sul"));
		// loja.adicionarPessoa(new Administrador("Bruno", "99512-4512", "512643612",
		// "admin", "1234",new Endereco("Vitoria", "Bela Vista", "Caxias do Sul")));
		try {
			FileInputStream file = new FileInputStream("Loja.dat");
			if (file.available() != 0) {
				ObjectInputStream ob = new ObjectInputStream(file);
				Object objeto = ob.readObject();
				if (objeto != null) {
					loja = (Loja) objeto;
				}
				ob.close();
				file.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}

		int log = 0;

		for (;;) {
			boolean valida = false;
			while (valida == false) {
				try {
					System.out.println("=== Entre- 1 ===");
					System.out.println("=== Cadastre-se- 2 ===");
					System.out.println("=== Sair- 3 ===");
					log = scan.nextInt();
					scan.nextLine();
					valida = true;
				} catch (InputMismatchException e) {
					System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
					scan.nextLine();
				}
			}
			if (log == 2) {

				System.out.println("Digite o nome:");
				String usuario = scan.nextLine();
				System.out.println("Digite a senha");
				String senha = scan.nextLine();
				System.out.println("Telefone");
				String telefone = scan.nextLine();
				System.out.println("CPF");
				String CPF = scan.nextLine();

				System.out.println("Rua");
				String rua = scan.nextLine();
				System.out.println("Bairro");
				String bairro = scan.nextLine();
				System.out.println("Cidade");
				String cidade = scan.nextLine();

				Endereco ende = new Endereco(rua, bairro, cidade);

				int validar = loja.adicionarPessoa(new Cliente(usuario, telefone, CPF, senha, ende));
				if (validar == 1) {
					System.out.println("Cliente cadastrado com sucesso!");
				} else if (validar == 0) {
					System.out.println("Não foi possível cadastrar o cliente.");
				}

			} else if (log == 1) {
				scan.nextLine();
				System.out.println("=== Sistema de Gestão ===");

				System.out.print("Usuário: ");
				String usuario = scan.nextLine();
				System.out.print("Senha: ");
				String senha = scan.nextLine();

				Administrador admin = null;
				Cliente logado = null;

				int opcao = 0;

				for (;;) {

					PessoaFisica escolha = loja.buscarPessoa(senha, usuario);

					if (escolha instanceof Administrador) {

						admin = (Administrador) escolha;
						System.out.println("=== Admin logado ===");

						for (;;) {
							valida = false;
							while (valida == false) {
								try {

									System.out.println("=== Opções ===");
									// fazer função ver pedidos
									System.out.println("1-Cadastrar novo admin");
									System.out.println("2-Excluir conta");
									System.out.println("3-Gerenciar loja");
									System.out.println("4-Listar clientes");
									System.out.println("5-Listar admins");
									System.out.println("6-Listar todos os fornecedores");
									System.out.println("7-Ver Pedidos");
									System.out.println("8-Deslogar");

									opcao = scan.nextInt();
									scan.nextLine();
									valida = true;
								} catch (InputMismatchException e) {
									System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
									scan.nextLine();
								}
							}
							if (opcao == 1) {

								System.out.println("Digite o nome:");
								usuario = scan.nextLine();
								System.out.println("Digite a senha");
								senha = scan.nextLine();
								System.out.println("Telefone");
								String telefone = scan.nextLine();
								System.out.println("CPF");
								String CPF = scan.nextLine();

								System.out.println("Rua");
								String rua = scan.nextLine();
								System.out.println("Bairro");
								String bairro = scan.nextLine();
								System.out.println("Cidade");
								String cidade = scan.nextLine();

								Endereco end = new Endereco(rua, bairro, cidade);
								int validar = loja.adicionarPessoa(
										new Administrador(usuario, telefone, CPF, "admin", senha, end));
								if (validar == 1) {
									System.out.println("Admin cadastrado com sucesso!");
								} else if (validar == 0) {
									System.out.println("Não foi possível cadastrar o admin.");
								} else if (validar == -1) {
									System.out.println("Nenhum dos campos pode estar vazio.");
								}

							} else if (opcao == 2) {

								System.out.println("Digite o nome:");
								usuario = scan.nextLine();
								System.out.println("Digite a senha");
								senha = scan.nextLine();

								try {
									if (loja.excluirPessoa(senha, usuario) == true) {
										System.out.println("Conta excluída com sucesso!");
									} else {
										System.out.println("Conta não encontrada!");
									}
								} catch (PessoaNaoEncontradaException pe) {
									System.out.println("Erro: " + pe.getMessage());
								}

							} else if (opcao == 3) {

								exibirMenu(admin, scan, loja);

							} else if (opcao == 4) {

								System.out.println(loja.listarPessoas(opcao));
								scan.nextLine();

							} else if (opcao == 5) {

								System.out.println(loja.listarPessoas(opcao));
								scan.nextLine();

							} else if (opcao == 6) {
								System.out.println(loja.listarEndFornecedores());
								scan.nextLine();
							} else if (opcao == 7) {
								int escolha1 = 0;
								for (;;) {
									try {
										System.out.println("1-Ver todos os pedidos");
										System.out.println("2-Pesquisar por data");
										System.out.println("2-Pesquisar por código");
										escolha1 = scan.nextInt();
										if (escolha1 < 1 || escolha1 > 3) {
											continue;
										} else {
											break;
										}
									} catch (InputMismatchException e) {
										System.out.println("Digite um número de 1 a 3");
									}
								}
								List<Pedidos> pedidos = new ArrayList<>();
								pedidos.clear();
								if (escolha1 == 1) {

									pedidos.addAll(loja.getPedidos());

								} else if (escolha1 == 2) {
									int diaI = 0;
									int diaF = 0;
									int mesI = 0;
									int mesF = 0;
									int anoI = 0;
									int anoF = 0;
									for (;;) {
										try {
											System.out.println("Digite a data inicial: ");
											System.out.println("Dia: ");
											diaI = scan.nextInt();
											System.out.println("Mês: ");
											mesI = scan.nextInt();
											System.out.println("Ano: ");
											anoI = scan.nextInt();
											System.out.println("Digite a data final: ");
											System.out.println("Dia: ");
											diaF = scan.nextInt();
											System.out.println("Mês: ");
											mesF = scan.nextInt();
											System.out.println("Ano: ");
											anoF = scan.nextInt();
											scan.nextLine();
											if (escolha1 < 1 || escolha1 > 2) {
												continue;
											} else {
												break;
											}

										} catch (InputMismatchException e) {
											System.out.println("Somente números!");
										}
									}

									pedidos.addAll(loja.getPedidosData(diaI, mesI, anoI, diaF, mesF, anoF));

								}else if(escolha1 ==3) {
									int cod=0;
									for (;;) {
										try {
											System.out.println("Digite o código: ");
											cod = scan.nextInt();
											break;
										} catch (InputMismatchException e) {
											System.out.println("Somente números!");
										}
									}
									if(loja.getPedidosPorCod(cod)!=null) {
										pedidos.add(loja.getPedidosPorCod(cod));
									}
								}
								if (pedidos.isEmpty()) {
									System.out.println("Nenhum pedido encontrado.");
								} else {
									System.out.println("--- Lista de Pedidos ---");
									for (Pedidos pedido : pedidos) {
										System.out.println("-----------------");
										System.out.println("Código: " + pedido.getCod());
										System.out.println("Cliente: " + pedido.getCliente().getNome());
										System.out.println("Dia: " + pedido.getData().getDia() + " mes: "
												+ pedido.getData().getMes() + " Ano: " + pedido.getData().getAno());
										System.out.println("Status: " + pedido.getStatus());
										System.out.println("Valor Total: R$" + pedido.getValorTotal());

										System.out.println("Produtos:");
										for (Map.Entry<Produto, Integer> entry : pedido.getProdutos().entrySet()) {
											System.out.println(
													" - " + entry.getKey().getNome() + " x" + entry.getValue());
										}
										System.out.println("-----------------");
										System.out.println("-------------------------");
									}

									int codPedido = 0;
									for (;;) {
										System.out.println("Digite o código de um pedido para alteração de status.");
										try {
											codPedido = scan.nextInt();
											scan.nextLine();
											break;
										} catch (InputMismatchException e) {
											System.out.println("Somente números.");
										}
									}
									Pedidos pedidoSelecionado = new Pedidos();
									pedidoSelecionado = loja.getPedidosPorCod(codPedido);

									if (pedidoSelecionado == null) {
										System.out.println("Pedido com o código: " + codPedido + " não encontrado");
									} else {
										int op = 0;
										for (;;) {
											System.out.println("Qual o novo status do pedido n° "
													+ pedidoSelecionado.getCod() + "?");
											System.out.println("1-Pedido cancelado.");
											System.out.println("2-Pedido Entregue");
											System.out.println("3-Cancelar alteração");
											try {
												op = scan.nextInt();
												scan.nextLine();
												if (op < 1 || op > 3) {
													System.out.println("Opção inválida, somente números de 1 a 3.");
													continue;
												} else {
													break;
												}
											} catch (InputMismatchException e) {
												System.out.println("Somente números.");
											}
										}
										if (op == 1) {
											pedidoSelecionado.setStatus("Cancelado");
											pedidoSelecionado.retornaEstoque();
											System.out.println("Pedido cancelado");

										} else if (op == 2) {
											int dia = 0;
											int mes = 0;
											int ano = 0;
											for (;;) {
												try {
													System.out.println("Informe a data de entrega:");
													System.out.println("Dia: ");
													dia = scan.nextInt();
													System.out.println("Mês: ");
													mes = scan.nextInt();
													System.out.println("Ano:");
													ano = scan.nextInt();
													scan.nextLine();
													break;
												} catch (InputMismatchException e) {
													System.out.println("Somente números");
												}
											}
											pedidoSelecionado.setStatus("Entregue");
											DataEnvio data = new DataEnvio(dia, mes, ano);
											pedidoSelecionado.setDataEnvio(data);
											System.out.println("Condição alterada com sucesso!");
										}
									}

								}

							} else if (opcao == 8) {

								System.out.println("Deslogando...");
								break;

							} else {

								System.out.println("Opção inválida");

							}
						}
					} else if (escolha instanceof Cliente) {

						logado = (Cliente) escolha;
						exibirMenuCliente(logado, scan, loja);
						break;

					} else if (escolha == null) {

						System.out.println("Usuário ou senha incorretos.");
						break;
					}

					break;

				}
			} else if (log == 3) {

				scan.close();

				try {
					FileOutputStream file = new FileOutputStream("Loja.dat");
					ObjectOutputStream ob = new ObjectOutputStream(file);

					ob.writeObject(loja);

					ob.close();
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			} else {

				System.out.println("Opção inválida");

			}
		}
	}

	private static void exibirMenuCliente(Cliente cliente, Scanner scanner, Loja loja) {

		while (cliente != null) {
			int opcao = 0;
			try {
				System.out.println("=== Opções ===");
				System.out.println("1-Comprar");
				System.out.println("2-Ver pedidos");
				System.out.println("3-Alterar informações");
				System.out.println("4-excluir Conta");
				System.out.println("5-deslogar");
				opcao = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
				scanner.nextLine();
			}
			if (opcao == 1) {

				double total = 0;
				String select;
				int dia = -1;
				int cancelar = 0;
				int mes = 0;
				int ano = 0;
				Map<Produto, Integer> mapaProdutos = new HashMap<>();
				List<Produto> listaProdutos = new ArrayList<>();

				for (;;) {
					System.out.println("=== Opções de compra===");
					int con = 0;
					int quantidade = 0;
					listaProdutos.removeAll(listaProdutos);
					System.out.print("Digite o nome ou código de um produto para pesquisa:");
					String nome = scanner.nextLine();
					listaProdutos.addAll(loja.buscarPorNome(nome));
					try {
						int num = Integer.parseInt(nome);
						if (loja.buscarPorCodigo(num) != null) {
							listaProdutos.add(loja.buscarPorCodigo(num));
						}
					} catch (NumberFormatException e) {

					}

					Collections.sort(listaProdutos);
					if (listaProdutos.isEmpty() == true) {
						int escolha = 0;
						for (;;) {

							System.out.println("Nenhum produto encontrado, continuar compra?1-sim 2-não");
							try {
								escolha = scanner.nextInt();
							} catch (InputMismatchException e) {
								System.out.println("Digite um número entre 1 e 2");
							}
							if (escolha < 1 || escolha > 2) {
								System.out.println("Digite um número entre 1 e 2");
							} else {
								break;
							}
						}
						if (escolha == 1) {
							scanner.nextLine();
							continue;
						} else {
							scanner.nextLine();
							break;
						}
					}
					for (Produto produto : listaProdutos) {
						if (produto.getEstoque().getQuantidade() > 0) {

							System.out.println("-Nome: " + produto.getNome() + " -Código: " + produto.getCodigo()
									+ " -Preço: " + produto.getPreco());
							con++;
						} else {
							System.out.println("-Nome: " + produto.getNome() + " ====Indisponível====");
						}
					}

					if (con == 0) {
						int escolha=0;
						for (;;) {

							System.out.println("Sem produtos disnponíveis, continuar?1-sim 2-não");
							try {
								escolha = scanner.nextInt();
							} catch (InputMismatchException e) {
								System.out.println("Digite um número entre 1 e 2");
							}
							if (escolha < 1 || escolha > 2) {
								System.out.println("Digite um número entre 1 e 2");
							} else {
								break;
							}
						}
						if(escolha==1) {
							continue;
						}else {
							break;
						}
					}
					for (;;) {
						try {
							System.out.println("Selecione o nome do produto que você quer adicionar ao carrinho:");
							select = scanner.nextLine();
							System.out.println("Digite a quantidade:");
							quantidade = scanner.nextInt();
							if (dia == -1) {
								System.out.println("Digite o dia atual:");
								dia = scanner.nextInt();
								System.out.println("Digite o mes atual:");
								mes = scanner.nextInt();
								System.out.println("Digite o ano atual:");
								ano = scanner.nextInt();
							}
							scanner.nextLine();
							if(quantidade<1) {
								System.out.println("Somente quantidade acima de 0.");
								continue;
							}
							break;
						} catch (InputMismatchException e) {
							System.out.println("a data e a quantidade só aceitam números inteiros.");
						}
					}

					int valido = 0;
					for (Produto produto : listaProdutos) {
						Integer cod = produto.getCodigo();
						if (produto.getNome().equalsIgnoreCase(select) || cod.toString().equals(select)) {
							valido++;
							if (quantidade > produto.getQuantidade()) {

								for (;;) {
									int alternativa = 0;
									try {
										if(mapaProdutos.containsKey(produto)==true) {
											System.out.println("O produto selecionado tem apenas " + produto.getQuantidade()
											+ " Novo total do produto: "
											+ produto.getPreco() * (produto.getQuantidade()+mapaProdutos.get(produto))
											+ " deseja comprar a quantidade maxima?1-sim 2-cancelar");
										}else {
											System.out.println("O produto selecionado tem apenas " + produto.getQuantidade()
											+ " Novo total do produto: "
											+ produto.getPreco() * produto.getQuantidade()
											+ " deseja comprar a quantidade maxima?1-sim 2-cancelar");
										}
										
										alternativa = scanner.nextInt();
									} catch (InputMismatchException e) {
										System.out.println("O valor deve ser um número inteiro.");
									}
									if (alternativa == 1) {
										quantidade = produto.getQuantidade();
										if (mapaProdutos.containsKey(produto) == true) {
											total += produto.getPreco() * quantidade;
											quantidade += mapaProdutos.get(produto);
										} else {
											total += produto.getPreco() * quantidade;
										}
										mapaProdutos.put(produto, quantidade);

										produto.diminuirEstoque(quantidade);

										System.out.println("Produto adicionado com sucesso!");
										break;
									} else if (alternativa == 2) {
										System.out.println("Produto não adicionado no carrinho!");
										valido = -1;
										break;
									} else {
										System.out.println("Alternativa inválida, digite um número entre 1 e 2.");
									}
								}
							} else {
								int alternativa = 0;
								for (;;) {
									try {
										System.out.println("Total do produto: " + produto.getPreco() * quantidade
												+ " deseja adicionar o produto no carrinho?1-sim 2-não");
										alternativa = scanner.nextInt();
										if (alternativa < 1 || alternativa > 2) {
											System.out.println("Somente números entre 1 e 2.");
											scanner.nextLine();
											continue;
										} else {
											scanner.nextLine();
											break;
										}
									} catch (InputMismatchException e) {
										System.out.println("Somente números.");
									}
								}
								if (alternativa == 2) {
									break;
								}
								if (mapaProdutos.containsKey(produto) == true) {
									total += produto.getPreco() * quantidade;
									quantidade += mapaProdutos.get(produto);
								} else {
									total += produto.getPreco() * quantidade;
								}
								mapaProdutos.put(produto, quantidade);
								produto.diminuirEstoque(quantidade);

								System.out.println("Produto adicionado com sucesso!");
								break;
							}
						}
					}

					if (valido == 0) {
						System.out.println("O produto listado não existe");
						continue;
					} else if (valido == -1) {
						scanner.nextLine();
						continue;
					}
					int validar = 0;
					int alternativa = 0;
					while (validar == 0) {
						try {
							System.out.println(
									"Deseja adicionar outro produto no carrinho?1-sim 2-não 3-cancelar pedido");
							System.out.println("Valor total atual(sem adição de 17%):" + total);
							listaProdutos.removeAll(listaProdutos);
							alternativa = scanner.nextInt();
							scanner.nextLine();
							validar = 3;
						} catch (InputMismatchException e) {
							System.out.println("O valor deve ser um número inteiro.");
						}
						if (alternativa < 1 || alternativa > 3) {
							System.out.println("Alternativa inválida, digite um número entre 1 e 2.");
							validar = 0;
						}
					}
					if (alternativa == 2) {
						validar = 1;
						break;
					} else if (alternativa == 3) {
						cancelar = 1;
						break;
					}

				}
				total *= 1.17;

				if (mapaProdutos.size() == 0) {
					System.out.println("Pedido Cancelado!");
				} else if (cancelar == 1) {
					System.out.println("Pedido Cancelado!");
				} else {
					Data dat = new Data(dia, mes, ano);
					Pedidos pedido = new Pedidos(mapaProdutos, total, dat, cliente);
					cliente.adicionarPedidos(pedido);
					loja.adicionarPedido(pedido);
				}
			} else if (opcao == 2) {

				int escolha = 0;
				for (;;) {
					try {
						System.out.println("1-Ver todos os pedidos");
						System.out.println("2-Pesquisar por data");
						System.out.println("3-Pesquisar por codigo");
						escolha = scanner.nextInt();
						if (escolha < 1 || escolha > 3) {
							System.out.println("Somente números de 1 a 3.");
							continue;
						} else {
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("Digite um número de 1 a 2");
					}
				}
				List<Pedidos> pedidos = new ArrayList<>();
				pedidos.clear();
				if (escolha == 1) {
					pedidos.addAll(cliente.getPedidos());
				} else if (escolha == 2) {
					int diaI = 0;
					int diaF = 0;
					int mesI = 0;
					int mesF = 0;
					int anoI = 0;
					int anoF = 0;
					for (;;) {
						try {
							System.out.println("Digite a data inicial: ");
							System.out.println("Dia: ");
							diaI = scanner.nextInt();
							System.out.println("Mês: ");
							mesI = scanner.nextInt();
							System.out.println("Ano: ");
							anoI = scanner.nextInt();
							System.out.println("Digite a data final: ");
							System.out.println("Dia: ");
							diaF = scanner.nextInt();
							System.out.println("Mês: ");
							mesF = scanner.nextInt();
							System.out.println("Ano: ");
							anoF = scanner.nextInt();
							if (escolha < 1 || escolha > 2) {
								continue;
							} else {
								break;
							}

						} catch (InputMismatchException e) {
							System.out.println("Somente números!");
						}
					}
					// implementar buscar por data
					pedidos.addAll(cliente.getPedidosData(diaI, mesI, anoI, diaF, mesF, anoF));
				}else if(escolha ==3) {
					int cod=0;
					for (;;) {
						try {
							System.out.println("Digite o código: ");
							cod = scanner.nextInt();
							break;
						} catch (InputMismatchException e) {
							System.out.println("Somente números!");
						}
					}
					if(loja.getPedidosPorCod(cod)!=null) {
						pedidos.add(loja.getPedidosPorCod(cod));
					}
				}

				if (pedidos.isEmpty() == true) {
					System.out.println("Ainda não há nenhum pedido.");
				} else {
					Map<Produto, Integer> listaProdutos = new HashMap<>();

					System.out.println("===Lista de pedidos===");
					for (Pedidos pedido : pedidos) {
						listaProdutos.clear();
						listaProdutos.putAll(pedido.getProdutos());
						System.out.println("==========");
						System.out.println("Codigo do pedido:" + pedido.getCod());
						for (Map.Entry<Produto, Integer> lista : listaProdutos.entrySet()) {
							System.out.println("Produtos: " + lista.getKey().getNome() + " Quantidade: "
									+ lista.getValue() + " Valor Unidade: " + lista.getKey().getPreco()
									+ " Valor total(sem adição de 17%): "
									+ lista.getKey().getPreco() * lista.getValue());
						}
						System.out.println("\nValor total(com adição 17%): " + pedido.getValorTotal());
						System.out.println("Data inicio:" + pedido.getData().getDia() + "/" + pedido.getData().getMes()
								+ "/" + pedido.getData().getAno());

						System.out.println("Status: " + pedido.getStatus());
						if (pedido.getDataEnvio() == null && !pedido.getStatus().equalsIgnoreCase("Cancelado")) {
							System.out.println("Pedido ainda não saiu para entrega.");
						} else if (pedido.getStatus().equalsIgnoreCase("Entregue")) {
							System.out.println("Data Envio:" + pedido.getDataEnvio().getDia() + "/"
									+ pedido.getDataEnvio().getMes() + "/" + pedido.getDataEnvio().getAno());
						}
					}
				}
			} else if (opcao == 3) {
				int escolha = 0;

				try {

					System.out.println("Informações para alterar:");

					System.out.println("1-Nome");
					System.out.println("2-Senha");
					System.out.println("3-Telefone");
					System.out.println("4-Endereço");
					System.out.println("5-Tudo");
					System.out.println("6-Cancelar");
					escolha = scanner.nextInt();
					scanner.nextLine();
				} catch (InputMismatchException e) {
					System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
					scanner.nextLine();
				}

				if (escolha == 1) {

					System.out.println("Digite seu novo nome");

					String nome = scanner.nextLine();

					cliente.setNome(nome);
					System.out.println("Nome alterado com sucesso");

				} else if (escolha == 2) {
					scanner.nextLine();
					System.out.println("Digite sua nova senha");

					String senha = scanner.nextLine();
					cliente.setSenha(senha);
					System.out.println("Senha alterado com sucesso");

				} else if (escolha == 3) {

					scanner.nextLine();
					System.out.println("Digite seu novo telefone");

					String telefone = scanner.nextLine();

					cliente.setNumero(telefone);
					System.out.println("Telefone alterado com sucesso");

				} else if (escolha == 4) {

					System.out.println("Digite seu novo endereço");
					System.out.println("Rua:");
					String rua = scanner.nextLine();
					System.out.println("Bairro:");
					String bairro = scanner.nextLine();
					System.out.println("Cidade:");
					String cidade = scanner.nextLine();

					cliente.alterarEndereco(rua, bairro, cidade);
					System.out.println("Endereço alterado com sucesso");

				} else if (escolha == 5) {
					System.out.println("Digite suas novas informações:");

					System.out.println("Digite o nome:");
					String usuario = scanner.nextLine();
					System.out.println("Digite a senha");
					String senha = scanner.nextLine();
					System.out.println("Telefone");
					String telefone = scanner.nextLine();

					System.out.println("Rua");
					String rua = scanner.nextLine();
					System.out.println("Bairro");
					String bairro = scanner.nextLine();
					System.out.println("Cidade");
					String cidade = scanner.nextLine();
					cliente.alterarPessoa(usuario, telefone, senha, rua, bairro, cidade);
					System.out.println("Informações alteradas com sucesso!");

				} else if (escolha == 6) {
					System.out.println("Ação cancelada!");
				} else {
					System.out.println("Digite um número entre 1 e 6.");
				}
			} else if (opcao == 4) {
				System.out.println("Você tem certeza que deseja excluir sua conta? 1-Sim 2-Não");
				int confirmar = scanner.nextInt();
				if (confirmar == 1) {

					try {
						if (loja.excluirPessoa(cliente.getSenha(), cliente.getNome()) == true) {
							System.out.println("Conta excluída com sucesso!");
						} else {
							System.out.println("Conta não encontrada!");
						}
					} catch (PessoaNaoEncontradaException pe) {
						System.out.println("Erro: " + pe.getMessage());
					}

					break;
				} else if (confirmar > 2) {
					System.out.println("Opção inválida");
				}
			} else if (opcao == 5) {
				System.out.println("Deslogando...");

				break;
			} else {
				System.out.println("Opção inválida, digite um número entre 1 e 5.");
			}
		}
	}

	private static void exibirMenu(Administrador admin, Scanner scanner, Loja loja) {
		int opcao;
		int id, codigo, fornecedorId, novaQuantidade, escolha;
		String nome, numero, cnpj;
		String novoNome, novoNumero, novoCnpj;
		double preco, novoPreco;
		Fornecedor fornecedor;
		Produto produto;

		do {
			boolean valida = false;
			while (valida == false) {
				try {
					System.out.println("\n=== Menu Principal ===");
					System.out.println("1 - Cadastro de Fornecedores");
					System.out.println("2 - Cadastro de Produtos");
					System.out.println("3 - Manutenção de Estoque");
					System.out.println("4 - Consultar Fornecedores");
					System.out.println("5 - Consultar Produtos");
					System.out.println("6 - Alterar Cadastro de Fornecedor");
					System.out.println("7 - Alterar Cadastro de Produto");
					System.out.println("8 - Excluir Fornecedor");
					System.out.println("9 - Excluir Produto");
					System.out.println("0 - Sair");

					opcao = scanner.nextInt();
					scanner.nextLine();

					switch (opcao) {
					case 1:// Cadastro de Fornecedores feito

						System.out.println("=== Cadastro de Fornecedor ===");
						System.out.print("Digite o ID do fornecedor: ");
						id = scanner.nextInt();
						scanner.nextLine();

						System.out.print("Digite o nome do fornecedor: ");
						nome = scanner.nextLine();

						System.out.print("Digite o contato do fornecedor: ");
						numero = scanner.nextLine();

						System.out.print("Digite o cnpj do fornecedor: ");
						cnpj = scanner.nextLine();

						System.out.println("Digite a rua");
						String rua = scanner.nextLine();
						System.out.println("Digite o bairro");
						String bairro = scanner.nextLine();
						System.out.println("Digite a cidade");
						String cidade = scanner.nextLine();

						Endereco end = new Endereco(rua, bairro, cidade);

						if (loja.adicionarFornecedor(new Fornecedor(nome, numero, cnpj, id, end)) == true) {
							System.out.println("Fornecedor cadastrado com sucesso!");
						} else {
							System.out.println("A lista de fornecedores está cheia!");
						}
						break;

					case 2:// Cadastro de Produtos feito
						System.out.println("=== Cadastro de Produto ===");
						System.out.print("Digite o código do produto: ");
						codigo = scanner.nextInt();
						scanner.nextLine();

						System.out.print("Digite o nome do produto: ");
						nome = scanner.nextLine();

						System.out.print("Digite o preço do produto: ");
						preco = scanner.nextDouble();

						System.out.print("Digite a quantidade do produto: ");
						novaQuantidade = scanner.nextInt();
						scanner.nextLine();

						System.out.println("Escolha um fornecedor pelo ID:");
						System.out.println(loja.listarFornecedores());

						System.out.print("Digite o ID do fornecedor: ");
						fornecedorId = scanner.nextInt();
						scanner.nextLine();

						fornecedor = loja.buscarPorId(fornecedorId);
						if (fornecedor == null) {
							System.out.println("Fornecedor não encontrado. Produto não cadastrado.");
							break;
						}

						if (loja.adicionarProduto(
								new Produto(codigo, nome, preco, new Estoque(novaQuantidade), fornecedor)) == true) {
							System.out.println("Produto cadastrado com sucesso!");
						} else {
							System.out.println("A lista de produtos está cheia!");
						}
						break;

					case 3:// Manutenção de Estoque feito

						System.out.print("Digite o código do produto para atualização: ");
						codigo = scanner.nextInt();
						scanner.nextLine();

						produto = loja.buscarPorCodigo(codigo);

						if (produto != null) {
							System.out.print("Digite a nova quantidade: ");
							novaQuantidade = scanner.nextInt();
							scanner.nextLine();

							if (produto.atualizarEstoque(produto, novaQuantidade) == true) {
								System.out.print("Estoque atualizado com sucesso");
							} else {
								System.out.print("Produto não encontrado");
							}

						} else {
							System.out.print("Produto não encontrado");
						}

						break;

					case 4:// Consultar Fornecedores
						System.out.println("=== Consulta de Fornecedores ===");
						System.out.println("1 - Consultar por ID");
						System.out.println("2 - Consultar por Nome");

						escolha = scanner.nextInt();
						scanner.nextLine();

						if (escolha == 1) {
							System.out.print("Digite o ID do fornecedor: ");
							id = scanner.nextInt();
							scanner.nextLine();

							fornecedor = loja.buscarPorId(id);
							if (fornecedor != null) {
								System.out.println("Fornecedor encontrado: " + fornecedor.getNome() + " - Contato: "
										+ fornecedor.getNumero());
							} else {
								System.out.println("Fornecedor com ID '" + id + "' não encontrado.");
							}
						} else if (escolha == 2) {
							System.out.println("Digite o nome do fornecedor: ");
							nome = scanner.nextLine();

							List<Fornecedor> fornecedores = new ArrayList<>();
							fornecedores.addAll(loja.buscarPorNomeFornecedor(nome));
							Collections.sort(fornecedores);

							if (fornecedores.isEmpty() == false) {
								for (Fornecedor fornece : fornecedores) {
									System.out.println("----Fornecedores encontrados----");
									System.out.println("Nome: " + fornece.getNome() + " ID: "
											+ fornece.getIdFornecedor() + " - Contato: " + fornece.getNumero()
											+ " CNPJ: " + fornece.getCnpj());
								}
							} else {
								System.out.println("Fornecedor não encontrado");
							}
						} else {
							System.out.println("Opção inválida.");
						}

						break;

					case 5:// Consultar Produtos feito
						System.out.println("=== Consulta de Produtos ===");
						System.out.println("1 - Consultar por Código");
						System.out.println("2 - Consultar por Nome");

						escolha = scanner.nextInt();
						scanner.nextLine();

						if (escolha == 1) {
							System.out.print("Digite o código do produto: ");
							codigo = scanner.nextInt();
							scanner.nextLine();

							produto = loja.buscarPorCodigo(codigo);
							if (produto != null) {
								System.out.println("Produto encontrado: " + produto.getNome() + " - Preço: R$"
										+ produto.getPreco() + " - Quantidade: " + produto.getQuantidade());
							} else {
								System.out.println("O código '" + codigo + "' não foi encontrado!");
							}
						} else if (escolha == 2) {
							System.out.print("Digite o nome do produto: ");
							nome = scanner.nextLine();

							List<Produto> produtos = new ArrayList<>();
							produtos.addAll(loja.buscarPorNome(nome));
							Collections.sort(produtos);

							if (produtos.isEmpty() == false) {
								for (Produto prod : produtos) {
									System.out.println("----Produtos encontrados----");
									System.out.println("Nome: " + prod.getNome() + " ID: " + prod.getCodigo()
											+ " - Preço: " + prod.getPreco() + " Quantidade: " + prod.getQuantidade()
											+ " Fornecedor: " + prod.getFornecedor().getNome());
								}
							} else {
								System.out.println("Produto não encontrado");
							}
						} else {
							System.out.println("Opção inválida.");
						}

						break;

					case 6:// Alterar Cadastro de Fornecedor feito
						try {
							System.out.println("Digite o ID do fornecedor que deseja alterar:");
							fornecedorId = scanner.nextInt();
							scanner.nextLine();

							fornecedor = loja.buscarPorId(fornecedorId);

							if (fornecedor != null) {
								int escolher = 0;
								try {
									scanner.nextLine();
									System.out.println("Informações para alterar:");

									System.out.println("1-Nome");
									System.out.println("2-CNPJ");
									System.out.println("3-Telefone");
									System.out.println("4-Endereço");
									System.out.println("5-Tudo");
									System.out.println("6-Cancelar");
									escolher = scanner.nextInt();

								} catch (InputMismatchException e) {
									System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
									scanner.nextLine();
								}

								if (escolher == 1) {
									scanner.nextLine();
									System.out.println("Digite o nome do fornecedor");

									nome = scanner.nextLine();

									fornecedor.setNome(nome);
									System.out.println("Nome alterado com sucesso");
									break;
								} else if (escolher == 2) {
									scanner.nextLine();
									System.out.println("Digite o novo CNPJ da empresa");

									String CNPJ = scanner.nextLine();
									fornecedor.setCnpj(CNPJ);
									System.out.println("Senha alterado com sucesso");
									break;
								} else if (escolher == 3) {

									scanner.nextLine();
									System.out.println("Digite o telefone do fornecedor");

									String telefone = scanner.nextLine();

									fornecedor.setNumero(telefone);
									System.out.println("Telefone alterado com sucesso");
									break;
								} else if (escolher == 4) {

									scanner.nextLine();
									System.out.println("Digite seu novo endereço");
									System.out.println("Rua:");
									rua = scanner.nextLine();
									System.out.println("Bairro:");
									bairro = scanner.nextLine();
									System.out.println("Cidade:");
									cidade = scanner.nextLine();

									fornecedor.alterarEndereco(rua, bairro, cidade);
									System.out.println("Endereço alterado com sucesso");
									break;
								} else if (escolher == 5) {
									System.out.println("Digite suas novas informações:");
									scanner.nextLine();
									System.out.println("Digite o nome:");
									nome = scanner.nextLine();
									System.out.println("Digite o cnpj");
									cnpj = scanner.nextLine();
									System.out.println("Telefone");
									String telefone = scanner.nextLine();

									System.out.println("Rua");
									rua = scanner.nextLine();
									System.out.println("Bairro");
									bairro = scanner.nextLine();
									System.out.println("Cidade");
									cidade = scanner.nextLine();
									fornecedor.alterarFornecedor(nome, telefone, cnpj, rua, bairro, cidade);
									System.out.println("Informações alteradas com sucesso!");
									break;
								} else if (escolher == 6) {
									break;
								} else {
									System.out.println("Digite um número entre 1 e 6.");
								}
							} else {
								throw new FornecedorNaoEncontradoException(
										"Fornecedor com ID '" + fornecedorId + "' não encontrado.");
							}
						} catch (FornecedorNaoEncontradoException fne) {
							System.out.println("Erro: " + fne.getMessage());
						}
						break;

					case 7:// Alterar Cadastro de Produto feito
						try {
							System.out.println("Digite o código do produto que deseja alterar:");
							codigo = scanner.nextInt();
							scanner.nextLine();
							produto = loja.buscarPorCodigo(codigo);

							if (produto != null) {

								int escolher = 0;
								try {
									scanner.nextLine();
									System.out.println("Informações para alterar:");

									System.out.println("1-Nome");
									System.out.println("2-Preço");
									System.out.println("3-Quantidade");
									System.out.println("4-Fornecedor");
									System.out.println("5-Tudo");
									System.out.println("6-Cancelar");
									escolher = scanner.nextInt();

								} catch (InputMismatchException e) {
									System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
									scanner.nextLine();
								}

								if (escolher == 1) {
									scanner.nextLine();
									System.out.print("Novo nome: ");
									novoNome = scanner.nextLine();
									produto.setNome(novoNome);
									System.out.println("Nome alterado com sucesso!");
									break;
								} else if (escolher == 2) {
									scanner.nextLine();
									System.out.print("Novo preço: ");
									novoPreco = scanner.nextDouble();
									produto.setPreco(novoPreco);
									System.out.println("Preço alterado com sucesso!");
									break;
								} else if (escolher == 3) {
									scanner.nextLine();
									System.out.print("Novo preço: ");
									novaQuantidade = scanner.nextInt();
									produto.alterarEstoque(novaQuantidade);
									System.out.println("Quantidade alterado com sucesso!");
									break;
								} else if (escolher == 4) {
									scanner.nextLine();
									loja.listarFornecedores();
									System.out.print("digite o novo código do fornecedor");
									id = scanner.nextInt();
									fornecedor = loja.buscarPorId(id);
									produto.setFornecedor(fornecedor);

								} else if (escolher == 5) {
									scanner.nextLine();
									System.out.print("Novo nome: ");
									novoNome = scanner.nextLine();

									System.out.print("Novo preço: ");
									novoPreco = scanner.nextDouble();

									System.out.print("Nova quantidade: ");
									novaQuantidade = scanner.nextInt();
									scanner.nextLine();

									loja.listarFornecedores();
									System.out.print("digite o novo código do fornecedor");
									id = scanner.nextInt();
									fornecedor = loja.buscarPorId(id);
									produto.alterarProduto(novoNome, novoPreco, novaQuantidade, fornecedor);
									System.out.println("Produto alterado com sucesso!");

								} else if (escolher == 6) {
									break;
								} else {
									System.out.println("Digite um número entre 1 e 6.");
								}
							} else {
								throw new ProdutoNaoEncontradoException(
										"O produto com código '" + codigo + "' não foi encontrado!");
							}
						} catch (ProdutoNaoEncontradoException pne) {
							System.out.println("Erro: " + pne.getMessage());
						}
						break;

					case 8:// Excluir fornecedor (adicionar excluir produtos com o fornecedor excluído
						try {
							System.out.println("Digite o ID do fornecedor para exclusão:");
							id = scanner.nextInt();
							scanner.nextLine();
							fornecedor = loja.buscarPorId(id);
							if (fornecedor != null) {
								loja.excluirFornecedorComProdutos(id);
								System.out.println("Fornecedor excluído com sucesso!");
							} else {
								throw new FornecedorNaoEncontradoException(
										"Fornecedor com ID '" + id + "' não encontrado.");
							}
						} catch (FornecedorNaoEncontradoException fne) {
							System.out.println("Erro: " + fne.getMessage());
						}
						break;

					case 9:// Excluir produto feito
						try {
							System.out.println("Digite o código do produto para exclusão:");
							codigo = scanner.nextInt();
							scanner.nextLine();
							produto = loja.buscarPorCodigo(codigo);
							if (produto != null) {
								loja.excluirProduto(codigo);
								System.out.println("Produto excluído com sucesso!");
							} else {
								throw new ProdutoNaoEncontradoException(
										"O produto com código '" + codigo + "' não foi encontrado!");
							}
						} catch (ProdutoNaoEncontradoException pne) {
							System.out.println("Erro: " + pne.getMessage());
						}
						break;

					case 0:// Sair
						System.out.println("Saindo...");

						return;

					default:
						System.out.println("Opção inválida. Tente novamente.");
					}
					valida = true;
				} catch (InputMismatchException e) {
					System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
					scanner.nextLine();
				}
			}
		} while (1 == 1);
	}
}