package fabrica;

import dao.BoletaDao;

public abstract class  Fabrica {

	public static final int TIPO_MYSQL =1;
	public static final int TIPO_SQL_SERVER =2;

	
	public abstract BoletaDao getBoletaDao();

	public static Fabrica getTipo(int tipo){
		switch (tipo) {
		case TIPO_MYSQL:
			return new FabricaMySql();
		case TIPO_SQL_SERVER:
			return null;
		}
		return null;
	}
	
	
}
