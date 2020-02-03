package hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.model.Habitacion;

public interface IHabitacionRepository extends JpaRepository<Habitacion, Integer>{

}
