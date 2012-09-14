package parchis;

import junit.framework.TestCase;

public class FichaTest extends TestCase {

	Ficha f1 = new Ficha(ColorP.AZUL);
	Ficha f2 = new Ficha(ColorP.ROJO);
	Ficha f3 = new Ficha(ColorP.AMARILLO);
	Ficha f4 = new Ficha(ColorP.VERDE);
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEnviarACasa() {
		
		// Movemos la ficha y posteriormente compromabos su posicion
		f1.setPosicion(5);
		assertEquals(f1.getPosicion(),5);
		assertFalse(f1.getPosicion() == 7);
		// Enviamos a casa la ficha comprobamos que su posicion sea -1
		f1.enviarACasa();
		assertTrue(f1.getPosicion() == -1);
		

		// Movemos otra ficha y posteriormente compromabos su posicion
		f2.setPosicion(68);
		assertEquals(f2.getPosicion(),68);
		assertFalse(f2.getPosicion() == 1);
		// Enviamos a casa la ficha comprobamos que su posicion sea -1
		f2.enviarACasa();
		assertTrue(f2.getPosicion() == -1);
		
	}

	public void testMoverFicha() {
			
		try{
			
		f2.setPosicion(1);
		f2.moverFicha(2);
		assertTrue(f2.getPosicion() == 3);
		
		f2.moverFicha(3);
		assertTrue(f2.getPosicion() == 6);
		
		f2.moverFicha(4);
		assertTrue(f2.getPosicion() == 10);
		
		f2.moverFicha(5);
		assertTrue(f2.getPosicion() == 15);
		
		f2.moverFicha(6);
		assertTrue(f2.getPosicion() == 21);
		
		f1.setPosicion(8);
		assertTrue(f1.getPosicion() == 8);
		
		//Probamos a mover la ficha 5 posiciones
		f1.moverFicha(5);
		assertTrue(f1.getPosicion() == 13);
		
		//Probamos a mover y que de fallo ya que entraria en el pasillo
		f1.moverFicha(6);
		assertFalse(f1.getPosicion() == 19);
		
		//Probamos a ver si salta de la posicion 68 a la 1 correctamente
		f1.setPosicion(66);
		f1.moverFicha(3);
		assertTrue(f1.getPosicion() == 1);
		f1.enviarACasa();
		
		f2.setPosicion(67);
		f2.moverFicha(3);
		assertTrue(f2.getPosicion() == 2);
		f2.enviarACasa();
		
		f4.setPosicion(68);
		f4.moverFicha(3);
		assertTrue(f4.getPosicion() == 3);
		
		f4.setPosicion(65);
		f4.moverFicha(6);
		assertTrue(f4.getPosicion() == 3);
		
		//El caso del amarillo se meteria al pasillo y no a la casilla 1
		f3.setPosicion(66);
		f3.moverFicha(3);
		assertFalse(f3.getPosicion() == 1);
		
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No ha sido posible mover la ficha");
		}
		
	}
	

}
