package org.anmat.conection;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import org.anmat.model.ResponseAnmat;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ResponseHandler {
	
	private List<String> keysSoapXML = new LinkedList<String>();

	public ResponseAnmat procesarResponse(String envelopeSoap){
		InputStream is = new ByteArrayInputStream(envelopeSoap.toString().getBytes());
		SOAPMessage responseSoap = null;		
		ResponseAnmat resp = null;
		try {
			responseSoap = MessageFactory.newInstance().createMessage(null, is);
			resp = this.convertirObject(responseSoap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
	}
	
	private ResponseAnmat convertirObject(SOAPMessage responseSoap) throws Exception {
		
		NodeList nodos = responseSoap.getSOAPBody().getChildNodes();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		responseSoap.writeTo(out);
		String respFull = new String(out.toByteArray());
		ResponseAnmat resp = new ResponseAnmat();
		List<String> errors = new LinkedList<String>();
		errors.addAll(searchListFromNodosSoap(nodos, "d_error"));
		resp.setDescError(errors);
		resp.setCodFalloXML(searchIteratorNodosSoap(nodos, "faultcode"));
		resp.setDescFalloXML(searchIteratorNodosSoap(nodos, "faultstring"));
		resp.setCodigoTransaccion(searchIteratorNodosSoap(nodos, "codigoTransaccion"));
		resp.setHayErrores((!(resp.getDescError() == null) || !(resp.getCodFalloXML() == null)));
		resp.setResponseFull(respFull);
		
		return resp;
	}

	private String searchIteratorNodosSoap(NodeList nodos,String clave) throws Exception {
		String value = null;
		for(int i = 0; i < nodos.getLength() ;i++){
			if(nodos.item(i) != null){
				Node nodo = nodos.item(i);
				if(clave.equals(nodo.getLocalName())){
					if(nodo.getChildNodes().getLength() >= 1){
						value = nodo.getFirstChild().getNodeValue();
						if(value == null){
							throw new Exception("El nodo no tiene valores, solo otros nodos");
						}
						break;
					}
				}else if(nodo.getChildNodes().getLength() > 0){
					value = searchIteratorNodosSoap(nodo.getChildNodes(),clave); 
					if(value != null){
						break;
					}
				}
					
			}
		}
		return value;
	}

	private List<String> searchListFromNodosSoap(NodeList nodos,String clave) throws Exception {
		String value = null;
		List<String> valores = new LinkedList<String>();
		for(int i = 0; i < nodos.getLength() ;i++){
			if(nodos.item(i) != null){
				Node nodo = nodos.item(i);
				if(clave.equals(nodo.getLocalName())){
					if(nodo.getChildNodes().getLength() >= 1){
						value = nodo.getFirstChild().getNodeValue();
						if(value == null){
							throw new Exception("El nodo no tiene valores, solo otros nodos");
						}
						valores.add(value);
					}
				}else if(nodo.getChildNodes().getLength() > 0){
					valores.addAll(searchListFromNodosSoap(nodo.getChildNodes(),clave)); 
				}
					
			}
		}
		return valores;
	}

	public List<String> getKeysSoapXML() {
		return keysSoapXML;
	}

	public void addPropertys(List<String> keysSoapXML) {
		this.keysSoapXML.addAll(keysSoapXML);
	}
	
	public void addProperty(String keysSoapXML) {
		this.keysSoapXML.add(keysSoapXML);
	}
}
