import java.util.Date;

public class PessoaFisica extends Cliente{
	
	private String cpf;
	private int qtdmaxparcelas;
	
	@Override
	public String paraString(){
		String info = "";
		info += super.getEndereco().paraString();
		info += "\nNome="+ super.getNome()+".\n";
		info += "\nData de cadastro="+super.getDatacadastro()+".\n";
		info += "\nCpf="+this.cpf+".\n";
		info +=	"\nQuantidade de parcelas="+this.qtdmaxparcelas+".\n";
		info += "-";
		return info;
	}
	//construtor de pf
	public PessoaFisica(String nome,Endereco endereco, Date datacadastro, String cpf, int qtdmaxparcelas ){
		super.setNome(nome);
		super.setEndereco(endereco);
		super.setDatacadastro(datacadastro);
		this.cpf = cpf;
		this.qtdmaxparcelas = qtdmaxparcelas;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getQtdmaxparcelas() {
		return qtdmaxparcelas;
	}
	public void setQtdmaxparcelas(int qtdmaxparcelas) {
		this.qtdmaxparcelas = qtdmaxparcelas;
	}
	

}
