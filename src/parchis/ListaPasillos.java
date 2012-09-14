package parchis;

import java.util.Hashtable;
import java.util.Observer;

public class ListaPasillos {
	private Hashtable<ColorP,Pasillo> lista;
	private static ListaPasillos miListaPasillos = new ListaPasillos();
	

	private ListaPasillos() {
		lista = new Hashtable<ColorP, Pasillo>();
		this.lista.put(ColorP.AMARILLO, new Pasillo());
		this.lista.put(ColorP.VERDE, new Pasillo());
		this.lista.put(ColorP.ROJO, new Pasillo());
		this.lista.put(ColorP.AZUL, new Pasillo());
	}

	public static ListaPasillos getMiListaPasillos() {
		return miListaPasillos;
	}
	
	public void introducirFichaEnPasillo(int numCas, ColorP color){
		this.lista.get(color).anadirFicha(numCas,color);
	}
	
	public void moverFichaEnPasillo(int tirada, Ficha f) throws NoEsPosibleMover, BonusException{
		this.lista.get(f.getCol()).moverFicha(tirada, f);
	}

	public void anadirObservadorACasilla(ColorP color,int numCas, Observer ob){
		this.lista.get(color).anadirObserver(numCas,ob);
	}
}
