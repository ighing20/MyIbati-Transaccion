package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.BoletaDao;
import entidad.BoletaBean;
import entidad.DetalleBoletaBean;
import entidad.ProductoBean;
import fabrica.Fabrica;

@WebServlet("/boleta")
public class ServletBoleta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ServletBoleta.class);
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String metodo = request.getParameter("metodo");
		if(metodo.equals("agregaSeleccion")){
			agregar(request, response);
		}else if(metodo.equals("eliminaSeleccion")){
			eliminar(request, response);
		}else if(metodo.equals("registraBoleta")){
			registra(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	protected void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("En agregar selección");
		
		String idProducto = request.getParameter("idProducto");
		String nombre = request.getParameter("nombreProducto");
		String precio = request.getParameter("precio");
		String cantidad = request.getParameter("cantidad");
		
		
		int idProd = Integer.parseInt(idProducto);
		int cant = Integer.parseInt(cantidad);
		double pre = Double.parseDouble(precio);
				
		ArrayList<ProductoBean> boleta  ;
		
		//Se verifica si existe en sesion
		HttpSession session = request.getSession();
		if(session.getAttribute("dataDeGrilla") == null){
			boleta = new ArrayList<ProductoBean>();
		}else{
			boleta = (ArrayList<ProductoBean>)session.getAttribute("dataDeGrilla");
		}
		
		//Se crear el objeto
		ProductoBean p = new ProductoBean();
		p.setCodigo(idProd);
		p.setNombre(nombre);
		p.setCantidad(cant);
		p.setPrecio(pre);
		
		boolean noExiste = true;
		//se verifica los repetidos
		for (int i = 0; i < boleta.size(); i++) {
			if(boleta.get(i).getCodigo() == idProd){
				boleta.set(i, p);
				noExiste = false;
				break;
			}
		}
		
		//Si no existe se agrega
		if(noExiste){
			boleta.add(p);
		}
		
		//la lista se agrega a sesion
		session.setAttribute("dataDeGrilla", boleta);
		request.getRequestDispatcher("/boleta.jsp").forward(request, response);	
	}
	
	@SuppressWarnings("unchecked")
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("En eliminar selección");
		
		
		String id = request.getParameter("id");
		
		HttpSession session = request.getSession();
		
		ArrayList<ProductoBean> boleta = (ArrayList<ProductoBean>)session.getAttribute("dataDeGrilla");

		//Se elimina
		for (ProductoBean p : boleta) {
			if(p.getCodigo() == Integer.parseInt(id)){
				boleta.remove(p);
				break;
			}
		}
		//la lista se agrega a sesion
		session.setAttribute("dataDeGrilla", boleta);
		request.getRequestDispatcher("/boleta.jsp").forward(request, response);	
	}
	
	@SuppressWarnings("unchecked")
	protected void registra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("En registra boleta");
		
		
		HttpSession session = request.getSession();
		//Boleta que esta en sesion
		ArrayList<ProductoBean> boleta = (ArrayList<ProductoBean>)session.getAttribute("dataDeGrilla");
		
		//Cliente
		String cliente = request.getParameter("idCliente");
		int idCliente = Integer.parseInt(cliente);
		
		//Creamos la Boleta
		BoletaBean b = new BoletaBean();
		b.setIdUsuario(1);
		b.setIdCliente(idCliente);
			
		//Creamos el detalle
		ArrayList<DetalleBoletaBean> detalles = new ArrayList<DetalleBoletaBean>();
		for (ProductoBean x : boleta) {
			DetalleBoletaBean det = new DetalleBoletaBean();
			det.setCantidad(x.getCantidad());
			det.setIdProducto(x.getCodigo());
			det.setPrecio(x.getPrecio());
			detalles.add(det);
		}
		
		//Se inserta la boleta
		Fabrica mysql = Fabrica.getTipo(Fabrica.TIPO_MYSQL);
		BoletaDao dao = mysql.getBoletaDao();
		
		dao.inserta(b, detalles);
		
		//limpiamos la sesion
		session.removeAttribute("dataDeGrilla");
		
		//reenvio
		request.getRequestDispatcher("/boleta.jsp").forward(request, response);	
	}

}
