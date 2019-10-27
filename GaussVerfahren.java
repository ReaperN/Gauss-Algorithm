/**
 * Beschreiben Sie hier die Klasse Gaus.
 * 
 * @author (Noah Steinle) 
 * @version (27.10.2019)
 */

import java.util.Scanner;

public class GaussVerfahren
{
/**
 ** Java Programm zur Implementierung des Gauss Algorithmus
 **/
    public void loese(double[][] A, double[] B)
    {
        int N = B.length;
        for (int k = 0; k < N; k++) 
        {
            /** Pivotzeile finden im 2 dim. Array **/
            int max = k;
            for (int i = k + 1; i < N; i++) 
                if (Math.abs(A[i][k]) > Math.abs(A[max][k])) 
                    max = i;
 
            /** Zeile in einer Matrix tauschen **/    
            double[] temp = A[k]; 
            A[k] = A[max]; 
            A[max] = temp;
 
            /** Tauschen Sie die entsprechenden Werte in der Konstantenmatrix (nicht veränderten) aus **/
            double t = B[k]; 
            B[k] = B[max]; 
            B[max] = t;
 
            /** Pivot (Drehpunkt) innerhalb von A und B **/
            for (int i = k + 1; i < N; i++) 
            {
                double faktor = A[i][k] / A[k][k];
                B[i] -= faktor * B[k];
                for (int j = k; j < N; j++) 
                    A[i][j] -= faktor * A[k][j];
            }
        }
 
        /** Zeige Dreiecksform **/
        printDreiecksform(A, B);
 
        /** Rückwärtssubstitution zur Loesung **/
        double[] loesung = new double[N];
        for (int i = N - 1; i >= 0; i--) 
        {
            double summe = 0.0;
            for (int j = i + 1; j < N; j++)
            {
                summe += A[i][j] * loesung[j];
            }
            loesung[i] = (B[i] - summe) / A[i][i];
        }        
        /** Zeige Loesung **/
        printLoesung(loesung);
    }
    /** Methode zur Anzeige der Dreiecksform **/
    public void printDreiecksform(double[][] A, double[] B)
    {
        int N = B.length;
        System.out.println("\nDreiecksformorm : ");
        for (int i = 0; i < N; i++)
           {
               for (int j = 0; j < N; j++)
                   System.out.printf("%.3f ", A[i][j]);  //Gleitkommazahl mit drei Nachkommastellen (nach dem Dezimalpunkt)
               System.out.printf("| %.3f\n", B[i]);      //Gleitkommazahl mit drei Nachkommastellen (nach dem Dezimalpunkt)
           }
           System.out.println();                         //Zwischenzeile
    }
    /** Methode zur Ausgabe der Lösung **/
    public void printLoesung(double[] loes)
    {
        int N = loes.length;
        System.out.println("\nLoesung : ");
        for (int i = 0; i < N; i++) 
            System.out.printf("%.3f ", loes[i]);   
        System.out.println();                            //Zwischenzeile
    }    
    /** Hauptfunktion, bzw. Main **/
    public static void main (String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Gaus Verfahren/Algorithmus Test\n");
        /** Object der GaussVerfahren class erstellen **/
        GaussVerfahren gv = new GaussVerfahren();
 
        System.out.println("\nAnzahl der Variabeln :");                     //Beispielswerte: 3
        int N = scan.nextInt();
 
        double[] B = new double[N];
        double[][] A = new double[N][N];
 
        System.out.println("\nGib "+ N +" Gleichungskoeefizienten an :");   //Beispielswerte: (2 1 -1) (-3 -1 2) (-2 1 2)
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = scan.nextDouble();
 
        System.out.println("\nGib "+ N +" Loesungen an :");                 //Beispielswerte: (8) (-11) (-3)
        for (int i = 0; i < N; i++)
            B[i] = scan.nextDouble();
 
        gv.loese(A,B);
    }
}