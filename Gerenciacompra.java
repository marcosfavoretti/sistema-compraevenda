import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Date;

public class Gerenciacompra {
	
	private ArrayList<Compra> compras = new ArrayList<Compra>();
		
	
	public void fazendocompra(ArrayList<ItemdeCompra> itenslista ,Gerenciacliente gerecli, Gerenciaprodutos gereprod, String credencial, String identificador, double pagoatual ) throws IOException {
	
		double	valortotaltemp = 0;
	
		Date datapago;
	
		Date agora =  new Date(System.currentTimeMillis());	
	
	for(ItemdeCompra item: itenslista)
	{
		valortotaltemp += item.getValortotal();
	}
		if(pagoatual >= valortotaltemp) {
		
		datapago = new Date(System.currentTimeMillis()); 
		
		}
		else {
		datapago = null; //inicia como null pq ainda nao foi pago
	}
	
	Compra compra =  new Compra(identificador, agora , itenslista, pagoatual, credencial, datapago);

	
	
	compras.add(compra);
		
	}
	
	public int identificadorunico(String identificador) {

		int pode = 1;
		for(Compra comp: this.compras) {
			if(identificador.equals(comp.getIdentificador())) {
				pode = 0;
				System.out.println("identificador da compra ja usado!!!");
			}
		}
		
		return pode;
	}
		
	
	 
	public String paraString() {
		String info = "";
		
		for(Compra compra: compras)
		{
			info +="\n========================================";
			info += compra.paraString();
			info += "\n"+ "========================================";
		}
	return info;
	}
	
	public String atualizaçaoPagamento(double valor, String codcompra){
		String info ="";
		for(Compra compra: this.compras) {
			
			if(codcompra.equals(compra.getIdentificador())){
				compra.setPagoatual(valor);;
					if(valor == compra.getValortotal()) {
						info = "Compra paga";
						compra.setDatadepagamento(new Date(System.currentTimeMillis()));
					}
					else if(valor > compra.getValortotal()){
						info = "Compra paga e troco de: " + (valor-compra.getValortotal())+" R$";
						compra.setDatadepagamento(new Date(System.currentTimeMillis()));
					}
					else {
						info = "Compra nao paga, falta: "+ (compra.getValortotal()-valor)+" R$";
					}
			}
			
			
		}
		return info;
	}
	
	
	public String buscapornumero(String codcompra) {
	String info = "";
		for(Compra compra: this.compras) {
			if(codcompra.equals(compra.getIdentificador())){
				 info += compra.paraString();
			}
		}
	
	return info;
	}
	
	
	public String verificaatt(String codcompra) {
	String info = "";
		for(Compra compra: this.compras) {
			if(codcompra.equals(compra.getIdentificador()) && compra.getPagoatual()<compra.getValortotal()){
				 info += compra.paraString();
			}
		}
	
	return info;
	}
	

	public ArrayList<Compra> getCompras() {
		return compras;
	}


	public void setCompras(ArrayList<Compra> compras) {
		this.compras = compras;
	}
	
	
	public String comprasnaopagas() {
		String info="";
		for(Compra compra: this.compras) {
			if(compra.getPagoatual() != compra.getValortotal()) {
				info = compra.paraString();			}
		}
		return info;
	}
	
	public Compra [] ultimascompras() {
		
		ArrayList<Compra> compraspagas = new ArrayList<Compra>();
		
		for(Compra comp: this.compras) {
			Date datapago = comp.getDatadepagamento();
			
			if( datapago != null) {//verifica q so havera pagos na lista
				compraspagas.add(comp);
				}
			else {
			
			}
			
		}			
		Compra [] vetor = new Compra[compraspagas.size()];
		int p= 0;
		for(Compra compra: compraspagas) {
			vetor[p] = compra;//atribuindo ao vetor as compras
			p++;
		}

		int rec = 0;
		
		for(int i=0; i < vetor.length;i++) {
			
			rec = i;
			
			for(int j=i+1 ;j<vetor.length; j++) {
				
				
				if(vetor[j].getDatadepagamento().compareTo(vetor[rec].getDatadepagamento())==1) {//se a data do j for depois de rec
					rec = j;
				}
				
			}
			
			Compra temp = vetor[rec];
			vetor[rec] = vetor[i];
			vetor[i] = temp;
	
			}

		
			return vetor;
	}
	

	public ArrayList<Compra> compracara() {
		
		ArrayList<Compra> compracara = new	ArrayList<Compra>();	
		
		int inicial = 0;
		int maiorVal = 0;
		
		for(int i = 1 ; i < this.compras.size(); i++){
	
			if(this.compras.get(inicial).getValortotal() > this.compras.get(i).getValortotal()){
				maiorVal = inicial;
			}
			else if(this.compras.get(inicial).getValortotal() < this.compras.get(i).getValortotal()){
				maiorVal = i;
				inicial = i;
			}
			else {
				System.out.println("sao iguais");
			}
			
		}
		compracara.add(compras.get(maiorVal));
		
		for(Compra barata: this.compras) {
			if(compras.get(maiorVal) == barata ) {
				if(!barata.getIdentificador().equals(this.compras.get(maiorVal).getIdentificador())) {
					compracara.add(barata);
				}
			}
		}
		
 
		return compracara;
	}
	
	public ArrayList<Compra> comprabarata() {

		ArrayList<Compra> comprabarata = new	ArrayList<Compra>();	
		
		int inicial = 0;
		int menorVal = 0;
		
		for(int i = 1 ; i < this.compras.size(); i++){
	
			if(this.compras.get(inicial).getValortotal() < this.compras.get(i).getValortotal()){
				menorVal = inicial;
			}
			else if(this.compras.get(inicial).getValortotal() > this.compras.get(i).getValortotal()){
				menorVal = i;
				inicial = i;
			}
	
  }
		comprabarata.add(compras.get(menorVal));
		
		for(Compra barata: this.compras) {
			if(compras.get(menorVal) == barata ) {
				if(!barata.getIdentificador().equals(this.compras.get(menorVal).getIdentificador())) {
					comprabarata.add(barata);
				}
			}
		}
		
 
		return comprabarata;
	}
	
	public ArrayList<Compra> compnaopagas() {
		
		ArrayList<Compra> compras = new ArrayList<Compra>();
		
		 for(Compra comp : this.compras) {
				if(comp.getPagoatual()<comp.getValortotal()) {
				
					compras.add(comp) ;
				}
			}
		
		return compras;
	}
	
	public double valortotal12() throws ParseException {
		
		double valortotal = 0;

		//data atual - 12 meses
		  Calendar datamin = Calendar.getInstance();//pegar a data de 12 meses atras
		  Format formato2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);
		  datamin.add(Calendar.MONTH, -(12));
		
		  //usei calender pois achei mais facil subtrair 12 meses 
		  
		  //conversao de calender para date
		  String dataminstr = formato2.format(datamin.getTime());
		  SimpleDateFormat formatocalendermin = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);
		  Date datadatemin = formatocalendermin.parse(dataminstr);
		  //--------------------------------------
		  Date agora = new Date(System.currentTimeMillis());//pega a data atual do sistema
		  //--------------------------------------
		  
		  for(Compra compra: this.compras) {
			  
			  if(datadatemin.compareTo(compra.getData()) == 0 || datadatemin.compareTo(compra.getData()) < 0 && agora.compareTo(compra.getData()) > 0 ) {
					  
				  valortotal += compra.getValortotal();
			  }
			
		  }
		  
		return valortotal;
	}
}
