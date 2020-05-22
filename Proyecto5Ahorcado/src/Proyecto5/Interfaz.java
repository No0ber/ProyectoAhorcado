package Proyecto5;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private static JPasswordField txtPalabra;
	private static JTextField txtLetra;
	private static JLabel lblPalabraAcertar, lblMoneco;
	private JPanel paneljugador1, paneljugador2;
	private static JButton btnAceptarJg1, btnAceptarJg2;
	private static ArrayList<String> palabraAdivinar, barritasArray;
	private static ArrayList<ImageIcon> imagenes;
	private static File recursos = new File("Resource");
	
	private static String palabraConstruir;
	
	private static Funcionalidades listener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		listener = new Funcionalidades();
		Interfaz frame = new Interfaz(listener);
		
		frame.setVisible(true);

		disablePlayer2();
		setImagenes();
	}
	
	public Interfaz(Funcionalidades listener) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 465);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		//-------------------------------------Imagen-------------------------------------
		JPanel panelahorcado = new JPanel();
		panelahorcado.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ahorcado", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelahorcado);
		panelahorcado.setLayout(null);
		
		lblMoneco = new JLabel("");
		lblMoneco.setBounds(10, 21, 307, 384);
		panelahorcado.add(lblMoneco);
		
		JPanel paneljugadores = new JPanel();
		contentPane.add(paneljugadores);
		GridBagLayout gbl_paneljugadores = new GridBagLayout();
		gbl_paneljugadores.columnWidths = new int[]{0, 0};
		gbl_paneljugadores.rowHeights = new int[]{0, 0, 0};
		gbl_paneljugadores.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_paneljugadores.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		paneljugadores.setLayout(gbl_paneljugadores);
		
		//-------------------------------------Jugador 1-------------------------------------
		paneljugador1 = new JPanel();
		paneljugador1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Jugador 1", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		paneljugador1.setLayout(null);
		GridBagConstraints gbc_paneljugador1 = new GridBagConstraints();
		gbc_paneljugador1.insets = new Insets(0, 0, 5, 0);
		gbc_paneljugador1.fill = GridBagConstraints.BOTH;
		gbc_paneljugador1.gridx = 0;
		gbc_paneljugador1.gridy = 0;
		paneljugadores.add(paneljugador1, gbc_paneljugador1);
		
		JLabel lblNewLabel = new JLabel("Palabra a adivinar");
		lblNewLabel.setBounds(10, 28, 167, 14);
		paneljugador1.add(lblNewLabel);
		
		txtPalabra = new JPasswordField ();
		txtPalabra.setBounds(10, 68, 307, 44);
		paneljugador1.add(txtPalabra);
		txtPalabra.setColumns(10);
		txtPalabra.setActionCommand("AceptarJugador1");
		txtPalabra.addActionListener(listener);
		
		btnAceptarJg1 = new JButton("Aceptar");
		btnAceptarJg1.setBounds(10, 150, 307, 44);
		paneljugador1.add(btnAceptarJg1);
		btnAceptarJg1.setActionCommand("AceptarJugador1");
		btnAceptarJg1.addActionListener(listener);

		//-------------------------------------Jugador 2-------------------------------------
		paneljugador2 = new JPanel();
		paneljugador2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Jugador 2", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		paneljugador2.setLayout(null);
		GridBagConstraints gbc_paneljugador2 = new GridBagConstraints();
		gbc_paneljugador2.fill = GridBagConstraints.BOTH;
		gbc_paneljugador2.gridx = 0;
		gbc_paneljugador2.gridy = 1;
		paneljugadores.add(paneljugador2, gbc_paneljugador2);
		
		JLabel lblLetra = new JLabel("Letra");
		lblLetra.setBounds(10, 28, 141, 14);
		paneljugador2.add(lblLetra);
		
		txtLetra = new JTextField();
		txtLetra.setBounds(10, 68, 307, 44);
		paneljugador2.add(txtLetra);
		txtLetra.setColumns(10);
		txtLetra.setActionCommand("AceptarJugador2");
		txtLetra.addActionListener(listener);
		
		btnAceptarJg2 = new JButton("Aceptar");
		btnAceptarJg2.setBounds(10, 134, 307, 23);
		paneljugador2.add(btnAceptarJg2);
		btnAceptarJg2.setActionCommand("AceptarJugador2");
		btnAceptarJg2.addActionListener(listener);
		
		lblPalabraAcertar = new JLabel("");
		lblPalabraAcertar.setBounds(10, 168, 307, 26);
		paneljugador2.add(lblPalabraAcertar);
	}
	
	//---------------Acciones interfaz---------------
	public static void setImagenes() {
		imagenes = new ArrayList<ImageIcon>();
		
		//Añade las imágenes a un array
		for(File f:recursos.listFiles()) {
			imagenes.add(new ImageIcon(f.getPath()));
		}
		

		lblMoneco.setIcon(imagenes.get(0));
	}
	
	public static void setNewImagen(int posicion) {
		lblMoneco.setIcon(imagenes.get(posicion));
	}
	
	//---------------Acciones jugador 1---------------
	public static boolean noVacio() {
		String palabra = txtPalabra.getText();
		
		if(palabra.length()<1) {
			JOptionPane.showMessageDialog(null, "No has introducido nada, vuelve a intetarlo.", "Wrong", JOptionPane.PLAIN_MESSAGE);
			
		} else {
			return true;
		}
		return false;
	}
	
	public static void setJuego() {
		String palabra = txtPalabra.getText();
		palabraAdivinar = new ArrayList<String>();
		barritasArray = new ArrayList<String>();
		
		for(int i=0; i<palabra.length(); i++) {
			//Añade la palabra letra por letra a un array
			palabraAdivinar.add(String.valueOf(palabra.charAt(i)));
			
			//Array lleno de barritas bajas
			barritasArray.add("_");

			//Pone la label con la cantidad de letras de la palabra
			lblPalabraAcertar.setText(lblPalabraAcertar.getText()+barritasArray.get(i)+" ");
		}
	}
	
	//---------------Acciones jugador 2---------------
	public static void setMoneco(int posicion) {
		lblMoneco.setIcon(imagenes.get(posicion));
	}
	
	public static boolean checkLetra() {
		String letra = txtLetra.getText();
		
		if(letra.length()>1 || letra.length()<1) {
			JOptionPane.showMessageDialog(null, "No has introducido una sola letra.", "Wrong", JOptionPane.PLAIN_MESSAGE);
			return false;
			
		} else {
			return true;
		}
	}
	
	public static String getLetra() {
		return txtLetra.getText();
	}
	
	public static boolean estaPresente(String letra) {
		int contador = 0;
		for(int i=0;i<palabraAdivinar.size();i++) {
			if(palabraAdivinar.get(i).equalsIgnoreCase(letra)) {
				barritasArray.set(i, letra);
				contador++;
			}
		}
		
		if(contador<1) {
			return false;
			
		} else return true;
	}
	
	public static void buscarLetra(String letra) {
		palabraConstruir = "";
		
		for(int i=0;i<palabraAdivinar.size();i++) {
			if(palabraAdivinar.get(i).equalsIgnoreCase(letra)) {
				barritasArray.set(i, letra);
			}
		}
		
		for(int j=0;j<barritasArray.size();j++) {
			palabraConstruir += barritasArray.get(j)+" ";
		}
		
		lblPalabraAcertar.setText(palabraConstruir);
		
	}
	
	public static boolean hasGanado() {
		String palabra = palabraAdivinar.toString();
		String acertacion = barritasArray.toString();
		
		if(palabra.equals(acertacion)) {
			return true;
		} else return false;
	}
	
	public static void vaciarLetra() {
		txtLetra.setText("");
	}
	
	//---------------Métodos activar o desactivar---------------
	public static void disablePlayer1() {
		txtPalabra.setEnabled(false);
		btnAceptarJg1.setEnabled(false);
	}
	
	public static void enablePlayer1() {
		txtPalabra.setEnabled(true);
		btnAceptarJg1.setEnabled(true);
	}
	
	public static void disablePlayer2() {
		txtLetra.setEnabled(false);
		btnAceptarJg2.setEnabled(false);
		lblPalabraAcertar.setEnabled(false);
	}
	
	public static void enablePlayer2() {
		txtLetra.setEnabled(true);
		btnAceptarJg2.setEnabled(true);
		lblPalabraAcertar.setEnabled(true);
	}
}