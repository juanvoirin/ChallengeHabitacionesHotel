package hotel.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="Habitacion")
@Entity
public class Habitacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int idHabitacion;
	@Column(name="nombre")	
	private String nombreHabitacion;
	@Column(name="cantCamas")
	private int cantCamas;
	@Column(name="precio")
	private float precio;
	@Column(name="ocupada")
	private boolean ocupada;
	@Column(name="limpieza")
	private boolean limpieza;
	@Column(name="mantenimiento")
	private boolean mantenimiento;
	@Column(name="ocupantes")
	private int ocupantes;
	@Column(name="diasOcupada")
	private int diasOcupada;
	@Column(name="fechaEntrada")
	private LocalDate fechaEntrada;
	@Column(name="cliente")
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idCliente")
	private Cliente responsable;
	@Column(name="servicio")
	@ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "servicio")
	private List<Servicio> servicios = new ArrayList<>();

	public Habitacion() {
	}

	public Habitacion(int idHabitacion, String nombreHabitacion, int cantCamas, float precio, boolean ocupada,
			boolean limpieza, boolean mantenimiento, int ocupantes, int diasOcupada, LocalDate fechaEntrada,
			Cliente responsable, List<Servicio> servicios) {
		this.idHabitacion = idHabitacion;
		this.nombreHabitacion = nombreHabitacion;
		this.cantCamas = cantCamas;
		this.precio = precio;
		this.ocupada = ocupada;
		this.limpieza = limpieza;
		this.mantenimiento = mantenimiento;
		this.ocupantes = ocupantes;
		this.diasOcupada = diasOcupada;
		this.fechaEntrada = fechaEntrada;
		this.responsable = responsable;
		this.servicios = servicios;
	}

	public int getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public String getNombreHabitacion() {
		return nombreHabitacion;
	}

	public void setNombreHabitacion(String nombreHabitacion) {
		this.nombreHabitacion = nombreHabitacion;
	}

	public int getCantCamas() {
		return cantCamas;
	}

	public void setCantCamas(int cantCamas) {
		this.cantCamas = cantCamas;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public boolean getOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public boolean getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(boolean limpieza) {
		this.limpieza = limpieza;
	}

	public boolean getMantenimiento() {
		return mantenimiento;
	}

	public void setMantenimiento(boolean mantenimiento) {
		this.mantenimiento = mantenimiento;
	}

	public int getOcupantes() {
		return ocupantes;
	}

	public void setOcupantes(int ocupantes) {
		this.ocupantes = ocupantes;
	}

	public int getDiasOcupada() {
		return diasOcupada;
	}

	public void setDiasOcupada(int diasOcupada) {
		this.diasOcupada = diasOcupada;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Cliente getResponsable() {
		return responsable;
	}

	public void setResponsable(Cliente responsable) {
		this.responsable = responsable;
	}
	
	private boolean comparaResponsable(Object obj) {
		if (this.getResponsable() == null & (Cliente)obj == null)
			return true;
		else
			return this.getResponsable().equals(obj);
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	public boolean mantenimiento() {
		if(!getOcupada()) {
			setMantenimiento(true);
			setOcupada(true);
			setLimpieza(false);
			return true;
		}else
			return false;
	}
	
	public boolean sacarMantenimiento() {
		if(getMantenimiento()) {
			setMantenimiento(false);
			setOcupada(false);
			return true;
		}else
			return false;
	}
	
	public boolean limpieza() {
		if(!getMantenimiento()) {
			setLimpieza(true);
			return true;
		}else
			return false;
	}
	
	public boolean dejarLimpiar() {
		if(getLimpieza()) {
			setLimpieza(false);
			return true;
		}else
			return false;
	}
	
	public boolean altaHabitacion(int ocupantes, Cliente responsable, int dias) {
		if(!getOcupada()) {
			setOcupada(true);
			setDiasOcupada(dias);
			setOcupantes(ocupantes);
			setResponsable(responsable);
			setFechaEntrada(LocalDate.now());
			return true;
		}else
			return false;
	}
	
	/**
	 * Corrobora que la habitacion se pueda dar de baja
	 * @return true si la baja se realizo correctamente. False si no fue posible.
	 */
	public boolean darDeBaja() {
		
		if(getFechaEntrada()!=null) {
			if(getFechaEntrada().isAfter(getFechaEntrada().plusDays(getDiasOcupada()))) {
				setOcupada(false);
				setDiasOcupada(0);
				setOcupantes(0);
				setResponsable(null);
				setFechaEntrada(null);
				return true;
			}else
				return false;
		}else
			return false;
	}

	@Override
	public String toString() {
		return "Habitacion: Nombre=" + nombreHabitacion + ", Cantidad de camas=" + cantCamas + ", Precio=" + precio +
				", Servicios=" + servicios + ", Ocupada=" + ocupada + ", Limpiando=" + limpieza + ", En Mantenimiento="
				+ mantenimiento + ", Ocupantes=" + ocupantes + ", Responsable=" + responsable + ".";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantCamas;
		result = prime * result + diasOcupada;
		result = prime * result + ((fechaEntrada == null) ? 0 : fechaEntrada.hashCode());
		result = prime * result + idHabitacion;
		result = prime * result + (limpieza ? 1231 : 1237);
		result = prime * result + (mantenimiento ? 1231 : 1237);
		result = prime * result + ((nombreHabitacion == null) ? 0 : nombreHabitacion.hashCode());
		result = prime * result + (ocupada ? 1231 : 1237);
		result = prime * result + ocupantes;
		result = prime * result + Float.floatToIntBits(precio);
		result = prime * result + ((responsable == null) ? 0 : responsable.hashCode());
		result = prime * result + ((servicios == null) ? 0 : servicios.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj != null && obj instanceof Habitacion) {
			Habitacion other = (Habitacion) obj;

			if (!getNombreHabitacion().equalsIgnoreCase(other.getNombreHabitacion()))
				return false;

			if (getCantCamas() != other.getCantCamas())
				return false;

			if (Float.floatToIntBits(getPrecio()) != Float.floatToIntBits(other.getPrecio()))
				return false;

			if (getOcupada() != other.getOcupada())
				return false;

			if (getLimpieza() != other.getLimpieza())
				return false;

			if (getMantenimiento() != other.getMantenimiento())
				return false;

			if (getOcupantes() != other.getOcupantes())
				return false;
			
			if (getDiasOcupada() != other.getDiasOcupada())
				return false;

			if (!comparaResponsable(other.getResponsable()))
				return false;

			if (!getServicios().equals(other.getServicios()))
				return false;

			return true;
		} else
			return false;
	}

}
