package parchis;

import java.util.ArrayList;
import java.util.Observer;

public class Pasillo {
	private ArrayList<CasillaPasillo> listaCasillas;
	
	
	public Pasillo() {
		this.listaCasillas = new ArrayList<CasillaPasillo>();
		for (int i = 71; i < 79; i++) {
			this.listaCasillas.add(new CasillaPasillo(i));
		}
	}
	
	public void anadirFicha(int numCas, ColorP color){
		try{
			this.listaCasillas.get(numCas - 1).introducirFicha(numCas, color);
		}catch(AnadirExcepcion e){
			System.out.println("No se ha podido a–adir la ficha a la casilla" +
					" debido a que ya hay 2 fichas");
			}
	}
	
	public void moverFicha(int tirada, Ficha f) throws NoEsPosibleMover, BonusException{
		int posAux = f.getPosicion() + tirada;
		if(posAux < 78){
			this.quitarFicha(f.getCol(),f.getPosicion() - 70);
			this.anadirFicha(posAux - 70, f.getCol());
			f.setPosicion(posAux);
			
		}else{
			if(posAux == 78){
				this.quitarFicha(f.getCol(), f.getPosicion() - 70);
				ListaJugadores.getMiListaJugadores().anadirFichaAMeta(f.getCol(), f.getPosicion());
				throw new BonusException();
				
			}else{
				if(posAux > 78){
					throw new NoEsPosibleMover();
				}
			}
		}
	}
	
	private void quitarFicha(ColorP color,int numCas){
		this.listaCasillas.get(numCas - 1).quitarFicha(numCas,color);
	
	}

	public void anadirObserver(int numCas,Observer ob) {
		this.listaCasillas.get(numCas).addObserver(ob);
		
	}
	

	
	
	
	
	

}
