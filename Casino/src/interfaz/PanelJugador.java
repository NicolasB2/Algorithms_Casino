package interfaz;

import java.awt.*;

import javax.print.MultiDocPrintJob;
import javax.swing.*;
import javax.swing.border.*;

import java.util.*;
import mundo.*;

public class PanelJugador extends JPanel {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	public final static int MAX_COLUMNAS = 7;
	
	 // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Matriz de etiquetas para las cartas 
     */
	private JLabel[][] labJugadores;
    /**
     * Etiqueta para el nombre de los jugadores 
     */
	private JLabel labNombres;
    /**
     * Etiqueta para el numero de partidas ganadas de los jugadores 
     */
	private JLabel labPartidas;
	

	// -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param mesa Es la Mesa que contiene la informacion que se va a mostrar en el panel 
     */
	public PanelJugador(Mesa mesa) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		refrescarPanelJugador(mesa);
	}

	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Metodo que permite refrescar la informacion de la mesa 
     * @param mesa Es la Mesa que contiene la informacion que se quiere actualizar
     */
	public void refrescarPanelJugador(Mesa mesa) {
		removeAll();
		labNombres = new JLabel("Nombre");
		labPartidas = new JLabel("Victorias");

		labNombres.setHorizontalAlignment(JLabel.CENTER);
		labPartidas.setHorizontalAlignment(JLabel.CENTER);
		labNombres.setBackground(Color.white);
		labPartidas.setBackground(Color.white);
		labNombres.setOpaque(true);
		labPartidas.setOpaque(true);

		// aux - Panel al que se le agregan los datos de la mesa 
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(1, 7));
		
		JLabel lbAux = new JLabel("");
		lbAux.setBackground(Color.white);
		lbAux.setOpaque(true);
		
		JLabel lbAux1 = new JLabel("");
		lbAux1.setBackground(Color.white);
		lbAux1.setOpaque(true);
		
		JLabel lbAux2 = new JLabel("");
		lbAux2.setBackground(Color.white);
		lbAux2.setOpaque(true);
		
		JLabel lbAux3 = new JLabel("");
		lbAux3.setBackground(Color.white);
		lbAux3.setOpaque(true);
		
		JLabel lbAux4 = new JLabel("");
		lbAux4.setBackground(Color.white);
		lbAux4.setOpaque(true);
		
		aux.add(labNombres);
		aux.add(labPartidas);
		aux.add(lbAux);
		aux.add(lbAux1);
		aux.add(lbAux2);
		aux.add(lbAux3);
		aux.add(lbAux4);
		add(aux, BorderLayout.NORTH);

		
		// matriz - Panel al que se agregan las cartas y datos de los jugadores 
		JPanel matriz = new JPanel();
		matriz.setLayout(new GridLayout(mesa.darJugadores().length, 5));
		labJugadores = new JLabel[mesa.darJugadores().length][MAX_COLUMNAS];
		Border borde = new LineBorder(Color.BLACK);

		for (int i = 0; i < mesa.darJugadores().length; i++) {
			for (int j = 0; j < MAX_COLUMNAS; j++) {

				Jugador[] miJugador = mesa.darJugadores();
				String nombre = miJugador[i].darNombre();
				String ganadas = miJugador[i].darPartidasGanadas() + "";

				if (j == 0) {
					labJugadores[i][j] = new JLabel(nombre);
				} else if (j == 1) {
					labJugadores[i][j] = new JLabel(ganadas);
				} else if (j <= miJugador[i].darCartas().size() + 1) {
					String numero = miJugador[i].darCartas().get(j - 2).darNumero() + "";
					String pinta = miJugador[i].darCartas().get(j - 2).darPinta();

					labJugadores[i][j] = new JLabel(new ImageIcon("./data/imagenes/mazo2/" + numero + pinta + ".PNG"));

				} else {
					labJugadores[i][j] = new JLabel("--");
				}
				labJugadores[i][j].setHorizontalAlignment(JLabel.CENTER);
				labJugadores[i][j].setVerticalAlignment(JLabel.CENTER);
				labJugadores[i][j].setBackground(Color.WHITE);
				labJugadores[i][j].setOpaque(true);
				labJugadores[i][j].setBorder(borde);
				labJugadores[i][j].setFont(new java.awt.Font("Bookman Old Style", 15, 15));
				matriz.add(labJugadores[i][j]);
			}
		}
		add(matriz, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

}
