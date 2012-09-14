package parchis;

import java.util.ArrayList;
import java.util.Observer;

public class Tablero{
	private ArrayList<Casilla> tablero;
	private static Tablero miTablero = new Tablero();

	private Tablero(){
		this.tablero = new ArrayList<Casilla>();
		for (int i = 1; i < 69; i++) {
			this.tablero.add(new Casilla(i, false));
		}
		int i = 4;
		while( i < 63){
			this.tablero.set(i, new Casilla(i + 1, true));
			i = i + 7;
			this.tablero.set(i, new Casilla(i + 1, true));
			i = i + 5;
			this.tablero.set(i, new Casilla(i + 1, true));
			i = i + 5;
		}
	}
	
	public static Tablero getMiTablero(){
		return miTablero;
	}
	
	public void anadirObservadorACasilla(int numCas, Observer ob){
		this.tablero.get(numCas).addObserver(ob);
	}
	
	/**
	 * 
	 * @param numCas
	 */
	public void quitarFichaDeCasilla(int numCas, ColorP color){
		this.tablero.get(numCas - 1).quitarFicha(numCas,color);
	}
	
	public void reiniciarTablero(){
		miTablero = new Tablero();
	}
	
	/**
	 * 
	 * @param numCas
	 * @param posAnt
	 * @param color
	 */
	public void introducirFichaEnCasilla(int numCas, int posAnt, ColorP color){
		try{//
			this.tablero.get(numCas - 1).introducirFicha(numCas, color);
					
		}catch(AnadirExcepcion e){
			System.out.println("No se ha podido a–adir la ficha a la casilla" +
				" debido a que ya hay 2 fichas");
		}
		
	}
	
	/**
	 * pre: numCas es un numero de casilla valido
	 * @param numCas
	 * @return devuelve  true si las dos fichas en la casilla 
	 * son del mismo color, false en caso contrario (en este
	 * caso sino es una casilla segura, se comer‡ la ficha
	 * correspondiente).
	 */
	public boolean hayBarrera(int numCas){
		return this.tablero.get(numCas - 1).hayBarrera();
	}
	
	public boolean hayDosFichas(int numCas){
		return this.tablero.get(numCas - 1).numeroFichas() == 2;
	}
	
	public boolean esCasillaSeguro(int numCas){
		return this.tablero.get(numCas - 1).esSeguro();
	}
	
	
	/**
	 * 
	 * @param numCas
	 * @param colFichaNoComida
	 */
	public void enviarFichaACasa(int numCas, ColorP colFichaNoComida){
		try{
			Ficha f = this.tablero.get(numCas - 1).enviarFichaComidaACasa(colFichaNoComida);
			if(f != null){
				ListaJugadores.getMiListaJugadores().enviarACasaFicha(f.getCol(),f.getPosicion());
			}else{
				throw new FichaNoExiste();
			}
			
		}catch(FichaNoExiste e){
			System.out.println("La ficha no existe en la casilla");
		}
		
	}
	
}
