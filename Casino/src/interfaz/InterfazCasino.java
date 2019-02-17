package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.tools.DiagnosticCollector;

import mundo.*;


	/**
	 * Es la clase principal de la interfaz
	 */
public class InterfazCasino extends JFrame {

	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
     * Relacion con el mundo
     */
	private Casino mundo;
	
	// -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es un ArrayList de los paneles con las mesas
     */
	private ArrayList<PanelMesa> PnMesa;
	
    /**
     * Es el panel donde se encuentra el boton de repartir cartas en todas las mesas
     */
	private PanelRepartir PnRepartir;

	
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz e inicializa todos sus componentes
     */
	public InterfazCasino() {

		setLayout(new BorderLayout());
		setTitle("Casino");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		setExtendedState(MAXIMIZED_BOTH);

		mundo = new Casino();
		PnRepartir = new PanelRepartir(this);
		cargarArchivo();
		iniciarMesas();

		// PanelMesas - Panel donde se organizan los PanelesMesa
		JPanel PanelTodasLasMesas = new JPanel();
		PanelTodasLasMesas.setLayout(new BoxLayout(PanelTodasLasMesas, BoxLayout.Y_AXIS));
		for (int i = 0; i < mundo.darMesas().size(); i++) {
			PanelTodasLasMesas.add(PnMesa.get(i));
		}

		// aux - Panel donde se carga la imagen del encabezado  
		JPanel aux = new JPanel();
		aux.setLayout(new BorderLayout());
		aux.add(new JLabel(new ImageIcon("./data/imagenes/banner1.png")), BorderLayout.NORTH);
		aux.add(PnRepartir, BorderLayout.CENTER);

		// miScroll - ScrollPane que contiene el PanelMesas 
		JScrollPane miScroll = new JScrollPane(PanelTodasLasMesas);
		miScroll.setBorder(null);

		add(aux, BorderLayout.NORTH);
		add(new JScrollPane(PanelTodasLasMesas), BorderLayout.CENTER);
		pack();

	}

	
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * método que permite cargar el archivo con las propiedades a trabajar
	 */
	public void cargarArchivo() {
		File archivoMesas = null;
		JFileChooser fc = new JFileChooser("./data");
		fc.setDialogTitle("Abrir archivo de mesa");
		int resultado = fc.showOpenDialog(this);

		if (resultado == JFileChooser.APPROVE_OPTION) {
			archivoMesas = fc.getSelectedFile();

			try {

				mundo.cargarArchivo(archivoMesas);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Método que inicializa los distintos PanelesMesa y los agrega al ArrayList PnMesa
	 */
	public void iniciarMesas() {
		PnMesa = new ArrayList<PanelMesa>();
		for (int i = 0; i < mundo.darMesas().size(); i++) {
			PnMesa.add(new PanelMesa(this, mundo.darMesas().get(i)));
		}
	}

	/**
	 * Método que permite iniciar un nuevo juego en una mesa
	 * @param numMesa Es el numero de la masa a la cual se le quiere iniciar un nuevo juego 
	 */
	public void iniciarNuevoJuego(int numMesa) {
		mundo.iniciarNuevoJuego(numMesa);
		PnMesa.get(numMesa).refrescarPanelMesa();
	}

	/**
	 * Método que permite actualizar las cartas de todos los jugadores de una mesa
	 * @param numMesa Es el numero de la mesa en la que se desea repartir las cartas 
	 */
	public void actualizarCartas(int numMesa) {
		mundo.darMesas().get(numMesa).repartirCartas();
		PnMesa.get(numMesa).refrescarPanelMesa();
	}

	/**
	 * Método que verifica al ganador de una mesa
	 * @param numMesa es el numero de la masa a la cual se le va asacar el ganador 
	 * @return El Jugador que gano la partida en la mesa
	 * @throws -En caso de no encontrar el ganador manda una excepcion con el mensaje : Dos o más jugadores han generado un empate
	 */
	public Jugador seleccionarGanador(int numMesa) throws Exception {
		Jugador ganador = mundo.darMesas().get(numMesa).verificarGanador();
		PnMesa.get(numMesa).refrescarPanelMesa();
		if (ganador != null) {
			return ganador;
		} else {
			throw new Exception("Dos o más jugadores han generado un empate");
		}
	}

	/**
	 * Método que activa el boton de repartir en todas las mesas 
	 */
	public void ActivarRepartirTodas() {
		PnRepartir.cambiarEstadoBoton(true);
	}

	/**
	 * Método que permite repartir una carta a los jugadores de todas las mesas 
	 */
	public void repartirTodasLasMesas() {

		int aux = 0;
		for (int i = 0; i < PnMesa.size(); i++) {
			actualizarCartas(i);

			if (PnMesa.get(i).darCalleActual() == 5) {
				PnMesa.get(i).cambiarEstadoBotones1y2(false, true);
				aux++;
			}

			if (aux == PnMesa.size()) {
				PnRepartir.cambiarEstadoBoton(false);
			}

		}
	}

	
	// -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación
     * @param args Son los parámetros de ejecución de la aplicación.
     */
	public static void main(String[] args) {
		InterfazCasino interfaz = new InterfazCasino();
		interfaz.setVisible(true);
	}

}
