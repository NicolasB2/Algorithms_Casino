package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import mundo.Mesa;

public class PanelOpciones extends JPanel implements ActionListener {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	public final static String REPARTIR = "REPARTIR";
	public final static String GANADOR = "GANADOR";
	public final static String NUEVO_PARTIDA = "NUEVA PARTIDA";
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia con la clase principal de la interfaz 
     */
	private InterfazCasino principal;
    /**
     * Es el panel que contiene la informacion de la mesa 
     */
	private PanelInformacion PnInformacion;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------
	
	/**
     * Es el botón que se usa para repartir una carta a cada jugador 
     */
	private JButton butRepartir;
	/**
     * Es el botón que se usa para decidir el ganador 
     */
	private JButton butGanador;
	/**
     * Es el botón que se usa para iniciar un nuevo juego 
     */
	private JButton butNuevoJuago;
	
	// -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param principal Es una referencia a la  clase principal de la interfaz - prinpal!=null
     * @param PnInformacion Es el panel con la informacion de la mesa -  PnInformacion != null 
     */
	public PanelOpciones(InterfazCasino principal, PanelInformacion PnInformacion) {

		this.principal = principal;
		this.PnInformacion = PnInformacion;

		setLayout(new GridLayout(5, 1));
		TitledBorder titulo = new TitledBorder("Opciones");
		titulo.setTitleColor(Color.BLACK);
		setBackground(Color.white);
		titulo.setBorder(new LineBorder(getBackground()));
		setBorder(titulo);

		butRepartir = new JButton("Repartir");
		butGanador = new JButton("Ganador de la Mesa");
		butNuevoJuago = new JButton("Nueva Partida");

		butRepartir.setEnabled(true);
		butGanador.setEnabled(false);
		butNuevoJuago.setEnabled(false);

		if (PnInformacion.darCalleActual() == 5) {
			butRepartir.setEnabled(false);
			butGanador.setEnabled(true);
		}

		if(butRepartir.isEnabled()){
			butRepartir.setBackground(Color.red);
		}else{
			butRepartir.setBackground(Color.gray);
		}
		
		if(butGanador.isEnabled()){
			butGanador.setBackground(Color.red);
		}else{
			butGanador.setBackground(Color.gray);
		}
		
		if(butNuevoJuago.isEnabled()){
			butNuevoJuago.setBackground(Color.red);
		}else{
			butNuevoJuago.setBackground(Color.gray);
		}

		butRepartir.setForeground(Color.white);
		butGanador.setForeground(Color.white);
		butNuevoJuago.setForeground(Color.white);
		
		butRepartir.setActionCommand(REPARTIR);
		butGanador.setActionCommand(GANADOR);
		butNuevoJuago.setActionCommand(NUEVO_PARTIDA);

		butRepartir.addActionListener(this);
		butGanador.addActionListener(this);
		butNuevoJuago.addActionListener(this);

		add(butRepartir);
		add(new JLabel(""));
		add(butGanador);
		add(new JLabel(""));
		add(butNuevoJuago);

	}
	
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

	/**
	 * Metodo que permite cambiar el estado del boton de repartir 
	 * @param nuevoEstadode variable de  tipo boolean que Indica si el boton se quiere activar o desactivar 
	 */
	public void cambiarEstadoBoton1(boolean nuevoEstado){
		butRepartir.setEnabled(nuevoEstado);
		butRepartir.setBackground(Color.gray);
	}
	
	/**
	 * Metodo que permite cambiar el estado del boton que decide el ganador 
	 * @param nuevoEstadode variable de  tipo boolean que Indica si el boton se quiere activar o desactivar 
	 */
	public void cambiarEstadoBoton2(boolean nuevoEstado){
		butGanador.setEnabled(nuevoEstado);
		butGanador.setBackground(Color.red);
	}
	
    /**
     * Este es el método que se ejecuta cuando se hace click sobre un botón
     * @param e Es el evento del click sobre el botón - e!=null
     */
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals(REPARTIR)) {
			
			if(PnInformacion.darCalleActual() < 5){
				principal.actualizarCartas(PnInformacion.darMesaActual() - 1);
			}

			if (PnInformacion.darCalleActual() == 5) {
				butRepartir.setEnabled(false);
				butRepartir.setBackground(Color.gray);
				butGanador.setEnabled(true);
				butGanador.setBackground(Color.red);
			}

		} else if (comando.equals(GANADOR)) {

			butGanador.setEnabled(false);
			butGanador.setBackground(Color.gray);
			butNuevoJuago.setEnabled(true);
			butNuevoJuago.setBackground(Color.red);
			try {
				JOptionPane.showMessageDialog(null,"Ganador: " + principal.seleccionarGanador(PnInformacion.darMesaActual() - 1).darNombre());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}

		} else if (comando.equals(NUEVO_PARTIDA)) {

			
			principal.ActivarRepartirTodas();
			PnInformacion.reiniciarCalle();
			principal.iniciarNuevoJuego(PnInformacion.darMesaActual() - 1);
			butRepartir.setEnabled(true);
			butRepartir.setBackground(Color.red);
			butGanador.setEnabled(false);
			butGanador.setBackground(Color.gray);
			butNuevoJuago.setEnabled(false);
			butNuevoJuago.setBackground(Color.gray);
			
			

		}

	}
	
}

