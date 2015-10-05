package imposibleGame;

import imposibleGame.img.Img;

import javax.swing.ImageIcon;

import utils.ObjetoJuego;


/**
 * Metodo en el que se crea el cubo, el cual es el personaje que controlas
 * Este saltara encima y solo encima del suelo. Si toca cualquier 
 * @author artop_000
 *
 */
public class Cubo extends ObjetoJuego {
	private static int ANCHO_CUBO = 90;
	private static int ALTO_CUBO = 90;
	private static int SALTO_CUBO = 720;


	/**
	 * Crea el personaje
	 * Al principio iba a ser un cubo normal pero devido al 30 aniversario de Pac-Man he decidido poner a Pac-Man
	 * @param ancho
	 * @param alto
	 */
	public Cubo(int ancho, int alto) {
		super(new JLabelEscalableRotable( new ImageIcon( Img.class.getResource("Retro_Pacman.png") ), ANCHO_CUBO, ALTO_CUBO, 0 ));
	}

	public void limiteDeCaidaLibre(){
		double i=Suelo.getAlturaActual()-90;
		if (y > i){
			y = i - 0.25;
			velY = 0;
		}
	}


	/** 
	 * Intenta un salto del cubo
	 */
	public void intentaSalto() {
		velY = -SALTO_CUBO;	
	}
}

