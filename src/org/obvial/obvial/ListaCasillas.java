package org.obvial.obvial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

//Tenemos 3 tipos de casillas:
//	-Tipo 1: Normales. Hay que contestar a una pregunta.
//	-Tipo 2: Retencion. X turnos sin tirar.
//	-Tipo 3: Traslado. Te desplaza a una casilla en concreto.

public class ListaCasillas extends Observable {
	
	private static ListaCasillas miListaCasillas = new ListaCasillas();
	private ArrayList <Casilla> listaCasillas;
	private ArrayList<CasillaRetencion> listaCasillasRetencion;
	private ArrayList<CasillaTraslado> listaCasillasTraslados;
	
	private ListaCasillas(){
		listaCasillas = new ArrayList <Casilla> ();
		listaCasillasRetencion = new ArrayList <CasillaRetencion> ();
		listaCasillasTraslados = new ArrayList <CasillaTraslado> ();
	}
	
	public static ListaCasillas getListaCasillas(){
		return miListaCasillas;
	}
	
	public void anadirCasilla(Casilla pCasilla){		
		if(this.listaCasillas == null){
			this.listaCasillas = new ArrayList<Casilla>();
		}else{
			this.listaCasillas.add(pCasilla);
		}
	}
	
	public void anadirCasillaRetencion(CasillaRetencion pCasilla){
		if(this.listaCasillasRetencion == null){
			this.listaCasillasRetencion = new ArrayList<CasillaRetencion>();
		}else{
			this.listaCasillasRetencion.add(pCasilla);
		}
	}
	
	public void anadirCasillaTraslado(CasillaTraslado pCasilla){
		if(this.listaCasillasTraslados == null){
			this.listaCasillasTraslados = new ArrayList<CasillaTraslado>();
		}else{
			this.listaCasillasTraslados.add(pCasilla);
		}
	}
	
	public void anadirJugadorACasilla(Jugador pJugador, int pNumCasilla){
		Casilla unaCasilla = this.buscarCasilla(pNumCasilla);
		if(unaCasilla == null){
			unaCasilla = this.buscarCasillaRetencion(pNumCasilla);
			if(unaCasilla == null){
				unaCasilla = this.buscarCasillaTraslado(pNumCasilla);
			}
		}
		
		if(unaCasilla != null){
			unaCasilla.anadirJugadorACasilla(pJugador);
			setChanged();
			notifyObservers(unaCasilla);
		}else{
			System.out.println("La casilla no existe");
		}
		
	}
	
	public Casilla buscarCasilla(int pNumCasilla){
		
		Casilla unaCasilla = null;
		Casilla casillaFinal = null;
		Iterator<Casilla> itr = this.listaCasillas.iterator();
		Boolean enc = false;
		
		while(itr.hasNext() && enc == false){
			unaCasilla = itr.next();
			if(unaCasilla.getNumero()==pNumCasilla){
				casillaFinal = unaCasilla;
				enc = true;
			}
		}
		return casillaFinal;
	}
	
	public CasillaRetencion buscarCasillaRetencion(int pNumCasilla){
		
		CasillaRetencion unaCasilla = null;
		CasillaRetencion casillaFinal = null;
		Iterator<CasillaRetencion> itr = this.listaCasillasRetencion.iterator();
		Boolean enc = false;
		
		while(itr.hasNext() && enc == false){
			unaCasilla = itr.next();
			if(unaCasilla.getNumero()==pNumCasilla){
				casillaFinal = unaCasilla;
				enc = true;
			}
		}
		return casillaFinal;
	}

	public CasillaTraslado buscarCasillaTraslado(int pNumCasilla){
		
		CasillaTraslado unaCasilla = null;
		CasillaTraslado casillaFinal = null;
		Iterator<CasillaTraslado> itr = this.listaCasillasTraslados.iterator();
		Boolean enc = false;
		
		while(itr.hasNext() && enc == false){
			unaCasilla = itr.next();
			if(unaCasilla.getNumero()==pNumCasilla){
				casillaFinal = unaCasilla;
				enc = true;
			}
		}
		return casillaFinal;
	}
	
	public int getTipoCasilla(int pNumCasilla){
		
		Casilla unaCasilla = this.buscarCasilla(pNumCasilla);
		if(unaCasilla == null){
			unaCasilla = this.buscarCasillaRetencion(pNumCasilla);
			if(unaCasilla == null){
				unaCasilla = this.buscarCasillaTraslado(pNumCasilla);
			}
		}
		
		return unaCasilla.getClase();
		
	}
	
	public void eliminarJugadorDeCasilla(int pNumCasilla, Jugador pJugador){
		
		Casilla casilla1 = this.buscarCasilla(pNumCasilla);
		if(casilla1 == null){
			CasillaRetencion casilla2 = this.buscarCasillaRetencion(pNumCasilla);
			if(casilla2 == null){
				CasillaTraslado casilla3 = this.buscarCasillaTraslado(pNumCasilla);
				if(casilla3 == null){
					System.out.println("La casilla no existe");
				}else{
					casilla3.eliminarJugadorDeCasilla(pJugador);
				}
			}else{
				casilla2.eliminarJugadorDeCasilla(pJugador);
				}
		}else{
			casilla1.eliminarJugadorDeCasilla(pJugador);
		}
	}
	
	public int getTema(){
		Random r= new Random();
		int tema= r.nextInt(5);
		return tema;
	}
	
	public void reiniciarCasillas(){
		listaCasillas = new ArrayList <Casilla> ();
		listaCasillasRetencion = new ArrayList <CasillaRetencion> ();
		listaCasillasTraslados = new ArrayList <CasillaTraslado> ();
		Inicializar.getInicializar().InicializarCasillas();
	}

	//Para las pruebas
	public int getTama�oListaCasillas(){
		return this.listaCasillas.size();
	}
	
	public int getTama�oListaCasillasRetencion(){
		return this.listaCasillasRetencion.size();
	}
	
	public int getTama�oListaCasillasTraslado(){
		return this.listaCasillasTraslados.size();
	}
}
