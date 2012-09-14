package parchis;

public class Dado {
	
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

	

}
