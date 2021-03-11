package org.anmat.model;

import java.util.List;

public interface ResponseAnmat {
	
	public List<String> getDescError();

	public void setDescError(List<String> descError);

	public String getCodFalloXML();

	public void setCodFalloXML(String codFalloXML);

	public String getDescFalloXML();

	public void setDescFalloXML(String descFalloXML);
	
	public boolean hayErrores();

	public void setHayErrores(boolean hayErrores);

	public String getResponseFull();

	public void setResponseFull(String responseFull);

	public void setCodigoTransaccion(String searchIteratorNodosSoap);
	
	public String getCodigoTransaccion();

}
