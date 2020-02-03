package hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

}
