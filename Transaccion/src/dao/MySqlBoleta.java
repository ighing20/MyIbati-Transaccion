package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import entidad.BoletaBean;
import entidad.ClienteBean;
import entidad.DetalleBoletaBean;
import entidad.ProductoBean;
import util.MysqlDBConexion;

public class MySqlBoleta implements BoletaDao{

	private static final Log log = LogFactory.getLog(MySqlBoleta.class);
	
	public int inserta(BoletaBean boletaBean, List<DetalleBoletaBean> lstDetalle){
		log.info("---> En MySqlBoleta-> inserta");
		
		
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm1 = null, pstm2= null,pstm3= null;
		
		try {
			conn = MysqlDBConexion.getConexion();
			//Se anula el auto envío
			conn.setAutoCommit(false);
			
			//se crea el sql de la cabecera
			String sql1 ="insert into boleta values(null,curdate(),?,?)";
			pstm1 = conn.prepareStatement(sql1);
			pstm1.setInt(1, boletaBean.getIdCliente());
			pstm1.setInt(2, boletaBean.getIdUsuario());
			pstm1.executeUpdate();
			
			//se obtiene el idBoleta insertado
			String sql2 ="select max(idBoleta) from boleta";
			pstm2 =  conn.prepareStatement(sql2);
			ResultSet rs = pstm2.executeQuery();
			rs.next();
			int idBoleta = rs.getInt(1);
			
			//se inserta el detalle de boleta
			String sql3 ="insert into producto_has_boleta values(?,?,?,?)";
			pstm3 =  conn.prepareStatement(sql3);
			for (DetalleBoletaBean aux : lstDetalle) {
				pstm3.setInt(1, aux.getIdProducto());
				pstm3.setInt(2, idBoleta);
				pstm3.setDouble(3, aux.getPrecio());
				pstm3.setInt(4, aux.getCantidad());
				pstm3.executeUpdate();
			}
			
			//se ejecuta todos los SQL en la base de datos
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				//se vuelva a un inicio 
				//No permite un SQL por separado
			} catch (SQLException e1) {}
			e.printStackTrace();
		} finally{
			try {
				conn.close();
				pstm1.close();
				pstm2.close();
				pstm3.close();
				
			} catch (SQLException e) {
			}
		}
		return contador;
	}

	@Override
	public ArrayList<ClienteBean> consultaCliente(String filtro) {
		log.info("---> En MySqlBoleta-> consultaCliente ->" + filtro);
		
		ArrayList<ClienteBean> data = new ArrayList<ClienteBean>();
		ClienteBean bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			String sql = null;
			conn = MysqlDBConexion.getConexion();
			 sql ="select * from cliente where  nombre like ? or apellido like ? ";
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, (filtro+"%"));
					pstm.setString(2, (filtro+"%"));
					
			log.info("---> SQL -> " + pstm);
					
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				bean = new ClienteBean();
				bean.setIdCliente(rs.getInt("idcliente"));
				bean.setNombre(rs.getString("nombre"));
				bean.setApellido(rs.getString("apellido"));
				bean.setEdad(rs.getInt("edad"));
				bean.setFecha(rs.getDate("fechaNacimiento"));
				data.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}

	@Override
	public ArrayList<ProductoBean> consultaXNombre(String filtro) {
		log.info("---> En MySqlBoleta-> consultaXNombre ->" + filtro);
		
		ArrayList<ProductoBean> data = new ArrayList<ProductoBean>();
		ProductoBean bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MysqlDBConexion.getConexion();
			String sql ="select * from producto  where nombre like ? ";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, (filtro+"%"));
		
			log.info("---> SQL -> " + pstm);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				bean = new ProductoBean();
				bean.setCodigo(rs.getInt("idproducto"));
				bean.setNombre(rs.getString("nombre"));
				bean.setMarca(rs.getString("marca"));
				bean.setPrecio(rs.getDouble("precio"));
				bean.setStock(rs.getInt("stock"));
				bean.setIdCategoria(rs.getInt("idcategoria"));
				data.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}
}
