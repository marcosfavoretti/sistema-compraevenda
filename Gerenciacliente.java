import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class Gerenciacliente {
	
	private ArrayList<PessoaFisica> pf = new ArrayList<PessoaFisica>();
	private ArrayList<PessoaJuridica> pj = new ArrayList<PessoaJuridica>();
	
	
	
	public void cadastrocliente( int numero,String bairro,String cep,String cidade,String estado,String nome,String cpf,int qtdmaxparcelas) throws IOException {
	
	
		
		Endereco enderecocliente = new Endereco(numero, bairro, cep, cidade , estado);
		
		Date agora =  new Date(System.currentTimeMillis());
		
		PessoaFisica clientePF = new PessoaFisica(nome ,enderecocliente, agora, cpf, qtdmaxparcelas);
		
		this.pf.add(clientePF);

		}
	
public void cadastrocliente( int numero,String  bairro,String cep,String cidade,String estado,String nome,String cnpj,String razaosocial,int prazopagamento) throws IOException {
		
		
		Endereco enderecocliente = new Endereco(numero, bairro, cep, cidade , estado);
		
		Date agora =  new Date(System.currentTimeMillis());
		
		PessoaJuridica clientePJ = new PessoaJuridica(nome ,enderecocliente, agora, cnpj,razaosocial, prazopagamento);
		
		this.pj.add(clientePJ);
		
	}
	public int cpfunico(String cpf) {

		int pode = 1;
		for(PessoaFisica Pf: this.pf) {
			if(cpf.equals(Pf.getCpf())) {
				pode = 0;
				System.out.println("cpf ja usado!!!");
			}
		}

		return pode;
	}
	
	public int cnpjunico(String cnpj) {
		
		int pode = 1;
		for(PessoaJuridica Pj: this.pj) {
			if(cnpj.equals(Pj.getCnpj())) {
				pode = 0;
				System.out.println("CNPJ ja usado!!!");
			}
		}
		
			
		return pode;
	}
	
	
	public String paraString() {
		
		String info = "";
		
		for(PessoaFisica cliente: pf)
		{
			info +="\n========================================";
			info += cliente.paraString();
			info += "\n"+ "========================================";
		}
		for(PessoaJuridica cliente: pj)
		{
			info +="\n========================================";
			info += cliente.paraString();
			info += "\n"+ "========================================";
		}
		return info;
	}

	

	  
		public void cadastrarJ(PessoaJuridica pessoaj) {
			this.pj.add(pessoaj);
			
		}
		
		public void cadastrarF(PessoaFisica pessoaf) {
			this.pf.add(pessoaf);
		}
			
//===========================================================================================
	  
	public int buscarcpf(String cpf) {

		int pos = -1, i =0;
		
		for(PessoaFisica pfs: this.pf) {
			
			if(pfs.getCpf().equals(cpf)) {

				pos = i;
			  }
		
			else {
			  i++;
			  }
		
		}
		return pos;
	}
	
//===========================================================================================
	public int buscarpornomepf(String nome) {

		
		int pos = -1, i =0;
		
		for(PessoaFisica pfs: this.pf) {
			
			if(pfs.getNome().equals(nome)) {

				pos = i;
			  }
		
			else {
			  i++;
			  }
		
		}
		return pos;
	}
//===========================================================================================para Pj lista
	public int buscarpornomepj(String nome) {

		
		int pos = -1, i =0;
		
		
		for(PessoaJuridica pjs: this.pj) {
			
			if(pjs.getNome().equals(nome)) {
				pos = i;
			  }
		
			else {
			  i++;
			  }
		
		}
		return pos;
	}
//===========================================================================================para Pj lista

	public int buscacnpj(String cnpj) {
		
		int pos = -1, i =0;
		
		for(PessoaJuridica pjs: this.pj) {
			if(pjs.getCnpj().equals(cnpj)) {
				
					pos = i;
					
				}
		 else {
			 i++;
		 }
	   }
				return pos;		
	}
		
	public void excluipj(int pos) throws IOException {
		

		this.pj.remove(pos);
			

			
	}
	
	public void excluipf(int pos) throws IOException {
			
			this.pf.remove(pos);
	
			
	}





	public ArrayList<Cliente> buscaporcaracteres(String caracteres) {
	
	ArrayList<Cliente> clienteachado = new ArrayList<Cliente>();

	for(PessoaJuridica pessoaju: pj) {
		if(pessoaju.getNome().toLowerCase().indexOf(caracteres) == 0) {
			clienteachado.add(pessoaju);
	}
		else {
			
		}
	}
	for(PessoaFisica pessoafi: pf) {
		if(pessoafi.getNome().toLowerCase().indexOf(caracteres)==0) {
			clienteachado.add(pessoafi);	
		}
		else {
			
		}
	}
	
	return clienteachado;
	}
	
	
	public ArrayList<PessoaFisica> getPf() {
		return pf;
	}

	public void setPf(ArrayList<PessoaFisica> pf) {
		this.pf = pf;
	}



	public ArrayList<PessoaJuridica> getPj() {
		return pj;
	}



	public void setPj(ArrayList<PessoaJuridica> pj) {
		this.pj = pj;
	}
}
