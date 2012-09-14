package parchis;

import junit.framework.TestCase;

public class CasillaTest extends TestCase {
	
	private Casilla c1 = new Casilla(12, false);
	private Casilla c2= new Casilla(5,true);
	private Casilla c3 = new Casilla(7,false);
	
	
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testNumeroFichas(){
		try{
		assertTrue(c1.numeroFichas()==0);
		c1.introducirFicha(2,ColorP.ROJO);
		assertTrue(c1.numeroFichas()==1);
		c1.introducirFicha(34,ColorP.VERDE);
		assertTrue(c1.numeroFichas()==2);
		}catch(AnadirExcepcion e){
			e.printStackTrace();
			System.out.println("No se ha podido a–adir la ficha a la casilla" +
					" debido a que ya hay 2 fichas");
			
		}
	}

	
	public void testQuitarFicha() {
		
		//Introducimos 2 fichas a la casilla y posteriormente quitamos 1, y comprobamos si estan las ficha que queda
		try{
			c1.introducirFicha(2,ColorP.ROJO);
			c1.introducirFicha(34,ColorP.VERDE);
			c1.quitarFicha(2,ColorP.ROJO);
			
			c1.quitarFicha(34,ColorP.VERDE);
			assertTrue(c1.numeroFichas() == 0);
		}catch(AnadirExcepcion e){
			e.printStackTrace();
			System.out.println("No se ha podido a–adir la ficha a la casilla" +
					" debido a que ya hay 2 fichas");
			
		}
		
	
	}

	public void testEnviarFichaComidaACasa() {
		
		try{
	
		//Anadimos dos fichas a la casilla
		c1.introducirFicha(12,ColorP.VERDE);
		c1.introducirFicha(12,ColorP.AMARILLO);
		assertTrue(c1.numeroFichas() == 2);
	
		// Y al enviar una ficha a casa en la casilla solo debe haber una ficha
		assertEquals(c1.enviarFichaComidaACasa(ColorP.AMARILLO), new Ficha(ColorP.VERDE,12));
		
		
		c1.introducirFicha(12,ColorP.AZUL);
		assertTrue(c1.numeroFichas() == 2);
		assertEquals(c1.enviarFichaComidaACasa(ColorP.AZUL), new Ficha(ColorP.AMARILLO,12));
		
		
		// Enviamos una ficha no existente a casa
		
		assertNull(c1.enviarFichaComidaACasa(ColorP.ROJO));
		
		
		}catch(AnadirExcepcion e){
			e.printStackTrace();
			System.out.println("No se ha podido a–adir la ficha a la casilla" +
					" debido a que ya hay 2 fichas");
			
		}
			

	
	}
	

	public void testEsSeguro() {
		// Casilla segura  --TRUE
		assertTrue(c2.esSeguro());
		
		//Casilla no segura ---FALSE
		assertFalse(c1.esSeguro());
	}

	public void testIntroducirFicha() {
		
		try{
		assertTrue(c2.numeroFichas() == 0);	
		//Introducimos 1 fichas y comprobamos que en la misma casilla 1 fichas
		c2.introducirFicha(5,ColorP.ROJO);
		assertTrue(c2.numeroFichas() == 1);
		//Introducimos 2 fichas y comprobamos que en la misma casilla 2 fichas
		c2.introducirFicha(5,ColorP.VERDE);
		assertTrue(c2.numeroFichas() == 2);
		// Intentamos introducir una tercera ficha, y vemos que salta la excepcion por pantalla
		System.out.println("Aqui debe saltar una excepcion y :");
		c2.introducirFicha(5,ColorP.AMARILLO);
		}catch(AnadirExcepcion e){
			System.out.println("No se ha podido a–adir la ficha a la casilla" +
					" debido a que ya hay 2 fichas");
			
		}
		
	}

	public void testHayBarrera() {

		try{
		//Casilla vacía   --- FALSE
		assertFalse(c3.hayBarrera());
		
		//Casilla con dos fichas de diferente color  --FALSE
		c2.introducirFicha(2,ColorP.ROJO);
		c2.introducirFicha(68,ColorP.AMARILLO);
		assertFalse(c2.hayBarrera());

		
		//Casilla con una ficha   --- FALSE
		c2.quitarFicha(2,ColorP.ROJO);
		assertFalse(c2.hayBarrera());
		
		//Casilla con dos fichas iguales --- TRUE
		c1.introducirFicha(2,ColorP.ROJO);
		c1.introducirFicha(2,ColorP.ROJO);
		assertTrue(c1.hayBarrera());
		
		}catch(AnadirExcepcion e){
			e.printStackTrace();
			System.out.println("No se ha podido a–adir la ficha a la casilla" +
					" debido a que ya hay 2 fichas");
			
		}

			
	}

}
