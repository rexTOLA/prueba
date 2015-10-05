package imposibleGame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import utils.VentanaJuego;

public class JuegoImposibleGame{

	private boolean sigueJuego = true;
	private boolean pulsadoSalto = false;
	private VentanaJuego vJuego;

	public static int LIM_JUEGO_Y = 800;
	public static int INICIO_CUBO_X = 270;
	public static int INICIO_CUBO_Y = 340;
	public static int VENT_ANCHO = 1350;
	public static int VENT_ALTO = 720;

	/**
	 * crear instancia de juego
	 */
	public JuegoImposibleGame(){
	}

	/**
	 * Asociar la ventana al juego
	 */
	public void setVentana(VentanaJuego vJuego){
		this.vJuego = vJuego;
	}
	/**
	 * Salto del cubo
	 */
	public void activaSalto(){
		System.out.println("activaSalto..."+cubo);
		this.pulsadoSalto = true;
		cubo.intentaSalto();
	}

	/**
	 * Provoca que el juego acabe
	 * este metodo esta vacio porque todavia no he pensado como lo voy a hacer, si como en el flappyUD que se acaba al de un tiempo
	 * o si se acaba porqu tu le das a un boton de acabar o volver a intentar
	 */
	public void acabaJuego(){
		this.sigueJuego = false;
	}
	Cubo cubo;
	//ArrayList<Obstaculo> obstaculo;		Como todavia no he hecho nada de los obstaculos lo dejo en comentarios
	ArrayList<Suelo> suelo;
	private void initJuego() {
		sigueJuego = true;
		cubo = new Cubo(180, 180);	
		cubo.setPos(INICIO_CUBO_X, INICIO_CUBO_Y);
		cubo.setVel(0, 0);
		cubo.setRadioColision(45);
		vJuego.addCubo(cubo);
		vJuego.getPanelPrincipal().setBackground( Color.CYAN );
		suelo = new ArrayList<Suelo>();
		//obstaculo = new ArrayList<Obstaculo>();	Como todavia no he hecho nada de los obstaculos lo dejo en comentarios	
	}
	/**
	 * Bucle principal del juego
	 */
	public void bucleDejuego(){
		initJuego();
		int msgEspera = 20;
		long milis = System.currentTimeMillis();
		long tiempoDeJuego = 0;
		long tiempoDeSuelo = 0;
		//long tiempoDeObstaculo = 0;	Como todavia no he hecho nada de los obstaculos lo dejo en comentarios
		System.out.println("Pos inicial del cubo"+vJuego.getCubo().getY());
		do{
			long difTiempo = (System.currentTimeMillis()-milis);
			//long difTiempo = (System.currentTimeMillis()-milis);
			//tiempoDeObstaculo += difTiempo;	Como todavia no he hecho nada de los obstaculos lo dejo en comentarios
			milis = System.currentTimeMillis();
			//System.out.println("Dentro del while");
			vJuego.getCubo().actualizaFisica(difTiempo / 1000.0);
			vJuego.actualizaPosCubo();
			vJuego.repaint();
		}while(!vJuego.comienzaJuego());
		while (sigueJuego) {
			try {
				Thread.sleep(msgEspera);
			} catch (InterruptedException e) {

			}
			long difTiempo = (System.currentTimeMillis()-milis);
			tiempoDeJuego += difTiempo;
			tiempoDeSuelo += difTiempo;
			milis = System.currentTimeMillis();
			vJuego.suelo.moverSuelo();

			//


			//Actualizar cubo
			//boolean a = false;
			//do{

			vJuego.getCubo().limiteDeCaidaLibre();

			if (pulsadoSalto) { 
				cubo.intentaSalto();
				pulsadoSalto = false; 
			}
			cubo.actualizaGrafico();
			//}while(a);
			vJuego.getCubo().actualizaFisica(difTiempo / 1000.0);
			vJuego.actualizaPosCubo();
			vJuego.repaint();
		}

	}
	public static void main(String[] args) {
		JuegoImposibleGame juego = new JuegoImposibleGame();
		VentanaJuego vJuego = new VentanaJuego(juego, VENT_ANCHO, VENT_ALTO);
		juego.setVentana(vJuego);
		vJuego.setVisible(true);
		juego.bucleDejuego();
	}
}
