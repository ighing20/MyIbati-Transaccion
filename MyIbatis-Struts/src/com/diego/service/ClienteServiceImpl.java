package com.diego.service;

import java.util.List;

import com.diego.bean.ClienteBean;
import com.diego.dao.MySqlClienteDAO;

public class ClienteServiceImpl implements ClienteService {

	MySqlClienteDAO dao = new MySqlClienteDAO();
	
	@Override
	public int eliminarCliente(int idCliente) throws Exception {

		return dao.eliminarCliente(idCliente);
	}

	@Override
	public int insertarliente(ClienteBean obj) throws Exception {

		return dao.insertarliente(obj);
	}

	@Override
	public int actualizarCliente(ClienteBean obj) throws Exception {
		return dao.actualizarCliente(obj);
	}

	@Override
	public List<ClienteBean> consultarCliente(String filtro) throws Exception {
		return dao.consultarCliente(filtro);
	}

}
