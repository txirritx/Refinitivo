package parchis;

import java.util.LinkedList;
import java.util.Observable;

public class Casilla extends Observable{
	
	protected LinkedList<Ficha> fichas;
	protected int numero;
	protected boolean seguro;
	
	  public Casilla(int numero, boolean seguro)  {
		super();
		this.fichas = new LinkedList<Ficha>();
		this.numero = numero;
		this.seguro = seguro;
		
	}
	  
	  public int getNumero(){
		  return this.numero;
	  }
	
	/**
	 * 
	 * @param colFichaNoComida
	 * @return devuelve la ficha quitada (la que ha sido comida) de la casilla, o null si no existia.
	 */
	public Ficha enviarFichaComidaACasa(ColorP colFichaNoComida) {
		Ficha aux = new Ficha(colFichaNoComida,this.numero);
		int indFichaNoComida = this.fichas.indexOf(aux);
		ColorP colComida = null;
		
		if(indFichaNoComida != -1){
			if(indFichaNoComida == 0){
				colComida = this.fichas.get(1).getCol();
			}else{
				colComida = this.fichas.get(0).getCol();
			}
			this.quitarFicha(this.numero, colComida);
			
			this.setChanged();
			notifyObservers(new OrdenObservable(colComida, "ficha comida"));
			return new Ficha(colComida, this.numero);
		}else{
			return null;
		}
		
	}
	
	public boolean esSeguro() {
		return seguro;
	}
	
	/**
	 * 
	 * @return devuelve true si es una barrera, y false si son de diferente color o no hay 2 fichas.
	 */
	public boolean hayBarrera(){
		boolean rdo = false;
		if(this.numeroFichas() == 2){
			rdo = this.fichas.getFirst().getCol() == this.fichas.getLast().getCol();
		}
		return rdo;
	}
	
	public void introducirFicha(int numCas, ColorP color) throws AnadirExcepcion{
		if(this.fichas.size() < 2){
			this.fichas.add(new Ficha(color, numCas));
			setChanged();
			notifyObservers(new OrdenObservable(color, "introducir ficha"));
			
		}else{
			throw new AnadirExcepcion();
		}
	}
	
	public int numeroFichas(){
		return this.fichas.size();
	}
	
	public void quitarFicha(int numCas,ColorP color){
		this.fichas.remove(new Ficha(color, numCas));
		setChanged();
		notifyObservers(new OrdenObservable(color, "quitar ficha"));
	}
	

}
