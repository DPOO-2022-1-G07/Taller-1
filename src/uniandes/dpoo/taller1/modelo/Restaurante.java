package uniandes.dpoo.taller1.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Restaurante 
{


	private ArrayList<Ingrediente> ingredientes;

	private ArrayList<Combo> combos;

	private Map<String,Producto> menuBase;

	private Map<Integer , Pedido> pedidos;

	private Pedido pedidoEnCurso;

	public Restaurante()
	{
		this.ingredientes = new ArrayList<>();
		this.combos = new ArrayList<>();
		this.menuBase = new HashMap<>();
		this.pedidos = new HashMap<>();

	}
	
	// Operadores pedidos

	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		this.pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);

	}

	public Pedido getPedidoEnCurso() 
	{
		return pedidoEnCurso;
	}
	
	public Map<Integer, Pedido> getPedidos()
	{
		
		return pedidos;
	}
	
	public void cerrarYGuardarPedido()
	{
		pedidos.put(null, pedidoEnCurso);
	}

	
	//Obtener menu e Ingredientes
	
	public ArrayList<Producto> getMenuBase()
	{
		ArrayList<Producto> listaMenu = new ArrayList<Producto>(menuBase.values());
		
		return listaMenu;
	}
	
	public ArrayList<Ingrediente> getIngredientes()
	{
		return ingredientes;
	}
	

	//Carga de archivos 

	File archivoIngredientes = new File("/data/ingredientes");
	File archivoMenu = new File("/data/menu");
	File archivoCombos = new File("/data/combos");


	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) throws FileNotFoundException, IOException
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombo(archivoCombos);

	}


	private void cargarIngredientes(File archivoIngredientes) throws FileNotFoundException, IOException
	{

		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		String linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(",");
			String nombreIngrediente = partes[0];
			int precioIngrediente = Integer.parseInt(partes[1]);

			Ingrediente elIngrediente = new Ingrediente(nombreIngrediente, precioIngrediente);

			//Agregar Ingrediente a la Lista de Ingredientes

			ingredientes.add(elIngrediente);

			linea = br.readLine(); // Leer la siguiente línea
		}

		br.close();
	}

	private void cargarMenu(File archivoMenu) throws FileNotFoundException, IOException
	{

		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
		String linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(",");
			String nombreProducto = partes[0];
			int precioProducto = Integer.parseInt(partes[1]);

			ProductoMenu elProducto = new ProductoMenu(nombreProducto, precioProducto);

			//Agregar Producto al Mapa de Productos

			menuBase.put(nombreProducto,elProducto);

			linea = br.readLine(); // Leer la siguiente línea
		}

		br.close();
	}

	private void cargarCombo(File archivoCombos) throws FileNotFoundException, IOException
	{

		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
		String linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea, y agregar nombre y descuento
			String[] partes = linea.split(",");
			String nombreCombo = partes[0];
			String descuentoStr = partes[1];
			String descuentoStr2= descuentoStr.substring(0, descuentoStr.length() - 1);  
			double descuentoCombo = (Integer.parseInt(descuentoStr2)/100);

			Combo elCombo = new Combo(descuentoCombo, nombreCombo);


			//Agregar el Combo a la Lista de Combos

			combos.add(elCombo);

			//Agregar productos a la lista de productos del combo

			for (int i=2; i<= partes.length; i+=1)
			{
				String producto = partes[i];
				Producto elProducto = menuBase.get(producto);
				elCombo.agregarItemACombo(elProducto);

			}

			linea = br.readLine(); // Leer la siguiente línea
		}

		br.close();
	}




}
