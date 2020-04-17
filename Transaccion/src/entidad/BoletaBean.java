package entidad;

import java.sql.Date;

public class BoletaBean {
	private int idBoleta,idUsuario, idCliente;
	private Date fecha;
	
	public int getIdBoleta() {
		return idBoleta;
	}
	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
	
}
