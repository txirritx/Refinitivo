package parchis;

import junit.framework.TestCase;

public class DadoTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	//Comprobamos por pantalla que el dado da numeros entre el 1 y el 6
	public void testRealizarTirada() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Dado.getMiDado().realizarTirada());
		}
	}

}
