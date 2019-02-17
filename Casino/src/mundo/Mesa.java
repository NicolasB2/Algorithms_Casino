package mundo;

import java.util.ArrayList;

/**
 * Entidad que modela una mesa en el casino
 */
public class Mesa {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante numérico que representa el puntaje de la escalera real de color
	 */
	public final static int ESCALERA_REAL_COLOR = 10;

	/**
	 * Constante numérico que representa el puntaje de la escalera de color
	 */
	public final static int ESCALERA_COLOR = 9;

	/**
	 * Constante numérico que representa el puntaje del poker
	 */
	public final static int POKER = 8;

	/**
	 * Constante numérico que representa el puntaje del full
	 */
	public final static int FULL = 7;

	/**
	 * Constante numérico que representa el puntaje del color
	 */
	public final static int COLOR = 6;

	/**
	 * Constante numérico que representa el puntaje de la escalera
	 */
	public final static int ESCALERA = 5;

	/**
	 * Constante numérico que representa el puntaje del trio
	 */
	public final static int TRIO = 4;

	/**
	 * Constante numérico que representa el puntaje de la doble pareja
	 */
	public final static int DOBLES_PAREJAS = 3;

	/**
	 * Constante numérico que representa el puntaje de la pareja
	 */
	public final static int PAREJAS = 2;

	/**
	 * Constante numérico que representa el puntaje de la carta alta
	 */
	public final static int CARTA_ALTA = 1;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Caracteristica que representa el número de la mesa
	 */
	private int numero_mesa;

	/**
	 * Caracteristica que representa la calle de la mesa
	 */
	private int numero_calle;

	/**
	 * Caracteristica que representa la apuesta de la mesa
	 */
	private double apuesta;

