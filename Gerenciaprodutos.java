import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Gerenciaprodutos {
	
	private ArrayList<Produtos> produtoslista = new ArrayList<Produtos>();
	private ArrayList<ProdutosPereciveis> produtoslistaval = new ArrayList<ProdutosPereciveis>();

	
	public void cadastroproduto(String codigo, String nomeprod,String descricao,double preco,String data) throws ParseException, IOException {

		String dataRecebida = data; 
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date dataFormatada = formato.parse(dataRecebida); 
		
		ProdutosPereciveis produtoval = new ProdutosPereciveis(codigo, nomeprod, descricao, preco , dataFormatada);
		produtoslistaval.add(produtoval);
		
	}

	public void cadastroproduto(String codigo, String nomeprod,String descricao,double preco) throws ParseException, IOException {

		Produtos produto = new Produtos(codigo, nomeprod, descricao, preco);
		
		produtoslista.add(produto);	


	}
	public int codigounico(String codigo) {

		int pode = 1;
		for(ProdutosPereciveis prodval: this.produtoslistaval) {
			if(codigo.equals(prodval.getCodigo())) {
				pode = 0;
				System.out.println("codigo do produto ja usado!!!");
			}
		}
		for(Produtos prod: this.produtoslista) {
			if(codigo.equals(prod.getCodigo())) {
				pode = 0;
				System.out.println("codigo do produto ja usado!!!");
			}
		}
		return pode;
	}
		
		
	public int buscarcodigoprod(String codigo) {
		int pos = -1, i = 0 ;

		for(Produtos prod: this.produtoslista) {
			if(prod.getCodigo().equals(codigo)) {
					pos = i;	
				}
		 else {
			 i++;
		 }
	   }
				return pos;		
	}

	public int buscarcodigoprodval(String codigo) {
		int pos = -1, i = 0 ;

		for(ProdutosPereciveis prodval: this.produtoslistaval) {
			if(prodval.getCodigo().equals(codigo)) {
					pos = i;	
				}
		 else {
			 i++;
		 }
	   }
				return pos;		
	}
	
	
	  public String paraString() {//manda todas da lista sem eu ter q fazer um for each da lista toda vez
		  String info = "";
	  
	  for(Produtos produtos: produtoslista) { info
	  +="\n========================================"; info +=
	  produtos.paraString(); info += "\n"+
	  "========================================"; } for(ProdutosPereciveis
	  produtosval: produtoslistaval) { info
	  +="\n========================================"; info +=
	  produtosval.paraString(); info += "\n"+
	  "========================================"; } 
	  return info; }
	 

	
	public ArrayList<Produtos> getProdutoslista() {
		return produtoslista;
	}
	
	public void setProdutoslista(ArrayList<Produtos> produtoslista) {
		this.produtoslista = produtoslista;
	}
	
	public ArrayList<Produtos> buscaproduto (String nome) {
		
		ArrayList<Produtos> prodachados = new ArrayList<Produtos>();		
		
		for(Produtos prod: produtoslista) {
			if(prod.getNomeproduto().toLowerCase().equals(nome)) {
				prodachados.add(prod);
			}
		}
			for(ProdutosPereciveis prodval: produtoslistaval) {
				if(prodval.getNomeproduto().toLowerCase().equals(nome)) {
				prodachados.add(prodval);
			}
		}
		return prodachados;
	}
	public Produtos buscaprodutosave (String nome) {
		Produtos achado = null;
		
		for(Produtos prod: produtoslista) {
			if(prod.getNomeproduto().equals(nome)) {
				achado = prod;
				return achado;
			}
		}
			for(ProdutosPereciveis prodval: produtoslistaval) {
				if(prodval.getNomeproduto().equals(nome)) {
					 achado = prodval;
					 return achado;
				}
		}
		return achado;
	}
	
	public ArrayList<ProdutosPereciveis> vencidos() {
		
		ArrayList<ProdutosPereciveis> listavencidos = new ArrayList<ProdutosPereciveis>();
		
		for(ProdutosPereciveis prod: produtoslistaval) {	
			if(prod.vencidoSouN()==false) {
				listavencidos.add(prod);
			}
		}
		
		return listavencidos;
	}
	
	public ArrayList<ProdutosPereciveis> getProdutoslistaval() {
		return produtoslistaval;
	}
	public void setProdutoslistaval(ArrayList<ProdutosPereciveis> produtoslistaval) {
		this.produtoslistaval = produtoslistaval;
	}

}
