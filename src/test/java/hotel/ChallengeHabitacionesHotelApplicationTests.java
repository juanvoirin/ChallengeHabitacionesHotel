package hotel;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hotel.model.Cliente;
import hotel.model.Habitacion;
import hotel.repository.IHabitacionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeHabitacionesHotelApplicationTests {

	@Autowired
	private IHabitacionRepository repoHabitacion;	
	
	@Test
	public void crearHabitacionConCliente() {
		
		
		Cliente us = new Cliente();
		us.setNombre("Lebron");
		us.setApellido("James");
		us.setCelular("111111");
		us.setCiudad("Cleveland");
		us.setDni("111111");
		
		Habitacion ha = new Habitacion();
		ha.setCantCamas(2);
		ha.setLimpieza(false);
		ha.setMantenimiento(false);
		ha.setOcupada(true);
		ha.setOcupantes(1);
		ha.setPrecio(1500);
		ha.setResponsable(us);
		
		Habitacion retorno = repoHabitacion.save(ha);
		
		assertTrue(retorno.getResponsable().getApellido().equalsIgnoreCase(us.getApellido()));
	}

}
