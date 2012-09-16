package packInterfazGrafica;

import java.awt.EventQueue;
import javax.swing.*;
import org.obvial.obvial.Carta;
import java.awt.*;
import java.awt.event.*;

public class IGPregunta extends JDialog {

	private JPanel panel;
	private JTextField txtTempo;
	private JTextArea txtPregunta;
	private ButtonGroup grupoDeBotones = new ButtonGroup();
	private JRadioButton rdbtnRespuestaA;
	private JRadioButton rdbtnRespuestaB;
	private JRadioButton rdbtnRespuestaC;
	private JRadioButton rdbtnRespuestaD;
	private JButton btnAceptarRespuesta;
	private int n=0;
	private Timer t;
	private static Carta cartaActual = new Carta(0, "", "", "", "", "", "");
	private boolean respuesta = false;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGPregunta dialog = new IGPregunta(cartaActual);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public IGPregunta(Carta pCarta) {
		cartaActual = pCarta;
		initialize();
	}
	private void initialize() {
		//Con esto quitamos los bordes de la ventana
		setUndecorated(true);
		setBounds(898, 47, 388, 726);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		t.start();
	}
	
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(7, 1, 0, 0));
			panel.setVisible(true);
			panel.setSize(500, 200);
			panel.add(getTxtTempo());
			panel.add(getTxtPregunta());
			getTxtPregunta().setText(cartaActual.getPregunta());
			panel.add(getRdbtnRespuestaA());
			getRdbtnRespuestaA().setText(cartaActual.getResp1());
			panel.add(getRdbtnRespuestaB());
			getRdbtnRespuestaB().setText(cartaActual.getResp2());
			panel.add(getRdbtnRespuestaC());
			getRdbtnRespuestaC().setText(cartaActual.getResp3());
			panel.add(getRdbtnRespuestaD());
			getRdbtnRespuestaD().setText(cartaActual.getResp4());
			panel.add(getBtnAceptarRespuesta());
			grupoDeBotones.add(rdbtnRespuestaA);
			grupoDeBotones.add(rdbtnRespuestaB);
			grupoDeBotones.add(rdbtnRespuestaC);
			grupoDeBotones.add(rdbtnRespuestaD);
		}
		return panel;
	}
	private JTextField getTxtTempo() {
		if (txtTempo == null) {
			txtTempo = new JTextField();
			txtTempo.setHorizontalAlignment(SwingConstants.CENTER);
			txtTempo.setFont(new Font("Lucida Grande", Font.BOLD, 50));
			txtTempo.setColumns(10);
			txtTempo.setEditable(false);
			ActionListener accion = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(n<30){
						String seg = String.valueOf(n);
						txtTempo.setText(seg);
						n=n+1;
					}else{
						t.stop();
						btnAceptarRespuesta.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Se te ha acabado el tiempo", "Fin de tiempo", JOptionPane.ERROR_MESSAGE);
						n=0;
						respuesta = false;
						dispose();
					}
				}
			};
			t = new Timer(1000, accion);
		}
		return txtTempo;
	}
	private JTextArea getTxtPregunta() {
		if (txtPregunta == null) {
			txtPregunta = new JTextArea();
			txtPregunta.setEditable(false);
			txtPregunta.setLineWrap(true);
		}
		return txtPregunta;
	}
	private JRadioButton getRdbtnRespuestaA(){
		if (rdbtnRespuestaA == null) {
			rdbtnRespuestaA = new JRadioButton();
		}
		return rdbtnRespuestaA;
	}
	private JRadioButton getRdbtnRespuestaB() {
		if (rdbtnRespuestaB == null) {
			rdbtnRespuestaB = new JRadioButton();
		}
		return rdbtnRespuestaB;
	}
	private JRadioButton getRdbtnRespuestaC() {
		if (rdbtnRespuestaC == null) {
			rdbtnRespuestaC = new JRadioButton();
		}
		return rdbtnRespuestaC;
	}
	private JRadioButton getRdbtnRespuestaD() {
		if (rdbtnRespuestaD == null) {
			rdbtnRespuestaD = new JRadioButton();
		}
		return rdbtnRespuestaD;
	}
	private JButton getBtnAceptarRespuesta() {
		if (btnAceptarRespuesta == null) {
			btnAceptarRespuesta = new JButton("Aceptar");
			btnAceptarRespuesta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					boolean result = false;
					
					if(grupoDeBotones.getSelection() == null){
						JOptionPane.showMessageDialog(null, "No has seleccionado ninguna opción", "Atención", JOptionPane.WARNING_MESSAGE);
					}else{
						if(rdbtnRespuestaA.isSelected()){
							result = cartaActual.esCorrecta("A");
						}else{
							if(rdbtnRespuestaB.isSelected()){
								result = cartaActual.esCorrecta("B");
							}else{
								if(rdbtnRespuestaC.isSelected()){
									result = cartaActual.esCorrecta("C");
								}else{
									if(rdbtnRespuestaD.isSelected()){
										result = cartaActual.esCorrecta("D");
									}
								}
							}
						}
						
						if(result){
							t.stop();
							JOptionPane.showMessageDialog(null, "¡¡Enhorabuena, has acertado!!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
							respuesta = true;
							dispose();
						}else{
							t.stop();
							JOptionPane.showMessageDialog(null, "Oooooh, has fallado...", "Resultado", JOptionPane.INFORMATION_MESSAGE);
							respuesta = false;
							dispose();
						}
					}
				}
			});
		}
		return btnAceptarRespuesta;
	}
	
	public boolean getRespuesta(){
		return respuesta;
	}

}
