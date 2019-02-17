package mundo;

import java.io.*;
import java.util.*;

/**
 * Entidad que modela un casino
 */
public class Casino {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * ArrayList de mesas que pertenecen al casino
	 */
	private ArrayList<Mesa> mesas;

	/**
	 * Método constructor del casino
	 */
	public Casino() {
	}

	/**
	 * Metodo inicializa el mundo a partir del archivo properties
	 * 
	 * @param archivo
	 *            es un archivo de tipo file
	 */
	public void cargarArchivo(File archivo) throws Exception {
		Properties datos = cargarInfoMesas(archivo);
		inicializarMesas(datos);
	}

	/**
	 * Metodo que retorna un archivo de tipo properties
	 * 
	 * @param arch
	 *            un archivo de tipo file
	 * @return properties con los datos
	 */
	public Properties cargarInfoMesas(File arch) throws Exception {

		Properties datos = new Properties();
		FileInputStream in = new FileInputStream(arch);

		try {
			datos.load(in);
			in.close();

		} catch (Exception e) {
			throw new Exception("formato invalido");
		}
		return datos;
	}

	/**
	 * Método para inicializar las mesas
	 * 
	 * @param datos
	 *            es un archivo de tipo properties
	 */
	public void inicializarMesas(Properties datos) {
		int numeroCalle = 0;
		int apuestaMesa = 0;
		int cantidadJugadores = 0;

		String nombreJugador = "";
		int partidasGanadas = 0;

		String carta = "";
		String numeroCarta = "";
		String pintaCarta = "";

		int maxMesas = Integer.parseInt(datos.getProperty("mesas"));
		mesas = new ArrayList<Mesa>();

		for (int i = 0; i < maxMesas; i++) {

			numeroCalle = Integer.parseInt(datos.getProperty("mesa" + i + ".calle"));
			apuestaMesa = Integer.parseInt(datos.getProperty("mesa" + i + ".apuesta"));
			cantidadJugadores = Integer.parseInt(datos.getProperty("mesa" + i + ".cantidad"));

			Jugador[] jugadoresMesa = new Jugador[cantidadJugadores];

			for (int j = 0; j < cantidadJugadores; j++) {

				nombreJugador = datos.getProperty("mesa" + i + ".jugador" + j + ".nombre");
				partidasGanadas = Integer.parseInt(datos.getProperty("mesa" + i + ".jugador" + j + ".ganadas"));

				ArrayList<Carta> cartasJugador = new ArrayList<Carta>();

				for (int k = 0; k < numeroCalle; k++) {
					carta = datos.getProperty("mesa" + i + ".jugador" + j + ".carta" + k);

					if (carta.length() <= 2) {
						numeroCarta = carta.charAt(0) + "";
						pintaCarta = carta.charAt(1) + "";
					} else if (carta.length() >= 3) {
						numeroCarta = carta.charAt(0) + "" + carta.charAt(1);
						pintaCarta = carta.charAt(2) + "";
					}
					Carta miCarta = new Carta(numeroCarta, pintaCarta);

					cartasJugador.add(miCarta);
				}
				Jugador miJugador = new Jugador(nombreJugador, partidasGanadas, cartasJugador);
				jugadoresMesa[j] = miJugador;

			}
			Mesa miMesa = new Mesa(i, numeroCalle, apuestaMesa, jugadoresMesa);
			mesas.add(miMesa);
		}

	}

	/**
	 * Método para dar el ArrayList de mesas
	 * 
	 * @return el ArrayList de mesas
	 */
	public ArrayList<Mesa> darMesas() {
		return mesas;
	}

	/**
	 * Método que se encarga de iniciar un nuevo juego en una mesa.
	 * 
	 * @param numMesa
	 *            Un entero que representa el número de la mesa.<br>
	 */
	public void iniciarNuevoJuego(int numMesa) {
		mesas.get(numMesa).reiniciarJuego();
	}

}
