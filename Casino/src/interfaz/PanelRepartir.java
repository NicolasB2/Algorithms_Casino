package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelRepartir extends JPanel implements ActionListener {

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
	private InterfazCasino principal;
	
	// -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

	/**
     * Es el botón que se usa para repartir una carta a los jugadores de todas las mesas 
     */
	private JButton BtRepartir;
	
	 // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param v Es una referencia a la Interfaz Principal - v != null
     */
	public PanelRepartir(InterfazCasino v) {
		
		principal = v;
		BtRepartir = new JButton("Repartir en todas las mesas");
		
		BtRepartir.setForeground(Color.white);	
		BtRepartir.setBackground(Color.red);
		BtRepartir.setActionCommand(PanelOpciones.REPARTIR);
		BtRepartir.addActionListener(this);
		setBackground(Color.white);
		
		add(BtRepartir);
	
	}
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este es el método que se ejecuta cuando se hace click sobre un botón
     * @param e Es el evento del click sobre el botón - e !=null
     */
	
	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();
		
		if(evento.equals(PanelOpciones.REPARTIR)){
			principal.repartirTodasLasMesas();
		}
		
	}
	
	
	/**
     * Metodo que cambia el estado del boton
     * @param a - variable de tipo boolean que Indica si el boton se quiere activar o desactivar - true para activar y false para desactivar  
     */
	public void cambiarEstadoBoton(boolean a){
		BtRepartir.setEnabled(a);
		
		if (a){
			BtRepartir.setBackground(Color.red);
		}else{
			BtRepartir.setBackground(Color.GRAY);
		}

	}
	
	
}
