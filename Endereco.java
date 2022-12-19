
public class Endereco {
 private int numero;
 private String bairro; 
 private String cep;
 private String cidade ;
 private String estado;
 
 
public String paraString() {
String info = "";

info += "\nNumero=" + this.numero +".\n";
info += "\nBairro=" + this.bairro +".\n";
info += "\nCep=" + this.cep+".\n";
info += "\nCidade=" + this.cidade +".\n";
info += "\nEstado="+ this.estado +".\n";


return info;
 }

public Endereco(int numero, String bairro, String cep, String cidade, String estado) {
	this.numero= numero;
	this.bairro = bairro;
	this.cep = cep;
	this.cidade = cidade;
	this.estado = estado;
}


public int getNumero() {
	return numero;
}


public void setNumero(int numero) {
	this.numero = numero;
}


public String getBairro() {
	return bairro;
}


public void setBairro(String bairro) {
	this.bairro = bairro;
}


public String getCep() {
	return cep;
}


public void setCep(String cep) {
	this.cep = cep;
}


public String getCidade() {
	return cidade;
}


public void setCidade(String cidade) {
	this.cidade = cidade;
}


public String getEstado() {
	return estado;
}


public void setEstado(String estado) {
	this.estado = estado;
}
}
