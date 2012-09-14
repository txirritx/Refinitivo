package parchis;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class ListaJugadores {
	
	private Hashtable<ColorP, Jugador> lista;
	private static ListaJugadores miListaJugadores = new ListaJugadores();

	/**
	 * En principio la partida se crea para dos jugadores.
	 */
	private ListaJugadores() {
		lista = new Hashtable<ColorP, Jugador>();
		lista.put(ColorP.AMARILLO, new Jugador(ColorP.AMARILLO));
		lista.put(ColorP.AZUL, new Jugador(ColorP.AZUL));
		lista.put(ColorP.ROJO, new Jugador(ColorP.ROJO));
		lista.put(ColorP.VERDE, new Jugador(ColorP.VERDE));
	}

	public static ListaJugadores getMiListaJugadores() {
		return miListaJugadores;
	}
	
	
	public void anadirFichaAMeta(ColorP color,int posFicha){
		try {
			this.lista.get(color).anadirFichaAMeta(color,posFicha);
		} catch (JuegoTerminado e) {
			JOptionPane.showMessageDialog(null,"EL JUGADOR "+e.getGanador()+" HA GANADO LA PARTIDA", "JUEGO TERMINADO", JOptionPane.INFORMATION_MESSAGE);
			
			try {
				Thread.sleep(10000);
				System.exit(0);
			} catch (Exception e1) {
			}
		}
	}
	
	public ArrayList<Integer> listaPosicionesFichas(ColorP color){
		return this.lista.get(color).listaPosicionesFichas();
	}

	
	public void enviarACasaFicha(ColorP color, int pos){
		try{
			this.lista.get(color).enviarFichaACasa(color ,pos);
		}catch(FichaNoExiste e){
		
		}
		
		
	}
	
	/**
	 * 
	 * @param jugador
	 * @throws NoEsPosibleMover 
	 * @throws BonusException 
	 */
	public void realizarTirada(ColorP color, int tirada, int indFichaAMover) throws NoEsPosibleMover, BonusException{
		this.lista.get(color).realizarTirada(tirada,indFichaAMover);
	}
	
	/**
	 * 
	 * @param c
	 * @throws NoEsPosibleSacarFichaDeCasa
	 */
	public void sacarFichaDeCasa(ColorP c) throws NoEsPosibleSacarFichaDeCasa{
		this.lista.get(c).sacarFichaDeCasaAlTablero(c);
		
	}
	
	public boolean quedanFichasEnCasa(ColorP color){
		return this.lista.get(color).quedanFichasEnCasa();
	}
	
	public boolean todasFichasEnCasa(ColorP color){
		return this.lista.get(color).todasFichasEnCasa();
	}
	
	public void anadirObservadorAJugador(ColorP color,Observer ob){
		this.lista.get(color).addObserver(ob);
	}
	
	public int fichasEnMeta(ColorP color){
		return this.lista.get(color).getFichasEnMeta();
	}


}
