package org.obvial.obvial;

import junit.framework.TestCase;

public class ListasTest extends TestCase{
	ListaCartas l1,l2,l3,l4,l5;
	Carta c1,c2,c3;
	
	protected void setUp() throws Exception {

		l1=new ListaCartas();
		l2=new ListaCartas();
		l3=new ListaCartas();
		l4=new ListaCartas();
		l5=new ListaCartas();
		
		
		c1 = new Carta(1,"¿Cual es el primer deseo de Aladdin?","D","Ser un sultan","Ser el genio mas poderoso del mundo","Jasmin se enamore de el","Convertirle en principe");
		c2 = new Carta(2,"La máxima puntuación que se puede conseguir en un juego de Pac-Man es ..","A","3.333","4.55","5","10");
		c3 = new Carta(3,"¿Cuántos atardeceres ven cada día los astronautas de la Estación Espacial Internacional?","C","7","24","15","4");
		
		l1.anadirCarta(c1);
		l1.anadirCarta(c2);
		l1.anadirCarta(c3);
		
		l2.anadirCarta(c1);
		l2.anadirCarta(c2);
		l2.anadirCarta(c3);
		
		l3.anadirCarta(c1);
		l3.anadirCarta(c2);
		l3.anadirCarta(c3);
		
		l4.anadirCarta(c1);
		l4.anadirCarta(c2);
		l4.anadirCarta(c3);
		
		l5.anadirCarta(c1);
		l5.anadirCarta(c2);
		l5.anadirCarta(c3);
	
		
		Listas.getMisListas().anadirListas(0, l1);
		Listas.getMisListas().anadirListas(1, l2);
		Listas.getMisListas().anadirListas(2, l3);
		Listas.getMisListas().anadirListas(3, l4);
		Listas.getMisListas().anadirListas(4, l5);
		
		
		
	}

	protected void tearDown() throws Exception {
		Listas.getMisListas().getListasDeCartas()[0]=null;
		Listas.getMisListas().getListasDeCartas()[1]=null;
		Listas.getMisListas().getListasDeCartas()[2]=null;
		Listas.getMisListas().getListasDeCartas()[3]=null;
		Listas.getMisListas().getListasDeCartas()[4]=null;
	
	}
	
	
	
	public void testAnadirListas(){
		ListaCartas l6;
		ListaCartas lista = Listas.getMisListas().getListasDeCartas()[4];
		assertTrue(lista!=null);
		Listas.getMisListas().anadirListas(4, l6=new ListaCartas());
		assertTrue(Listas.getMisListas().getListasDeCartas()[4]==l6);
		Listas.getMisListas().getListasDeCartas()[0]=null;
		assertTrue(Listas.getMisListas().getListasDeCartas()[0]==null);
		Listas.getMisListas().anadirListas(0, l6=new ListaCartas());
		assertTrue(Listas.getMisListas().getListasDeCartas()[0]==l6);
		
	}
	
	public void testBuscarCartaTema(){
		
		Carta carta= Listas.getMisListas().buscarCartaTema(0);
		assertTrue(carta!=null);
		Carta carta1= Listas.getMisListas().buscarCartaTema(1);
		assertTrue(carta1!=null);
		Carta carta2= Listas.getMisListas().buscarCartaTema(2);
		assertTrue(carta2!=null);
		Carta carta3= Listas.getMisListas().buscarCartaTema(3);
		assertTrue(carta3!=null);
		Carta carta4= Listas.getMisListas().buscarCartaTema(4);
		assertTrue(carta4!=null);
	
	}
	
	
	public void testReiniciarListas(){
		Listas.getMisListas().getListasDeCartas()[0].buscarCarta(c1.getNum()).usarCarta();
		Listas.getMisListas().getListasDeCartas()[1].buscarCarta(c2.getNum()).usarCarta();
		Listas.getMisListas().getListasDeCartas()[2].buscarCarta(c3.getNum()).usarCarta();
		assertTrue(Listas.getMisListas().getListasDeCartas()[0].buscarCarta(c1.getNum()).haSidoUsada());
		assertTrue(Listas.getMisListas().getListasDeCartas()[1].buscarCarta(c2.getNum()).haSidoUsada());
		assertTrue(Listas.getMisListas().getListasDeCartas()[2].buscarCarta(c3.getNum()).haSidoUsada());

		Listas.getMisListas().reiniciarListas();
		
		assertFalse(Listas.getMisListas().getListasDeCartas()[0].buscarCarta(c1.getNum()).haSidoUsada());
		assertFalse(Listas.getMisListas().getListasDeCartas()[1].buscarCarta(c2.getNum()).haSidoUsada());
		assertFalse(Listas.getMisListas().getListasDeCartas()[2].buscarCarta(c3.getNum()).haSidoUsada());
		
		
	}
	
	
	
	
}
