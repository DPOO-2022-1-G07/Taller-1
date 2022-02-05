package uniandes.dpoo.taller1.modelo;


public class ProductoAjustado implements Producto {
	
	private Ingrediente adicion;
	private Ingrediente eliminar;
	private ProductoMenu base;
	
	public ProductoAjustado(ProductoMenu base, Ingrediente adicion, Ingrediente eliminar)
	{
		this.base = base;
		this.adicion = adicion;
		this.eliminar=eliminar;		
	}
	
	public ProductoMenu getBase()
	{
		return base;
	}

	public Ingrediente getAdicion()
	{
		return adicion;
	}
	
	public Ingrediente getEliminar()
	{
		return eliminar;
	}

	@Override
	public int getPrecio() {
		// TODO Auto-generated method stub
		int precio = base.getPrecio();
		precio += adicion.getCostoAdicional();
		
		return precio;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		String nombre = base.getNombre();
		if (adicion.getNombre()!= null)
		{
			nombre = nombre + "con" + adicion.getNombre();
		}
		if (eliminar.getNombre() != null)
		{
			nombre = nombre + "sin" + eliminar.getNombre();
		}
		
		return nombre;
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		String nombre = getNombre();
		int precio = getPrecio();
		String textoFactura = nombre  + ".........."+ precio ;
		return textoFactura;
	}
	
}
