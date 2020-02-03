package hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	private String nombre;
	private String apellido;
	private String dni;
	private String celular;
	private String ciudad;

	
	public Cliente() {
	}

	public Cliente(int idCliente, String nombre, String apellido, String dni, String celular, String ciudadOrigen) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.celular = celular;
		this.ciudad = ciudadOrigen;
	}
	
	public Cliente(String nombre, String apellido, String dni, String celular, String ciudad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.celular = celular;
		this.ciudad = ciudad;
	}


	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Cliente: Nombre=" + nombre + ", Apellido=" + apellido + ", DNI=" + dni
				+ ", Celular=" + celular + ", Ciudad=" + ciudad + ".";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + idCliente;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
				
		if (obj != null && obj instanceof Cliente) {
			Cliente other = (Cliente) obj;

			if (!getNombre().equalsIgnoreCase(other.getNombre()))
				return false;

			if (!getApellido().equalsIgnoreCase(other.getApellido()))
				return false;

			if (!getDni().equalsIgnoreCase(other.getDni()))
				return false;
			
			if (!getCelular().equalsIgnoreCase(other.getCelular()))
				return false;
			
			if(!getCiudad().equalsIgnoreCase(other.getCiudad()))
				return false;

			return true;
		} else
			return false;
	}

	
}
