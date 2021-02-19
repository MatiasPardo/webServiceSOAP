package org.anmat.conection;

import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import org.anmat.model.TransaccionAnmat;



public class Test {

	public static void main(String[] args) throws MalformedURLException{
		
		pruebaTrazaProduct();


	}
	
	private static void pruebaTrazaProduct() {
//		Anmat miAnmat = new Anmat("7798232400004","Trazaisko1");
		Anmat miAnmat = new Anmat("pruebasws","Clave1234");
		
		TransaccionAnmat transaccion = new TransaccionAnmat();
		transaccion.setfEvento("17/11/2020");
		transaccion.sethEvento("11:17");
		transaccion.setIdEvento(103);
		transaccion.setgLNOrigen("7798232400004");
		transaccion.setgTIN("05029867691614");
		transaccion.setNroSerial("10914424659");
		transaccion.setNroRemito("R0004-00046594");
		transaccion.setgLNDestino("9960110390006");
		transaccion.setLote("724");
		transaccion.setVencimiento("07/10/2024");
		
		miAnmat.getResponse().setKeysSoapXML("c_error");
		miAnmat.getResponse().setKeysSoapXML("d_error");
		List<TransaccionAnmat> transs = new LinkedList<TransaccionAnmat>(); 
		transs.add(transaccion);
		for(String mens: miAnmat.informarProducto(transs)){
				System.out.println(mens);
			}
	}

	private static void pruebaGetMedico() {
//		Anmat miAnmat = new Anmat("7798232400004","Trazaisko1");
		Anmat miAnmat = new Anmat("pruebasws","Clave1234");
		
		
		miAnmat.getResponse().setKeysSoapXML("c_error");
		miAnmat.getResponse().setKeysSoapXML("d_error");
		
		for(String mens: miAnmat.getMedico("20134739445")){
				System.out.println(mens);
			}
	}

	
	
}