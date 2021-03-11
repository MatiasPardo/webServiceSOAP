package org.anmat.conection;

import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import org.anmat.model.InformarProductoResponse;
import org.anmat.model.ResponseAnmat;
import org.anmat.model.TransaccionAnmat;



public class Test {

	public static void main(String[] args) throws MalformedURLException{
		
		//pruebaGetMedico();
		pruebaTrazaProduct();


	}
	
	private static void pruebaTrazaProduct() {
		Anmat miAnmat = new Anmat("7798232400004","Trazaisko1");
//		Anmat miAnmat = new Anmat("pruebasws","pruebasws");
//		miAnmat.setEnProduccion(false);
		
		TransaccionAnmat transaccion1 = new TransaccionAnmat();
		transaccion1.setfEvento("11/03/2021");
		transaccion1.sethEvento("10:10");
		transaccion1.setIdEvento(118);
		transaccion1.setgLNOrigen("7798232400004");
		transaccion1.setgTIN("0898202001310");
		transaccion1.setNroSerial("O14019209B-55");
		transaccion1.setNroRemito("00001-00047978");
		transaccion1.setCuitMedico("20178021606");
		transaccion1.setLote("735");
		transaccion1.setVencimiento("30/04/2023");
		
		TransaccionAnmat transaccion2 = new TransaccionAnmat();
		transaccion2.setfEvento("11/03/2021");
		transaccion2.sethEvento("10:10");
		transaccion2.setIdEvento(118);
		transaccion2.setgLNOrigen("7798232400004");
		transaccion2.setgTIN("0898202001013");
		transaccion2.setNroSerial("O13819206249");
		transaccion2.setNroRemito("R0000-00048140");
		transaccion2.setCuitMedico("23142235609");
		transaccion2.setLote("757");
		transaccion2.setVencimiento("30/04/2023");
				
		List<TransaccionAnmat> transs = new LinkedList<TransaccionAnmat>(); 
		transs.add(transaccion2);
		transs.add(transaccion1);
		InformarProductoResponse traza = (InformarProductoResponse) miAnmat.informarProductos(transs);
		System.out.println("Hay errores: "+traza.hayErrores()+"\n");
		System.out.println(traza.getCodigoTransaccion()+"\n");
		System.out.println(traza.getDescError()+"\n");

		System.out.println(traza.getResponseFull());

	}

	private static void pruebaGetMedico() {
//		Anmat miAnmat = new Anmat("7798232400004","Trazaisko1");
		Anmat miAnmat = new Anmat("Usuario","Pass");
		miAnmat.setEnProduccion(false);		
		
		miAnmat.getResponse().addProperty("apellidoNombre");
		miAnmat.getResponse().addProperty("nroMatricula");
		
		ResponseAnmat traza = miAnmat.getMedico("20134739445");
		System.out.println("Hay errores: "+traza.hayErrores());
		System.out.println(traza.getResponseFull());
		
	}

	
	
}
