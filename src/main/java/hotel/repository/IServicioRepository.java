package hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.model.Servicio;

public interface IServicioRepository extends JpaRepository<Servicio, Integer>{

	public boolean existsByNombre(String nombre);
}
