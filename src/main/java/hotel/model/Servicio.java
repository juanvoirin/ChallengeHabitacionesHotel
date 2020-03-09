package hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Servicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int idServicio;
	@Column(name="servicio")
	private String nombre;

	
	public Servicio(int idServicio, String nombre) {
		this.idServicio = idServicio;
		this.nombre = nombre;
	}

	public Servicio() {
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Servicio: Nombre=" + nombre + ".";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idServicio;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Servicio) {
			
			Servicio other = (Servicio) obj;
			
			if (idServicio != other.idServicio)
				return false;
			
			if (!nombre.equalsIgnoreCase(other.getNombre()))
				return false;
				
			return true;
			} else
			return false;
	}
	
	
}
