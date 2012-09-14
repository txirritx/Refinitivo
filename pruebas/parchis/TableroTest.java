package parchis;

import junit.framework.TestCase;

public class TableroTest extends TestCase {
	
	Ficha f1 = new Ficha(ColorP.AZUL);
	Ficha f2 = new Ficha(ColorP.AZUL);
	Ficha f3 = new Ficha(ColorP.VERDE);

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testQuitarFichaDeCasilla() {
		
		// Introducimos dos fichas en la casilla
		Tablero.getMiTablero().introducirFichaEnCasilla(8,-1,ColorP.AZUL);
		Tablero.getMiTablero().introducirFichaEnCasilla(8,-1,ColorP.AZUL);		
		assertTrue(Tablero.getMiTablero().hayBarrera(8));
		
		// Quitamos una de las fichas del tablero y comprobamos que ya no esté esa ficha en la casilla
		Tablero.getMiTablero().quitarFichaDeCasilla(8, ColorP.AZUL);		
		assertFalse(Tablero.getMiTablero().hayBarrera(8));
	}

	public void testIntroducirFichaEnCasilla() {
		
		// Introducimos dos fichas en la casilla y comprobamos que se haya formado una barrera
		Tablero.getMiTablero().introducirFichaEnCasilla(63,-1,ColorP.AZUL);
		assertFalse(Tablero.getMiTablero().hayBarrera(63));
		Tablero.getMiTablero().introducirFichaEnCasilla(63,-1,ColorP.AZUL);		
		assertTrue(Tablero.getMiTablero().hayBarrera(63));
	}

	
	public void testHayBarrera() {
		
		// Una ficha   ---FALSE
		Tablero.getMiTablero().introducirFichaEnCasilla(23,63,ColorP.AZUL);
		assertFalse(Tablero.getMiTablero().hayBarrera(23));
		
		// Dos fichas del mismo color   --- TRUE
		Tablero.getMiTablero().introducirFichaEnCasilla(23,63,ColorP.AZUL);
		assertTrue(Tablero.getMiTablero().hayBarrera(23));
		
		// Dos fichas de distinto color  ---FALSE
		Tablero.getMiTablero().quitarFichaDeCasilla(23,ColorP.AZUL);
		Tablero.getMiTablero().introducirFichaEnCasilla(23,-1, ColorP.VERDE);
		assertFalse(Tablero.getMiTablero().hayBarrera(23));
		
	}

	public void testEsCasillaSeguro() {
		// Casillas seguras   --TRUE
		
		assertTrue(Tablero.getMiTablero().esCasillaSeguro(5));
		assertTrue(Tablero.getMiTablero().esCasillaSeguro(12));
		assertTrue(Tablero.getMiTablero().esCasillaSeguro(17));
		
		// Casillas no seguras  ---FALSE
		
		assertFalse(Tablero.getMiTablero().esCasillaSeguro(6));
		assertFalse(Tablero.getMiTablero().esCasillaSeguro(4));
		assertFalse(Tablero.getMiTablero().esCasillaSeguro(10));
	}

	

}
