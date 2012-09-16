package org.obvial.obvial;

import java.util.ArrayList;

import junit.framework.TestCase;

public class ListaCasillasTest extends TestCase {

	private Casilla c1;
	private Casilla c2;
	private Casilla c3;
	private CasillaRetencion c4;
	private CasillaRetencion c5;
	private CasillaRetencion c6;
	private CasillaTraslado c7;
	private CasillaTraslado c8;
	private CasillaTraslado c9;
	private Jugador j1;
	private Jugador j2;
	
	protected void setUp() throws Exception {
		c1 = new Casilla(1, 1, 1, 1);
		c2 = new Casilla(4, 1, 1, 1);
		c3 = new Casilla(7, 1, 1, 1);
		c4 = new CasillaRetencion(13, 2, 2, 1, 1);
		c5 = new CasillaRetencion(45, 3, 2, 1, 1);
		c6 = new CasillaRetencion(27, 2, 2, 1, 1);
		c7 = new CasillaTraslado(34, 45, 3, 1, 1);
		c8 = new CasillaTraslado(47, 58, 3, 1, 1);
		c9 = new CasillaTraslado(51, 54, 3, 1, 1);
		j1 = new Jugador("Manu", "Verde", 1);
		j2 = new Jugador("Manu", "Rojo", 2);
		ListaCasillas.getListaCasillas().anadirCasilla(c1);
		ListaCasillas.getListaCasillas().anadirCasilla(c2);
		ListaCasillas.getListaCasillas().anadirCasilla(c3);
		ListaCasillas.getListaCasillas().anadirCasillaRetencion(c4);
		ListaCasillas.getListaCasillas().anadirCasillaRetencion(c5);
		ListaCasillas.getListaCasillas().anadirCasillaRetencion(c6);
		ListaCasillas.getListaCasillas().anadirCasillaTraslado(c7);
		ListaCasillas.getListaCasillas().anadirCasillaTraslado(c8);
		ListaCasillas.getListaCasillas().anadirCasillaTraslado(c9);
	}
	
	
	protected void tearDown() throws Exception {
		
	}
	
	public void testGetListaCasillas(){
		assertTrue(ListaCasillas.getListaCasillas().getTamañoListaCasillas() == 3);
		assertTrue(ListaCasillas.getListaCasillas().getTamañoListaCasillasRetencion() == 3);
		assertTrue(ListaCasillas.getListaCasillas().getTamañoListaCasillasTraslado() == 3);
	}
	
	public void testBuscarCasillas(){
		assertTrue(ListaCasillas.getListaCasillas().buscarCasilla(4).getNumero() == 4);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasillaRetencion(45).getNumero() == 45);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasillaTraslado(47).getNumero() == 47);
	}
	
	public void testGetTipoCasilla(){
		assertTrue(ListaCasillas.getListaCasillas().getTipoCasilla(45) == 2);
		assertTrue(ListaCasillas.getListaCasillas().getTipoCasilla(47) == 3);
	}
	
	public void testAñadirJugadorACasilla(){
		ListaCasillas.getListaCasillas().anadirJugadorACasilla(j1, 45);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasillaRetencion(45).esta(j1));
		assertFalse(ListaCasillas.getListaCasillas().buscarCasillaRetencion(45).esta(j2));
		ListaCasillas.getListaCasillas().eliminarJugadorDeCasilla(45, j1);
		assertFalse(ListaCasillas.getListaCasillas().buscarCasillaRetencion(45).esta(j1));
	}
	
	public void testGetTema(){
		int a=ListaCasillas.getListaCasillas().getTema();
		assertTrue(a==0 || a==1 || a==2 || a==3 || a==4 ); 
		
	}
	
}
