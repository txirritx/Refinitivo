package parchis;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Parchis implements Observer {
	
	private ColorP turno;
	private JButton[] listaBotonesCasillas;
	private JLabel[] listaCasas;
	private Controlador controlador = null;
	
	private JFrame frame;
	private JPanel izqarriba;
	private JPanel medarriba;
	
	private JPanel izqmedio;
	
	private JPanel medmedio;
	private JLabel label_16;
	
	private JButton btnTirarDado;
	private JPanel dchmedio;
	private JPanel centro;
			
	private JPanel izqabajo;
	
	private JPanel medabajo;
	
	private JPanel dchabajo;
	
	private JButton metaAmarilla;
	private JButton metaRoja;
	private JButton metaAzul;
	private JButton metaVerde;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Parchis window = new Parchis();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Parchis() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(getControlador());
		this.listaBotonesCasillas = new JButton[100];
		this.listaCasas = new JLabel[16];
		turno = ColorP.AMARILLO;
		
		//anadir osbservadores a las casillas -------
			//casillas 1-68
		for (int i = 0; i < 68; i++) {
			Tablero.getMiTablero().anadirObservadorACasilla(i, this);
		}
		
			//pasillos
		ColorP c = ColorP.AMARILLO;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				ListaPasillos.getMiListaPasillos().anadirObservadorACasilla(c, j, this);
			}
			
			if(c == ColorP.AMARILLO){
				c = ColorP.AZUL;
				
			}else if(c == ColorP.AZUL){
				c = ColorP.ROJO;
				
			}else if(c == ColorP.ROJO){
				c = ColorP.VERDE;
				
			}else if(c == ColorP.VERDE){
				c = ColorP.AMARILLO;
			}
			
		}
		//-------------
		
		//anadir observadores Jugadores
		ListaJugadores.getMiListaJugadores().anadirObservadorAJugador(ColorP.AMARILLO, this);
		ListaJugadores.getMiListaJugadores().anadirObservadorAJugador(ColorP.VERDE, this);
		ListaJugadores.getMiListaJugadores().anadirObservadorAJugador(ColorP.ROJO, this);
		ListaJugadores.getMiListaJugadores().anadirObservadorAJugador(ColorP.AZUL, this);
		
		//
		
		
		
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 3, 0, 0));
		
		
		// CASA DEL COLOR AMARILLO --> 0,1,2,3
		
		izqarriba = new JPanel();
		izqarriba.setBackground(Color.YELLOW);
		frame.getContentPane().add(izqarriba);
		izqarriba.setLayout(new GridLayout(2, 2, 0, 0));
		
		
		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/AMARILLOG.png")));
		izqarriba.add(label);
		listaCasas[0] = label;
		
		
		JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/AMARILLOG.png")));
		izqarriba.add(label_2);
		listaCasas[1] = label_2;
		
		JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/AMARILLOG.png")));
		izqarriba.add(label_1);
		listaCasas[2] = label_1;
		
		JLabel label_15 = new JLabel();
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/AMARILLOG.png")));
		izqarriba.add(label_15);
		listaCasas[3] = label_15;
		
		
		//Pasillo AMARILLO
		//pAm1= 69, pAm2=70, pAm3=71, pAm4=72, pAm5=73, pAm6=74, pAm7=75
		
	
		medarriba = new JPanel();
		frame.getContentPane().add(medarriba);
		medarriba.setLayout(new GridLayout(8, 3, 0, 0));
		
		
		JButton boton1 = new JButton();
		boton1.addActionListener(getControlador());
		
		boton1.setEnabled(false);
		boton1.setBackground(Color.WHITE);
		boton1.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton1.getFont(), "1"));
		medarriba.add(boton1);
		listaBotonesCasillas[1] = boton1;
		
				
		JButton boton68BPAM = new JButton();
		boton68BPAM.addActionListener(getControlador());	
		boton68BPAM.setEnabled(false);
		boton68BPAM.setBackground(Color.LIGHT_GRAY);
		boton68BPAM.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton68BPAM.getFont(), "68"));
		medarriba.add(boton68BPAM);
		listaBotonesCasillas[68] = boton68BPAM;
						
		JButton boton67 = new JButton();
		boton67.addActionListener(getControlador());
		boton67.setEnabled(false);
		boton67.setBackground(Color.WHITE);
		boton67.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton67.getFont(), "67"));
		medarriba.add(boton67);
		listaBotonesCasillas[67] = boton67;
		
		JButton boton2 = new JButton();
		boton2.addActionListener(getControlador());
		boton2.setEnabled(false);
		boton2.setBackground(Color.WHITE);
		boton2.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton2.getFont(), "2"));		
		medarriba.add(boton2);
		listaBotonesCasillas[2] = boton2;
		
		JButton pAm1 = new JButton("");
		pAm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(69, 71);
			}
		});
		pAm1.setEnabled(false);
		pAm1.setBackground(Color.YELLOW);		
		medarriba.add(pAm1);
		listaBotonesCasillas[69] = pAm1;
		
		JButton boton66 = new JButton("");
		boton66.addActionListener(getControlador());
		boton66.setEnabled(false);
		boton66.setBackground(Color.WHITE);
		boton66.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton66.getFont(), "66"));
		medarriba.add(boton66);
		listaBotonesCasillas[66] = boton66;
		
		JButton boton3 = new JButton();
		boton3.addActionListener(getControlador());
		boton3.setEnabled(false);
		boton3.setBackground(Color.WHITE);
		boton3.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton3.getFont(), "3"));
		medarriba.add(boton3);
		listaBotonesCasillas[3] = boton3;
		
		JButton pAm2 = new JButton("");
		pAm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(70, 72);
			}
		});
		pAm2.setEnabled(false);
		pAm2.setBackground(Color.YELLOW);
		medarriba.add(pAm2);
		listaBotonesCasillas[70] = pAm2;
		
		JButton boton65 = new JButton("");
		boton65.addActionListener(getControlador());
		boton65.setEnabled(false);
		boton65.setBackground(Color.WHITE);
		boton65.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton65.getFont(), "65"));
		medarriba.add(boton65);
		listaBotonesCasillas[65] = boton65;
		
		JButton boton4 = new JButton();
		boton4.addActionListener(getControlador());
		boton4.setEnabled(false);
		boton4.setBackground(Color.WHITE);
		boton4.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton4.getFont(), "4"));
		medarriba.add(boton4);
		listaBotonesCasillas[4] = boton4;
		
		JButton pAm3 = new JButton("");
		pAm3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(71, 73);
			}
		});
		pAm3.setEnabled(false);
		pAm3.setBackground(Color.YELLOW);
		medarriba.add(pAm3);
		listaBotonesCasillas[71] = pAm3;
		
		JButton boton64 = new JButton("");
		boton64.addActionListener(getControlador());
		boton64.setEnabled(false);
		boton64.setBackground(Color.WHITE);
		boton64.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton64.getFont(), "64"));
		medarriba.add(boton64);
		listaBotonesCasillas[64] = boton64;
		
		JButton boton5BSAM = new JButton();
		boton5BSAM.addActionListener(getControlador());
		boton5BSAM.setEnabled(false);
		boton5BSAM.setBackground(Color.YELLOW);
		boton5BSAM.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton5BSAM.getFont(), "5"));
		medarriba.add(boton5BSAM);
		listaBotonesCasillas[5] = boton5BSAM;
		
		JButton pAm4 = new JButton("");
		pAm4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(72, 74);
			}
		});
		pAm4.setEnabled(false);
		pAm4.setBackground(Color.YELLOW);
		medarriba.add(pAm4);
		listaBotonesCasillas[72] = pAm4;
		
		JButton boton63B = new JButton("");
		boton63B.addActionListener(getControlador());
		boton63B.setEnabled(false);
		boton63B.setBackground(Color.LIGHT_GRAY);
		boton63B.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton63B.getFont(), "63"));
		medarriba.add(boton63B);
		listaBotonesCasillas[63] = boton63B;
		
		JButton boton6 = new JButton();
		boton6.addActionListener(getControlador());
		boton6.setEnabled(false);
		boton6.setBackground(Color.WHITE);
		boton6.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton6.getFont(), "6"));
		medarriba.add(boton6);
		listaBotonesCasillas[6] = boton6;
		
		JButton pAm5 = new JButton("");
		pAm5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(73, 75);
			}
		});
		pAm5.setEnabled(false);
		pAm5.setBackground(Color.YELLOW);
		medarriba.add(pAm5);
		listaBotonesCasillas[73] = pAm5;
		
		JButton boton62 = new JButton("");
		boton62.addActionListener(getControlador());
		boton62.setEnabled(false);
		boton62.setBackground(Color.WHITE);
		boton62.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton62.getFont(), "62"));
		medarriba.add(boton62);
		listaBotonesCasillas[62] = boton62;
		
		JButton boton7 = new JButton();
		boton7.addActionListener(getControlador());
		boton7.setEnabled(false);
		boton7.setBackground(Color.WHITE);
		boton7.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton7.getFont(), "7"));
		medarriba.add(boton7);
		listaBotonesCasillas[7] = boton7;
		
		JButton pAm6 = new JButton("");
		pAm6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(74, 76);
			}
		});
		pAm6.setEnabled(false);
		pAm6.setBackground(Color.YELLOW);
		medarriba.add(pAm6);
		listaBotonesCasillas[74] = pAm6;
		
		JButton boton61 = new JButton("");
		boton61.addActionListener(getControlador());
		boton61.setEnabled(false);
		boton61.setBackground(Color.WHITE);
		boton61.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton61.getFont(), "61"));
		medarriba.add(boton61);
		listaBotonesCasillas[61] = boton61;
		
		JButton boton8 = new JButton();
		boton8.addActionListener(getControlador());
		boton8.setEnabled(false);
		boton8.setBackground(Color.WHITE);
		boton8.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton8.getFont(), "8"));
		medarriba.add(boton8);
		listaBotonesCasillas[8] = boton8;
		
		JButton pAm7 = new JButton("");
		pAm7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(75, 77);
			}
		});
		pAm7.setEnabled(false);
		pAm7.setBackground(Color.YELLOW);
		medarriba.add(pAm7);
		listaBotonesCasillas[75] = pAm7;
		
		JButton boton60 = new JButton("");
		boton60.addActionListener(getControlador());
		boton60.setEnabled(false);
		boton60.setBackground(Color.WHITE);
		boton60.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton60.getFont(), "60"));
		medarriba.add(boton60);
		listaBotonesCasillas[60] = boton60;
		 

		
		// CASA DEL COLOR VERDE --> 4,5,6,7
		
		JPanel dchaarriba = new JPanel();
		dchaarriba.setBackground(new Color(50, 205, 50));
		frame.getContentPane().add(dchaarriba);
		dchaarriba.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/VERDEG.png")));
		dchaarriba.add(label_3);
		listaCasas[4] = label_3;
		
		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/VERDEG.png")));
		dchaarriba.add(label_4);
		listaCasas[5] = label_4;
		
		JLabel label_5 = new JLabel("");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/VERDEG.png")));
		dchaarriba.add(label_5);
		listaCasas[6] = label_5;
		
		JLabel label_6 = new JLabel("");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/VERDEG.png")));
		dchaarriba.add(label_6);
		listaCasas[7] = label_6;
				
		izqmedio = new JPanel();
		frame.getContentPane().add(izqmedio);
		izqmedio.setLayout(new GridLayout(3, 8, 0, 0));
		
		
		//Pasillo Azul 
		//pAz1= 76, pAz2=77, pAz3=78, pAz4=79, pAz5=80, pAz6=81, pAz7=82
		
		JButton boton16 = new JButton("");
		boton16.addActionListener(getControlador());
		boton16.setEnabled(false);
		boton16.setBackground(Color.WHITE);
		boton16.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton16.getFont(), "16"));
		izqmedio.add(boton16);
		listaBotonesCasillas[16] = boton16;
		
		JButton boton15 = new JButton("");
		boton15.addActionListener(getControlador());
		boton15.setEnabled(false);
		boton15.setBackground(Color.WHITE);
		boton15.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton15.getFont(), "15"));
		izqmedio.add(boton15);
		listaBotonesCasillas[15] = boton15;
		
		JButton boton14 = new JButton("");
		boton14.addActionListener(getControlador());
		boton14.setEnabled(false);
		boton14.setBackground(Color.WHITE);
		boton14.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton14.getFont(), "14"));
		izqmedio.add(boton14);
		listaBotonesCasillas[14] = boton14;
		
		JButton boton13 = new JButton("");
		boton13.addActionListener(getControlador());
		boton13.setEnabled(false);
		boton13.setBackground(Color.WHITE);
		boton13.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton13.getFont(), "13"));
		izqmedio.add(boton13);
		listaBotonesCasillas[13] = boton13;
		
		JButton boton12B = new JButton("");
		boton12B.addActionListener(getControlador());
		boton12B.setEnabled(false);
		boton12B.setBackground(Color.LIGHT_GRAY);
		boton12B.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton12B.getFont(), "12"));
		izqmedio.add(boton12B);
		listaBotonesCasillas[12] = boton12B;
		
		JButton boton11 = new JButton("");
		boton11.addActionListener(getControlador());
		boton11.setEnabled(false);
		boton11.setBackground(Color.WHITE);
		boton11.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton11.getFont(), "11"));
		izqmedio.add(boton11);
		listaBotonesCasillas[11] = boton11;
		
		JButton boton10 = new JButton("");
		boton10.addActionListener(getControlador());
		boton10.setEnabled(false);
		boton10.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton10.getFont(), "10"));
		boton10.setBackground(Color.WHITE);
		izqmedio.add(boton10);
		listaBotonesCasillas[10] = boton10;
		
		JButton boton9 = new JButton();
		boton9.addActionListener(getControlador());
		boton9.setEnabled(false);
		boton9.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton9.getFont(), "9"));
		boton9.setBackground(Color.WHITE);
		izqmedio.add(boton9);
		listaBotonesCasillas[9] = boton9;
		
		JButton boton17BPAZ = new JButton("");
		boton17BPAZ.addActionListener(getControlador());
		boton17BPAZ.setEnabled(false);
		boton17BPAZ.setBackground(Color.LIGHT_GRAY);
		boton17BPAZ.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton17BPAZ.getFont(), "17"));
		izqmedio.add(boton17BPAZ);
		listaBotonesCasillas[17] = boton17BPAZ;
		
		JButton pAz1 = new JButton("");
		pAz1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(76, 71);
			}
		});
		pAz1.setEnabled(false);
		pAz1.setBackground(Color.BLUE);
		izqmedio.add(pAz1);
		listaBotonesCasillas[76] = pAz1;
		
		JButton pAz2 = new JButton("");
		pAz2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(77, 72);
			}
		});
		pAz2.setEnabled(false);
		pAz2.setBackground(Color.BLUE);
		izqmedio.add(pAz2);
		listaBotonesCasillas[77] = pAz2;
		
		JButton pAz3 = new JButton("");
		pAz3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(78, 73);
			}
		});
		pAz3.setEnabled(false);
		pAz3.setBackground(Color.BLUE);
		izqmedio.add(pAz3);
		listaBotonesCasillas[78] = pAz3;
		
		JButton pAz4 = new JButton("");
		pAz4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(79, 74);
			}
		});
		pAz4.setEnabled(false);
		pAz4.setBackground(Color.BLUE);
		izqmedio.add(pAz4);
		listaBotonesCasillas[79] = pAz4;
		
		JButton pAz5 = new JButton("");
		pAz5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(80, 75);
			}
		});
		pAz5.setEnabled(false);
		pAz5.setBackground(Color.BLUE);
		izqmedio.add(pAz5);
		listaBotonesCasillas[80] = pAz5;
		
		JButton pAz6 = new JButton("");
		pAz6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(81, 76);
			}
		});
		pAz6.setEnabled(false);
		pAz6.setBackground(Color.BLUE);
		izqmedio.add(pAz6);
		listaBotonesCasillas[81] = pAz6;
		
		JButton pAz7 = new JButton("");
		pAz7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(82, 77);
			}
		});
		pAz7.setEnabled(false);
		pAz7.setBackground(Color.BLUE);
		izqmedio.add(pAz7);
		listaBotonesCasillas[82] = pAz7;
				
		JButton boton18 = new JButton("");
		boton18.addActionListener(getControlador());
		boton18.setEnabled(false);
		boton18.setBackground(Color.WHITE);
		boton18.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton18.getFont(), "18"));
		izqmedio.add(boton18);
		listaBotonesCasillas[18] = boton18;
			
		JButton boton19 = new JButton("");
		boton19.addActionListener(getControlador());
		boton19.setEnabled(false);
		boton19.setBackground(Color.WHITE);
		boton19.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton19.getFont(), "19"));
		izqmedio.add(boton19);
		listaBotonesCasillas[19] = boton19;
		
		JButton boton20 = new JButton("");
		boton20.addActionListener(getControlador());
		boton20.setEnabled(false);
		boton20.setBackground(Color.WHITE);
		boton20.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton20.getFont(), "20"));
		izqmedio.add(boton20);
		listaBotonesCasillas[20] = boton20;
		
		JButton boton21 = new JButton("");
		boton21.addActionListener(getControlador());
		boton21.setEnabled(false);
		boton21.setBackground(Color.WHITE);
		boton21.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton21.getFont(), "21"));
		izqmedio.add(boton21);
		listaBotonesCasillas[21] = boton21;
		
		JButton boton22BSAZ = new JButton("");
		boton22BSAZ.addActionListener(getControlador());
		boton22BSAZ.setForeground(new Color(255, 255, 255));
		boton22BSAZ.setEnabled(false);
		boton22BSAZ.setBackground(Color.BLUE);
		boton22BSAZ.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton22BSAZ.getFont(), "22"));
		izqmedio.add(boton22BSAZ);
		listaBotonesCasillas[22] = boton22BSAZ;
		
		JButton boton23 = new JButton("");
		boton23.addActionListener(getControlador());
		boton23.setEnabled(false);
		boton23.setBackground(Color.WHITE);
		boton23.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton23.getFont(), "23"));
		izqmedio.add(boton23);
		listaBotonesCasillas[23] = boton23;
		
		JButton boton24 = new JButton("");
		boton24.addActionListener(getControlador());
		boton24.setEnabled(false);
		boton24.setBackground(Color.WHITE);
		boton24.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton24.getFont(), "24"));
		izqmedio.add(boton24);
		listaBotonesCasillas[24] = boton24;
		
		JButton boton25 = new JButton("");
		boton25.addActionListener(getControlador());
		boton25.setEnabled(false);
		boton25.setBackground(Color.WHITE);
		boton25.setIcon(new RotatedTextIcon(RotatedTextIcon.CW,boton25.getFont(), "25"));
		izqmedio.add(boton25);
		listaBotonesCasillas[25] = boton25;


				
		
		medmedio = new JPanel();
		medmedio.setBackground(Color.WHITE);
		frame.getContentPane().add(medmedio);
		medmedio.setLayout(new BorderLayout(0, 0));
		
		centro = new JPanel();
		centro.setBackground(Color.WHITE);
		medmedio.add(centro, BorderLayout.CENTER);
		centro.setLayout(new GridLayout(2, 1, 0, 0));
		
		btnTirarDado = new JButton("LANZAR");
		centro.add(btnTirarDado);
		
		label_16 = new JLabel("");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBackground(Color.WHITE);
		centro.add(label_16);
		
		metaAmarilla = new JButton("");
		metaAmarilla.setEnabled(false);
		metaAmarilla.setForeground(new Color(0, 0, 0));
		metaAmarilla.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,metaAmarilla.getFont(), "0"));
		metaAmarilla.setBackground(Color.YELLOW);
		medmedio.add(metaAmarilla, BorderLayout.NORTH);
		
		metaRoja = new JButton("");
		metaRoja.setEnabled(false);
		metaRoja.setForeground(new Color(0, 0, 0));
		metaRoja.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,metaRoja.getFont(), "0"));
		metaRoja.setBackground(Color.RED);
		medmedio.add(metaRoja, BorderLayout.SOUTH);
		
		metaAzul = new JButton("");
		metaAzul.setEnabled(false);
		metaAzul.setForeground(new Color(255, 255, 255));
		metaAzul.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,metaAzul.getFont(), "0"));
		metaAzul.setBackground(Color.BLUE);
		medmedio.add(metaAzul, BorderLayout.WEST);
		
		metaVerde = new JButton("");
		metaVerde.setEnabled(false);
		metaVerde.setForeground(new Color(0, 0, 0));
		metaVerde.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,metaVerde.getFont(), "0"));
		metaVerde.setBackground(new Color(50, 205, 50));
		medmedio.add(metaVerde, BorderLayout.EAST);
		
		btnTirarDado.addActionListener(getControlador());
		
	
	
				
		dchmedio = new JPanel();
		frame.getContentPane().add(dchmedio);
		dchmedio.setLayout(new GridLayout(3, 8, 0, 0));
		
		
		//Pasillo VERDE
		//pVe1= 83, pVe2=84, pVe3=85, pVe4=86, pVe5=87, pVe6=88, pVe7=89
		
		JButton boton59 = new JButton("");
		boton59.addActionListener(getControlador());
		boton59.setEnabled(false);
		boton59.setBackground(Color.WHITE);
		boton59.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton59.getFont(), "59"));
		dchmedio.add(boton59);
		listaBotonesCasillas[59] = boton59;
		
		JButton boton58 = new JButton("");
		boton58.addActionListener(getControlador());
		boton58.setEnabled(false);
		boton58.setBackground(Color.WHITE);
		boton58.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton58.getFont(), "58"));
		dchmedio.add(boton58);
		listaBotonesCasillas[58] = boton58;
		
		JButton boton57 = new JButton("");
		boton57.addActionListener(getControlador());
		boton57.setEnabled(false);
		boton57.setBackground(Color.WHITE);
		boton57.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton57.getFont(), "57"));
		dchmedio.add(boton57);
		listaBotonesCasillas[57] = boton57;
		
		JButton boton56BSVE = new JButton("");
		boton56BSVE.addActionListener(getControlador());
		boton56BSVE.setEnabled(false);
		boton56BSVE.setBackground(new Color(50, 205, 50));
		boton56BSVE.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton56BSVE.getFont(), "56"));
		dchmedio.add(boton56BSVE);
		listaBotonesCasillas[56] = boton56BSVE;
		
		JButton boton55 = new JButton("");
		boton55.addActionListener(getControlador());
		boton55.setEnabled(false);
		boton55.setBackground(Color.WHITE);
		boton55.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton55.getFont(), "55"));
		dchmedio.add(boton55);
		listaBotonesCasillas[55] = boton55;
		
		JButton boton54 = new JButton("");
		boton54.addActionListener(getControlador());
		boton54.setEnabled(false);
		boton54.setBackground(Color.WHITE);
		boton54.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton54.getFont(), "54"));
		dchmedio.add(boton54);
		listaBotonesCasillas[54] = boton54;
		
		JButton boton53 = new JButton("");
		boton53.addActionListener(getControlador());
		boton53.setEnabled(false);
		boton53.setBackground(Color.WHITE);
		boton53.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton53.getFont(), "53"));
		dchmedio.add(boton53);
		listaBotonesCasillas[53] = boton53;
		
		JButton boton52 = new JButton("");
		boton52.addActionListener(getControlador());
		boton52.setEnabled(false);
		boton52.setBackground(Color.WHITE);
		boton52.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton52.getFont(), "52"));
		dchmedio.add(boton52);
		listaBotonesCasillas[52] = boton52;
		
		JButton pVe7 = new JButton("");
		pVe7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(89, 77);
			}
		});
		pVe7.setEnabled(false);
		pVe7.setBackground(new Color(50, 205, 50));
		dchmedio.add(pVe7);
		listaBotonesCasillas[89] = pVe7;
		
		JButton pVe6 = new JButton("");
		pVe6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(88, 76);
			}
		});
		pVe6.setEnabled(false);
		pVe6.setBackground(new Color(50, 205, 50));
		dchmedio.add(pVe6);
		listaBotonesCasillas[88] = pVe6;
		
		JButton pVe5 = new JButton("");
		pVe5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(87, 75);
			}
		});
		pVe5.setEnabled(false);
		pVe5.setBackground(new Color(50, 205, 50));
		dchmedio.add(pVe5);
		listaBotonesCasillas[87] = pVe5;
		
		JButton pVe4 = new JButton("");
		pVe4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(86, 74);
			}
		});
		pVe4.setEnabled(false);
		pVe4.setBackground(new Color(50, 205, 50));
		dchmedio.add(pVe4);
		listaBotonesCasillas[86] = pVe4;
		
		JButton pVe3 = new JButton("");
		pVe3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(85, 73);
			}
		});
		pVe3.setEnabled(false);
		pVe3.setBackground(new Color(50, 205, 50));
		dchmedio.add(pVe3);
		listaBotonesCasillas[85] = pVe3;
		
		JButton pVe2 = new JButton("");
		pVe2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(84, 72);
			}
		});
		pVe2.setEnabled(false);
		pVe2.setBackground(new Color(50, 205, 50));
		dchmedio.add(pVe2);
		listaBotonesCasillas[84] = pVe2;
		
		JButton pVe1 = new JButton("");
		pVe1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(83, 71);
			}
		});
		pVe1.setEnabled(false);
		pVe1.setBackground(new Color(50, 205, 50));
		dchmedio.add(pVe1);
		listaBotonesCasillas[83] = pVe1;
		
		JButton boton51BPVE = new JButton("");
		boton51BPVE.addActionListener(getControlador());
		boton51BPVE.setEnabled(false);
		boton51BPVE.setBackground(Color.LIGHT_GRAY);
		boton51BPVE.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton51BPVE.getFont(), "51"));
		dchmedio.add(boton51BPVE);
		listaBotonesCasillas[51] = boton51BPVE;
		
		JButton boton43 = new JButton("");
		boton43.addActionListener(getControlador());
		boton43.setEnabled(false);
		boton43.setBackground(Color.WHITE);
		boton43.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton43.getFont(), "43"));
		dchmedio.add(boton43);
		listaBotonesCasillas[43] = boton43;
		
		JButton boton44 = new JButton("");
		boton44.addActionListener(getControlador());
		boton44.setEnabled(false);
		boton44.setBackground(Color.WHITE);
		boton44.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton44.getFont(), "44"));
		dchmedio.add(boton44);
		listaBotonesCasillas[44] = boton44;
		
		JButton boton45 = new JButton("");
		boton45.addActionListener(getControlador());
		boton45.setEnabled(false);
		boton45.setBackground(Color.WHITE);
		boton45.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton45.getFont(), "45"));
		dchmedio.add(boton45);
		listaBotonesCasillas[45] = boton45;
		
		JButton boton46B = new JButton("");
		boton46B.addActionListener(getControlador());
		boton46B.setEnabled(false);
		boton46B.setBackground(Color.LIGHT_GRAY);
		boton46B.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton46B.getFont(), "46"));
		dchmedio.add(boton46B);
		listaBotonesCasillas[46] = boton46B;
		
		JButton boton47 = new JButton("");
		boton47.addActionListener(getControlador());
		boton47.setEnabled(false);
		boton47.setBackground(Color.WHITE);
		boton47.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton47.getFont(), "47"));
		dchmedio.add(boton47);
		listaBotonesCasillas[47] = boton47;
		
		JButton boton48 = new JButton("");
		boton48.addActionListener(getControlador());
		boton48.setEnabled(false);
		boton48.setBackground(Color.WHITE);
		boton48.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton48.getFont(), "48"));
		dchmedio.add(boton48);
		listaBotonesCasillas[48] = boton48;
		
		JButton boton49 = new JButton("");
		boton49.addActionListener(getControlador());
		boton49.setEnabled(false);
		boton49.setBackground(Color.WHITE);
		boton49.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton49.getFont(), "49"));
		dchmedio.add(boton49);
		listaBotonesCasillas[49] = boton49;
		
		JButton boton50 = new JButton("");
		boton50.addActionListener(getControlador());
		boton50.setEnabled(false);
		boton50.setBackground(Color.WHITE);
		boton50.setIcon(new RotatedTextIcon(RotatedTextIcon.CCW,boton50.getFont(), "50"));
		dchmedio.add(boton50);
		listaBotonesCasillas[50] = boton50;



		//CASA DEL COLOR AZUL  --> 8,9,10,11
		izqabajo = new JPanel();
		izqabajo.setBackground(Color.BLUE);
		frame.getContentPane().add(izqabajo);
		izqabajo.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel label_7 = new JLabel("");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/AZULG.png")));		
		izqabajo.add(label_7);
		listaCasas[8] = label_7;
		
		JLabel label_8 = new JLabel("");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/AZULG.png")));
		izqabajo.add(label_8);
		listaCasas[9] = label_8;
		
		JLabel label_9 = new JLabel("");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/AZULG.png")));
		izqabajo.add(label_9);
		listaCasas[10] = label_9;
		
		JLabel label_10 = new JLabel("");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/AZULG.png")));
		izqabajo.add(label_10);
		listaCasas[11] = label_10;
		
		
			
		medabajo = new JPanel();
		frame.getContentPane().add(medabajo);
		medabajo.setLayout(new GridLayout(8, 3, 0, 0));
		
		
		//PASILLO ROJO
		// pRo1 = 90; pRo2 = 91; pRo3 = 92; pRo4 = 93; pRo5 = 94; pRo6 = 95; pRo7 = 96
		
		JButton boton26 = new JButton("");
		boton26.addActionListener(getControlador());
		boton26.setEnabled(false);
		boton26.setBackground(Color.WHITE);
		boton26.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton26.getFont(), "26"));
		medabajo.add(boton26);
		listaBotonesCasillas[26] = boton26; 
		
		JButton pRo7 = new JButton("");
		pRo7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControlador().reaccionBotones(96, 77);
			}
		});
		pRo7.setEnabled(false);
		pRo7.setBackground(Color.RED);
		medabajo.add(pRo7);
		listaBotonesCasillas[96] = pRo7;
		
		JButton boton42 = new JButton("");
		boton42.addActionListener(getControlador());
		boton42.setEnabled(false);
		boton42.setBackground(Color.WHITE);
		boton42.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton42.getFont(), "42"));
		medabajo.add(boton42);
		listaBotonesCasillas[42] = boton42;
		
		JButton boton27 = new JButton("");
		boton27.addActionListener(getControlador());
		boton27.setEnabled(false);
		boton27.setBackground(Color.WHITE);
		boton27.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton27.getFont(), "27"));
		medabajo.add(boton27);
		listaBotonesCasillas[27] = boton27;
		
		JButton pRo6 = new JButton("");
		pRo6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(95, 76);
			}
		});
		pRo6.setEnabled(false);
		pRo6.setBackground(Color.RED);
		medabajo.add(pRo6);
		listaBotonesCasillas[95] = pRo6;
		
		JButton boton41 = new JButton("");
		boton41.addActionListener(getControlador());
		boton41.setEnabled(false);
		boton41.setBackground(Color.WHITE);
		boton41.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton41.getFont(), "41"));
		medabajo.add(boton41);
		listaBotonesCasillas[41] = boton41;
		
		JButton boton28 = new JButton("");
		boton28.addActionListener(getControlador());
		boton28.setEnabled(false);
		boton28.setBackground(Color.WHITE);
		boton28.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton28.getFont(), "28"));
		medabajo.add(boton28);
		listaBotonesCasillas[28] = boton28;
		
		JButton pRo5 = new JButton("");
		pRo5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(94, 75);
			}
		});
		pRo5.setEnabled(false);
		pRo5.setBackground(Color.RED);
		medabajo.add(pRo5);
		listaBotonesCasillas[94] = pRo5;
		
		JButton boton40 = new JButton("");
		boton40.addActionListener(getControlador());
		boton40.setEnabled(false);
		boton40.setBackground(Color.WHITE);
		boton40.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton40.getFont(), "40"));	
		medabajo.add(boton40);
		listaBotonesCasillas[40] = boton40;
		
		JButton boton29B = new JButton("");
		boton29B.addActionListener(getControlador());
		boton29B.setEnabled(false);
		boton29B.setBackground(Color.LIGHT_GRAY);
		boton29B.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton29B.getFont(), "29"));
		medabajo.add(boton29B);
		listaBotonesCasillas[29] = boton29B;
		
		JButton pRo4 = new JButton("");
		pRo4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(93, 74);
			}
		});
		pRo4.setEnabled(false);
		pRo4.setBackground(Color.RED);
		medabajo.add(pRo4);
		listaBotonesCasillas[93] = pRo4;
		
		JButton boton39BSRO = new JButton("");
		boton39BSRO.addActionListener(getControlador());
		boton39BSRO.setEnabled(false);
		boton39BSRO.setBackground(Color.RED);
		boton39BSRO.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton39BSRO.getFont(), "39"));
		medabajo.add(boton39BSRO);
		listaBotonesCasillas[39] = boton39BSRO;
		
		JButton boton30 = new JButton("");
		boton30.addActionListener(getControlador());
		boton30.setEnabled(false);
		boton30.setBackground(Color.WHITE);
		boton30.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton30.getFont(), "30"));
		medabajo.add(boton30);
		listaBotonesCasillas[30] = boton30;
		
		JButton pRo3 = new JButton("");
		pRo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(92, 73);
			}
		});
		pRo3.setEnabled(false);
		pRo3.setBackground(Color.RED);
		medabajo.add(pRo3);
		listaBotonesCasillas[92] = pRo3;
		
		JButton boton38 = new JButton("");
		boton38.addActionListener(getControlador());
		boton38.setEnabled(false);
		boton38.setBackground(Color.WHITE);
		boton38.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton38.getFont(), "38"));
		medabajo.add(boton38);
		listaBotonesCasillas[38] = boton38;
		
		JButton boton31 = new JButton("");
		boton31.addActionListener(getControlador());
		boton31.setEnabled(false);
		boton31.setBackground(Color.WHITE);
		boton31.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton31.getFont(), "31"));
		medabajo.add(boton31);
		listaBotonesCasillas[31] = boton31;
		
		JButton pRo2 = new JButton("");
		pRo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(91, 72);
			}
		});
		pRo2.setEnabled(false);
		pRo2.setBackground(Color.RED);
		medabajo.add(pRo2);
		listaBotonesCasillas[91] = pRo2;
		
		JButton boton37 = new JButton("");
		boton37.addActionListener(getControlador());
		boton37.setEnabled(false);
		boton37.setBackground(Color.WHITE);
		boton37.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton37.getFont(), "37"));
		medabajo.add(boton37);
		listaBotonesCasillas[37] = boton37;
		
		JButton boton32 = new JButton("");
		boton32.addActionListener(getControlador());
		boton32.setEnabled(false);
		boton32.setBackground(Color.WHITE);
		boton32.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton32.getFont(), "32"));
		medabajo.add(boton32);
		listaBotonesCasillas[32] = boton32;
		
		JButton pRo1 = new JButton("");
		pRo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControlador().reaccionBotones(90, 71);
			}
		});
		pRo1.setEnabled(false);
		pRo1.setBackground(Color.RED);
		medabajo.add(pRo1);
		listaBotonesCasillas[90] = pRo1;
		
		JButton boton36 = new JButton("");
		boton36.addActionListener(getControlador());
		boton36.setEnabled(false);
		boton36.setBackground(Color.WHITE);
		boton36.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton36.getFont(), "36"));
		medabajo.add(boton36);
		listaBotonesCasillas[36] = boton36;
		
		JButton boton33 = new JButton("");
		boton33.addActionListener(getControlador());
		boton33.setEnabled(false);
		boton33.setBackground(Color.WHITE);
		boton33.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton33.getFont(), "33"));
		medabajo.add(boton33);
		listaBotonesCasillas[33] = boton33;
		
		JButton boton34BPRO = new JButton("");
		boton34BPRO.addActionListener(getControlador());
		boton34BPRO.setEnabled(false);
		boton34BPRO.setBackground(Color.LIGHT_GRAY);
		boton34BPRO.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton34BPRO.getFont(), "34"));
		medabajo.add(boton34BPRO);
		listaBotonesCasillas[34] = boton34BPRO;
		
		JButton boton35 = new JButton("");
		boton35.addActionListener(getControlador());
		boton35.setEnabled(false);
		boton35.setBackground(Color.WHITE);boton35.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,boton35.getFont(), "35"));
		medabajo.add(boton35);
		listaBotonesCasillas[35] = boton35;

	
		
		//CASA DEL COLOR ROJO   ---> 12,13,14,15
		
		dchabajo = new JPanel();
		dchabajo.setBackground(Color.RED);
		frame.getContentPane().add(dchabajo);
		dchabajo.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel label_11 = new JLabel("");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/ROJOG.png")));
		dchabajo.add(label_11);
		listaCasas[12] = label_11;
		
		JLabel label_12 = new JLabel("");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/ROJOG.png")));
		dchabajo.add(label_12);
		listaCasas[13] = label_12;
		
		JLabel label_13 = new JLabel("");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/ROJOG.png")));
		dchabajo.add(label_13);
		listaCasas[14] = label_13;
		
		JLabel label_14 = new JLabel("");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/ROJOG.png")));
		dchabajo.add(label_14);
		listaCasas[15] = label_14;
		
		for (int i = 1; i < 69; i++) {
			this.listaBotonesCasillas[i].setName(String.valueOf(i));
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		Casilla cas;
		
		OrdenObservable orden = (OrdenObservable) arg1;	
			
			if (arg0 instanceof CasillaPasillo) {
				//pasillos
				cas = (CasillaPasillo) arg0;
				int ind = getControlador().cambiarNumCasABoton(cas.getNumero());
				if(orden.getAccion().equalsIgnoreCase("introducir ficha")){
					if(!cas.hayBarrera()){
						this.listaBotonesCasillas[ind].setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/"+orden.getColor() +"P.png")));
					}else{
						this.listaBotonesCasillas[ind].setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/"+orden.getColor()+orden.getColor() +".png")));
					}
				}else{
					if(cas.numeroFichas() == 0){
						this.listaBotonesCasillas[ind].setIcon(null);

					}else{
						this.listaBotonesCasillas[ind].setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/"+orden.getColor() +"P.png")));
					}
				}
					
					
			}else if (arg0 instanceof Casilla) {
				cas = (Casilla) arg0;
				
				if(orden.getAccion().equalsIgnoreCase("introducir ficha")){
					//introducir ficha en una casilla
					if(cas.numeroFichas() == 1){
						this.listaBotonesCasillas[cas.getNumero()].setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/"+orden.getColor() +"P.png")));
					
					}else{
						if(Tablero.getMiTablero().hayBarrera(cas.getNumero())){
							if (cas.getNumero() >= 9 && cas.getNumero() <= 25 || cas.getNumero() >= 43 && cas.getNumero() <= 59) {
								this.listaBotonesCasillas[cas.getNumero()].setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/"+orden.getColor()+orden.getColor() +"VERT.png")));
							}
							else{
								this.listaBotonesCasillas[cas.getNumero()].setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/"+orden.getColor()+orden.getColor() +".png")));
							}
						}
						else{
						//hay dos fichas diferentes en un seguro.
							ColorP col1 = cas.fichas.getFirst().getCol();
							ColorP col2 = cas.fichas.getLast().getCol();
							getControlador().visualizarFichasDiferentesEnCasilla(cas.getNumero(), col1, col2);
							
						}	
					}
				
				}else{
					//quitar ficha de una casilla
					if(orden.getAccion().equalsIgnoreCase("quitar ficha")){
						
						if(cas.numeroFichas() == 0){
							this.listaBotonesCasillas[cas.getNumero()].setIcon(IconosDelTablero.getMiIconosDelTablero().getIcon(cas.getNumero()));
						}else{
							if(cas.numeroFichas() == 1){
								ColorP col = cas.fichas.getFirst().getCol();
								this.listaBotonesCasillas[cas.getNumero()].setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Fichas/"+ col +"P.png")));
							}
						}
					}else if (orden.getAccion().equalsIgnoreCase("ficha comida")) {
						JOptionPane.showMessageDialog(null,"Jugador " + turno+ ": mueve veinte por comer ficha", "MOVER 20", JOptionPane.INFORMATION_MESSAGE);
						getControlador().enviarFichaACasa(orden);
						label_16.setName("20");
						this.listaBotonesCasillas[cas.getNumero()].setEnabled(true);
					}
					
				}
				
			}else if (arg0 instanceof Jugador) {
				
				if(orden.getAccion().equalsIgnoreCase("sacar ficha de casa")){
					getControlador().sacarFichaDeCasa(orden);
					
				}else if (orden.getAccion().equalsIgnoreCase("anadir ficha meta")) {
					getControlador().anadirFichaMeta(orden);
					label_16.setName("10");
				}
		}
	}
	
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener{

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton b = (JButton) e.getSource();
			if(b == btnTirarDado){
				tirarDado();
			}else{
				int ind = Integer.parseInt(b.getName());
				reaccionBotones(ind,ind);
			}
		}
		
		
		private void tirarDado(){
			int tiradaDado = Dado.getMiDado().realizarTirada();
			String loc = "/Imagenes/Dados/dado"+String.valueOf(tiradaDado)+".JPG";
			label_16.setIcon(new ImageIcon(this.getClass().getResource(loc)));
			label_16.setName(String.valueOf(tiradaDado));
			btnTirarDado.setEnabled(false);
			
			//si se saca 5 y se puede sacar ficha de casa, pregunta al usuario.
			if(tiradaDado == 5 && ListaJugadores.getMiListaJugadores().quedanFichasEnCasa(turno)){
				int respuesta = JOptionPane.showConfirmDialog(null,"Jugador " + turno + ": Desea sacar una ficha de casa?","SACAR FICHA DE CASA",JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_OPTION){
					
					try {
						ListaJugadores.getMiListaJugadores().sacarFichaDeCasa(turno);
						cambiarTurno(tiradaDado);
						
					} catch (NoEsPosibleSacarFichaDeCasa e) {
						activarDesactivarBotonesJugador(true,turno);
						JOptionPane.showMessageDialog(null,"No ha sido posible sacar ficha de casa", "RESULTADO", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}else{
					activarDesactivarBotonesJugador(true,turno);
					if(todosBotonesDeFichasDesactivados(turno)){
						cambiarTurno(tiradaDado);
					}
				}	
			
			}else{
				if(!ListaJugadores.getMiListaJugadores().todasFichasEnCasa(turno)){
					activarDesactivarBotonesJugador(true,turno);
					
					if(todosBotonesDeFichasDesactivados(turno)){
						cambiarTurno(tiradaDado);
					}
					
				}else{
					cambiarTurno(tiradaDado);
				}
				
			}
			
			
		}

		private void activarDado(){
			btnTirarDado.setEnabled(true);
		}

		private void activarDesactivarBotonesPasillos(int cas,ColorP color, boolean activar){
			//pAm1= 69, pAm2=70, pAm3=71, pAm4=72, pAm5=73, pAm6=74, pAm7=75
			if(color == ColorP.AMARILLO){
				switch (cas) {
				case 71:
					listaBotonesCasillas[69].setEnabled(activar);
					break;
		
				case 72:
					listaBotonesCasillas[70].setEnabled(activar);
					break;
				
				case 73:
					listaBotonesCasillas[71].setEnabled(activar);
					break;
		
				case 74:
					listaBotonesCasillas[72].setEnabled(activar);
					break;
					
				case 75:
					listaBotonesCasillas[73].setEnabled(activar);
					break;
		
				case 76:
					listaBotonesCasillas[74].setEnabled(activar);
					break;
					
				case 77:
					listaBotonesCasillas[75].setEnabled(activar);
					break;
				}
				
				
			}else if(color == ColorP.AZUL){
				
				//pAz1=76 , pAz2=77, pAz3=78, pAz4=79, pAz5=80, pAz6=81, pAz7=82
					switch (cas) {
					case 71:
						listaBotonesCasillas[76].setEnabled(activar);
						break;
		
					case 72:
						listaBotonesCasillas[77].setEnabled(activar);
						break;
					
					case 73:
						listaBotonesCasillas[78].setEnabled(activar);
						break;
		
					case 74:
						listaBotonesCasillas[79].setEnabled(activar);
						break;
						
					case 75:
						listaBotonesCasillas[80].setEnabled(activar);
						break;
		
					case 76:
						listaBotonesCasillas[81].setEnabled(activar);
						break;
						
					case 77:
						listaBotonesCasillas[82].setEnabled(activar);
						break;
					}
				
				
			}else if(color == ColorP.ROJO){
				//pRo1 = 90; pRo2 = 91; pRo3 = 92; pRo4 = 93; pRo5 = 94; pRo6 = 95; pRo7 = 96
				switch (cas) {
				case 71:
					listaBotonesCasillas[90].setEnabled(activar);
					break;
		
				case 72:
					listaBotonesCasillas[91].setEnabled(activar);
					break;
				
				case 73:
					listaBotonesCasillas[92].setEnabled(activar);
					break;
		
				case 74:
					listaBotonesCasillas[93].setEnabled(activar);
					break;
					
				case 75:
					listaBotonesCasillas[94].setEnabled(activar);
					break;
		
				case 76:
					listaBotonesCasillas[95].setEnabled(activar);
					break;
					
				case 77:
					listaBotonesCasillas[96].setEnabled(activar);
					break;
				}
			
				
				
			}else if(color == ColorP.VERDE){
				//pVe1= 83, pVe2=84, pVe3=85, pVe4=86, pVe5=87, pVe6=88, pVe7=89
				switch (cas) {
				case 71:
					listaBotonesCasillas[83].setEnabled(activar);
					break;
		
				case 72:
					listaBotonesCasillas[84].setEnabled(activar);
					break;
				
				case 73:
					listaBotonesCasillas[85].setEnabled(activar);
					break;
		
				case 74:
					listaBotonesCasillas[86].setEnabled(activar);
					break;
					
				case 75:
					listaBotonesCasillas[87].setEnabled(activar);
					break;
		
				case 76:
					listaBotonesCasillas[88].setEnabled(activar);
					break;
					
				case 77:
					listaBotonesCasillas[89].setEnabled(activar);
					break;
				}
				
			}
			
		}

		private void activarDesactivarBotonesJugador(boolean activar ,ColorP color){
			ArrayList<Integer> listaPos = ListaJugadores.getMiListaJugadores().listaPosicionesFichas(color);
			for (int i = 0; i < 4; i++) {
				int cas = listaPos.get(i);
				if(cas != -1 && cas != 79){
					if(cas < 69){
						listaBotonesCasillas[cas].setEnabled(activar);
					}else{
						activarDesactivarBotonesPasillos(cas,color, activar);
						
					}
					
				}	
			}	
		}

		/**
		 * Actualiza la label de la meta correspondiente al color que orden indica , y avanza 10 casillas con la ficha de 
		 * eleccion del jugador
		 * @param orden 
		 */
		private void anadirFichaMeta(OrdenObservable orden){
			label_16.setName("10");
			if(orden.getColor() == ColorP.AMARILLO){
				metaAmarilla.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,metaAmarilla.getFont(), String.valueOf(ListaJugadores.getMiListaJugadores().fichasEnMeta(ColorP.AMARILLO))));
				
			}else if(orden.getColor() == ColorP.AZUL){
				metaAzul.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,metaAzul.getFont(), String.valueOf(ListaJugadores.getMiListaJugadores().fichasEnMeta(ColorP.AZUL))));
			
			}else if(orden.getColor() == ColorP.ROJO){
				metaRoja.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,metaRoja.getFont(), String.valueOf(ListaJugadores.getMiListaJugadores().fichasEnMeta(ColorP.ROJO))));
			
			}else if(orden.getColor() == ColorP.VERDE){
				metaVerde.setIcon(new RotatedTextIcon(RotatedTextIcon.NONE,metaVerde.getFont(), String.valueOf(ListaJugadores.getMiListaJugadores().fichasEnMeta(ColorP.VERDE))));
			}
		
			JOptionPane.showMessageDialog(null,"Jugador "+ turno+": mueve diez por llegar a la meta", "MOVER 10", JOptionPane.INFORMATION_MESSAGE);
			this.activarDesactivarBotonesJugador(true, turno);
		}

		/**
		 * Este metodo cambia el numero de casilla en el pasillo (1,2,3..7) por el indice 
		 * del boton en esta clase
		 * @param posACambiar
		 * @return
		 */
		private int cambiarNumCasABoton(int posACambiar){
			int ind = 0;
			if(turno == ColorP.AMARILLO){
				//pAm1= 69, pAm2=70, pAm3=71, pAm4=72, pAm5=73, pAm6=74, pAm7=75
				if(posACambiar > 8){
					ind = posACambiar -70 + 68;
				}else{ind = posACambiar + 68;}
				
				
			}else if(turno == ColorP.AZUL){
				//pAz1=76 , pAz2=77, pAz3=78, pAz4=79, pAz5=80, pAz6=81, pAz7=82
				if(posACambiar > 8){
					ind = posACambiar -70 + 75;
				}else{ind = posACambiar + 75;}
						
			}else if(turno == ColorP.ROJO){
				// pRo1 = 90; pRo2 = 91; pRo3 = 92; pRo4 = 93; pRo5 = 94; pRo6 = 95; pRo7 = 96
				if(posACambiar > 8){
					ind = posACambiar -70 + 89;
				}else{ind = posACambiar + 89;}
				
			}else if(turno == ColorP.VERDE){
				//pVe1= 83, pVe2=84, pVe3=85, pVe4=86, pVe5=87, pVe6=88, pVe7=89
				if(posACambiar > 8){
					ind = posACambiar -70 + 82;
				}else{ind = posACambiar + 82;}
				
			}
			
			return ind;
		}

		private void cambiarTurno(int ultTiradaDado){
			if(ultTiradaDado != 6){
				if(turno == ColorP.AMARILLO){
					turno = ColorP.AZUL;
					
				}else if(turno == ColorP.AZUL){
					turno = ColorP.ROJO;
					
				}else if(turno == ColorP.ROJO){
					turno = ColorP.VERDE;
					
				}else if(turno == ColorP.VERDE){
					turno = ColorP.AMARILLO;
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		activarDado();
			
		}

		/**
		 * Pone una imagen de ficha m‡s en la casa del color que orden indique.
		 * @param orden indique.
		 */
		private void enviarFichaACasa(OrdenObservable orden){
			boolean cambiado = false;
			int i = 0;
			
			if(orden.getColor() == ColorP.AMARILLO){
				while(!cambiado){
					if(!listaCasas[i].isVisible()){
						listaCasas[i].setVisible(true);
						cambiado = true;
					}
					i++;
					
				}
				
				
			}else if(orden.getColor() == ColorP.AZUL){
				//CASA DEL COLOR AZULLL  --> 8,9,10,11
				i = 8;
				while(!cambiado){
					if(!listaCasas[i].isVisible()){
						listaCasas[i].setVisible(true);
						cambiado = true;
					}
					i++;
					
				}
			
			}else if(orden.getColor() == ColorP.ROJO){
				//CASA DEL COLOR ROJO   ---> 12,13,14,15
				i = 12;
				while(!cambiado){
					if(!listaCasas[i].isVisible()){
						listaCasas[i].setVisible(true);
						cambiado = true;
					}
					i++;
					
				}
			
			}else if(orden.getColor() == ColorP.VERDE){
				// CASA DEL COLOR VERDE --> 4,5,6,7
				i = 4;
				while(!cambiado){
					if(!listaCasas[i].isVisible()){
						listaCasas[i].setVisible(true);
						cambiado = true;
					}
					i++;
					
				}
			}
		}

		/**
		 * Quita una imagen de ficha en la casa del color que orden indique
		 * @param orden 
		 */
		private void sacarFichaDeCasa(OrdenObservable orden){
		
			boolean cambiado = false;
			int i = 0;
			
			if(orden.getColor() == ColorP.AMARILLO){
				// CASA DEL COLOR AMARILLO --> 0,1,2,3
				while(!cambiado){
					if(listaCasas[i].isVisible()){
						listaCasas[i].setVisible(false);
						cambiado = true;
					}
					i++;
					
				}
			
			}else if(orden.getColor() == ColorP.AZUL){
				//CASA DEL COLOR AZULLL  --> 8,9,10,11
				i = 8;
				while(!cambiado){
					if(listaCasas[i].isVisible()){
						listaCasas[i].setVisible(false);
						cambiado = true;
					}
					i++;
					
				}
				
			
			}else if(orden.getColor() == ColorP.ROJO){
				//CASA DEL COLOR ROJO   ---> 12,13,14,15
				i = 12;
				while(!cambiado){
					if(listaCasas[i].isVisible()){
						listaCasas[i].setVisible(false);
						cambiado = true;
					}
					i++;
				}						
				
				
			
			}else if(orden.getColor() == ColorP.VERDE){
				// CASA DEL COLOR VERDE --> 4,5,6,7
				i = 4;
				while(!cambiado){
					if(listaCasas[i].isVisible()){
						listaCasas[i].setVisible(false);
						cambiado = true;
					}
					i++;
					
				}
				
			}
			
			
		}

		private void visualizarFichasDiferentesEnCasilla(int numCas,ColorP col1,ColorP col2){
			boolean vertical = numCas >= 9 && numCas <= 25 || numCas >= 43 && numCas <= 59;
			String icono = null;
		
			
			if((col1 == ColorP.AMARILLO && col2 == ColorP.AZUL) || (col2 == ColorP.AMARILLO && col1 == ColorP.AZUL)){
				icono = "/Imagenes/Fichas/"+ColorP.AMARILLO+ColorP.AZUL+".png";
				if(vertical){
					icono = "/Imagenes/Fichas/"+ColorP.AMARILLO+ColorP.AZUL+"VERT.png";
				}
				listaBotonesCasillas[numCas].setIcon(new ImageIcon(this.getClass().getResource(icono)));
				
			}else if ((col1 == ColorP.AMARILLO && col2 == ColorP.ROJO) || (col2 == ColorP.AMARILLO && col1 == ColorP.ROJO)) {
				icono = "/Imagenes/Fichas/"+ColorP.AMARILLO+ColorP.ROJO+".png";
				if(vertical){
					icono = "/Imagenes/Fichas/"+ColorP.AMARILLO+ColorP.ROJO+"VERT.png";
				}
				listaBotonesCasillas[numCas].setIcon(new ImageIcon(this.getClass().getResource(icono)));
				
			}else if ((col1 == ColorP.AMARILLO && col2 == ColorP.VERDE) || (col2 == ColorP.AMARILLO && col1 == ColorP.VERDE)) {
				icono = "/Imagenes/Fichas/"+ColorP.AMARILLO+ColorP.VERDE+".png";
				if(vertical){
					icono = "/Imagenes/Fichas/"+ColorP.AMARILLO+ColorP.VERDE+"VERT.png";
				}
				listaBotonesCasillas[numCas].setIcon(new ImageIcon(this.getClass().getResource(icono)));
				
			}else if ((col1 == ColorP.AZUL && col2 == ColorP.ROJO) || (col1 == ColorP.ROJO && col2 == ColorP.AZUL)) {
				icono = "/Imagenes/Fichas/"+ColorP.AZUL+ColorP.ROJO+".png";
				if(vertical){
					icono = "/Imagenes/Fichas/"+ColorP.AZUL+ColorP.ROJO+"VERT.png";
				}
				listaBotonesCasillas[numCas].setIcon(new ImageIcon(this.getClass().getResource(icono)));
				
			}else if ((col1 == ColorP.AZUL && col2 == ColorP.VERDE) || (col1 == ColorP.VERDE && col2 == ColorP.AZUL)) {
				icono = "/Imagenes/Fichas/"+ColorP.AZUL+ColorP.VERDE+".png";
				if(vertical){
					icono = "/Imagenes/Fichas/"+ColorP.AZUL+ColorP.VERDE+"VERT.png";
				}
				listaBotonesCasillas[numCas].setIcon(new ImageIcon(this.getClass().getResource(icono)));
				
			}else if ((col1 == ColorP.ROJO && col2 == ColorP.VERDE) || (col1 == ColorP.VERDE && col2 == ColorP.ROJO)) {
				icono = "/Imagenes/Fichas/"+ColorP.ROJO+ColorP.VERDE+".png";
				if(vertical){
					icono = "/Imagenes/Fichas/"+ColorP.ROJO+ColorP.VERDE+"VERT.png";
				}
				listaBotonesCasillas[numCas].setIcon(new ImageIcon(this.getClass().getResource(icono)));
				
			}
			
			
		}

		private boolean todosBotonesDeFichasDesactivados(ColorP color){
			ArrayList<Integer> listaPos = ListaJugadores.getMiListaJugadores().listaPosicionesFichas(color);
			boolean desactivados = true;
			int i = 0;
			while(desactivados && i < listaPos.size()){
				int pos = listaPos.get(i);
				if(pos > 68 && pos != 79){
					pos = cambiarNumCasABoton(pos);
				}
				
				if(pos != -1 && pos != 79){
					if(listaBotonesCasillas[pos].isEnabled()){
						desactivados = false;
					}
				}
				i++;
			}
			return desactivados;
		}

		private void reaccionBotones(int numBoton,int numCasilla){
			listaBotonesCasillas[numBoton].setEnabled(false);
			int tiradaDado = Integer.parseInt(label_16.getName());
			int indFicha = ListaJugadores.getMiListaJugadores().listaPosicionesFichas(turno).indexOf(numCasilla);
			
			try {
				ListaJugadores.getMiListaJugadores().realizarTirada(turno, tiradaDado, indFicha);
				activarDesactivarBotonesJugador(false, turno);
				cambiarTurno(tiradaDado);
			} catch (Exception e) {//BonusException & NoEsPosibleMover
				if(this.todosBotonesDeFichasDesactivados(turno)){
					this.cambiarTurno(1);
				}
				
			}
		}

		
		
	}



	

}
