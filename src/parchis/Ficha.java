package parchis;

public class Ficha {
	
	
	//atributos
	private ColorP color;
	private int posicion;


	//getters
	public ColorP getCol() {
		return color;
	}

	public int getPosicion() {
		return posicion;
	}
	
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	//constructora
	public Ficha(ColorP pCol) {
		this.color = pCol;
		this.posicion = -1; //-1 indica que esta en casa y no ha salido al tablero
	}
	
	public Ficha(ColorP color, int posicion) {
		this.color = color;
		this.posicion = posicion;
	}

	public void enviarACasa(){
		this.posicion = -1;
	}
	
	/**
	 * 
	 * @param tirada
	 * @return devuelve 0 si no hay barrera, o un int 
	 * con el numero de casilla donde esta la barrera
	 */
	private int esPosibleMover(int tirada){
		int cont = 0;
		int posBar = this.posicion + 1;
		boolean barrera = false;
		
		while(cont < tirada && !barrera){
			if(posBar == 69){posBar = 1;}
			
			if(Tablero.getMiTablero().hayBarrera(posBar) || (Tablero.getMiTablero().hayDosFichas(posBar) && Tablero.getMiTablero().esCasillaSeguro(posBar))){
				barrera = true;
			}else{
				posBar++;
				cont++;
			}
			
		}
		if(!barrera){
			posBar = 0;
		}
		
		return posBar;
	}
	
	/**
	 * el parametro que se le pasa como color a enviarFichaACasa
	 * es el color de la ficha que come, NO la comida. 
	 * @throws BonusException 
	 */
	private void comerFicha() throws BonusException{
		
		if(!Tablero.getMiTablero().esCasillaSeguro(posicion)){
			Tablero.getMiTablero().enviarFichaACasa(posicion, color);
			throw new BonusException();
		}
	}

	/**
	 * La posicion primera del pasillo sera la 71
	 * 
	 * @param numCasEnPasillo
	 * @throws NoEsPosibleMover 
	 * @throws BonusException 
	 */
	private void entrarAPasillo(int numCasEnPasillo) throws NoEsPosibleMover, BonusException{
		if(numCasEnPasillo < 8){
			Tablero.getMiTablero().quitarFichaDeCasilla(posicion, color);
			this.posicion = numCasEnPasillo + 70;
			ListaPasillos.getMiListaPasillos().introducirFichaEnPasillo(numCasEnPasillo, this.getCol());
		}else{
			if(numCasEnPasillo == 8){
				Tablero.getMiTablero().quitarFichaDeCasilla(this.getPosicion(),this.getCol());
				ListaJugadores.getMiListaJugadores().anadirFichaAMeta(this.color,this.posicion);
				throw new BonusException();
				
			}else{
				throw new NoEsPosibleMover();
			}
			
		}
		
	}
	
	private boolean comprobacionesDePasillo(int tirada) throws NoEsPosibleMover, BonusException{
		boolean	rdo = false;
		int movido = this.posicion + tirada;
		
		if(this.getCol() == ColorP.AZUL && movido > 68){
			movido = movido - 68;
		}
		
		if(this.color == ColorP.AMARILLO  && movido > 68 && this.posicion <= 68 && this.posicion >= 49){
			int numCasEnPasillo = movido - 68; 
			this.entrarAPasillo(numCasEnPasillo);
			rdo = true;
		}else{
			if(this.color == ColorP.AZUL && movido > 17 && ( (this.posicion <= 17 && this.posicion >= 1)
					|| ( this.posicion <= 68  && this.posicion >= 66) ) ){
				
				int numCasEnPasillo = movido - 17; 
				this.entrarAPasillo(numCasEnPasillo);
				rdo = true;
				
			}else{
				
				if(this.color == ColorP.VERDE && movido > 51 && this.posicion <= 51 && this.posicion >= 32){
					int numCasEnPasillo = movido - 51;
					this.entrarAPasillo(numCasEnPasillo);
					rdo = true;
					
				}else{
					if(this.color == ColorP.ROJO && movido > 34 && this.posicion <= 34 && this.posicion >= 15){
						int numCasEnPasillo = movido - 34;
						this.entrarAPasillo(numCasEnPasillo);
						rdo = true;
					}
				}
			}
		}
		
		return rdo;
	}
	

	/**
	 * Pre: hay al menos una ficha en el tablero (tambien puede estar en su pasillo)
	 * @param tirada
	 * @throws NoEsPosibleMover 
	 * @throws BonusException 
	 */
	public void moverFicha(int tirada) throws NoEsPosibleMover, BonusException{
		int posAntigua = this.posicion;
		
		if(this.posicion < 69){//si no esta en pasillo	
		
		if(!this.comprobacionesDePasillo(tirada)){//comprueba si tiene que entrar en pasillo
			
			int barrera = this.esPosibleMover(tirada);
			
			if(barrera == 0){//si no hay barrera --> barrera = 0
				Tablero.getMiTablero().quitarFichaDeCasilla(this.posicion,this.color);
				
				//si nos pasamos del 68 volvemos al 1
				if(this.posicion + tirada > 68){
					this.posicion = this.posicion + tirada - 68;
				}else{
					this.posicion = this.posicion + tirada;
					}
				
				Tablero.getMiTablero().introducirFichaEnCasilla(this.posicion, posAntigua, this.color);
				
				if(Tablero.getMiTablero().hayDosFichas(posicion) && !Tablero.getMiTablero().hayBarrera(posicion)){
					this.comerFicha();
					
				}
			}else{
				throw new NoEsPosibleMover();
			}
			
		}
		
		}else{
			ListaPasillos.getMiListaPasillos().moverFichaEnPasillo(tirada,this);
		}
	}
	
	public boolean equals(Object o){
		Ficha f = (Ficha) o;
		boolean rdo = false;
		if(this.getCol() == f.getCol() && this.posicion == f.posicion){
			rdo = true;
		}
		return rdo;
	}

}
