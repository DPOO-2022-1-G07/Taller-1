package uniandes.dpoo.taller1.aplicacionModificada;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uniandes.dpoo.taller1.modeloModificacion.Ingrediente;
import uniandes.dpoo.taller1.modeloModificacion.Producto;
import uniandes.dpoo.taller1.modeloModificacion.ProductoAjustado;
import uniandes.dpoo.taller1.modeloModificacion.Restaurante;



public class Aplicacion {
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}

	// TODO Auto-generated method stub



	File archivoIngredientes = new File("./data/ingredientes.txt");
	File archivoMenu = new File("./data/menuSinBebidas.txt");
	File archivoCombos = new File("./data/combos.txt");
	File archivoBebidas = new File("./data/bebidas.txt");

	private Restaurante restaurante;

	public void setRestaurante(Restaurante restaurante)
	{
		this.restaurante = new Restaurante();
	}

	public void ejecutarAplicacion() throws FileNotFoundException, IOException
	{
		System.out.println("Calculadora men� hamburguesas\n");
		setRestaurante(restaurante);
		ejecutarCargarDatos();
		System.out.println("cargando archivos");
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1 && restaurante != null)
					ejecutarMostrarMenu();
				else if (opcion_seleccionada == 2 && restaurante != null)
					ejecutarIniciarNuevoPedido();
				else if (opcion_seleccionada == 3 && restaurante != null)
					ejecutarAgregarElementoAlPedido();
				else if (opcion_seleccionada == 4 && restaurante != null)
					ejecutarCerrarPedido();
				else if (opcion_seleccionada == 5 && restaurante != null)
					ejecutarConsultarPedido();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicaci�n ...");
					continuar = false;
				}
				else if (restaurante == null)
				{
					System.out.println("ERROR cargando archivos.");
				}
				else
				{
					System.out.println("Por favor seleccione una opci�n v�lida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los n�meros de las opciones.");
			}
		}
	}



	private void ejecutarCargarDatos() throws FileNotFoundException, IOException
	{
		// TODO Auto-generated method stub
		restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos, archivoBebidas);

	}

	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar men�");
		System.out.println("2. Iniciar nuevo pedido");
		System.out.println("3. Agregar elemento al pedido");
		System.out.println("4. Cerrar pedido y emitir factura");
		System.out.println("5. Consultar informaci�n de un pedido");
		System.out.println("6. Salir de aplicacion");
	}


	private void ejecutarConsultarPedido() throws IOException 
	{
		
		int idFactura = Integer.parseInt(input("Ingrese numero de Id de factura"));
		BufferedReader br = new BufferedReader(new FileReader("./docs/Factura pedido"+idFactura+".txt"));
		String linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			System.out.println(linea);
			linea = br.readLine();
		}
		br.close();
	}

	private void ejecutarCerrarPedido() 
	{
		restaurante.getPedidoEnCurso().guardarFactura();
		restaurante.cerrarYGuardarPedido();

	}

	private void ejecutarAgregarElementoAlPedido()
	{
		ArrayList<Producto> listaProductos =restaurante.getMenuBase();
		System.out.println("\n"+"SELECCIONE PRODUCTO A AGREGAR:"+"\n");
		int item = 0;
		for (Producto elProducto: listaProductos)
		{

			String nombreProducto = elProducto.getNombre();
			System.out.println("("+item+") " + nombreProducto);
			item +=1;
		}

		int numeroElegido = Integer.parseInt(input("Ingrese numero de producto: "));
		Producto productoElegido = listaProductos.get(numeroElegido);
		String productoElegidoStr = listaProductos.get(numeroElegido).getNombre();
		boolean esCombo= productoElegidoStr.contains("combo");

		if (esCombo == true)
		{
			restaurante.getPedidoEnCurso().agregarProducto(productoElegido);
		}
		else
		{
			int modificar = Integer.parseInt(input("Desea modificar? (0 = SI) (1 = NO) "));
			if (modificar ==1)
			{
				restaurante.getPedidoEnCurso().agregarProducto(productoElegido);
			}
			else
			{
				ProductoAjustado productoAjustado = new ProductoAjustado(productoElegido);
				boolean ciclo = true;
				while (ciclo)
				{
					ArrayList<Ingrediente> listaIngredientes =restaurante.getIngredientes();
					System.out.println("\n"+"SELECCIONE INGREDIENTE A MODIFICAR:"+"\n");
					int itemIngrediente = 0;
					for (Ingrediente elIngrediente: listaIngredientes)
					{

						String nombreIngrediente = elIngrediente.getNombre();
						System.out.println("("+itemIngrediente+") " + nombreIngrediente);
						itemIngrediente +=1;
					}

					int numeroAgregarOEliminar = Integer.parseInt(input("Desea: (0 = AGREGAR) (1 = ELIMINAR) "));
					int numeroIngredienteElegido = Integer.parseInt(input("Ingrese numero de Ingrediente "));
					Ingrediente ingredienteElegido = listaIngredientes.get(numeroIngredienteElegido);

					

					if (numeroAgregarOEliminar == 0)
					{

						productoAjustado.agregarListaAdicion(ingredienteElegido);

					}
					else
					{
						productoAjustado.agregarListaEliminar(ingredienteElegido);
					}

					int continuar = Integer.parseInt(input("Desea seguir modificando?: (0 = SI) (1 = NO) "));
					if (continuar == 1)
					{
						ciclo = false;
						restaurante.getPedidoEnCurso().agregarProducto(productoAjustado);
					}
				}

			}
		}
	}

	private void ejecutarIniciarNuevoPedido() 
	{

		String nombreCliente = input("Ingrese su nombre: ");
		String direccion = input("Ingrese su direccion: ");
		restaurante.iniciarPedido(nombreCliente, direccion);
		int idPedido = restaurante.getPedidoEnCurso().getIdPedido();
		System.out.println("INICIANDO PEDIDO CON ID:........."+idPedido);

	}

	private void ejecutarMostrarMenu() {
		ArrayList<Producto> listaProductos =restaurante.getMenuBase();
		System.out.println("\n"+"********MENU EL CORRAL********"+"\n");
		for (Producto elProducto: listaProductos)
		{

			String nombreProducto = elProducto.getNombre();
			String precioProducto = String.valueOf(elProducto.getPrecio());
			System.out.println(nombreProducto+"............"+precioProducto);
		}

	}

	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

}



