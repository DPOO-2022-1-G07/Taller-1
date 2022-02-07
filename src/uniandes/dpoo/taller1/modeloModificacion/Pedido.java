package uniandes.dpoo.taller1.modeloModificacion;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Pedido {

	private static int numeroPedidos = 0;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;


	public Pedido(String nombreCliente, String direccionCliente)
	{

		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.itemsPedido = new ArrayList<>();
		Pedido.numeroPedidos = numeroPedidos +1;
		this.idPedido = (numeroPedidos+1);
	}

	public int getNumeroPedidos()
	{
		return numeroPedidos;
	}

	public int getIdPedido()
	{
		return idPedido;
	}

	public String getNombreCliente()
	{
		return nombreCliente;
	}

	public String getDireccionCliente()
	{
		return direccionCliente;
	}



	public void agregarProducto(Producto nuevoItem)
	{
		itemsPedido.add(nuevoItem);

	}

	private int getPrecioNetoPedido()
	{
		int precioNeto=0;
		ArrayList<Producto> items = itemsPedido;
		for (Producto item : items)
		{
			precioNeto += item.getPrecio();
		}		
		return precioNeto;
	}

	private int getPrecioIvaPedido()
	{
		int totalIva=0;
		int totalNeto=getPrecioNetoPedido();
		totalIva= (int) ( totalNeto * 0.19);
		return totalIva;
	}

	private int getPrecioTotalPedido()
	{
		int totalIva = getPrecioIvaPedido();
		int totalNeto = getPrecioNetoPedido();
		int totalPedido = 0;
		totalPedido = totalIva + totalNeto;
		return totalPedido;
	}

	public String generarTextoFactura()
	{
		String contenido = "Factura numero "+ getIdPedido() +"\n Nombre Cliente: "+ getNombreCliente()
		+"\n Direccion: "+ getDireccionCliente();
		ArrayList<Producto> items = itemsPedido;
		for (Producto item : items)
		{
			contenido += "\n"+item.generarTextoFactura();
		}
		contenido = contenido + "\n"+"Precio neto" + ".........." + getPrecioNetoPedido() + "\n";
		contenido = contenido + "Precio IVA" + ".........." + getPrecioIvaPedido() + "\n";
		contenido = contenido + "Precio TOTAL" + ".........." + getPrecioTotalPedido();
		return contenido;
	}

	public void guardarFactura()
	{
		try {
			String ruta = "./docs/Factura pedido"+getIdPedido()+".txt";
			String contenido = generarTextoFactura();
			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contenido);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}






















}
