package org.anmat.model;

import java.util.List;

public class ResponseAnmat {
	
	private List<String> descError;
	
	private String codFalloXML;
	
	private String descFalloXML;
	
	private boolean hayErrores;
	
	private String idTransaccion;
	
	private String responseFull;

	private String codigoTransaccion;
	
	public String getIdTransaccion() {
		return idTransaccion;
	}
	
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public List<String> getDescError() {
		if(this.descError != null && this.descError.isEmpty()){
			return null;
		}else{
			return this.descError;
		}
	}

	public void setDescError(List<String> descError) {
		this.descError = descError;
	}

	public String getCodFalloXML() {
		return this.codFalloXML;
	}

	public void setCodFalloXML(String codFalloXML) {
		this.codFalloXML = codFalloXML;
	}

	public String getDescFalloXML() {
		if(this.descFalloXML != null && this.descFalloXML.isEmpty()){
			return null;
		}else{
			return this.descFalloXML;
		}
	}

	public void setDescFalloXML(String descFalloXML) {
		this.descFalloXML = descFalloXML;
	}

	public boolean hayErrores() {
		return this.hayErrores;
	}

	public void setHayErrores(boolean hayErrores) {
		this.hayErrores= hayErrores;		
	}

	public String getResponseFull() {
		return this.responseFull;
	}

	public void setResponseFull(String responseFull) {
		this.responseFull = responseFull;
	}

	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}

	public String getCodigoTransaccion() {
		return this.codigoTransaccion;
	}

}
