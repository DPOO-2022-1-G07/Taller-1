package uniandes.dpoo.taller1.aplicacion;

public class Aplicacion {
	public static void main(String[] args)
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}
	
	// TODO Auto-generated method stub

	public void ejecutarAplicacion()
	{
		System.out.println("Calculadora menú hamburguesas\n");
		ejecutarCargarDatos();
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opciÃ³n"));
				if (opcion_seleccionada == 1 && calculadora != null)
					ejecutarMostrarMenu();
				else if (opcion_seleccionada == 2 && calculadora != null)
					ejecutarIniciarNuevoPedido();
				else if (opcion_seleccionada == 3 && calculadora != null)
					ejecutarAgregarElementoAlPedido();
				else if (opcion_seleccionada == 4 && calculadora != null)
					ejecutarCerrarPedido();
				else if (opcion_seleccionada == 5 && calculadora != null)
					ejecutarConsultarPedido();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (calculadora == null)
				{
					System.out.println("ERROR cargando archivos.");
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	

	private void ejecutarCargarDatos() {
		// TODO Auto-generated method stub
		
	}

	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicaciÃ³n\n");
		System.out.println("1. Mostrar menú");
		System.out.println("2. Iniciar nuevo pedido");
		System.out.println("3. Agregar elemento al pedido");
		System.out.println("4. Cerrar pedido y emitir factura");
		System.out.println("5. Consultar información de un pedido");
	}
	
	
	private void ejecutarConsultarPedido() {
		// TODO Auto-generated method stub
		
	}

	private void ejecutarCerrarPedido() {
		// TODO Auto-generated method stub
		
	}

	private void ejecutarAgregarElementoAlPedido() {
		// TODO Auto-generated method stub
		
	}

	private void ejecutarIniciarNuevoPedido() {
		// TODO Auto-generated method stub
		
	}

	private void ejecutarMostrarMenu() {
		// TODO Auto-generated method stub
		
	}

}



