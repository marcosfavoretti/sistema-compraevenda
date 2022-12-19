import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProdutosPereciveis extends Produtos{
	

	private Date validade;
	

	public ProdutosPereciveis(String codigo, String nomeproduto, String descricao, double preco, Date validade) throws ParseException {
		super(codigo, nomeproduto, descricao, preco );
		this.validade = validade;
	}
	
	public boolean vencidoSouN() {
	
	Date agora = new Date(System.currentTimeMillis());//pega a data atual do sistema
	
	boolean vencimentoTouF = false;
	int vencimento;
	
	vencimento = this.validade.compareTo(agora);
	
	
	if(vencimento < 0) {
		vencimentoTouF = false;
	}
	else if (vencimento == 0) {
		vencimentoTouF = false;
	}
	else if(vencimento > 0){
		vencimentoTouF = true;
	}
	System.out.println(vencimentoTouF);
	return vencimentoTouF;
	
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	public String paraString() {
		String info = "";
		
		info += "\nCodigo="+super.getCodigo()+"_\n";
		info +="\nDescricao="+super.getDescricao()+"_\n";
		info += "\nNome="+super.getNomeproduto()+"_\n";
		info +="\nPreco="+ super.getPreco()+"_\n";
		info += "\nValidade="+this.validade+"_\n";
		info += "-";
		
		
		return info;
	}
}
