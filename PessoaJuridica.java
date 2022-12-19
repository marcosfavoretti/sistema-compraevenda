import java.util.Date;

public class PessoaJuridica extends Cliente {
	
	private String cnpj;
	private String razaosocial;
	private int prazopagamento;
	
	@Override
	public String paraString(){
		
		String info = "";
		
		info += super.getEndereco().paraString();
		info += "\nNome="+ super.getNome()+".\n";
		info += "\nData de cadastro="+super.getDatacadastro()+".\n";
		info += "\nCNPJ="+this.cnpj+".\n";
		info += "\nRazao social="+this.razaosocial+".\n";
		info += "\nPrazo de pagamento em dias="+this.prazopagamento+".\n";
		info += "-";
		return info;
		
	}
	//construtor pj
	public PessoaJuridica(String nome,Endereco endereco, Date datacadastro, String cnpj, String razaosocial, int prazo){
		super.setNome(nome);
		super.setEndereco(endereco);
		super.setDatacadastro(datacadastro);
		this.cnpj = cnpj;
		this.razaosocial = razaosocial;
		this.prazopagamento = prazo;
	}	

	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	public int getPrazopagamento() {
		return prazopagamento;
	}

	public void setPrazopagamento(int prazopagamento) {
		this.prazopagamento = prazopagamento;
	}

	
}
