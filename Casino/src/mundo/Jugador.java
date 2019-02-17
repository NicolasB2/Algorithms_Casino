package mundo;

import java.util.ArrayList;
import java.util.Arrays;

public class Jugador {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Atributo que representa el nombre del jugador
	 */
	private String nombre;

	/**
	 * Atributo que representa la cantidad de partidas ganadas
	 */
	private int partidasGanadas;

	/**
	 * ArrayList con las cartas del jugador
	 */
	private ArrayList<Carta> cartas;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Metodo constructor de la clase Mesa
	 * 
	 * @param numJ
	 *            numero de jugador
	 * @param partidasGJ
	 *            numero de partidas ganadas
	 * @param cartasJ
	 *            arreglo de cartas del jugaddor
	 */
	public Jugador(String nomJ, int partidasGJ, ArrayList<Carta> cartasJ) {
		nombre = nomJ;
		partidasGanadas = partidasGJ;
		cartas = cartasJ;
	}

	/**
	 * M�todo para dar el nombre del jugador
	 */
	public String darNombre() {
		return nombre;
	}

	/**
	 * M�todo para dar la cantidad de partidas ganadas del jugador
	 */
	public int darPartidasGanadas() {
		return partidasGanadas;
	}

	/**
	 * M�todo para dar las cartas del jugador
	 */
	public ArrayList<Carta> darCartas() {
		return cartas;
	}

	/**
	 * M�todo para agregar una carta al ArrayList de cartas del jugador
	 */
	public void agregarCarta(Carta c) {
		cartas.add(c);
	}

	/**
	 * M�todo para reiniciar las cartas del jugador
	 */
	public void reiniciarMazo() {
		cartas = new ArrayList<Carta>();
	}

	/**
	 * M�todo para ordenar las cartas del jugador
	 * 
	 * @return Un arreglo de enteros con las cartas ordenadas
	 */
	public int[] ordenarCartas() {

		int[] numCartas = new int[cartas.size()];

		for (int i = 0; i < cartas.size(); i++) {
			numCartas[i] = cartas.get(i).darNumero();
		}
		Arrays.sort(numCartas);

		if (numCartas[0] == 1) {
			numCartas[0] = 14;
			Arrays.sort(numCartas);
		}

		return numCartas;
	}

	/**
	 * M�todo para dar el valor de la carta m�s alta del jugador
	 * 
	 * @return
	 */
	public int darValorCartaAlta() {
		int valorCA = ordenarCartas()[ordenarCartas().length - 1];
		return valorCA;
	}

	/**
	 * M�todo para agregar una victoria a las partidas ganadas del jugador
	 */
	public void agregarVictoria() {
		partidasGanadas++;
	}

}
