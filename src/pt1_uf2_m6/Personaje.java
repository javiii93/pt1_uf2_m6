package pt1_uf2_m6;

public class Personaje {
	int personaje_id;
	String nombre_personaje;
	float ataque, defensa;
	int faccion_id;

	public Personaje(int personaje_id, String nombre_personaje, float ataque, float defensa, int faccion_id) {
		super();
		this.personaje_id = personaje_id;
		if (nombre_personaje.length() <= 15) {
			this.nombre_personaje = nombre_personaje;
		}
		this.ataque = ataque;
		this.defensa = defensa;
		this.faccion_id = faccion_id;
	}

	public int getPersonaje_id() {
		return personaje_id;
	}

	public void setPersonaje_id(int personaje_id) {
		this.personaje_id = personaje_id;
	}

	public String getNombre_personaje() {
		return nombre_personaje;
	}

	public void setNombre_personaje(String nombre_personaje) {
		this.nombre_personaje = nombre_personaje;
	}

	public float getAtaque() {
		return ataque;
	}

	public void setAtaque(float ataque) {
		this.ataque = ataque;
	}

	public float getDefensa() {
		return defensa;
	}

	public void setDefensa(float defensa) {
		this.defensa = defensa;
	}

	public int getFaccion_id() {
		return faccion_id;
	}

	public void setFaccion_id(int faccion_id) {
		this.faccion_id = faccion_id;
	}

	@Override
	public String toString() {
		return "Personaje [personaje_id=" + personaje_id + ", nombre_personaje=" + nombre_personaje + ", ataque="
				+ ataque + ", defensa=" + defensa + ", faccion_id=" + faccion_id + "]";
	}

}
