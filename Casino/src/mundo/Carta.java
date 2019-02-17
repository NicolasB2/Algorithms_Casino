package mundo;

/**
 * Entidad que las cartas
 */
public class Carta {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante que representa la pinta de corazones
	 */
	public final static String CORAZONES = "C";

	/**
	 * Constante que representa la pinta de diamantes
	 */
	public final static String DIAMANTES = "D";

	/**
	 * Constante que representa la pinta de picas
	 */
	public final static String PICAS = "P";

	/**
	 * Constante que representa la pinta de trebol
	 */
	public final static String TREBOL = "T";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Atributo que representa el número de la carta
	 */
	private int numero;

	/**
	 * Atributo que representa la pinta de la carta
	 */
	private String pinta;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------
	/**
	 * Método constructor de la clase Carta
	 * 
	 * @param numC
	 *            numero de la carta
	 * @param pintaC
	 *            pinta de la carta
	 */
	public Carta(String numC, String pintaC) {

		if (numC.equals("A")) {
			numero = 1;
		} else if (numC.equals("J")) {
			numero = 11;
		} else if (numC.equals("Q")) {
			numero = 12;
		} else if (numC.equals("K")) {
			numero = 13;
		} else {
			numero = Integer.parseInt(numC);
		}
		pinta = pintaC;

	}

	/**
	 * Método que retorna el número de la carta
	 */
	public int darNumero() {
		return numero;
	}

	/**
	 * Método que retorna la pinta de la carta
	 */
	public String darPinta() {
		return pinta;
	}

}
