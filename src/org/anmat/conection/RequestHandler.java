package org.anmat.conection;


import java.util.List;

import org.anmat.model.ResponseAnmat;
import org.anmat.model.TransaccionAnmat;


public class RequestHandler {

	private final static String  USER_HEADER = "testwservice";
	private final static String  PWD_HEADER = "testwservicepsw";
		
		
	private Anmat anmat;
	
	
	public RequestHandler(Anmat anmat){
		this.anmat = anmat;
	}
	
	
	public ResponseAnmat buildMedicalDataRequest(String cuit){
		StringBuilder body = new StringBuilder(""
				+ "<soap:Body>"
				+ "\r\n <ns1:getMedico xmlns:ns1=\"http://business.WebServiceSenasa.inssjp.com/\">"
					+ "\r\n <usuario>"+this.anmat.getUser()+"</usuario>"
					+ "\r\n <password>"+this.anmat.getPwd()+"</password>"
					+ "\r\n <cuit>"+cuit+"</cuit>"
				+ "\r\n </ns1:getMedico>"
			+ "\r\n </soap:Body>");
		this.anmat.crearConexionBase();
		try {
			return this.anmat.enviarRequest(this.armarConsultaSoap(body.toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ResponseAnmat buildCancelTransacc(String codigoTransaccion){
		StringBuilder body = new StringBuilder(""
				+ "<soap:Body>"
				+ "\r\n <ns1:sendCancelacTransacc xmlns:ns1=\"http://business.WebServiceSenasa.inssjp.com/\">"
					+ "\r\n <transaccion>"+codigoTransaccion+"</transaccion>"
					+ "\r\n <usuario>"+this.anmat.getUser()+"</usuario>"
					+ "\r\n <password>"+this.anmat.getPwd()+"</password>"
				+ "\r\n </ns1:sendCancelacTransacc>"
			+ "\r\n </soap:Body>");
		this.anmat.crearConexionBase();
		try {
			return this.anmat.enviarRequest(this.armarConsultaSoap(body.toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ResponseAnmat buildCancelTransaccParcial(String codigoTransaccion, String gtin, String serie){
		StringBuilder body = new StringBuilder(""
				+ "<soap:Body>"
				+ "\r\n <ns1:sendCancelacTransaccParcial xmlns:ns1=\"http://business.WebServiceSenasa.inssjp.com/\">"
					+ "\r\n <usuario>"+this.anmat.getUser()+"</usuario>"
					+ "\r\n <password>"+this.anmat.getPwd()+"</password>"
					+ "\r\n <transaccion>"+codigoTransaccion+"</transaccion>"
					+ "\r\n <gtin>"+gtin+"</gtin>"
					+ "\r\n <serie>"+serie+"</serie>"
				+ "\r\n </ns1:sendCancelacTransaccParcial>"
			+ "\r\n </soap:Body>");
		this.anmat.crearConexionBase();
		try {
			return this.anmat.enviarRequest(this.armarConsultaSoap(body.toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ResponseAnmat buildMedicalProductRequest(TransaccionAnmat tr){
		StringBuilder body = new StringBuilder(""
				+ "<soapenv:Body>"
				+"\r\n <bus:informarProducto>"
					+"\r\n <transacciones>"
//						+"\r\n <apellidos></apellidos>"
//						+"\r\n <calle></calle>"
//						+"\r\n <codHiv></codHiv>"
//						+"\r\n <codPostal></codPostal>"
						+"\r\n <cuitMedico>"+tr.getCuitMedico()+"</cuitMedico>"
//						+"\r\n <departamento>"+""+"</departamento>"
//						+"\r\n <fechaNacimiento>"+""+"</fechaNacimiento>"
						+"\r\n <glnDestino>"+tr.getgLNDestino()+"</glnDestino>"
						+"\r\n <glnOrigen>"+tr.getgLNOrigen()+"</glnOrigen>"
						+"\r\n <gtin>"+tr.getgTIN()+"</gtin>"
						+"\r\n <idEvento>"+tr.getIdEvento()+"</idEvento>"
						+"\r\n <idMotivoDevolucion>"+tr.getIdMotivoDevolucion()+"</idMotivoDevolucion>"
//						+"\r\n <idObraSocial>"+""+"</idObraSocial>"
//						+"\r\n <idTipoDocumento>"+""+"</idTipoDocumento>"
//						+"\r\n <localidad>"+""+"</localidad>"
						+"\r\n <lote>"+tr.getLote()+"</lote>"
//						+"\r\n <nombres>"+""+"</nombres>"
//						+"\r\n <nroAfiliado>"+""+"</nroAfiliado>"
//						+"\r\n <nroCalle>"+""+"</nroCalle>"
//						+"\r\n <nroDocumento>"+""+"</nroDocumento>"
						+"\r\n <nroFactura>"+tr.getNroFactura()+"</nroFactura>"
						+"\r\n <nroRemito>"+tr.getNroRemito()+"</nroRemito>"
						+"\r\n <nroSerial>"+tr.getNroSerial()+"</nroSerial>"
//						+"\r\n <otroMotivoDevolucion>"+""+"</otroMotivoDevolucion>"
//						+"\r\n <piso>"+""+"</piso>"
//						+"\r\n <provincia>"+""+"</provincia>"
//						+"\r\n <sexo>"+""+"</sexo>"
//						+"\r\n <telefono>"+""+"</telefono>"
						+"\r\n <vencimiento>"+tr.getVencimiento()+"</vencimiento>"
						+"\r\n <fEvento>"+tr.getfEvento()+"</fEvento>"
						+"\r\n <hEvento>"+tr.gethEvento()+"</hEvento>"
//						+"\r\n <codDiagnostico>"+""+"</codDiagnostico>"
						+"\r\n </transacciones>"
						+"\r\n <usuario>"+this.anmat.getUser()+"</usuario>"
						+"\r\n <password>"+this.anmat.getPwd()+"</password>"
					+"\r\n </bus:informarProducto>"
				+"\r\n</soapenv:Body>");
		
		this.anmat.crearConexionBase();
		
		try {
			return this.anmat.enviarRequest(this.armarConsultaSoapEnv(body.toString()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	public ResponseAnmat buildMedicalProductsRequest(List<TransaccionAnmat> transacciones){
		StringBuilder body = new StringBuilder(""
				+ "<soapenv:Body>"
				+"\r\n <bus:informarProducto>"
					);
				for(int i = 0;i<transacciones.size();i++){
					TransaccionAnmat tr = transacciones.get(i);
						body.append(""
								+"\r\n <transacciones>"
//								+"\r\n <apellidos></apellidos>"
//								+"\r\n <calle></calle>"
//								+"\r\n <codHiv></codHiv>"
//								+"\r\n <codPostal></codPostal>"
								+"\r\n <cuitMedico>"+tr.getCuitMedico()+"</cuitMedico>"
//								+"\r\n <departamento>"+""+"</departamento>"
//								+"\r\n <fechaNacimiento>"+""+"</fechaNacimiento>"
								+"\r\n <glnDestino>"+tr.getgLNDestino()+"</glnDestino>"
								+"\r\n <glnOrigen>"+tr.getgLNOrigen()+"</glnOrigen>"
								+"\r\n <gtin>"+tr.getgTIN()+"</gtin>"
								+"\r\n <idEvento>"+tr.getIdEvento()+"</idEvento>"
								+"\r\n <idMotivoDevolucion>"+tr.getIdMotivoDevolucion()+"</idMotivoDevolucion>"
//								+"\r\n <idObraSocial>"+""+"</idObraSocial>"
//								+"\r\n <idTipoDocumento>"+""+"</idTipoDocumento>"
//								+"\r\n <localidad>"+""+"</localidad>"
								+"\r\n <lote>"+tr.getLote()+"</lote>"
//								+"\r\n <nombres>"+""+"</nombres>"
//								+"\r\n <nroAfiliado>"+""+"</nroAfiliado>"
//								+"\r\n <nroCalle>"+""+"</nroCalle>"
//								+"\r\n <nroDocumento>"+""+"</nroDocumento>"
								+"\r\n <nroFactura>"+tr.getNroFactura()+"</nroFactura>"
								+"\r\n <nroRemito>"+tr.getNroRemito()+"</nroRemito>"
								+"\r\n <nroSerial>"+tr.getNroSerial()+"</nroSerial>"
//								+"\r\n <otroMotivoDevolucion>"+""+"</otroMotivoDevolucion>"
//								+"\r\n <piso>"+""+"</piso>"
//								+"\r\n <provincia>"+""+"</provincia>"
//								+"\r\n <sexo>"+""+"</sexo>"
//								+"\r\n <telefono>"+""+"</telefono>"
								+"\r\n <vencimiento>"+tr.getVencimiento()+"</vencimiento>"
								+"\r\n <fEvento>"+tr.getfEvento()+"</fEvento>"
								+"\r\n <hEvento>"+tr.gethEvento()+"</hEvento>"
//								+"\r\n <codDiagnostico>"+""+"</codDiagnostico>"
								+"\r\n </transacciones>"
						+ "");
				}
						body.append(
						"\r\n <usuario>"+this.anmat.getUser()+"</usuario>"
						+"\r\n <password>"+this.anmat.getPwd()+"</password>"
					+"\r\n </bus:informarProducto>"
				+"\r\n</soapenv:Body>");
		
		this.anmat.crearConexionBase();
		
		try {
			return this.anmat.enviarRequest(this.armarConsultaSoapEnv(body.toString()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	
	

	private String armarConsultaSoap(String body) {
		
		StringBuilder soapEnvelopeOpen = new StringBuilder("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n ");
		soapEnvelopeOpen.append(this.getHeader()).append(body).append("\r\n</soap:Envelope>");
		return soapEnvelopeOpen.toString();
	}
	
	private String armarConsultaSoapEnv(String body) {
		
		StringBuilder soapEnvelopeOpen = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bus=\"http://business.webservice.inssjp.com/\">\r\n ");
		soapEnvelopeOpen.append(this.getHeaderEnv()).append(body).append("\r\n</soapenv:Envelope>");
		return soapEnvelopeOpen.toString();
	}
	
	private StringBuilder getHeader(){
		StringBuilder header = new StringBuilder(""
				+ "\r\n <soap:Header>"
				+ "\r\n <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"
					+ "\r\n <wsse:UsernameToken wsu:Id=\"UsernameToken-3\" xmlns:wsu=\"http://docs.oasisopen.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">"
					+ "\r\n <wsse:Username>"+USER_HEADER+"</wsse:Username>"
					+ "\r\n <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"+PWD_HEADER+"</wsse:Password>"
					+ "\r\n </wsse:UsernameToken>"
				+ "\r\n </wsse:Security>"
				+ "\r\n </soap:Header>");
		
		return header;
	}
	
	private StringBuilder getHeaderEnv() {
		StringBuilder headerEnv = new StringBuilder(""
				+ "\r\n <soapenv:Header>"
				+ "\r\n <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"
					+ "\r\n <wsse:UsernameToken wsu:Id=\"UsernameToken-3\" xmlns:wsu=\"http://docs.oasisopen.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">"
					+ "\r\n <wsse:Username>"+USER_HEADER+"</wsse:Username>"
					+ "\r\n <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"+PWD_HEADER+"</wsse:Password>"
					+ "\r\n </wsse:UsernameToken>"
				+ "\r\n </wsse:Security>"
				+ "\r\n </soapenv:Header>");
		
		return headerEnv;
			
		
	}

	@SuppressWarnings("unused")
	private String constructRequestTest() {
		String reqXML = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "\r\n <soap:Header>"
						+ "\r\n <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"
							+ "\r\n <wsse:UsernameToken wsu:Id=\"UsernameToken-3\" xmlns:wsu=\"http://docs.oasisopen.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">"
							+ "\r\n <wsse:Username>testwservice</wsse:Username>"
							+ "\r\n <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">testwservicepsw</wsse:Password>"
							+ "\r\n </wsse:UsernameToken>"
						+ "\r\n </wsse:Security>"
					+ "\r\n </soap:Header>"
					+ "\r\n <soap:Body>"
						+ "\r\n <ns1:getMedico xmlns:ns1=\"http://business.WebServiceSenasa.inssjp.com/\">"
							+ "\r\n <usuario>7798232400004</usuario>"
							+ "\r\n <password>Trazaisko1</password>"
							+ "\r\n <cuit>20134739445</cuit>"
						+ "\r\n </ns1:getMedico>"
					+ "\r\n </soap:Body>"
				+ "\r\n</soap:Envelope>";
		return reqXML;
	}
}
