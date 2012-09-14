package parchis;
/**
 * Esta es otra clase modificada en github, y esta voy a querer actualizar en el repositorio local
 * @author Mikel
 *
 */
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

	
public int Probaaaaaaaaaaaa(){
		
		return (int) Math.ceil(Math.random()*6);
	}

	

}
