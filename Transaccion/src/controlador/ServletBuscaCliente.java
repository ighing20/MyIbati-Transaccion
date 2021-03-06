package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.BoletaDao;
import entidad.ClienteBean;
import fabrica.Fabrica;

@WebServlet("/buscaCliente")
public class ServletBuscaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
   private static final Log log = LogFactory.getLog(ServletBuscaCliente.class);
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("En ServletBuscaCliente");
		
		String filtro = request.getParameter("filtro");
		log.info("Filtro -> " + filtro);
		
		
		Fabrica subFrabrica = Fabrica.getTipo(Fabrica.TIPO_MYSQL);
		BoletaDao dao = subFrabrica.getBoletaDao();
		
		if(filtro == null) filtro ="";
		List<ClienteBean> data = dao.consultaCliente(filtro);
		
		JsonArrayBuilder array = Json.createArrayBuilder();
		JsonObject obj = null;
		
		for (ClienteBean x : data) {
			obj = Json.createObjectBuilder().
					add("var_id", x.getIdCliente()).
					add("var_nombre", x.getNombre()).
					add("var_apellido", x.getApellido()).build();
			array.add(obj);
		}
		
		//Se imprime el resultado
		log.info(array.build());
		
		//Notificamos el tipo de archivo
		response.setContentType("application/json;charset=UTF-8");
		
		//Se genera el archivo JSON y se envia
		PrintWriter out =response.getWriter();
		out.println(array.build());
	}

}
