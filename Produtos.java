import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Produtos {
	
	private String codigo;
	private String nomeproduto;
	private String descricao;
	private double preco;
	
	
	public Produtos(String codigo, String nomeproduto, String descricao, double preco) {
		this.codigo = codigo;
		this.nomeproduto = nomeproduto;
		this.descricao = descricao;
		this.preco = preco;
		
	} 	
	
	public String paraString() {
		String info = "";
		info += "\nCodigo="+this.codigo+"_\n";
		info += "\nDescricao="+this.descricao+"_\n";
		info += "\nNome="+this.nomeproduto+"_\n";
		info += "\nPreco="+this.preco+"_\n";
		info += "-";
		return info;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNomeproduto() {
		return nomeproduto;
	}
	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	
	
	

	
}
