package Proyecto5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Funcionalidades implements ActionListener{
	private int posicion = 1;
	
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
			case "AceptarJugador1":
				if(Interfaz.noVacio()) {
					Interfaz.setJuego();
					Interfaz.enablePlayer2();
					Interfaz.disablePlayer1();
				}
				break;
				
			case "AceptarJugador2":
				if(Interfaz.checkLetra()) {
					String letra = Interfaz.getLetra();
					
					if(Interfaz.estaPresente(letra)) {
						Interfaz.buscarLetra(letra);
						Interfaz.vaciarLetra();
					} else {
						if(posicion == 5) {
							JOptionPane.showMessageDialog(null, "Has perdido. :(", "Sad times", JOptionPane.PLAIN_MESSAGE);
							System.exit(0);
						} else {
							System.out.println("Has fallado!");
							Interfaz.setNewImagen(posicion);
							posicion++;
						}
					}
				}
				
				if(Interfaz.hasGanado()) {
					JOptionPane.showMessageDialog(null, "Has ganado. :)", "Good times", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}
				
				break;
		}
	}
}