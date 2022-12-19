
import java.util.ArrayList;
import java.util.Date;

public class Compra {
	
	private String identificador;
	private Date data;
	private double valortotal;///soma valor total de cada item de compra
	private ArrayList<ItemdeCompra> itens;//um ou mais itens podem ser comprados em uma compra
	private double pagoatual;
	private String credencial;

//>
	private Date datadepagamento;//nao estava pedindo esse atributo porem e o unico jeito de ordenas as 10 ultimas compras pagas
//>

public Compra(String identificador, Date agora, ArrayList<ItemdeCompra> itens, double pago, String cpf, Date datapago) {
	double valortotaltemp = 0;
	this.identificador = identificador;
	
	this.data = agora;
	
	for(ItemdeCompra item: itens)
	{
		valortotaltemp += item.getValortotal();
	}
	this.valortotal = valortotaltemp;
	
	this.credencial =  cpf;
	
	this.itens = itens;
	
	this.pagoatual = pago;
	
	this.datadepagamento = datapago;
}

public String paraString() {
	int j = 1 ; 
	String info ="";
	info += "\nIdentificador="+this.identificador+"_\n";
	info += "\nValor total="+this.valortotal+"_\n";
	info +=	"\nData="+this.data+"_\n";
	info += "\nCNPJ ou CPF=" + this.credencial+"_\n";
	info += "\nValor pago=" + this.pagoatual+"_\n";
	info += "\nData do pagamento=" + this.datadepagamento+"_\n";
	for(ItemdeCompra item : itens) {
	info += "\nNomes dos itens comprados ["+j+"]="+item.getQuantidade()+ ">"+ item.getNome()+"_\n";//talvez mostrar todas as infos de itenscompra porem tem q criar a para string na classe 
	j++;
	}
	info+="-";
	return info;
}

public double faltapagar() {
	
	double valorfaltante = 0;
	
	valorfaltante = this.valortotal - this.pagoatual;
	
	return valorfaltante;
}



public String getIdentificador() {
	return identificador;
}



public void setIdentificador(String identificador) {
	this.identificador = identificador;
}



public Date getData() {
	return data;
}



public void setData(Date data) {
	this.data = data;
}



public double getValortotal() {
	return valortotal;
}



public void setValortotal(double valortotal) {
	this.valortotal = valortotal;
}

public ArrayList<ItemdeCompra> getItens() {
	return itens;
}



public void setItens(ArrayList<ItemdeCompra> itens) {
	this.itens = itens;
}


public double getPagoatual() {
	return pagoatual;
}


public void setPagoatual(double pagoatual) {
	this.pagoatual = pagoatual;
}
public String getCredencial() {
	return credencial;
}
public void setCredencial(String credencial) {
	this.credencial = credencial;
}

public Date getDatadepagamento() {
	return datadepagamento;
}

public void setDatadepagamento(Date datadepagamento) {
	this.datadepagamento = datadepagamento;
}

}
