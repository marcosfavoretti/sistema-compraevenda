import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Menu {
	
	public static void main(String[] args) throws ParseException, IOException {
		
		Gerenciacliente gerecli = new Gerenciacliente();
		Gerenciaprodutos gereprod = new Gerenciaprodutos();
		Gerenciacompra gerecomp = new Gerenciacompra();
		Salvarinfos save = new Salvarinfos();
		
		save.criar();
		save.lerarquivo(gerecli, gerecomp, gereprod);
		

//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''//
		int pode = 0, loop = 0;
		String cnpj ="", cpf="";
	do {	
		int resp = Integer.parseInt(JOptionPane.showInputDialog("\n1 - cadastro de clientes" +
		  "\n2 - deletar cliente pelo CPF" + "\n3 - deletar cliente pelo nome" +
		  "\n4 - cadastro de produtos" + "\n5 - efetuaçao de uma compra" +
		  "\n6 - atualizaçao da situaçao de pagamento de uma compra" +
			"\n7 - relatorios" + "\n8 - sair"));
	
		if(resp == 1) {
		JOptionPane.showMessageDialog(null,"Cadastro de cliente", "Mensagem",
				JOptionPane.INFORMATION_MESSAGE);
		
		int pfoupj = Integer.parseInt(JOptionPane.showInputDialog("Cadastro de cliente: \nO cliente é uma (1)PessoaJuridica ou um (2)PessoaFisica: \nDigite '1' ou '2'"));
		
		if(pfoupj == 1) {//nao esta salvando o arquivo
			
			int numero = Integer.parseInt(JOptionPane.showInputDialog("Endereço \nNumero do endereço: "));
			String bairro  =JOptionPane.showInputDialog("Endereço \nBairro: ");
			String cep  =JOptionPane.showInputDialog("Endereço \nCEP: ");
			String cidade  =JOptionPane.showInputDialog("Endereço \nCidade: ");
			String estado  =JOptionPane.showInputDialog("Endereço \nEstado: ");
			
			String nome  =JOptionPane.showInputDialog("Cadastro Pessoa Juridica \nNome: ");
			pode = 0;
			do {
				cnpj  =JOptionPane.showInputDialog("Cadastro Pessoa Juridica \nCNPJ: ");
				pode = gerecli.cnpjunico(cnpj);
				}while(pode == 0);
			
			String razaosocial  =JOptionPane.showInputDialog("Cadastro Pessoa Juridica \nRazao Social: ");
			int prazopagamento = Integer.parseInt(JOptionPane.showInputDialog("Cadastro Pessoa Juridica \nPrazo pagamento: (inserir o numero de dias) "));
			gerecli.cadastrocliente( numero, bairro, cep, cidade, estado,nome, cnpj, razaosocial, prazopagamento);
			
		}
		else if(pfoupj == 2) {
			int numero = Integer.parseInt(JOptionPane.showInputDialog("Endereço \nNumero do endereço: "));
			String bairro  =JOptionPane.showInputDialog("Endereço \nBairro: ");
			String cep  =JOptionPane.showInputDialog("Endereço \nCEP: ");
			String cidade  =JOptionPane.showInputDialog("Endereço \nCidade: ");
			String estado  =JOptionPane.showInputDialog("Endereço \nEstado: ");
			String nome  =JOptionPane.showInputDialog("Cadastro Pessoa Fisica \nNome: ");
			pode = 0;
			do {
				cpf  =JOptionPane.showInputDialog("Cadastro Pessoa Fisica \nCPF: ");
				pode = gerecli.cpfunico(cpf);
				
			}while(pode == 0);
			
			int qtdmaxparcelas = Integer.parseInt(JOptionPane.showInputDialog("Cadastro Pessoa Fisica \nEm quantas parcelas sera o pagamento?"));
			gerecli.cadastrocliente(numero, bairro, cep, cidade, estado, nome, cpf, qtdmaxparcelas);

		}
		else {
			JOptionPane.showMessageDialog(null,"Por favor, coloque (1) ou (2)", "Mensagem",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	else if (resp == 2) {//buscar para apagar cpf
	
			
		String cpfoucnpj = JOptionPane.showInputDialog("Procurar cliente por CPF ou CNPJ para ser deletado: ");
		
		
		int pospf = gerecli.buscarcpf(cpfoucnpj);
		
		if( pospf != -1) {
			
			JOptionPane.showMessageDialog(null,"O cliente sera excluido "+gerecli.getPf().get(pospf).getNome(), "Mensagem",
					JOptionPane.WARNING_MESSAGE);
			gerecli.excluipf(pospf);
			
		}
		else if(pospf == -1 ) {
		
			int pospj = gerecli.buscacnpj(cpfoucnpj);
				
			if(pospj == -1) {
				JOptionPane.showMessageDialog(null,"Nao existe esse cliente ", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,"O cliente sera excluido "+gerecli.getPj().get(pospj).getNome(), "Mensagem",
							JOptionPane.WARNING_MESSAGE);
					gerecli.excluipj(pospj);
				}
		}
		
	}
	else if (resp == 3) {//buscar por nome
		
		String nome = JOptionPane.showInputDialog("Procurar cliente por Nome para ser deletado: ");
		
		
		int pospf = gerecli.buscarpornomepf(nome);
		
		
		if( pospf != -1) {
			
			JOptionPane.showMessageDialog(null,"O cliente sera excluido "+gerecli.getPf().get(pospf).getNome(), "Mensagem",
					JOptionPane.WARNING_MESSAGE);
			gerecli.excluipf(pospf);
			
		}
		else if(pospf == -1 ) {
		
			int pospj = gerecli.buscarpornomepj(nome);
				
			if(pospj == -1) {
				JOptionPane.showMessageDialog(null,"Nao existe esse cliente ", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,"O cliente sera excluido "+gerecli.getPj().get(pospj).getNome(), "Mensagem",
							JOptionPane.WARNING_MESSAGE);
					gerecli.excluipj( pospj);
				}
		}
	
	
			
			
	}
	else if (resp == 4) {
		String codigo;
		
		JOptionPane.showMessageDialog(null,"Cadastro de Produtos", "Mensagem",
				JOptionPane.INFORMATION_MESSAGE);
		
		int typeprod = Integer.parseInt(JOptionPane.showInputDialog("Cadastro de Produto: \nO Produto possui validade (1)SIM ou (2)NAO: \nDigite '1' ou '2'"));
		
		if(typeprod == 1) {
		pode = 0;
			do {
			codigo = JOptionPane.showInputDialog("Produto perecivel \ncodigo: ");
			pode = gereprod.codigounico(codigo);
			}while(pode == 0);
			String nomeprod = JOptionPane.showInputDialog("Produto perecivel \nNome do produto: ");
			String descricao  = JOptionPane.showInputDialog("Produto perecivel \ndescricao: ");
			double preco = Double.parseDouble(JOptionPane.showInputDialog("Produto perecivel \npreço: "));
			String data = JOptionPane.showInputDialog("Produto perecivel \ndata: dd/MM/yyyy obs: utilizar as '/' para separar  ");

			gereprod.cadastroproduto(codigo, nomeprod, descricao, preco, data);
			
		}
		
		else if(typeprod == 2) {
			
			 codigo = JOptionPane.showInputDialog("Produto perecivel \ncodigo: ");
			String nomeprod = JOptionPane.showInputDialog("Produto perecivel \nNome do produto: ");
			String descricao  = JOptionPane.showInputDialog("Produto perecivel \ndescricao: ");
			double preco = Double.parseDouble(JOptionPane.showInputDialog("Produto perecivel \npreço: "));

			gereprod.cadastroproduto(codigo, nomeprod, descricao, preco);

		}
		
		else {
			JOptionPane.showMessageDialog(null,"Por favor, coloque (1) ou (2)", "Mensagem",
					JOptionPane.INFORMATION_MESSAGE);
		}
		

		
	}
	else if (resp == 5) {
		double pagoatual = 0, valortotaltemp = 0;
		int loop3 = 0, i = 1;
		String identificador;
		
		ArrayList<ItemdeCompra> itens = new ArrayList<ItemdeCompra>();

		String cpfoucnpj = JOptionPane.showInputDialog("Realizando compra \nCNPJ ou CPF do cliente que esta fazendo a compra: ");
		
		int pospf = gerecli.buscacnpj(cpfoucnpj);
		int pospj = gerecli.buscarcpf(cpfoucnpj);
		
		if(pospf == -1 && pospj == -1) {
			System.out.println("Nao achamos clientes com esse dado cpf inserir outro");
		}
		else {
		pode =0 ;
			do {	
				identificador= JOptionPane.showInputDialog("Realizando compra \nidentificador: ");
				pode = gerecomp.identificadorunico(identificador);
			}while(pode == 0);
		
//--------------------------------ITEM DE COMPRA SENDO GERADO----------------------
		do {
			for(ProdutosPereciveis prodval:gereprod.getProdutoslistaval()) {
				JOptionPane.showMessageDialog(null,prodval.paraString(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
				
			}
			for(Produtos prod: gereprod.getProdutoslista()) {
				JOptionPane.showMessageDialog(null,prod.paraString(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);

			}

			String codigo = JOptionPane.showInputDialog("Realizando compra \nCodigo do ["+i+"] produto escolhido");

//produtos apenas-------------------------------------------------
			int pos = gereprod.buscarcodigoprod(codigo);
			int posval = gereprod.buscarcodigoprodval(codigo);
		


//produtos apenas-------------------------------------------------				
		
			if(pos != -1 || posval == -1) {
					int qtd =Integer.parseInt( JOptionPane.showInputDialog("Realizando compra \nDeseja qtds unid. deste produto: "));
					ItemdeCompra itemprod = new ItemdeCompra(qtd, gereprod.getProdutoslista().get(pos));	
				
				itens.add(itemprod);
//produtos apenas-------------------------------------------------				
				
				
			String continua = JOptionPane.showInputDialog("Realizando compra \nDeseja adicionar outro produto na compra: [s]=sim [n]=nao");
				

				
				if(continua.equals("s")) {
				}
				else if (continua.equals("n")){
					
					for(ItemdeCompra item: itens)
					{
					 valortotaltemp += item.getValortotal();
					}
					 pagoatual = Double.parseDouble(JOptionPane.showInputDialog("Valor Total da compra: "+valortotaltemp+"\nRealizando compra \nValor pago até o momento: "));
					 loop3++;
				}
				else {
					JOptionPane.showMessageDialog(null,"Entrada invalida, entedemos que voce nao deseja adicionar outro produto!!!", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
				loop3++;
				}
				i++;	
				
				
				
				
			}
//produtos val--------------------------------------------------------
			
			else if(posval != -1 || pos == -1) {
				int qtd =Integer.parseInt( JOptionPane.showInputDialog("Realizando compra \nDeseja qtds unid. deste produto: "));
				
			ItemdeCompra itemprodval = new ItemdeCompra(qtd, gereprod.getProdutoslistaval().get(posval));	
				
				itens.add(itemprodval);
				
//produtos val--------------------------------------------------------
				String continua = JOptionPane.showInputDialog("Realizando compra \nDeseja adicionar outro produto na compra: [s]=sim [n]=nao");
				

				if(continua.equals("s")) {

				}
				else if (continua.equals("n")){
					
					for(ItemdeCompra item: itens)
					{
					 valortotaltemp += item.getValortotal();
					}
					 pagoatual = Double.parseDouble(JOptionPane.showInputDialog("Valor Total da compra: "+valortotaltemp+"\nRealizando compra \nValor pago até o momento: "));

					loop3++;
				}
				else {
					JOptionPane.showMessageDialog(null,"Entrada invalida, entedemos que voce nao deseja adicionar outro produto!!!", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
				loop3++;
				}
				
				i++;
				
			
			}
			else {
				JOptionPane.showMessageDialog(null,"codigo do produto nao achado", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
			}
		
		}while(loop3 == 0);
//--------------------------------ITEM DE COMPRA SENDO TERMINADO----------------------
//--------------------------------passando dados para fazer a compra--------------------
		gerecomp.fazendocompra(itens ,gerecli, gereprod,cpfoucnpj, identificador, pagoatual);

		}
		
	}
	else if (resp == 6) {
		String id =JOptionPane.showInputDialog("Atualizaçao do valor pago: "
				+ "\nQual compra sera feita a atualizaçao? [informar o identificador da compra] ");
		
		if(gerecomp.verificaatt(id).equals("") ) {
			JOptionPane.showMessageDialog(null,"Nao foi achado o id da compra ou a compra ja foi paga!!!", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			
			double valornovo = Double.parseDouble(JOptionPane.showInputDialog("Qual é o novo valor total pago atualmente?"));
			String info = gerecomp.atualizaçaoPagamento(valornovo, id);
			JOptionPane.showMessageDialog(null,info, "Mensagem",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	else if (resp == 7) {

		String resp2 = JOptionPane.showInputDialog("Relatorio: \na)Relaçao Clientes inicados por sequencia de caracteres: "
				+ "\nb) Todos os Produtos: "
				+ "\nc)Buscar produto por nome: "
				+ "\nd)Produtos vencidos: "
				+ "\ne)Todas compras: "
				+ "\nf)Buscar compra por numero: "
				+ "\ng)Compras nao pagas ainda: "
				+ "\nh)Ultimas 10 compras pagas: "
				+ "\ni)Compra mais cara: "
				+ "\nj)Compra mais barata: "
				+ "\nk)Valor de compras nos ultimos 12 meses: ");

if(resp2.equals("a")) {//buscar nome iniciado por sequencia de caracteres
	String caracteres = JOptionPane.showInputDialog("Buscar nome de Cliente iniciado por sequencia de caracteres: "
			+ "\nEscreva uma sequencia de caracter que deseja procurar: ");	
	ArrayList<Cliente> cli = gerecli.buscaporcaracteres(caracteres);
	
	if(cli.size() == 0) {
		JOptionPane.showMessageDialog(null,"Nenhum nome iniciado com : "+ caracteres ,"Mensagem",JOptionPane.WARNING_MESSAGE);
	}
	else {
	for(Cliente clientes: cli) {
		JOptionPane.showMessageDialog(null,"Clientes com nome iniciado por "+ caracteres+": \n"
			+ clientes.paraString() ,"Mensagem",JOptionPane.INFORMATION_MESSAGE);
	}
	}
		}
if(resp2.equals("b")) {//todos os produtos
	for(ProdutosPereciveis prodval: gereprod.getProdutoslistaval()) {
		JOptionPane.showMessageDialog(null,"Produto Perecivel: \n"+prodval.paraString(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
	}
	for(Produtos prod: gereprod.getProdutoslista()) {
		JOptionPane.showMessageDialog(null,"Produto não perecivel: \n"+prod.paraString(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
	}
}
	

if(resp2.equals("c")) {//buscar prdoutos por nome
	String prodproc = JOptionPane.showInputDialog("Buscar produto pelo nome"
			+ "\nEscreva o nome do produto que deseja procurar: ");
	
	ArrayList<Produtos> achados = gereprod.buscaproduto(prodproc);
	if(achados.size() == 0) {
	JOptionPane.showMessageDialog(null,"Produto nao encontrado, procure por outro nome", "Mensagem",
			JOptionPane.WARNING_MESSAGE);
	}
	else{
		for(Produtos prod: achados) {
		JOptionPane.showMessageDialog(null,"Produto(s) com o nome o nome "+prodproc+" : \n" + prod.paraString(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
if(resp2.equals("d")) {//produtos vencidos

	ArrayList<ProdutosPereciveis> vencidos = gereprod.vencidos();
	for(ProdutosPereciveis prodval: vencidos) {
	JOptionPane.showMessageDialog(null,"Todos os produtos cadastrados que estao vencidos "+prodval.paraString(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
	}
}
if(resp2.equals("e")) {//todas as compras ja feitas
	for(Compra compra: gerecomp.getCompras()) {
		if(gerecomp.getCompras().size() == 0)
	{
		JOptionPane.showMessageDialog(null,"Nenhuma compra encontrada", "Mensagem",
				JOptionPane.INFORMATION_MESSAGE);
	}
		else {
			JOptionPane.showMessageDialog(null,"Todas as compras encontradas: "+ compra.paraString(), "Mensagem",
				JOptionPane.INFORMATION_MESSAGE);
			}
	}

	
	
}
if(resp2.equals("f")) {//busca compra pelo numero 
	String codcompra =JOptionPane.showInputDialog("Buscar uma compra pelo numero de identificaçao "
			+ "\nQual numero da compra desejado? ");
	if(gerecomp.buscapornumero(codcompra).equals("")) {
		JOptionPane.showMessageDialog(null,	"Compra nao encontrada", "Mensagem",JOptionPane.WARNING_MESSAGE);
	}
	else {
	JOptionPane.showMessageDialog(null,	gerecomp.buscapornumero(codcompra),"Mensagem",JOptionPane.INFORMATION_MESSAGE);
	}
}

if(resp2.equals("g")) {//compra ainda nao pagas
	ArrayList <Compra> compnpg = gerecomp.compnaopagas();
	if(compnpg.size() == 0) {
	JOptionPane.showMessageDialog(null,	"Nao achamos nenhuma compra pendente!!!","Mensagem",JOptionPane.WARNING_MESSAGE);
	}
	else {
		for(Compra compra : compnpg) {
	JOptionPane.showMessageDialog(null,	"Compras que ainda nao forma pagas: "
			+ compra.paraString()+ "\n","Mensagem",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
if(resp2.equals("h")) {//ultimas 10 compras
	Compra [] dezcomp = gerecomp.ultimascompras();
	if(dezcomp.length < 10) {
		for(int i = 0 ; i < dezcomp.length; i++) {
		JOptionPane.showMessageDialog(null,	"Nao existem 10 compras realizadas mas as ultimas são:\n"
			+ dezcomp[i].paraString() ,"Mensagem",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	else {
		for(int i = 0; i < 10 ; i++) {
		JOptionPane.showMessageDialog(null,	"As 10 ultimas compras realizadas são:\n"
				+ dezcomp[i].paraString() ,"Mensagem",JOptionPane.INFORMATION_MESSAGE);
	}
}
}
if(resp2.equals("i")) {//compra mais cara
	ArrayList<Compra> compcara = gerecomp.compracara();
	for(Compra comp: compcara) {
		JOptionPane.showMessageDialog(null,	"Compra mais cara realizada:"
			+ comp.paraString() ,"Mensagem",JOptionPane.INFORMATION_MESSAGE);
	}
}
if(resp2.equals("j")) {//compra mais barata
	ArrayList<Compra> compbarata = gerecomp.comprabarata();
	for(Compra comp: compbarata) {
		JOptionPane.showMessageDialog(null,	"Compra mais barata realizada:"
			+ comp.paraString() ,"Mensagem",JOptionPane.INFORMATION_MESSAGE);
	}
	
}
if(resp2.equals("k")) {//compra nos ultimos 12 meses
	if(gerecomp.valortotal12() == 0) {
		JOptionPane.showMessageDialog(null,	"Não houve vendas durante esses 12 meses [VALOR TOTAL = "+gerecomp.valortotal12()+" R$ ]","Mensagem",JOptionPane.WARNING_MESSAGE);
	}
	else {
		JOptionPane.showMessageDialog(null,	"Valor total de compras no ultimos 12 meses: \n"
				+ gerecomp.valortotal12()+" R$" ,"Mensagem",JOptionPane.INFORMATION_MESSAGE);
		}
	}		
	}
	else if (resp == 8) {
		
		
			save.fecharesalvar( gerecli,  gereprod,  gerecomp);
		
				
		loop = 1;
		
	}
		}while(loop == 0);
		
	
	}
}
	
	
	
	
	
	
	


