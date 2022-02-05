package uniandes.dpoo.taller1.modelo;


public class Ingrediente {

	private String nombre;

	private int costoAd;

	public Ingrediente(String nombre, int costoAd)
	{
		this.nombre = nombre;
		this.costoAd = costoAd;
	}

	public String getNombre()
	{
		return nombre;
	}

	public int getCostoAdicional()
	{
		return costoAd;
	}


}
