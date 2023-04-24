// 1 - Pacote: conjunto de classes
package br.com.iterasys;

// 2 - Bibliotecas

// 3 - Classe
public class Calculadora { // inicio da classe
    public static double somarDoisNumeros(double num1, double num2) { // inicio da função somar
        return num1 + num2;

    } // final da função somar

    public static double subtrairDoisNumeros(double num1, double num2){ // inicio da função subtrair
        return num1 - num2;
    } // final da função subtrair

    public static double multiplicarDoisNumeros(double num1, double num2){ // inicio da função multiplicar
        return num1 * num2;
    } // final da função multiplicar

    public static String dividirDoisNumerosInteiros(int numA, int numB){
        try{
            return String.valueOf(numA / numB);
        }
        catch(Exception e){
            return "Não é possível dividir por zero";
        }

    }

    public static double dividirDoisNumeros(double num1, double num2) { // inicio da função dividi
        try{
            if (num1/num2 < Double.MAX_VALUE && num1/num2 > Double.MIN_VALUE){
                return num1 / num2;
            } else {
                System.out.println("Não foi possível dividir por zero!");
                return 0;
            }
        }
        catch (RuntimeException e){
            System.out.println("Não foi possível dividir por zero");
            return 0;
        }

    } // final da função dividir

    public static double areaQuadrado(double aresta) {
        return aresta * aresta;
    }
    public static double volumeCubo(double aresta) {
        return Math.pow(aresta, 3);
    }

    public static double areaRetangulo(double base, double altura) {
        return base * altura;
    }

    public static double volumeParalelepipedo(double base, double altura, double comprimento) {
        return base * altura * comprimento;
    }

    public static double areaTriangulo(double base, double altura) {
        return (base * altura) / 2;
    }

    public static double volumePiramideTriangular(double base, double altura, double comprimento) {
        return (((base * altura) / 2) * comprimento) / 3;
    }

    public static double volumePiramideQuadrada(double base, double comprimento) {
        return (Math.pow(base, 2) * comprimento) / 3;
    }

    public static double volumePiramideRetangular(double base, double altura, double comprimento) {
        return (base * altura * comprimento) / 3;
    }

    public static double areaCirculo(double raio) {
        return Math.pow(raio, 2) * 3.14;
    }

    public static double volumeCilindro(double raio, double altura) {
        return Math.pow(raio, 2) * 3.14 * altura;
    }




} // final da classe
