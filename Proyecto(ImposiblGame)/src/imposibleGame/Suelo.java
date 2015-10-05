package imposibleGame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import javax.swing.*;

import utils.ObjetoJuego;

/**
 * Un array donde el suelo se genera random con ciertas exccepciones. Este es un JLbabel con una imagen asociada.
 * Los huecos de este array que se llenen seran solo los que haya suelo, el resto estara vacio. 
 * La posicion de los objetos que esten en el array se moveran en -1 en "X", esto es, se moveran hacia la izquierda.  
 */
public class Suelo{
	
	private Image imageSuelo;
	public static ArrayList<JLabel> suelo;
	private int constanteAltura;
	private int alturaVentana;
	private ArrayList<Integer> altura;
	private int alturaMaxima = 720;
	private static double alturaActual;
	
	public Suelo(ArrayList<JLabel> suelo, int alturaMax){
		ImageIcon imageIcon = new ImageIcon("200x200.png");
		this.imageSuelo = imageIcon.getImage();
		this.imageSuelo = imageSuelo.getScaledInstance(suelo.get(0).getWidth(), 
				suelo.get(0).getHeight(), java.awt.Image.SCALE_SMOOTH);
		this.suelo = suelo;
		this.constanteAltura=(int)(alturaMax*0.7)/8;
		altura = new ArrayList<Integer>();
		
	}

	

	public void crearSuelo(){
		for(JLabel lSuelo: suelo){
			lSuelo.setIcon(new ImageIcon (imageSuelo));
			altura.add(0);
			}
	}

	public ArrayList<JLabel> getSuelo() {
		return suelo;
	}
	
	/**
	 * Metodo que hace que el suelo se mueva en cuanto el cubo (Personaje) toque el suelo
	 * El suelo se movera infinitamente a menos que no mueras por colisionar contra los laterales del suelo al variar la altura
	 * o colisionar contra obstaculos
	 */
	public void moverSuelo(){
		final int posicionAnteriorMasIzqX=suelo.get(0).getWidth();
		final int anchuraUni=suelo.size()-1;
		final double velocidadX=(350*20)/1000.0;//px/seg
		
		
		
		for(int a = 0; a<suelo.size();a++){
			double posiX=suelo.get(a).getX()-velocidadX;
			
			suelo.get(a).setLocation((int)posiX, suelo.get(a).getY());
			if (posiX-360<10)
				alturaActual=suelo.get(a).getY();
			
		}
		//Se encarga de las partes si hay que ponerlas al final
		if(suelo.get(0).getX() < suelo.get(0).getWidth()*-1){
			
				int posiFinalXMasUno=suelo.get(anchuraUni).getX() + posicionAnteriorMasIzqX;
				suelo.get(0).setLocation(posiFinalXMasUno, suelo.get(0).getY());
			
			//Modifica posiciones en el arraylist principal
			JLabel z=suelo.get(0);
			suelo.remove(0);
			suelo.add(z);
//			int numero=altura.remove(0);
//			altura.add(numero);
			variarAltura();
		}
		
	}

	/**
	 * La altura del suelo varia
	 * case 1 sube la altra en 1 / case 2, 3, 4, 5 mantienen la altura donde esta / case 6 bajan la altura en 1
	 * hay tantos casos en los que se mantiene el suelo en la altura donde esta porque si no uno se volveria loco
	 * y entonces si que seria imposible de verdad.
	 * @return 
	 */
	public void variarAltura(){
		boolean z = false;
		do{
			
			Random a=new Random();
			int b=0;
			if(suelo.get(suelo.size()-2).getY()<270){
				b=1;
			}else if(suelo.get(suelo.size()-2).getY()>=alturaMaxima-suelo.get(suelo.size()-2).getHeight()){
				b=2;
			}else{
				b = a.nextInt(3);
			}
			switch (b) {
			case 0:
				suelo.get(suelo.size()-1).setLocation(suelo.get(suelo.size()-1).getX(),suelo.get(suelo.size()-2).getY());
				//System.out.println(suelo.get(suelo.size()-2).getY());
					break;
			case 2:
				if(suelo.get(suelo.size()-2).getY()>0){
					int num=this.altura.remove(suelo.size()-1);
					this.altura.add(num+1);
					int numero=(8-altura.get(altura.size()-1))*constanteAltura;
					suelo.get(suelo.size()-1).setLocation(suelo.get(suelo.size()-1).getX(),suelo.get(suelo.size()-2).getY()-suelo.get(suelo.size()-2).getHeight()) ;
					
					//System.out.println(suelo.get(suelo.size()-2).getY());
				}
				break;
			case 1:
				int num=this.altura.remove(suelo.size()-1);
				this.altura.add(num+1);
				int numero=(8-altura.get(altura.size()-1))*constanteAltura;
				suelo.get(suelo.size()-1).setLocation(suelo.get(suelo.size()-1).getX(),suelo.get(suelo.size()-2).getY()+suelo.get(suelo.size()-2).getHeight()) ;
				//System.out.println(suelo.get(suelo.size()-2).getY());
				break;
			}
		}while(z);

	}
	public static double getAlturaActual(){
		return alturaActual;
	}

	
	public static double getalturaVariada(){
		return suelo.get(suelo.size()-2).getY();
	}

}

