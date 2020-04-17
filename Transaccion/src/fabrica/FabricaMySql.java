package fabrica;

import dao.BoletaDao;
import dao.MySqlBoleta;

public class FabricaMySql extends Fabrica {

	
	
	@Override
	public BoletaDao getBoletaDao() {
		return new MySqlBoleta();
	}

	
	
}
