package com.diego.service;

import java.util.List;

import com.diego.bean.ClienteBean;

public interface ClienteService {
	public abstract int eliminarCliente(int idCliente) throws Exception;

	public abstract int insertarliente(ClienteBean obj) throws Exception;

	public abstract int actualizarCliente(ClienteBean obj) throws Exception;

	public abstract List<ClienteBean> consultarCliente(String filtro) throws Exception;

}
