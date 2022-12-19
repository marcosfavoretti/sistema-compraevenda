import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Salvarinfos {

		
	private File clientearq;
	private File compraarq;
	private File produtoarq;

	
	
	public void escrevercliente(String cliente) throws IOException {
		
		FileWriter fwcli = new FileWriter(clientearq, false);
		
		BufferedWriter bwcli = new BufferedWriter(fwcli);
		
		bwcli.write(cliente);
		
		bwcli.close();
		
		fwcli.close();
	}

	public void escreverproduto(String produto) throws IOException {
		
		
		FileWriter fwprod = new FileWriter(produtoarq, false);
		
		BufferedWriter bwprod = new BufferedWriter(fwprod);

		bwprod.write(produto);
		
		bwprod.close();
		
		fwprod.close();

	}

	public void escrevercompra(String compra) throws IOException {
	
		FileWriter fwcomp = new FileWriter(compraarq, false);

		BufferedWriter bwcomp = new BufferedWriter(fwcomp);

		bwcomp.write(compra);
		
		bwcomp.close();
		
		fwcomp.close();
	}
	

	public Salvarinfos() throws IOException {
		
		File file = new File("../baseDados");

		if (!file.exists()) {
		    file.mkdirs();
		}
	
		this.clientearq = new File("..\\baseDados\\cliente.txt");
		this.compraarq = new File("..\\baseDados\\compra.txt");
		this.produtoarq = new File("..\\baseDados\\produto.txt");

	}

	public void lerarquivo(Gerenciacliente gerecli, Gerenciacompra gerecomp, Gerenciaprodutos gereprod)
			throws IOException, ParseException {
		
		String[] dadoscli;
		String[] dadoscomp;
		String[] dadosprod;
		
		FileReader frcli = new FileReader(this.clientearq);
		FileReader frcomp = new FileReader(this.compraarq);
		FileReader frprod = new FileReader(this.produtoarq);

		BufferedReader brcli = new BufferedReader(frcli);
		BufferedReader brcomp = new BufferedReader(frcomp);
		BufferedReader brprod = new BufferedReader(frprod);
		
//''''''''''''''''''''''''''''READER''''''''''''''''''''''''''''''''''//

		String tempcli = "";
		String tempprod = "";
		String tempcomp = "";

		while (brcli.ready()) {
			tempcli += brcli.readLine();
		}

		while (brprod.ready()) {
			tempprod += brprod.readLine();
		}

		while (brcomp.ready()) {
			tempcomp += brcomp.readLine();
		}

//--------------------------carregar arquivo de cliente----------------------------------------------------------------

		tempcli = tempcli.replace('.', '=');

		String[] tempclientes;

		if (tempcli.equals("")) {
			System.out.println("nao existe clientes para carregar");

		} else {
			dadoscli = tempcli.split("-");

			for (int j = 0; j < dadoscli.length; j++) {

				if (dadoscli[j].indexOf("CNPJ") == -1) {
					tempclientes = dadoscli[j].split("=");
					

			
					Endereco end = new Endereco(Integer.parseInt(tempclientes[1]), tempclientes[3], tempclientes[5],
							tempclientes[7], tempclientes[9]);

					SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);
					Date data = formato.parse(tempclientes[13]);
					PessoaFisica pf = new PessoaFisica(tempclientes[11], end, data, tempclientes[15],
					Integer.parseInt(tempclientes[17]));
					gerecli.getPf().add(pf);
				} // pessoa fisica

				else {
					tempclientes = dadoscli[j].split("=");
					


					Endereco end = new Endereco(Integer.parseInt(tempclientes[1]), tempclientes[3], tempclientes[5],
							tempclientes[7], tempclientes[9]);

					SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);
					Date data = formato.parse(tempclientes[13]);
					PessoaJuridica pj = new PessoaJuridica(tempclientes[11], end, data, tempclientes[15],
							tempclientes[17], Integer.parseInt(tempclientes[19]));

					gerecli.getPj().add(pj);
				} // pessoa juridica
			}
		}
//---------------------------------------carregar arquivo de produtos----------------------------------------------------------------
		tempprod = tempprod.replace('_', '=');// tive que mudar de . para _ pois o double usa . e nao queria ter que
												// concatenar

		String[] tempprodutos;

		if (tempprod.equals("")) {
			System.out.println("nao tem produtos para carregar");
		
		} else {
			dadosprod = tempprod.split("-");

			for (int j = 0; j < dadosprod.length; j++) {

				if (dadosprod[j].indexOf("Validade") == -1) {
					tempprodutos = dadosprod[j].split("=");
					Produtos produto = new Produtos(tempprodutos[1], tempprodutos[5], tempprodutos[3],
					Double.parseDouble(tempprodutos[7]));
					gereprod.getProdutoslista().add(produto);
				} // produtos

				else {
					tempprodutos = dadosprod[j].split("=");


					SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);
					Date data = formato.parse(tempprodutos[9]);
					ProdutosPereciveis produtoval = new ProdutosPereciveis(tempprodutos[1], tempprodutos[5],
					tempprodutos[3], Double.parseDouble(tempprodutos[7]), data);
					gereprod.getProdutoslistaval().add(produtoval);
				} // produtos validade
			}
		}
