package parchis;

public class OrdenObservable {
	private ColorP color;
	private String accion;
	
	public OrdenObservable(ColorP pColor, String pAccion) {
		this.color = pColor;
		this.accion =pAccion;
	}
	
	public ColorP getColor() {
		return color;
	}
	public String getAccion() {
		return accion;
	}
	


}
