package parchis;

public class Dado {
	/**
	 * Y esta actualización sólo es para la rama.. NuevaRama
	 * Y esta otra también
	 */
	private static Dado miDado = new Dado();
	
	private Dado(){
		
	}
	
	public static Dado getMiDado() {
		return miDado;
	}
	
	/**
	 * 
	 * @return
	 */
	public int realizarTirada(){
		
		return (int) Math.ceil(Math.random()*6);
	}

	
public int Probaaaaaaaaaaaa(){
		
		return (int) Math.ceil(Math.random()*6);
	}

	

}
