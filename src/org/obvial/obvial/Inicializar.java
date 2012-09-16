package org.obvial.obvial;

//import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//En esta clase se encuentran todos los metodos para inicializar el juego

public class Inicializar {
	
	private static Inicializar miInicio = new Inicializar();
	
	private Inicializar(){
		
	}
	
	public static Inicializar getInicializar(){
		return miInicio;
	}
	
	public void InicializarCartas(){
	
		try {
			InputStream archivo = Inicializar.class.getResourceAsStream("datos/Preguntas_de_Curiosidades.txt");
			this.recorrerArchivo(archivo, 0);
			archivo = Inicializar.class.getResourceAsStream("datos/Preguntas_de_Series.txt");
			this.recorrerArchivo(archivo, 1);
			archivo = Inicializar.class.getResourceAsStream("datos/Preguntas_de_Peliculas.txt");
			this.recorrerArchivo(archivo, 2);
			archivo = Inicializar.class.getResourceAsStream("datos/Preguntas_de_Deportes.txt");
			this.recorrerArchivo(archivo, 3);
			archivo = Inicializar.class.getResourceAsStream("datos/Preguntas_de_Videojuegos.txt");
			this.recorrerArchivo(archivo, 4);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error cargando el fichero de preguntas 1.");
		}
	}
	
	//Este es especifico para cargar las cartas
	public void recorrerArchivo(InputStream pArchivo, int pTema){

		
		//Recorre el archivo a cargar
		
		String pregunta;
		String respCorrecta;
		String resp1;
		String resp2;
		String resp3;
		String resp4;
		Carta carta;
		ListaCartas listaCartas = new ListaCartas();
		
		int cont = 0;
		
		try{
			//BufferedReader bufRdr = new BufferedReader(new FileReader(pArchivo));
			BufferedReader bufRdr  = new BufferedReader(new InputStreamReader(pArchivo));
			String linea = null;

			//Recorrer lineas
			//Si la linea devuelve null, es que hemos 
			//terminado de recorrer el archivo 
			linea = bufRdr.readLine();
			while( linea != null ){

				//separamos la linea por los ";" y contamos 
				StringTokenizer lineaActual = new StringTokenizer(linea,";");
				cont++;

				//Debe tener 6 tokens
				if (lineaActual.countTokens() == 6) {

					pregunta = lineaActual.nextToken();	//token 1 Pregunta
					respCorrecta = lineaActual.nextToken();	//token 2 RespuestaCorrecta
					resp1 = lineaActual.nextToken();	//token 3 RespuestaA
					resp2 = lineaActual.nextToken();	//token 4 RespuestaB
					resp3 = lineaActual.nextToken();	//token 5 RespuestaC
					resp4 = lineaActual.nextToken();	//token 6 RespuestaD

					//Anadimos la carta
					carta = new Carta(cont+1, pregunta, respCorrecta, resp1, resp2, resp3, resp4);
					listaCartas.anadirCarta(carta);
					
					//System.out.println("Inserto carta " + Cont);
					linea = bufRdr.readLine();
				} else {
					System.out.println("La linea preguntas" + cont + " contiene errores.");
					linea = bufRdr.readLine();
				}
			}
			Listas.getMisListas().anadirListas(pTema, listaCartas);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error cargando el fichero de preguntas 2."+pTema);
		}
	}

	public void InicializarCasillas(){
		
		String token1;
		String token2;
		String token3;
		String token4;
		String token5;

		int Cont = 0;

		try {
	
			InputStream archivo = Inicializar.class.getResourceAsStream("datos/Datos_Casillas.txt");
			//BufferedReader bufRdr = new BufferedReader(new FileReader(archivo));
			BufferedReader bufRdr  = new BufferedReader(new InputStreamReader(archivo));
			String linea = null;

			//Recorrer lineas
			//Si la linea devuelve null, es que hemos 
			//terminado de recorrer el archivo 
			while((linea = bufRdr.readLine()) != null)
			{

				//Separamos la linea por los ";" y contamos 
				StringTokenizer lineaActual = new StringTokenizer(linea,";");
				Cont++;

				//Debe tener 5 tokens
				if (lineaActual.countTokens() == 5) {

					//Con el parseInt pasamos de String a integer cada uno de los tokens
					token1 = lineaActual.nextToken();	//token 1 Numero de casilla
					int numCasilla = Integer.parseInt(token1);
					token2 = lineaActual.nextToken();	//token 2 Tipo de casilla
					int tipo = Integer.parseInt(token2);
					token3 = lineaActual.nextToken();	//token 3 Numero de turnos de retencion o casilla a la que desplazarse
					int opcion = Integer.parseInt(token3);
					token4 = lineaActual.nextToken();	//token 4 Posicion X en el tablero
					int posX = Integer.parseInt(token4);
					token5 = lineaActual.nextToken();	//token 5 Posicion Y en el tablero
					int posY = Integer.parseInt(token5);

					//Aniadimos la casilla
					
					switch(tipo){
					case 1: Casilla casilla = new Casilla(numCasilla, tipo, posX, posY);
							ListaCasillas.getListaCasillas().anadirCasilla(casilla);
							break;
					case 2: CasillaRetencion casilla1 = new CasillaRetencion(numCasilla, opcion, tipo, posX, posY);
							ListaCasillas.getListaCasillas().anadirCasillaRetencion(casilla1);
							break;
					case 3: CasillaTraslado casilla2 = new CasillaTraslado(numCasilla, opcion, tipo, posX, posY);
							ListaCasillas.getListaCasillas().anadirCasillaTraslado(casilla2);
							break;
					}
				}else{
					System.out.println("La linea de casillas " + Cont + " contiene errores.");
					linea = bufRdr.readLine();
				}
			}
		}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error cargando el fichero de casillas.");
			}
		}
	
}
