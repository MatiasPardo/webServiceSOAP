package org.anmat.model;

import java.util.List;

public class InformarProductoResponse implements ResponseAnmat{
	
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

	@Override
	public List<String> getDescError() {
		return this.descError;
	}

	@Override
	public void setDescError(List<String> descError) {
		this.descError = descError;
	}

	@Override
	public String getCodFalloXML() {
		return this.codFalloXML;
	}

	@Override
	public void setCodFalloXML(String codFalloXML) {
		this.codFalloXML = codFalloXML;
	}

	@Override
	public String getDescFalloXML() {
		return this.descFalloXML;
	}

	@Override
	public void setDescFalloXML(String descFalloXML) {
		this.descFalloXML = descFalloXML;
	}

	@Override
	public boolean hayErrores() {
		return this.hayErrores;
	}

	@Override
	public void setHayErrores(boolean hayErrores) {
		this.hayErrores= hayErrores;		
	}

	@Override
	public String getResponseFull() {
		return this.responseFull;
	}

	@Override
	public void setResponseFull(String responseFull) {
		this.responseFull = responseFull;
	}

	@Override
	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}

	@Override
	public String getCodigoTransaccion() {
		return this.codigoTransaccion;
	}

	

}
