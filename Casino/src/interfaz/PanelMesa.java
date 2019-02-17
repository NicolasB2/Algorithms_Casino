package interfaz;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import mundo.Casino;
import mundo.Mesa;

public class PanelMesa extends JPanel {

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la mesa que se muestra en el panel 
     */
	private Mesa MesaPrincipal;
	
	// -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

	/**
     * Es el Panel que contiene a los jugadores con sus respectivas cartas 
     */
	private PanelJugador PnlJugador;
	/**
     * Es el Panel qe contiene las opciones de la mesa 
     */
	private PanelOpciones PnlOpciones;
	/**
     * Es el Panel que contiene la informacion de la mesa  
     */
	private PanelInformacion PnlInformacion;

	

	// -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param principal Es una referencia a la clase principal de la interfaz 
     * @param mesa Es la Mesa que se va mostrar en el panel 
     */
	public PanelMesa(InterfazCasino principal, Mesa mesa) {
		MesaPrincipal = mesa;
		PnlJugador = new PanelJugador(mesa);
		PnlInformacion = new PanelInformacion(mesa);
		PnlOpciones = new PanelOpciones(principal, PnlInformacion);

		setLayout(new BorderLayout());
		setBackground(Color.white);
		TitledBorder titulo = new TitledBorder("Información Mesa");
		titulo.setTitleColor(Color.black);
		titulo.setBorder(new LineBorder(Color.red));
		setBorder(titulo);

		setPreferredSize(new Dimension(0, 350));

		JPanel aux2 = new JPanel();
		aux2.setLayout(new GridLayout(2, 1));
		aux2.add(PnlInformacion);
		aux2.add(PnlOpciones);

		add(PnlJugador, BorderLayout.CENTER);
		add(aux2, BorderLayout.EAST);
	}
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

	/**
     * Permite actualizar los datos y cartas de la mesa 
     */
	public void refrescarPanelMesa() {
		PnlInformacion.actualizarCalle();
		PnlJugador.refrescarPanelJugador(MesaPrincipal);
	}
	

	/**
     * Cambia los estados de los botones 1 y 2
     * @param nuevoEstado1 estado del primer boton 
     * @param nuevoEstado2 estado del segundo boton 
     */
	public void cambiarEstadoBotones1y2(boolean nuevoEstado1, boolean nuevoEstado2){
		
		PnlOpciones.cambiarEstadoBoton1(nuevoEstado1);
		PnlOpciones.cambiarEstadoBoton2(nuevoEstado2);
	}
	

	/**
     * Retorna el numero de la calle actual 
     * @return un entero con el numero de la calle 
     */
	public int darCalleActual(){
		int retorno = PnlInformacion.darCalleActual();
		return retorno;
	}
	

}
