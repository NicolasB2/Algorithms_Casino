package interfaz;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import mundo.*;

public class PanelInformacion extends JPanel {
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	/**
     * Es la mesa que contiene la informacion del panel 
     */
	private Mesa MesaPrincipal;

	// -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

	/**
     * Es la etiqueta para el numero de calle
     */
	private JLabel labNumeroCalle;
	/**
     * Es la etiqueta para el numero de la mesa 
     */
	private JLabel labNumeroMesa;
	
	/**
     * Es la etiqueta para el valor de la apuesta 
     */
	private JLabel labApuesta;

    /**
     * Es el campo para el numero de la calle 
     */
	private JTextField txtNumeroCalle;
    /**
     * Es el campo para el numero de la mesa
     */
	private JTextField txtNumeroMesa;
    /**
     * Es el campo para el valor de la apuesta 
     */
	private JTextField txtApuesta;

    
	 // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param mesa Es la mesa que contiene la informacion que se va a mostrar en el panel 
     */
	public PanelInformacion(Mesa mesa) {

		MesaPrincipal = mesa;

		setLayout(new GridLayout(3, 2));
		setBackground(Color.white);
		TitledBorder titulo = new TitledBorder("Informacion");
		titulo.setTitleColor(Color.BLACK);
		titulo.setBorder(new LineBorder(getBackground()));
		setBorder(titulo);

		labNumeroMesa = new JLabel(" Número de mesa:");
		labNumeroCalle = new JLabel(" Número de la calle:");
		labApuesta = new JLabel(" Apuesta inicial:");

		labNumeroMesa.setForeground(Color.BLACK);
		labNumeroCalle.setForeground(Color.BLACK);
		labApuesta.setForeground(Color.BLACK);
		
		txtNumeroCalle = new JTextField("");
		txtNumeroMesa = new JTextField("");
		txtApuesta = new JTextField("");

		txtNumeroCalle.setBackground(null);
		txtNumeroMesa.setBackground(null);
		txtApuesta.setBackground(null);
		
		txtNumeroCalle.setForeground(Color.red);
		txtNumeroMesa.setForeground(Color.red);
		txtApuesta.setForeground(Color.red);
		
		txtNumeroCalle.setEditable(false);
		txtNumeroMesa.setEditable(false);
		txtApuesta.setEditable(false);

		txtNumeroCalle.setBorder(null);
		txtNumeroMesa.setBorder(null);
		txtApuesta.setBorder(null);

		actualizarNumeroMesa();
		actualizarCalle();
		actualizarApuestaMesa();

		txtNumeroCalle.setHorizontalAlignment(JLabel.CENTER);
		txtNumeroMesa.setHorizontalAlignment(JLabel.CENTER);
		txtApuesta.setHorizontalAlignment(JLabel.CENTER);

		add(labNumeroMesa);
		add(txtNumeroMesa);
		add(labNumeroCalle);
		add(txtNumeroCalle);
		add(labApuesta);
		add(txtApuesta);

	}
	
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * metodo que retorna el numero de la mesa actual 
     * @return un entero con el numero de la mesa 
     */
	public int darMesaActual() {
		return Integer.parseInt(txtNumeroMesa.getText());
	}
	
    /**
     * metodo que retorna el numero de la calle 
     * @return un entero con el numero de la calle 
     */
	public int darCalleActual(){
		return Integer.parseInt(txtNumeroCalle.getText());
	}
	
    /**
     * Este método permite actualizar el numero de la calle 
     */
	public void actualizarCalle(){
		txtNumeroCalle.setText(""+MesaPrincipal.darNumeroCalle());
	}
	
    /**
     * Este método cambia el numero de la calle a cero 
     */
	public void reiniciarCalle(){
		txtNumeroCalle.setText("0");
	}
	
	/**
     * Este método permite actualizar el numero de la meesa
     */
	public void actualizarNumeroMesa() {
		txtNumeroMesa.setText(MesaPrincipal.darNumeroMesa() + 1 + "");
	}

	/**
     * Este método permite actualizar la apuesta de la mesa 
     */
	public void actualizarApuestaMesa() {
		txtApuesta.setText(MesaPrincipal.darApuesta() + "");
	}

}
