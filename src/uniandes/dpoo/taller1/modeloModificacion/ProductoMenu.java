package uniandes.dpoo.taller1.modeloModificacion;

public class ProductoMenu implements Producto{
	
	private String nombre;
	
	private int precioBase;
	
	public ProductoMenu(String nombre, int precioBase)
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	public String getNombre()
	{
		return nombre;
	}

	public int getPrecio()
	{
		return precioBase;
	}


	public String generarTextoFactura() {
	
		String nombre = getNombre();
		int precio = getPrecio();
		String textoFactura = nombre  + ".........."+ precio ;
		return textoFactura;
	}
	

	
	
}
