package org.obvial.obvial;

//Para facilitar la representacion de las fichas, cada color tiene un valor:
//Rojo: 1
//Verde: 10 
//Azul: 2
//Amarillo: 20

public class Jugador {
	
	private String nombre;
	private int posicion;
	private int id;
	private int color;
	private boolean haFinalizado;
	//para cuando caiga en una casilla de retencion
	private int retencion;
	
	public Jugador (String pNombre, String pColor, int pId){
		nombre = pNombre;
		posicion = 1;
		id = pId;
		haFinalizado = false;
		retencion = 0;
		
		if(pColor.equals("Rojo")){
			color = 1;
		}else{
			if(pColor.equals("Verde")){
				color = 10;
			}else{
				if(pColor.equals("Azul")){
					color = 2;
				}else{
					color = 20;
				}
			}
		}
		
	}
	
	public boolean compararId(int pId){
		return (id == pId);
	}
	
	public int getId(){
		return this.id;
	}
	
	public void retener(int pRet){
		retencion = pRet;
	}
	
	public int getRetencion(){
		return retencion;
	}
	
	public void actualizarRet(){
		if(this.retencion > 0){
			retencion --;
		}
	}
	
	public int getPosicion(){
		return this.posicion;
	}
	
	public int getColor(){
		return this.color;
	}
	
	public boolean haFinalizado(){
		return this.haFinalizado;
	}
	
	public void ganar(){
		this.haFinalizado = true;
	}
	
	public void avanzar(int pCantidad){
		
		//Posicion en la que se encontraria el jugador si avanza
		//el numero de casillas pasado por parametro 
		int posicionFutura = this.posicion+pCantidad;
		
		if(posicionFutura > 63){
			//Calculas cuantas posiciones "rebotas"
			posicionFutura = posicionFutura - 63;
			//Calculas a que posicion iras mediante tus posiciones de "rebote"
			this.moverACasilla(63 - posicionFutura);
		}else{
			this.moverACasilla(posicionFutura);
		}
	}
	
	public void moverACasilla(int pCasilla){
		ListaCasillas.getListaCasillas().eliminarJugadorDeCasilla(posicion, this);
		this.posicion = pCasilla;
		ListaCasillas.getListaCasillas().anadirJugadorACasilla(this, pCasilla);
	}
	
	//Para las pruebas
	public String getNombre(){
		return nombre;
	}
	

}
