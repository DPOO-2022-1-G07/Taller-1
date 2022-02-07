package uniandes.dpoo.taller1.modeloModificacion;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {

	private ArrayList<Ingrediente> adicion;
	private ArrayList<Ingrediente> eliminar;
	private Producto base;

	public ProductoAjustado(Producto base)
	{
		this.base = base;
		this.adicion = new ArrayList<>();
		this.eliminar = new ArrayList<>();

	}

	public Producto getBase()
	{
		return base;
	}

	public void agregarListaAdicion(Ingrediente ingrediente)
	{
		adicion.add(ingrediente);
	}

	public void agregarListaEliminar(Ingrediente ingrediente)
	{
		eliminar.add(ingrediente);
	}



	public int getPrecio() {

		int precio = base.getPrecio();
		for(Ingrediente ingrediente: adicion)
		{
			precio += ingrediente.getCostoAdicional();

		}

		return precio;
	}


	public String getNombre() {

		String nombre = base.getNombre();
		for(Ingrediente ingrediente: adicion)
		{
			if (ingrediente.getNombre()!= null)
			{
				nombre = nombre + "\n" + "   con " + ingrediente.getNombre();
			}
		}
		for(Ingrediente ingrediente: eliminar)
		{
			if (ingrediente.getNombre() != null)
			{
				nombre = nombre +"\n"+ "   sin " + ingrediente.getNombre();
			}
		}

		return nombre;
	}


	public String generarTextoFactura() {

		String nombre = getNombre();
		int precio = getPrecio();
		String textoFactura = nombre  + ".........."+ precio ;
		return textoFactura;
	}

}
