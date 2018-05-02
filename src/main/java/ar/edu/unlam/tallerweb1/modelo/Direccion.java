package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Direccion {

	//atributos
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)//autoincremental del id
		private Long id;
		private String calle;
		private String numero;
		
		@ManyToOne (fetch = FetchType.LAZY)// muchas direcciones pueden estar en un barrio
		@JoinColumn(name="idBarrio")//referencia a la pk de barrio
		private Barrio barrio;
		
		//constructores
		public Direccion(){}
		
		public Direccion(String calle, String numero,Barrio barrio){
			this.barrio=barrio;
			this.calle = calle;
			this.numero = numero;
		}
		//getters and setters
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCalle() {
			return calle;
		}
		public void setCalle(String calle) {
			this.calle = calle;
		}
		public String getNumero() {
			return numero;
		}
		public void setNumero(String numero) {
			this.numero = numero;
		}
		public Barrio getBarrio() {
			return barrio;
		}
		public void setBarrio(Barrio barrio) {
			this.barrio = barrio;
		}
}
