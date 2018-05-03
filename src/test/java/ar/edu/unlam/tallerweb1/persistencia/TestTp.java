package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;


import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;

public class TestTp extends SpringTest{

	@Test
	@Transactional
	@Rollback(true)
public void TraerTodasLasFarmaciasDeTurnoEnLosDiasMartes() {
		
		Farmacia farmacia1 = new Farmacia("Vilela", "Martes");
		Farmacia farmacia2 = new Farmacia("Morales", "Domingo");
		Farmacia farmacia3 = new Farmacia("Inca", "Domingo");
		Farmacia farmacia4 = new Farmacia("Ruiz", "Martes");
		Farmacia farmacia5 = new Farmacia("Moyano", "Martes");
		
		getSession().save(farmacia1);
		getSession().save(farmacia2);
		getSession().save(farmacia3);
		getSession().save(farmacia4);
		getSession().save(farmacia5);
		
		List<Farmacia> farmacias= getSession().createCriteria(Farmacia.class)
				.add(Restrictions.eq("diaDeTurno", "Martes"))
				.list();

		

		for(Farmacia farmacia:farmacias)
		{
			assertThat(farmacia.getDiaDeTurno()).isEqualTo("Martes");
		}	
		

}
	
	@Test
	@Transactional
	@Rollback(true)
	
public void TraerTodasLasFarmaciasDeUnaCalle() {
		
		Barrio barrio1=new Barrio("Flores");
		Barrio barrio2=new Barrio("San Justo");
		
		getSession().save(barrio1);
		getSession().save(barrio2);
		
		
		Direccion direccion1=new Direccion("Int.Russo","5827",barrio1);
		Direccion direccion2=new Direccion("Int.Russo","3050",barrio1);
		Direccion direccion3=new Direccion("Arieta","1000",barrio2);
		
		getSession().save(direccion1);
		getSession().save(direccion2);
		getSession().save(direccion3);
	
		Farmacia farmacia1 = new Farmacia("Vilela", "Martes");
		Farmacia farmacia2 = new Farmacia("Morales", "Domingo");
		Farmacia farmacia3 = new Farmacia("Inca", "Domingo");
		
		
		farmacia1.setDireccion(direccion1);
		farmacia2.setDireccion(direccion2);
		farmacia3.setDireccion(direccion3);
		
		getSession().save(farmacia1);
		getSession().save(farmacia2);
		getSession().save(farmacia3);

		List<Farmacia> farmacias = getSession().createCriteria(Farmacia.class)
				.createAlias("direccion", "dir")
				.add(Restrictions.eq("dir.calle", "Int.Russo"))
				.list();
		
		
		for(Farmacia farmacia:farmacias)
		{
			assertThat(farmacia.getDireccion().getCalle()).isEqualTo("Int.Russo");
		}	


}

}