//-----------------------------------------------carregar arquivo de compras----------------------------------------------------------------


		tempcomp = tempcomp.replace('_', '=');// tive que mudar de . para _ pois o double usa . e nao queria ter que
												// concatenar

		String[] tempcompra;
		double vt = 0, pagoatual = 0;
		String cred = "", nomeprod, id = "";
		int qtd = 0;
		Date data = null;
		Date datapago = null;

		  if(tempcomp.equals("") || tempcomp.equals(null) ) {
		  System.out.println("nao tem compras para carregados"); } 
		  
		  else {
		 
			  dadoscomp = tempcomp.split("-"); 
			  
			  
		  
		  for(int j = 0 ; j < dadoscomp.length; j++) {
			  
			  ArrayList<ItemdeCompra>itens= new ArrayList<ItemdeCompra>();
			  tempcompra = dadoscomp[j].split("=");
		  
				

		  for(int i = 0 ; i < tempcompra.length; i++) {
			 
			  		if( i == 1) { 
			  			id = tempcompra[i];
			  		}
			  		if( i == 3) {
			  			vt = Double.parseDouble(tempcompra[i]);
			  		}
			  		if(i == 5) {
			  			SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);
			  			data = formato.parse(tempcompra[5]);
			  		}
			  		if(i == 7) {
			  			cred =tempcompra[i];
			  		}
			  		if(i == 9) {
			  			pagoatual = Double.parseDouble(tempcompra[i]);
			  		}
			  		if(i == 11) {
			  			SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);
			  			
			  			if(tempcompra[11].equals("null")) {
			  				datapago = null;
			  			}
			  			else {
			  				datapago = formato.parse(tempcompra[11]);
			  			}
			  			
			  		}
			  		if(i > 11) {
			  			if(i%2 != 0) {
				  			String [] itemcompratemp = tempcompra[i].split(">");
				  		
				  			qtd = Integer.parseInt(itemcompratemp[0]);
				  			
				  			nomeprod = itemcompratemp[1];
				  			
				  			Produtos produto = gereprod.buscaprodutosave(nomeprod);
				  			
				  			if(produto == null) {
				  				System.out.println("produto nao achado!!! ");
				  			}
				  			else {
				  				ItemdeCompra itemcompra = new ItemdeCompra(qtd, produto);
				  				itens.add(itemcompra);
				  				
				  			}
			  			}
			  		
			  		}
			  
		  }
		  Compra compra = new Compra(id, data, itens, pagoatual, cred, datapago );
		  gerecomp.getCompras().add(compra);
		  }
		  	
		  }
//--------------------------------------------------------
		frprod.close();
		frcomp.close();
		frcli.close();

		brprod.close();
		brcomp.close();
		brcli.close();
	}

	public void criar() throws IOException {

		boolean existecli = clientearq.exists();

		boolean existeprod = produtoarq.exists();

		boolean existecomp = compraarq.exists();

		if (existecli == false) {
			System.out.println("arquivos cliente criado");
			clientearq.createNewFile();
		}

		else {
			System.out.println("arquivos cliente achado");

		}

		if (existeprod == false) {
			System.out.println("arquivos produto criado");

			produtoarq.createNewFile();
		} else {
			System.out.println("arquivos produto achado");

		}

		if (existecomp == false) {
			System.out.println("arquivos compra criado");

			compraarq.createNewFile();
		} else {
			System.out.println("arquivos compra achado");

		}
	}

	public void fecharesalvar(Gerenciacliente gerecli, Gerenciaprodutos gereprod, Gerenciacompra gerecomp) throws IOException {
//---------------------------------------------
		String info= "";
		for(PessoaFisica pf: gerecli.getPf()) {
			info += pf.paraString();
		}
		for(PessoaJuridica pj: gerecli.getPj()) {
			info += pj.paraString();
		}
		escrevercliente(info);
//----------------------------------------------
		info = "";
		for(Produtos prod: gereprod.getProdutoslista()) {
			info += prod.paraString();

		}
		for(ProdutosPereciveis prodval :gereprod.getProdutoslistaval()) {
			info += prodval.paraString();
		}
		escreverproduto(info);
//----------------------------------------------
		info ="";
		for(Compra comp: gerecomp.getCompras()) {
			 info += comp.paraString();
		}
		escrevercompra(info);

		System.out.println("Informaçoes salvas !!!");

	}

	

}
