package com.diego.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.diego.bean.ClienteBean;
import com.diego.service.ClienteService;
import com.diego.service.ClienteServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ParentPackage("myibatis")
public class ClienteAction extends ActionSupport {

	private static final Log log = LogFactory.getLog(ClienteAction.class);

	private @Getter @Setter List<ClienteBean> clientes = new ArrayList<ClienteBean>();
	private @Getter @Setter String filtro = "";

	private @Getter @Setter ClienteBean cliente;
	private @Getter @Setter String id;

	@Action(value = "/eliminaCliente", results = { @Result(name = "success", location = "/crudCliente.jsp") })
	public String elimina() {
		log.info("Eliminar cliente");

		try {
			ClienteService service = new ClienteServiceImpl();
			service.eliminarCliente(Integer.parseInt(id));
			clientes = service.consultarCliente(filtro);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "/actualizaCliente", results = { @Result(name = "success", location = "/crudCliente.jsp") })
	public String actualiza() {
		log.info("Actualiza Cliente");

		try {
			ClienteService service = new ClienteServiceImpl();
			service.actualizarCliente(cliente);
			clientes = service.consultarCliente(filtro);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "/registraCliente", results = { @Result(name = "success", location = "/crudCliente.jsp") })
	public String registra() {
		log.info("Registrar cliente");

		try {
			ClienteService service = new ClienteServiceImpl();
			service.insertarliente(cliente);
			clientes = service.consultarCliente(filtro);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "/consultaCliente", results = { @Result(name = "success", location = "/crudCliente.jsp") })
	public String listar() {
		log.info("Listar cliente");

		ClienteService service = new ClienteServiceImpl();
		try {
			clientes = service.consultarCliente(filtro);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
