package utils;

import imposibleGame.Cubo;
import imposibleGame.JuegoImposibleGame;
import imposibleGame.Suelo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class VentanaJuego extends JFrame{
	private JPanel panelPrin;
	private JLabel lSuelo, lPersonaje, lObstaculo;
	private Cubo cubo;
	private Suelo s;
	private JuegoImposibleGame miJuego;

	public Suelo getSuelo() {
		return s;
	}

	public Cubo getCubo() {
		return cubo;
	}

	public void setCubo(Cubo cubo) {
		this.cubo = cubo;
	}

	public void setSuelo(Suelo s) {
		this.s = s;
	}
	public Suelo suelo;
	public VentanaJuego(JuegoImposibleGame juego, int vENT_ANCHO, int vENT_ALTO){
		this.miJuego=juego;
		setTitle("Impossible Game");
		setSize(vENT_ANCHO, vENT_ALTO);
		panelPrin = new JPanel();
		panelPrin.setLayout(null);
		panelPrin.setBackground( Color.BLACK );
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		final int ALTURA_DE_SUELO = 90;
		final int ANCHURA_DE_SUELO = 90;
		ArrayList<JLabel> suelo = new ArrayList<>();

		int cont=0;
		for(int a=0; a<getWidth()+90; a=a+90){
			lSuelo = new JLabel();
			lSuelo.setBounds(a, 600, 90, 90);

			suelo.add(lSuelo);
			panelPrin.add(lSuelo);
			cont++;
			System.out.println(cont);
		}
		s = new Suelo(suelo,(int)(getHeight()));
		this.suelo=s;
		s.crearSuelo();

		System.out.println("Comprobammos las coordenadas Y del suelo:");
		for (JLabel label:s.getSuelo()){
			System.out.println(label.getBounds().getY());
		}	
		getContentPane().add( panelPrin, BorderLayout.CENTER );
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("KeyPressed..."+miJuego);
				miJuego.activaSalto();
			}
		});
	}

	public JLabel getlPersonaje() {
		return lPersonaje;
	}

	public void setlPersonaje(JLabel lPersonaje) {
		this.lPersonaje = lPersonaje;
		getPanelPrincipal().add(lPersonaje);
	}

	public JPanel getPanelPrincipal(){
		return panelPrin;
	}


	public static void main(String[] args) {
		VentanaJuego ventana1 = new VentanaJuego(null, 1350, 720);
		ventana1.setVisible(true);
	}

	public void addCubo(Cubo cubo) {
		// TODO Auto-generated method stub
		this.cubo=cubo;
		setlPersonaje(cubo.getLabel());
	}

	public boolean colisionaConSuelo() {
		int posYCubo = (int)cubo.getY()+(int)cubo.getAlto();
		for (JLabel ls:s.getSuelo()){
			//			System.out.println("Pos Y del cubo: "+posYCubo);
			//			System.out.println("Pos Y del suelo: "+ls.getY());
			if ((ls.getY()-posYCubo<5))
				return true;
		}
		return false;
	}
	public boolean comienzaJuego() {
		int posYCubo = (int)cubo.getY()+(int)cubo.getAlto();
		for (JLabel ls:s.getSuelo()){
			//			System.out.println("Pos Y del cubo: "+posYCubo);
			//			System.out.println("Pos Y del suelo: "+ls.getY());
			if ((ls.getY()-posYCubo<5))
				return true;
		}
		return false;
	}

	public void actualizaPosCubo(){
		lPersonaje.getBounds().setLocation((int)cubo.getX(), (int)cubo.getY());
		lPersonaje.updateUI();
	}	
}
