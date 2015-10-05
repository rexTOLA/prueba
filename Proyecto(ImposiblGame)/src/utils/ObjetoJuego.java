package utils;

import javax.swing.JLabel;

import imposibleGame.JLabelEscalableRotable;
import imposibleGame.Suelo;

public class ObjetoJuego {
	protected double x,y,velX,velY;
	protected int ancho, alto;
	protected JLabelEscalableRotable grafico;
	protected static final double G = 980.0;  // px/sg2
	protected int radioColision = 45;  // Tamaño radio de colisión (si es 0 es que no colisiona)

	/** Constructor de objeto de juego
	 * @param grafico	Label gráfico asociado al objeto
	 */
	public ObjetoJuego(JLabelEscalableRotable grafico) { 
		this.grafico = grafico; 
		this.ancho = grafico.getWidth();
		this.alto = grafico.getHeight();
	}

	/** Pone el radio de colisión del objeto de juego
	 * @param radio	Pixels de radio de colisión del objeto (si <= 0 no colisiona)
	 */
	public void setRadioColision( int radio ) {
		radioColision = radio;
		// Descomentar si se quieren ver los radios de colisión
		// grafico.dibujaRadioColision( radio );
	}

	/** Chequea la colisión entre dos objetos de juego
	 * @param o2	Segundo objeto de juego 
	 * @return	true si this y o2 colisionan (según sus radios de colisión, suponiendo esferas virtuales)
	 */
//	public boolean colisionaCon( ObjetoJuego o2 ) {
//		if (radioColision<=0 || o2.radioColision<0) return false;
//		double centro1x = x + ancho/2.0;
//		double centro1y = y + alto/2.0;
//		double centro2x = o2.x + o2.ancho/2.0;
//		double centro2y = o2.y + o2.alto/2.0;
//		double distRadios = Math.sqrt( (centro2x-centro1x)*(centro2x-centro1x) + 
//				(centro2y-centro1y)*(centro2y-centro1y) );
//		return distRadios < radioColision + o2.radioColision;
//	}

	/** Modifica la posición del objeto en el espacio lógico de juego
	 * (no mueve en la ventana - ver método {@link #actualizaGrafico()})
	 * @param x	Nueva posición x
	 * @param y	Nueva posición y
	 */
	public void setPos( double x, double y ) { 
		this.x = x; this.y = y; 
		grafico.setLocation((int)x,(int) y);
	}

	/** Modifica la velocidad del objeto
	 * @param velX	Nueva velocidad x (píxels/sg)
	 * @param velY	Nueva velocidad y (píxels/sg)
	 */
	public void setVel( double velX, double velY ) { 
		this.velX = velX; this.velY = velY; 
	}

	/** Actualiza las físicas mecánicas del objeto de juego (velocidad en función de la gravedad,
	 * posición en función de la velocidad), de acuerdo al tiempo transcurrido en segundos
	 * @param tiempo	Tiempo transcurrido (segundos)
	 */
	public void actualizaFisica(double tiempo) {
		velY += (G * tiempo);
		setPos(x + velX * tiempo, y + velY * tiempo);
	}

	/** Actualiza la posición del gráfico en la ventana de acuerdo a la posición actual
	 */
	public void actualizaGrafico() { 
		grafico.setLocation( (int)Math.round(this.x), (int)Math.round(this.y) );
	}

	public double getX() { 
		return x; }
	public double getY() { 
		return y; }
	public double getVelX() { 
		return velX; }
	public double getVelY() {
		return velY; }
	public int getAncho() { 
		return ancho; }
	public int getAlto() { 
		return alto; }
	public JLabelEscalableRotable getLabel() {
		return grafico; }

}
