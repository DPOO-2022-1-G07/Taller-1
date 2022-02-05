package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;

public class Combo implements Producto{
	
	private double descuento;
	
	private String nombreCombo;
	
	private ArrayList<Producto> itemsCombo;

	public Combo(double descuento, String nombreCombo)
	{
		this.nombreCombo = nombreCombo;
		this.descuento = descuento;
		this.itemsCombo = new ArrayList<>();
	}
	
	public String getNombre()
	{
		return nombreCombo;
	}

	public double getDescuento()
	{
		return descuento;
	}
	
	public void agregarItemACombo(Producto itemCombo)
	{
		itemsCombo.add(itemCombo);
	}
	
	public int getPrecio()
	{
		int precioSinDescuento = 0;
		int precioTotal = 0;
		ArrayList<Producto> items = itemsCombo;
		for (Producto item: items)
		{
			precioSinDescuento += item.getPrecio();
			
			
		}
		precioTotal=(int) (precioSinDescuento*(1-descuento));
		return precioTotal;
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return null;
	}
}

















