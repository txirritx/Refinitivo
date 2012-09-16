package org.obvial.obvial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class ListaJugadores extends Observable{
	
	private static ListaJugadores miListaJugadores = new ListaJugadores();
	private ArrayList <Jugador> listaJ;
	
	private ListaJugadores(){
		listaJ = new ArrayList <Jugador> ();
	}
	
	public static ListaJugadores getListaJugadores(){
		return miListaJugadores;
	}
	
	public void anadirJugador(String pNombre, String pColor){
		
		if (listaJ.size() == 4){
			System.out.println("No se pueden anadir mas jugadores");
		}else{
			if(listaJ.size() < 4){
				Jugador j = new Jugador (pNombre, pColor, listaJ.size() +1);
				listaJ.add(j);
				setChanged();
				notifyObservers(j);
				ListaCasillas.getListaCasillas().anadirJugadorACasilla(j, j.getPosicion());
			}
		}
	}
	
	public Iterator <Jugador> getIterador(){
		return listaJ.iterator();
	}
	
	public Jugador buscarJugador (int pId){
		Iterator <Jugador> itrJ = this.getIterador();
	
		boolean enc = false;
		Jugador res = null;
		
		while (itrJ.hasNext() && enc == false){
			res = itrJ.next();
			if (res.compararId(pId)){
				enc = true;
			}
		}
		
		if ( enc == false){
			res = null;
		}
		
		return res;
	}
	
	public boolean algunoHaTerminado(){
		Iterator<Jugador> itrJ = this.getIterador();
		
		boolean ret = false;
		Jugador unJugador = null;
		
		while(itrJ.hasNext() && ret == false){
			unJugador = itrJ.next();
			if(unJugador.haFinalizado() == true){
				ret = true;
			}
		}
		
		return ret;
	}
	
	public void vaciarLista(){
		listaJ = new ArrayList<Jugador>();
	}
	
	//Para las pruebas
	public ArrayList <Jugador> getListaJ(){
		return listaJ;
	}

}
