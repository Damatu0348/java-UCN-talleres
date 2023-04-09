package cl.ucn.programacionavanzada;

import edu.princeton.cs.stdlib.StdDraw;

import javax.annotation.processing.SupportedSourceVersion;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * Protector de pantalla Mystify
 *
 * @author Dantte Márquez Tuohy
 */
public class Main {
    /**
     * Rutina donde se ejecuta el codigo completo
     */

    //Rango del lienzo
    static double minimo = -1.0;
    static double maximo = 1.0;

    //Valor de las velocidades en ejes x e y
    static double valorVelX = randomVelocidad();
    static double valorVelY = randomVelocidad();

    //Velocidades de ejes x e y
    static double[] velocidadXInicio = {valorVelX, valorVelX, valorVelX, valorVelX, valorVelX, valorVelX};
    static double[] velocidadXFinal = {valorVelX, valorVelX, valorVelX, valorVelX, valorVelX, valorVelX};
    static double[] velocidadYInicio = {valorVelY, valorVelY, valorVelY, valorVelY, valorVelY ,valorVelY};
    static double[] velocidadYFinal = {valorVelY, valorVelY, valorVelY, valorVelY, valorVelY ,valorVelY};

    //Generar las coordenadas iniciales y finales
    static double puntoXInicio = randomPunto();
    static double puntoYInicio = randomPunto();
    static double puntoXFinal = randomPunto();
    static double puntoYFinal = randomPunto();

    //Ubicar en arreglos las coordenadas iniales y finales para crear las lineas para cada color
    static double[] xInicio = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    static double[] yInicio = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    static double[] xFinal = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    static double[] yFinal = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

    //Delta que ayuda en la ubicacion de los ejes
    static double delta = 0.025;
    public static void main(String[] args) {
        //Un valor que determina cuandto se desplaza la linea de la original
        double factorDesplazamiento;

        //Evitar parpadeo de la pantalla
        StdDraw.enableDoubleBuffering();

        //Inicializar coordenadas de las 6 lineas en arreglos
        for (int indiceCoord = 0; indiceCoord <= 5; indiceCoord++){
            factorDesplazamiento = indiceCoord;
            xInicio[indiceCoord] = puntoXInicio + (delta * factorDesplazamiento);
            yInicio[indiceCoord] = puntoYInicio + (delta * factorDesplazamiento);
            xFinal[indiceCoord] = puntoXFinal + (delta * factorDesplazamiento);
            yFinal[indiceCoord] = puntoYFinal + (delta * factorDesplazamiento);
        }

        //Definir los colores en un arreglo (se usaran los colores del arcoiris como referencia)
        Color[] colores = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
        Color colorLineaActual;

        //Ciclo infinito
        while (true){
            //Dibujar LINEA
            for (int indiceLinea = 0; indiceLinea <= 5; indiceLinea++){
                colorLineaActual = colores[indiceLinea];
                crearLinea(colorLineaActual, indiceLinea);
            }
            //Determinar la velocidad de dibujado
            StdDraw.pause(100);
            //Borrar linea
            StdDraw.clear();
        }
    }

    /**
     * Rutina que permite dibujar una linea
     * @param colorLinea color de la lista
     */
    public static void crearLinea(Color colorLinea, int indiceIndicadorLinea){
        //Colision con borde lienzo
        if (Math.abs(xInicio[indiceIndicadorLinea] + velocidadXInicio[indiceIndicadorLinea]) >= maximo){
            velocidadXInicio[indiceIndicadorLinea] = -velocidadXInicio[indiceIndicadorLinea];
        }
        if (Math.abs(xFinal[indiceIndicadorLinea] + velocidadXFinal[indiceIndicadorLinea]) >= maximo){
            velocidadXFinal[indiceIndicadorLinea] = -velocidadXFinal[indiceIndicadorLinea];
        }
        if (Math.abs(yInicio[indiceIndicadorLinea] + velocidadYInicio[indiceIndicadorLinea]) >= maximo) {
            velocidadYInicio[indiceIndicadorLinea] = -velocidadYInicio[indiceIndicadorLinea];
        }
        if (Math.abs(yFinal[indiceIndicadorLinea] + velocidadYFinal[indiceIndicadorLinea]) >= maximo){
            velocidadYFinal[indiceIndicadorLinea] = -velocidadYFinal[indiceIndicadorLinea];
        }

        //Incrementar o disminuir punto inicial y punto final
        xInicio[indiceIndicadorLinea] = xInicio[indiceIndicadorLinea] + velocidadXInicio[indiceIndicadorLinea];
        yInicio[indiceIndicadorLinea] = yInicio[indiceIndicadorLinea] + velocidadYInicio[indiceIndicadorLinea];
        xFinal[indiceIndicadorLinea] = xFinal[indiceIndicadorLinea] + velocidadXFinal[indiceIndicadorLinea];
        yFinal[indiceIndicadorLinea] = yFinal[indiceIndicadorLinea] + velocidadYFinal[indiceIndicadorLinea];

        //Definir colr de linea
        StdDraw.setPenColor(colorLinea);
        StdDraw.line(xInicio[indiceIndicadorLinea], yInicio[indiceIndicadorLinea], xFinal[indiceIndicadorLinea], yFinal[indiceIndicadorLinea]);
        StdDraw.show();
    }

    /**
     * Rutina que permite controlar las velocidades aleatorias
     * @return la velocidad aleatoria
     */
    public static double randomVelocidad(){
        double resultado;
        //velocidad adecuada despues de varias pruebas
        double[] velocidades = {0.0500, 0.0400, 0.0300, 0.0250, 0.0120};
        double random = Math.random();
        int posicion;
        if (random < 0.2) {
            posicion = 0;
        } else if (random < 0.4) {
            posicion = 1;
        } else if (random < 0.6) {
            posicion = 2;
        } else if (random < 0.8) {
            posicion = 3;

        } else {
            posicion = 4;
        }
        resultado = velocidades[posicion];

        return resultado;
    }

    /**
     * Rutina que permite controlar la posicion azar de los puntos iniciales
     * @return la posicion aleatoria
     */
    public static double randomPunto(){
        double resultado;
        resultado = minimo + (maximo - minimo) * Math.random();
        double limiteMaximo = maximo * 0.8; //el 80% del maximo
        double limiteMinimo = minimo * 0.8; //el 80% del minimo
        if (resultado > limiteMaximo){
            resultado = limiteMaximo;
        }
        if (resultado < limiteMinimo){
            resultado = limiteMinimo;
        }

        return resultado;
    }

    //Crear un archivo para rutear (borrar para futuro)
    public static void crearArchivoRuteo(String nombreArchivo){
        File archivo = new File(nombreArchivo);

        //Excepcion por si existe o no el archivo
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Archivo de ruteo creado");
        }
        catch (FileNotFoundException excepcion){
            excepcion.printStackTrace(System.out);
        }
    }

    //Crear un archivo para rutear (borrar para futuro)
    public static void escribirArchivoRuteo(String nombreArchivo, String contenido){
        File archivo = new File(nombreArchivo);

        //Excepcion por si existe o no el archivo
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(contenido);
            salida.close();
            //System.out.println("Archivo de ruteo escrito en el");
        }
        catch (FileNotFoundException excepcion){
            excepcion.printStackTrace(System.out);
        }
        catch (IOException ex){
            ex.printStackTrace(System.out);
        }
    }
}
