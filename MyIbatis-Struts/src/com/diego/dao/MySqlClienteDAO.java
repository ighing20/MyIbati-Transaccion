package com.diego.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.diego.bean.ClienteBean;

public class MySqlClienteDAO implements ClienteDAO {

	SqlSessionFactory sqlMapper = null;
	{

		String archivo = "ConfiguracionIbatis.xml";
		try {

			Reader reader = Resources.getResourceAsReader(archivo);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public int eliminarCliente(int idCliente) throws Exception {
		int salida = -1;
		SqlSession session = null;
		try {
			session = sqlMapper.openSession();
			salida = session.delete("SQL_eliminaCliente", idCliente);
			session.commit();
		} catch (Exception e) {

			e.printStackTrace();
			session.rollback();

		} finally {
			session.close();
		}

		return salida;
	}

	@Override
	public int insertarliente(ClienteBean obj) throws Exception {

		int salida = -1;
		SqlSession session = null;

		try {
			session = sqlMapper.openSession();
			salida = session.insert("SQL_insertaCliente", obj);
			session.commit();
		} catch (Exception e) {

			e.printStackTrace();
			session.rollback();

		} finally {
			session.close();
		}

		return salida;
	}

	@Override
	public int actualizarCliente(ClienteBean obj) throws Exception {
		int salida = -1;
		SqlSession session = null;

		try {
			session = sqlMapper.openSession();
			salida = session.update("SQL_actualizaCliente", obj);
			session.commit();
		} catch (Exception e) {

			e.printStackTrace();
			session.rollback();

		} finally {
			session.close();
		}

		return salida;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClienteBean> consultarCliente(String filtro) throws Exception {

		SqlSession session = null;
		List<ClienteBean> salida = null;
		try {
			session = sqlMapper.openSession();
			salida = session.selectList("SQL_listaCliente", filtro + "%");
			session.commit();
		} catch (Exception e) {

			e.printStackTrace();
			session.rollback();

		} finally {
			session.close();
		}

		return salida;
	}

}
