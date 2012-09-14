package parchis;

public class JuegoTerminado extends Exception {

	private ColorP ganador;
	
	public JuegoTerminado(ColorP color) {
		this.ganador = color;
	}

	public ColorP getGanador(){
		return ganador;
	}
}
