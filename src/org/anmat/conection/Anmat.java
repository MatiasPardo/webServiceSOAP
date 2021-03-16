package org.anmat.conection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.xml.soap.SOAPException;

import org.anmat.model.ResponseAnmat;
import org.anmat.model.TransaccionAnmat;


public class Anmat {
	
	private final String PATH_ENTRENAMIENTO = "https://servicios.pami.org.ar/trazaenprodmed.WebService";
	
	private final String PATH_PRODUCCION = "https://servicios.pami.org.ar/trazaprodmed.WebService";
	
	private boolean enProduccion = true;
	
	private String user;
	
	private String pwd;
	
	private HttpURLConnection conexion;
	
	private ResponseHandler response = new ResponseHandler();
	
	public ResponseAnmat getMedico(String cuit){
		RequestHandler request = new RequestHandler(this);
		ResponseAnmat response = request.buildMedicalDataRequest(cuit);
		return response;
	}
	
	public ResponseAnmat informarProducto(List<TransaccionAnmat> transacciones){
		TransaccionAnmat transaccion = transacciones.get(0);
		RequestHandler request = new RequestHandler(this);
		ResponseAnmat response = request.buildMedicalProductRequest(transaccion);
		return response;
	}
	
	public ResponseAnmat informarProductos(List<TransaccionAnmat> transacciones){
		RequestHandler request = new RequestHandler(this);
		ResponseAnmat response = request.buildMedicalProductsRequest(transacciones);
		return response;
	}
	
	public ResponseAnmat cancelarTransaccion(String transaccion){
		RequestHandler request = new RequestHandler(this);
		ResponseAnmat response = request.buildCancelTransacc(transaccion);
		return response;
	}
	
	public ResponseAnmat cancelarTransaccionParcial(String transaccion, String gtin, String serie){
		RequestHandler request = new RequestHandler(this);
		ResponseAnmat response = request.buildCancelTransaccParcial(transaccion,gtin, serie);
		return response;
	}
	
	public void crearConexionBase() {
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) new URL(this.getURL()).openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-type", "text/xml; charset=utf-8");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.conexion = con;
		
	}

	private String getURL() {
		if(this.enProduccion){
			return PATH_PRODUCCION;
		}else{
			return PATH_ENTRENAMIENTO;
		}
	}

	public void setEnProduccion(boolean enProduccion){
		this.enProduccion = enProduccion;
	}
	
	public ResponseAnmat enviarRequest(String request) throws IOException, SOAPException {
		
		String reqXML = request;
		OutputStream reqStream = null;
		BufferedReader reader = null;
		try {
			reqStream = conexion.getOutputStream ();
			reqStream.write (reqXML.getBytes());
			InputStream resStream = conexion.getInputStream();
			reader = new BufferedReader(new InputStreamReader(resStream));;
			
		} catch (IOException e1) {
			InputStream resStream = conexion.getErrorStream();
			reader = new BufferedReader(new InputStreamReader(resStream));;
		}
		String auxLine;
		StringBuffer auxResponse = new StringBuffer();
		try {
			while ((auxLine = reader.readLine()) != null) {
				auxResponse.append(auxLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(reader != null){
			reader.close();
		}
		if(reqStream != null){
			reqStream.close();
		}
		
		return response.procesarResponse(auxResponse.toString());
	}
	

	public Anmat(String user, String pwd){
		this.user=user;
		this.pwd=pwd;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public HttpURLConnection getConexion() {
		return conexion;
	}

	public void setConexion(HttpURLConnection conexion) {
		this.conexion = conexion;
	}

	public ResponseHandler getResponse() {
		return response;
	}

	public void setResponse(ResponseHandler response) {
		this.response = response;
	}

}
