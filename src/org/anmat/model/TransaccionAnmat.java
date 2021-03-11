package org.anmat.model;

public class TransaccionAnmat {

	private String gLNDestino;
	
	private String gLNOrigen;
	
	private String gTIN;
	
	private int idEvento;
	
	private String vencimiento;

	private String fEvento;
	
	private String hEvento;
	
	private String cuitMedico;
	
	private int idMotivoDevolucion;
		
	private String nroFactura;
	
	private String nroRemito;
	
	private String nroSerial;
	
	private String lote;
	
	private String otroMotivoDevolucion;

	public String getgLNDestino() {
		return validarCadena(gLNDestino);
	}

	public void setgLNDestino(String gLNDestino) {
		this.gLNDestino = gLNDestino;
	}

	public String getgLNOrigen() {
		return validarCadena(gLNOrigen);
	}

	public void setgLNOrigen(String gLNOrigen) {
		this.gLNOrigen = gLNOrigen;
	}

	public String getgTIN() {
		return validarCadena(gTIN);
	}

	public void setgTIN(String gTIN) {
		this.gTIN = gTIN;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getVencimiento() {
		return validarCadena(vencimiento);
	}

	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getfEvento() {
		return validarCadena(fEvento);
	}

	public void setfEvento(String fEvento) {
		this.fEvento = fEvento;
	}

	public String gethEvento() {
		return validarCadena(hEvento);
	}

	public void sethEvento(String hEvento) {
		this.hEvento = hEvento;
	}

	public String getCuitMedico() {
		return validarCadena(cuitMedico);
	}

	public void setCuitMedico(String cuitMedico) {
		this.cuitMedico = cuitMedico;
	}

	public int getIdMotivoDevolucion() {
		return idMotivoDevolucion;
	}

	public void setIdMotivoDevolucion(int idMotivoDevolucion) {
		this.idMotivoDevolucion = idMotivoDevolucion;
	}

	public String getNroFactura() {
		return validarCadena(nroFactura);
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public String getNroRemito() {
		return validarCadena(nroRemito);
	}

	public void setNroRemito(String nroRemito) {
		this.nroRemito = nroRemito;
	}

	public String getNroSerial() {
		return validarCadena(nroSerial);
	}

	public void setNroSerial(String nroSerial) {
		this.nroSerial = nroSerial;
	}

	public String getLote() {
		return validarCadena(lote);
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getOtroMotivoDevolucion() {
		return validarCadena(otroMotivoDevolucion);
	}

	public void setOtroMotivoDevolucion(String otroMotivoDevolucion) {
		this.otroMotivoDevolucion = otroMotivoDevolucion;
	}
	
	private String validarCadena(String cadena) {
		if(cadena == null){
			cadena = "";
		}
		return cadena;
	}
	
	
}
