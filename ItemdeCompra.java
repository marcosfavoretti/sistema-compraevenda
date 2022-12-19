
public class ItemdeCompra {
	
	private int quantidade;
	private double valortotal;
	private String nome;
	private double valorunid;
	private Produtos produtos;
	
	public ItemdeCompra(int qtd, Produtos produto) {
		this.quantidade=qtd;
		this.nome = produto.getNomeproduto();
		this.valorunid = produto.getPreco();
		this.valortotal = this.valorunid * qtd;
	}
	public ItemdeCompra(int qtd, ProdutosPereciveis produto) {
		this.quantidade=qtd;
		this.nome = produto.getNomeproduto();
		this.valorunid = produto.getPreco();
		this.valortotal = this.valorunid * qtd;
	}
	
	public String paraString() {
		String info = "";
		info += "\nQuantidade="+this.quantidade+"_\n";
		info += "\nvalototal="+this.valortotal+"_\n";
		info += "\nNome="+this.nome+"_\n";
		info += "\nValor da unidade="+this.valorunid+"_\n";
		info += "\nValor da Total="+this.valortotal+"_\n";
		info += "-";
		return info;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValortotal() {
		return valortotal;
	}
	public void setValortotal(double valortotal) {
		this.valortotal = valortotal;
	}
	public Produtos getProdutos() {
		return produtos;
	}
	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorunid() {
		return valorunid;
	}

	public void setValorunid(double valorunid) {
		this.valorunid = valorunid;
	}
	

}
