package org.anmat.conection;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ResponseHandler {
	
	private List<String> keysSoapXML = new LinkedList<String>();
	
 
	public SOAPMessage pruebasXMLParser(String resp) throws IOException, SOAPException{
		//StringBuilder configXML = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		InputStream is = new ByteArrayInputStream(resp.toString().getBytes());
		SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
		return request;
	}

	public List<String> procesarResponse(String envelopeSoap) throws IOException, SOAPException{
		InputStream is = new ByteArrayInputStream(envelopeSoap.toString().getBytes());
		SOAPMessage responseSoap = MessageFactory.newInstance().createMessage(null, is);
		List<String> mensages = new LinkedList<String>();
		
		
		/*
		 * 
		 */
		
		responseSoap.writeTo(System.out);
		System.out.print("\n");
		
		/*
		 * 
		 */
		
		
		try {

			NodeList nodos = responseSoap.getSOAPBody().getChildNodes();
			
			if(this.getKeysSoapXML().size() == 0){
				mensages.add(iterarNodosSoap(nodos,"c_error"));
				mensages.add(iterarNodosSoap(nodos,"d_error"));
			}
			for(String mens: this.getKeysSoapXML()){
				mensages.add(iterarNodosSoap(nodos,mens));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mensages;
	}
	
	private String iterarNodosSoap(NodeList nodos,String clave) throws Exception {
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
					value = iterarNodosSoap(nodo.getChildNodes(),clave); 
					if(value != null){
						break;
					}
				}
					
			}
		}
		return value;
	}

	public List<String> getKeysSoapXML() {
		return keysSoapXML;
	}

	public void setKeysSoapXML(List<String> keysSoapXML) {
		this.keysSoapXML.addAll(keysSoapXML);
	}
	public void setKeysSoapXML(String keysSoapXML) {
		this.keysSoapXML.add(keysSoapXML);
	}
}
