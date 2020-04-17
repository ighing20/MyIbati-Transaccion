package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.BoletaBean;
import entidad.ClienteBean;
import entidad.DetalleBoletaBean;
import entidad.ProductoBean;

public interface BoletaDao {
	
	public int inserta(BoletaBean boletaBean, List<DetalleBoletaBean> lstDetalle);
	public ArrayList<ClienteBean> consultaCliente(String filtro);
	public ArrayList<ProductoBean> consultaXNombre(String filtro);
}