	/**
	 * Arreglo con los jugadores de la mesa
	 */
	private Jugador[] jugadores;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Método constructor de la clase Mesa
	 * 
	 * @param numM
	 *            número de la mesa
	 * @param numC
	 *            número de calle
	 * @param apuestaM
	 *            apuesta de la mesa
	 * @param jugadoresM
	 *            arreglo de jugadores
	 */
	public Mesa(int numMesa, int numCalle, double apuestaMesa, Jugador[] jugadoresMesa) {
		numero_mesa = numMesa;
		numero_calle = numCalle;
		apuesta = apuestaMesa;
		jugadores = jugadoresMesa;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Método para dar el número de la mesa
	 * 
	 * @return El número de la mesa
	 */
	public int darNumeroMesa() {
		return numero_mesa;
	}

	/**
	 * Método para dar la calle de la mesa
	 * 
	 * @return La mesa de la calle
	 */
	public int darNumeroCalle() {
		return numero_calle;
	}

	/**
	 * Método para dar la apuesta de la mesa
	 * 
	 * @return La apuesta de la mesa
	 */
	public double darApuesta() {
		return apuesta;
	}

	/**
	 * Método para dar el arreglo de jugadores de la mesa
	 * 
	 * @return El arreglo de jugadores
	 */
	public Jugador[] darJugadores() {
		return jugadores;
	}

	/**
	 * Método para iniciar un nuevo juego en la mesa
	 */
	public void reiniciarJuego() {
		for (int i = 0; i < jugadores.length && jugadores != null; i++) {
			jugadores[i].reiniciarMazo();
		}

		numero_calle = 0;
	}

	/**
	 * Método para repartir cartas a los jugadores de la mesa
	 */
	public void repartirCartas() {
		boolean repetir = true;

		for (int i = 0; i < jugadores.length; i++) {
			boolean b = true;
			while (b == true) {
				int numCarta = (int) Math.floor(Math.random() * (14 - 1) + 1);
				int numPinta = (int) Math.floor(Math.random() * (5 - 1) + 1);
				String tipoPinta = "";

				repetir = false;

				switch (numPinta) {

				case 1:
					tipoPinta = Carta.CORAZONES;
					break;
				case 2:
					tipoPinta = Carta.DIAMANTES;
					break;
				case 3:
					tipoPinta = Carta.PICAS;
					break;
				case 4:
					tipoPinta = Carta.TREBOL;
					break;
				}

				for (int j = 0; j < jugadores.length && !repetir && jugadores[j] != null; j++) {

					ArrayList<Carta> misCartas = jugadores[j].darCartas();

					for (int k = 0; k < misCartas.size() && !repetir; k++) {
						if (misCartas.get(k).darNumero() == numCarta && misCartas.get(k).darPinta().equals(tipoPinta)) {
							repetir = true;
						}
					}
				}

				if (repetir) {
					b = true;
				} else {
					jugadores[i].agregarCarta(new Carta(String.valueOf(numCarta), tipoPinta));
					b = false;
				}
			}
		}

		numero_calle++;
	}

	/**
	 * Método para conocer el ganador de la mesa
	 * 
	 * @return El jugador con el mejor mazo
	 */
	public Jugador verificarGanador() {
		Jugador jugadorGanador = null;
		int puntajeGanador = 0;

		ArrayList<Jugador> jugadoresPuntajeIgual = new ArrayList<Jugador>();

		for (int i = 0; i < jugadores.length; i++) {

			if (verificarPuntaje(jugadores[i]) > puntajeGanador) {
				puntajeGanador = verificarPuntaje(jugadores[i]);
				jugadorGanador = jugadores[i];
			} else if (verificarPuntaje(jugadores[i]) == puntajeGanador) {
				jugadoresPuntajeIgual.add(jugadores[i]);
				jugadoresPuntajeIgual.add(jugadorGanador);

				switch (puntajeGanador) {
				case CARTA_ALTA:
					jugadorGanador = verificarDesempateCartaAlta(jugadoresPuntajeIgual);
					break;
				case PAREJAS:
					jugadorGanador = verificarDesempateParejas(jugadoresPuntajeIgual);
					break;
				case DOBLES_PAREJAS:
					jugadorGanador = verificarDesempateDoblesParejas(jugadoresPuntajeIgual);
					break;
				case TRIO:
					jugadorGanador = verificarDesempateTrio(jugadoresPuntajeIgual);
					break;
				case ESCALERA:
					jugadorGanador = verificarDesempateEscalera(jugadoresPuntajeIgual);
					break;
				case COLOR:
					jugadorGanador = verificarDesempateColor(jugadoresPuntajeIgual);
					break;
				case FULL:
					jugadorGanador = verificarDesempateFull(jugadoresPuntajeIgual);
					break;
				case POKER:
					jugadorGanador = verificarDesempatePoker(jugadoresPuntajeIgual);
					break;
				case ESCALERA_COLOR:
					jugadorGanador = verificarDesempateEscaleraColor(jugadoresPuntajeIgual);
					break;
				case ESCALERA_REAL_COLOR:
					jugadorGanador = null;
					break;
				}
			}
			jugadoresPuntajeIgual = new ArrayList<Jugador>();
		}

		if (jugadorGanador != null) {
			agregarVictoria(jugadorGanador.darNombre());
			return jugadorGanador;
		}
		numero_calle = -1;
		return jugadorGanador;

	}

	/**
	 * Método para agregar una victoria al ganador de la mesa
	 * 
	 * @param nombre
	 *            Es el nombre del jugador ganador
	 */

	public void agregarVictoria(String nombre) {
		for (int i = 0; i < jugadores.length && jugadores[i] != null; i++) {
			if (jugadores[i].darNombre().equals(nombre)) {
				jugadores[i].agregarVictoria();
			}
		}
	}

	/**
	 * Método para realizar un desempate entre los jugadores que solo poseen
	 * carta alta
	 * 
	 * @param jugadores
	 *            Son los jugadores que poseen un empate de carta alta
	 * 
	 * @return Es el jugador con la carta más alta
	 */
	public Jugador verificarDesempateCartaAlta(ArrayList<Jugador> jugadores) {
		Jugador ganador = null;

		int[] mazo1 = jugadores.get(0).ordenarCartas();
		int[] mazo2 = jugadores.get(1).ordenarCartas();

		boolean a = false;

		for (int i = 0; i < numero_calle && !a; i++) {

			if (mazo1[mazo1.length - i - 1] > mazo2[mazo2.length - i - 1]) {
				a = true;
				ganador = jugadores.get(0);
			} else if (mazo2[mazo2.length - i - 1] > mazo1[mazo1.length - i - 1]) {
				a = true;
				ganador = jugadores.get(1);
			}
		}

		return ganador;
	}

	/**
	 * Método para realizar un desempate entre los jugadores que poseen una
	 * escalera de color
	 * 
	 * @param jugadores
	 *            Son los jugadores que poseen un empate de escalera de color
	 * 
	 * @return Es el ganador con la escalera mayor
	 */
	public Jugador verificarDesempateEscaleraColor(ArrayList<Jugador> jugadores) {
		Jugador ganador = null;
		int valorCartaMayorJG1 = jugadores.get(0).darValorCartaAlta();
		int valorCartaMayorJG2 = jugadores.get(1).darValorCartaAlta();

		if (valorCartaMayorJG1 > valorCartaMayorJG2) {
			ganador = jugadores.get(0);
		} else {
			ganador = jugadores.get(1);
		}

		return ganador;
	}

	/**
	 * Método para realizar un desempate entre los jugadores que solo poseen
	 * poker
	 * 
	 * @param jugadores
	 *            Son los jugadores que poseen un empate de poker
	 * 
	 * @return Es en jugador con el poker más alto
	 */
	public Jugador verificarDesempatePoker(ArrayList<Jugador> jugadores) {
		Jugador ganador = null;

		int valorPokerJG1 = darValorPoker(jugadores.get(0).darCartas());
		int valorPokerJG2 = darValorPoker(jugadores.get(1).darCartas());

		if (valorPokerJG1 > valorPokerJG2) {
			ganador = jugadores.get(0);
		} else {
			ganador = jugadores.get(1);
		}

		return ganador;
	}

	/**
	 * Método para realizar un desempate entre los jugadores que solo poseen
	 * full
	 * 
	 * @param jugadores
	 *            Son los jugadores que poseen un empate de full
	 * 
	 * @return Es el jugador con el full más alto
	 */
	public Jugador verificarDesempateFull(ArrayList<Jugador> jugadores) {
		Jugador ganador = null;

		int valorTrioJG1 = darValorTrio(jugadores.get(0).darCartas());
		int valorTrioJG2 = darValorTrio(jugadores.get(1).darCartas());
		int valorParejaJG1 = darValorParejaFull(jugadores.get(0).darCartas(), valorTrioJG1);
		int valorParejaJG2 = darValorParejaFull(jugadores.get(1).darCartas(), valorTrioJG2);

		if (valorTrioJG1 > valorTrioJG2) {
			ganador = jugadores.get(0);
		} else if (valorTrioJG2 > valorTrioJG1) {
			ganador = jugadores.get(1);
		} else if (valorParejaJG1 > valorParejaJG2) {
			ganador = jugadores.get(0);
		} else if (valorParejaJG2 > valorParejaJG1) {
			ganador = jugadores.get(1);
		}
		return ganador;
	}

	/**
	 * Método para realizar un desempate entre los jugadores que solo poseen
	 * color
	 * 
	 * @param jugadores
	 *            Son los jugadores que poseen un empate de color
	 * 
	 * @return Es el jugador con la mayor carta en su color
	 */
	public Jugador verificarDesempateColor(ArrayList<Jugador> jugadores) {
		Jugador ganador = null;
		boolean a = false;
		int[] mazo1 = jugadores.get(0).ordenarCartas();
		int[] mazo2 = jugadores.get(1).ordenarCartas();

		for (int i = 0; i < mazo2.length && !a; i++) {
			if (mazo1[mazo1.length - 1 - i] > mazo2[mazo2.length - 1 - i]) {
				ganador = jugadores.get(0);
				a = true;
			} else if (mazo2[mazo2.length - 1 - i] > mazo1[mazo1.length - 1 - i]) {
				ganador = jugadores.get(1);
				a = true;
			}
		}
		return ganador;
	}

	/**
	 * Método para realizar un desempate entre los jugadores que solo poseen
	 * escalera
	 * 
	 * @param jugadores
	 *            Son los jugadores que poseen un empate de escalera
	 * 
	 * @return Es el jugador con la escalera más alta
	 */
	public Jugador verificarDesempateEscalera(ArrayList<Jugador> jugadores) {
		Jugador ganador = null;
		int[] mazo1 = jugadores.get(0).ordenarCartas();
		int[] mazo2 = jugadores.get(1).ordenarCartas();

		if (mazo1[mazo1.length - 1] > mazo2[mazo2.length - 1]) {
			ganador = jugadores.get(1);
		} else if (mazo2[mazo2.length - 1] > mazo1[mazo1.length - 1]) {
			ganador = jugadores.get(0);
		}

		return ganador;
	}

	/**
	 * Método para realizar un desempate entre los jugadores que solo poseen
	 * trio
	 * 
	 * @param jugadores
	 *            Son los jugadores que poseen un empate de trio
	 * 
	 * @return Es el jugador con el trio más alto
	 */
	public Jugador verificarDesempateTrio(ArrayList<Jugador> jugadores) {
		Jugador ganador = null;

		if (darValorTrio(jugadores.get(0).darCartas()) > darValorTrio(jugadores.get(1).darCartas())) {
			ganador = jugadores.get(0);
		} else {
			ganador = jugadores.get(1);
		}

		return ganador;
	}

	/**
	 * Método para realizar un desempate entre los jugadores que solo poseen
	 * doble parejas
	 * 
	 * @param jugadores
	 *            Son los jugadores que poseen un empate de doble pareja
	 * 
	 * @return Es el jugador con las parejas más altas
	 */
	public Jugador verificarDesempateDoblesParejas(ArrayList<Jugador> jugadores) {
		Jugador ganador = null;

		if (darValorDoblePareja(jugadores.get(0).darCartas()) > darValorDoblePareja(jugadores.get(1).darCartas())) {
			ganador = jugadores.get(0);
		} else if (darValorDoblePareja(jugadores.get(0).darCartas()) == darValorDoblePareja(
				jugadores.get(1).darCartas())) {

			if (jugadores.get(0).darValorCartaAlta() > jugadores.get(1).darValorCartaAlta()) {
				ganador = jugadores.get(0);
			} else {
				ganador = jugadores.get(1);
			}
		} else {
			ganador = jugadores.get(1);
		}
		return ganador;
	}

	/**
	 * Método para realizar un desempate entre los jugadores que solo poseen
	 * parejas
	 * 
	 * @param jugadores
	 *            Son los jugadores que poseen un empate de parejas
	 * 
	 * @return Es el jugador con la pareja más alta
	 */
	public Jugador verificarDesempateParejas(ArrayList<Jugador> jugadores) {
		Jugador ganador = null;

		if (darValorPareja(jugadores.get(0).darCartas()) > darValorPareja(jugadores.get(1).darCartas())) {
			ganador = jugadores.get(0);
		} else if (darValorPareja(jugadores.get(0).darCartas()) == darValorPareja(jugadores.get(1).darCartas())) {

			if (jugadores.get(0).darValorCartaAlta() > jugadores.get(1).darValorCartaAlta()) {
				ganador = jugadores.get(0);
			} else {
				ganador = jugadores.get(1);
			}

		} else {
			ganador = jugadores.get(1);
		}
		return ganador;
	}

	/**
	 * Método para calcular el valor del poker de un jugador
	 * 
	 * @param cartas
	 *            Son las cartas del jugador que tiene poker
	 * 
	 * @return El valor del poker del jugador
	 */
	public int darValorPoker(ArrayList<Carta> cartas) {
		int valorPoker = 0;
		boolean encontrada = false;
		for (int i = 0; i < cartas.size() && !encontrada; i++) {
			for (int j = 0; j < cartas.size() && !encontrada; j++) {
				for (int k = 0; k < cartas.size() && !encontrada; k++) {
					for (int l = 0; l < cartas.size() && !encontrada; l++) {

						if (i != j && j != k && k != l && cartas.get(i).darNumero() == cartas.get(j).darNumero()
								&& cartas.get(j).darNumero() == cartas.get(k).darNumero()
								&& cartas.get(k).darNumero() == cartas.get(l).darNumero()) {
							valorPoker = cartas.get(i).darNumero();
							encontrada = true;
						}
					}
				}
			}
		}
		return valorPoker;
	}

	/**
	 * Método para calcular el valor de la pareja dentro de un full
	 * 
	 * @param cartas
	 *            Son las cartas del jugador que tiene full
	 * @param valorTrioFull
	 *            Es el valor del trio dentro del full
	 * @return El valor de la pareja del full
	 */
	public int darValorParejaFull(ArrayList<Carta> cartas, int valorTrioFull) {
		int valorParejaFull = 0;

		for (int i = 0; i < cartas.size(); i++) {
			for (int j = 0; j < cartas.size(); j++) {
				if (i != j && cartas.get(i).darNumero() == cartas.get(j).darNumero()
						&& cartas.get(i).darNumero() != valorTrioFull) {
					valorParejaFull = cartas.get(i).darNumero();
				}
			}
		}
		return valorParejaFull;
	}

	/**
	 * Método para calcular el valor del trio
	 * 
	 * @param cartas
	 *            Son las cartas del jugador que tiene trio
	 * 
	 * @return El valor del trio
	 */
	public int darValorTrio(ArrayList<Carta> cartas) {
		int valorTrio = 0;

		for (int i = 0; i < cartas.size(); i++) {
			for (int j = 0; j < cartas.size(); j++) {
				for (int k = 0; k < cartas.size(); k++) {
					if (i != j && j != k && i != k && cartas.get(i).darNumero() == cartas.get(j).darNumero()
							&& cartas.get(j).darNumero() == cartas.get(k).darNumero()) {
						valorTrio = cartas.get(k).darNumero();
					}
				}
			}
		}
		return valorTrio;
	}

	/**
	 * Método para calcular la pareja más alta en una doble pareja
	 * 
	 * @param cartas
	 *            Es el mazo que cuenta con doble pareja
	 * 
	 * @return Es el valor de la pareja más alta
	 */
	public int darValorDoblePareja(ArrayList<Carta> cartas) {
		int parejaMasAlta = 0;

		for (int i = 0; i < cartas.size(); i++) {
			for (int j = 0; j < cartas.size(); j++) {
				if (i != j && cartas.get(i).darNumero() == cartas.get(j).darNumero()
						&& cartas.get(i).darNumero() > parejaMasAlta) {
					parejaMasAlta = cartas.get(i).darNumero();
				}
			}
		}
		return parejaMasAlta;
	}

	/**
	 * Método para calcular el valor de la pareja
	 * 
	 * @param cartas
	 *            Es el mazo que cuenta con una pareja
	 * @return Es el valor de la pareja del mazo
	 */
	public int darValorPareja(ArrayList<Carta> cartas) {
		boolean a = false;
		int valorPareja = 0;
		for (int i = 0; i < cartas.size() && !a; i++) {
			for (int j = 0; j < cartas.size() && !a; j++) {

				if (i != j && cartas.get(i).darNumero() == cartas.get(j).darNumero()) {
					a = true;
					valorPareja = cartas.get(i).darNumero();
				}

			}
		}
		return valorPareja;
	}

	/**
	 * Método que asigna un puntaje equivalente según el mazo del jugador
	 * 
	 * @param miJugador
	 *            Es el jugador al cual se busca conocer el puntaje de su mazo
	 * @return El puntaje equivalente al juego que posee el jugador
	 */
	public int verificarPuntaje(Jugador miJugador) {
		int puntaje = CARTA_ALTA;
		ArrayList<Carta> mazo = miJugador.darCartas();
		int[] mazoOrdenado = miJugador.ordenarCartas();

		if (verificarEscaleraDeColorReal(miJugador)) {
			puntaje = ESCALERA_REAL_COLOR;
		} else if (verificarEscaleraDeColor(miJugador)) {
			puntaje = ESCALERA_COLOR;
		} else if (verificarPoker(mazo)) {
			puntaje = POKER;
		} else if (verificarFull(mazo)) {
			puntaje = FULL;
		} else if (verificarColor(mazo)) {
			puntaje = COLOR;
		} else if (verificarEscalera(mazoOrdenado)) {
			puntaje = ESCALERA;
		} else if (verificarTrio(mazo)) {
			puntaje = TRIO;
		} else if (verificarDoblePareja(mazo)) {
			puntaje = DOBLES_PAREJAS;
		} else if (verificarPareja(mazo)) {
			puntaje = PAREJAS;
		}
		return puntaje;
	}

	/**
	 * Método para verificar si un jugador tiene una escalera real de color
	 * 
	 * @param miJugador
	 *            Es el jugador al cual se busca conocer si posee escalera real
	 *            de color
	 * @return Un boolean que representa si el jugador posee una escalera real
	 *         de color
	 */
	public boolean verificarEscaleraDeColorReal(Jugador miJugador) {
		boolean hayEscaleraColorReal = true;
		hayEscaleraColorReal = verificarColor(miJugador.darCartas());

		if (hayEscaleraColorReal == true) {
			int[] mazoOrdenado = miJugador.ordenarCartas();

			for (int j = 0; j < mazoOrdenado.length; j++) {
				if (!(mazoOrdenado[j] == 10 + j)) {
					hayEscaleraColorReal = false;
				}
			}

		}

		return hayEscaleraColorReal;
	}

	/**
	 * Método para verificar si un jugador tiene una escalera de color
	 * 
	 * @param miJugador
	 *            Es el jugador al cual se busca conocer si posee escalera de
	 *            color
	 * @return Un boolean que representa si el jugador posee una escalera de
	 *         color
	 */
	public boolean verificarEscaleraDeColor(Jugador miJugador) {
		boolean hayEscaleraColor = true;
		hayEscaleraColor = verificarColor(miJugador.darCartas());

		if (hayEscaleraColor == true) {
			int[] mazoOrdenado = miJugador.ordenarCartas();
			hayEscaleraColor = verificarEscalera(mazoOrdenado);
		}

		return hayEscaleraColor;
	}

	/**
	 * Método para verificar si un jugador tiene un poker
	 * 
	 * @param mazo
	 *            Es el mazo del jugador
	 * @return Un boolean que representa si el jugador posee poker
	 */
	public boolean verificarPoker(ArrayList<Carta> mazo) {

		int contCartasIguales = 0;
		boolean hayPoker = false;

		for (int i = 0; i < mazo.size(); i++) {
			for (int j = 0; j < mazo.size(); j++) {
				if (mazo.get(i).darNumero() == mazo.get(j).darNumero() && i != j) {
					contCartasIguales++;
				}
			}
		}

		if (contCartasIguales == 12) {
			hayPoker = true;
		}
		return hayPoker;
	}

	/**
	 * Método para verificar si un jugador tiene full
	 * 
	 * @param mazo
	 *            Es el mazo del jugador
	 * @return Un boolean que representa si el jugador posee full
	 */
	public boolean verificarFull(ArrayList<Carta> mazo) {
		boolean hayFull = false;
		int contCartasIguales = 0;

		for (int i = 0; i < mazo.size(); i++) {
			for (int j = 0; j < mazo.size(); j++) {
				if (i != j && mazo.get(i).darNumero() == mazo.get(j).darNumero()) {
					contCartasIguales++;
				}
			}
		}
		if (contCartasIguales == 8) {
			hayFull = true;
		}
		return hayFull;
	}

	/**
	 * Método para verificar si un jugador tiene color
	 * 
	 * @param mazo
	 *            Es el mazo del jugador
	 * @return Un boolean que representa si el jugador posee una color
	 */
	public boolean verificarColor(ArrayList<Carta> mazo) {
		boolean pintasIguales = true;

		for (int i = 0; i < mazo.size() && pintasIguales; i++) {
			if (!(mazo.get(0).darPinta().equals(mazo.get(i).darPinta()))) {
				pintasIguales = false;
			}
		}
		return pintasIguales;
	}

	/**
	 * Método que verifica si un jugador tiene escalera
	 * 
	 * @param mazoOrdenado
	 *            Es el mazo del jugador ya ordenado
	 * @return Un boolean que representa si el jugador posee una escalera
	 */
	public boolean verificarEscalera(int[] mazoOrdenado) {
		boolean control = true;
		for (int i = 1; i < mazoOrdenado.length && control; i++) {
			if (!(mazoOrdenado[0] == mazoOrdenado[i] - i)) {
				control = false;
			}
		}
		return control;
	}

	/**
	 * Método que verifica si un jugador tiene trio
	 * 
	 * @param mazo
	 *            Es el mazo del jugador
	 * @return Un boolean que representa si el jugador posee un trio
	 */
	public boolean verificarTrio(ArrayList<Carta> mazo) {
		boolean hayTrio = false;
		int contTrio = 0;

		for (int i = 0; i < mazo.size(); i++) {
			for (int j = 0; j < mazo.size(); j++) {
				if (i != j && mazo.get(i).darNumero() == mazo.get(j).darNumero()) {
					contTrio++;
				}
			}
		}
		if (contTrio == 6) {
			hayTrio = true;
		}
		return hayTrio;
	}

	/**
	 * Método para verificar si el jugador tiene una doble pareja
	 * 
	 * @param mazo
	 *            Es el mazo del jugador
	 * @returnUn boolean que representa si el jugador posee una doble parejaF
	 */
	public boolean verificarDoblePareja(ArrayList<Carta> mazo) {
		boolean hayDP = false;
		int contDP = 0;
		for (int i = 0; i < mazo.size(); i++) {
			for (int j = 0; j < mazo.size(); j++) {
				if (i != j && mazo.get(i).darNumero() == mazo.get(j).darNumero()) {
					contDP++;
				}
			}
		}
		if (contDP == 4) {
			hayDP = true;
		}

		return hayDP;
	}

	/**
	 * Método para verificar si el jugador tiene una pareja
	 * 
	 * @param mazo
	 *            Es el mazo del jugador
	 * @return Un boolean que representa si el jugador posee una pareja
	 */
	public boolean verificarPareja(ArrayList<Carta> mazo) {
		boolean hayPareja = false;
		int contP = 0;
		for (int i = 0; i < mazo.size(); i++) {
			for (int j = 0; j < mazo.size(); j++) {
				if (i != j && mazo.get(i).darNumero() == mazo.get(j).darNumero()) {
					contP++;
				}
			}
		}
		if (contP == 2) {
			hayPareja = true;
		}
		return hayPareja;
	}

}
