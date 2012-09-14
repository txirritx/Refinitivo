package parchis;

import java.util.ArrayList;
import java.util.Observable;

public class Jugador extends Observable {
	private int fichasEnMeta;
	private Ficha[] listaFichas;
	
	
	public Jugador(ColorP c){
		super();
		this.fichasEnMeta = 0;
		listaFichas = new Ficha[4];
		for (int i = 0; i < 4; i++) {
			listaFichas[i] = new Ficha(c);;
		}
	}
	
	/**
	 * La posicion 79 representa la meta
	 * @param f
	 * @throws JuegoTerminado 
	 */
	public void anadirFichaAMeta(ColorP c,int posFicha) throws JuegoTerminado{
		this.fichasEnMeta++;
		Ficha f = new Ficha(c, posFicha);
		
		if(this.fichasEnMeta < 4){
			boolean enviada = false;
			int aux = 0;
			while(aux < 4 && !enviada){
				if(this.listaFichas[aux].equals(f)){
					this.listaFichas[aux].setPosicion(79);
				}
				aux++;
			}
			
			setChanged();
			notifyObservers(new OrdenObservable(c, "anadir ficha meta"));
		}else{
			throw new JuegoTerminado(c);
		}
		
	}
	
	public void enviarFichaACasa(ColorP color, int posFicha) throws FichaNoExiste{
		boolean enviadaACasa = false;
		int i = 0;
		Ficha f = new Ficha(color, posFicha);
		
		while(i<3 && !enviadaACasa){
			if(this.listaFichas[i].equals(f)){
				this.listaFichas[i].enviarACasa();
				enviadaACasa = true;
			}
			i++;
		}
		
		if(!enviadaACasa){
			throw new FichaNoExiste();
		}
	}
	
	public ArrayList<Integer> listaPosicionesFichas(){
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < listaFichas.length; i++) {
			lista.add(listaFichas[i].getPosicion());
		}
		
		return lista;
	}

	
	/**
	 * 
	 * @return
	 */
	public boolean quedanFichasEnCasa(){
		return this.indiceDeFichaEnCasa() != -1;
	}
	
	
	/**
	 * 
	 * @param tirada
	 * @throws NoEsPosibleMover 
	 * @throws BonusException 
	 */
	public void realizarTirada(int tirada, int indFichaAMover) throws NoEsPosibleMover, BonusException{
		this.listaFichas[indFichaAMover].moverFicha(tirada);		
		
	
	}
	

	/**
	 * 
	 * @param c
	 * @throws NoEsPosibleSacarFichaDeCasa
	 */
	public void sacarFichaDeCasaAlTablero(ColorP c) throws NoEsPosibleSacarFichaDeCasa{
		int aux = this.indiceDeFichaEnCasa();
		
		if(aux != -1){
		
		if(c == ColorP.AMARILLO){
			if(!Tablero.getMiTablero().hayDosFichas(5)){
				this.listaFichas[aux].setPosicion(5);
				Tablero.getMiTablero().introducirFichaEnCasilla(5, this.listaFichas[aux].getPosicion(),this.listaFichas[aux].getCol());
			}else{throw new NoEsPosibleSacarFichaDeCasa();}
			
		}else{
			if(c == ColorP.AZUL){
				if(!Tablero.getMiTablero().hayDosFichas(22)){
					this.listaFichas[aux].setPosicion(22);
					Tablero.getMiTablero().introducirFichaEnCasilla(22, this.listaFichas[aux].getPosicion(),this.listaFichas[aux].getCol());
				}else{throw new NoEsPosibleSacarFichaDeCasa();}
				
			}else{
				if(c == ColorP.VERDE){
					if(!Tablero.getMiTablero().hayDosFichas(56)){
						this.listaFichas[aux].setPosicion(56);
						Tablero.getMiTablero().introducirFichaEnCasilla(56, this.listaFichas[aux].getPosicion(),this.listaFichas[aux].getCol());
					}else{throw new NoEsPosibleSacarFichaDeCasa();}
					
				}else{
					if(c == ColorP.ROJO){
						if(!Tablero.getMiTablero().hayDosFichas(39)){
							this.listaFichas[aux].setPosicion(39);
							Tablero.getMiTablero().introducirFichaEnCasilla(39, this.listaFichas[aux].getPosicion(),this.listaFichas[aux].getCol());
						}else{throw new NoEsPosibleSacarFichaDeCasa();}
						
					}	
				}		
			}
		}
		
		setChanged();
		notifyObservers(new OrdenObservable(c, "sacar ficha de casa"));
		}else{
			throw new NoEsPosibleSacarFichaDeCasa();
			}
	}
	
	public int getFichasEnMeta() {
		return fichasEnMeta;
	}
	
	
	/**
	 * 
	 * @return
	 */
	private int indiceDeFichaEnCasa(){
		boolean rdo = false;
		int i = 0;
		while(!rdo && i < 4){
			if(listaFichas[i].getPosicion() == -1){
				rdo = true;
			}else{
				i++;
			}
			
		}
		if(!rdo){
			i = -1;
		}
		
		return i;
	}
	
	public boolean todasFichasEnCasa(){
		boolean todasEnCasa = true;
		int i = 0;
		while(i <4 && todasEnCasa){
			if(this.listaFichas[i].getPosicion() != -1){
				todasEnCasa = false;
			}
			i++;
		}
		
		return todasEnCasa;
	}
	
	
	
	
}
