package com.mycompany.lab_3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Lab_3 {

    static class Nodo {
        String valor;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(String valor) {
            this.valor = valor;
            izquierdo = null;
            derecho = null;
        }
    }

    static Nodo crearArbol(String expresion) {
        expresion = expresion.replaceAll("\\s+", ""); // Eliminar espacios en blanco
        if (expresion.length() == 0) {
            return null;
        }

        // Buscar el operador de menor precedencia en la expresión
        int balance = 0;
        for (int i = expresion.length() - 1; i >= 0; i--) {
            char c = expresion.charAt(i);
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            } else if (balance == 0 && (c == '+' || c == '-' || c == '*' || c == '/')) {
                Nodo nodo = new Nodo(Character.toString(c));
                nodo.izquierdo = crearArbol(expresion.substring(0, i));
                nodo.derecho = crearArbol(expresion.substring(i + 1));
                return nodo;
            }
        }

        // Si no se encuentra un operador, se asume que la expresión es un valor
        if (expresion.charAt(0) == '(' && expresion.charAt(expresion.length() - 1) == ')') {
            return crearArbol(expresion.substring(1, expresion.length() - 1));
        }

        return new Nodo(expresion);
    }

    static void imprimirArbol(Nodo raiz, String prefijo, boolean esIzquierdo) {
        if (raiz == null) {
            return;
        }

        imprimirArbol(raiz.izquierdo, prefijo + "     ", true);
        System.out.print(prefijo);
        System.out.print(esIzquierdo ? "├── " : "└── ");
        System.out.println(raiz.valor);
        imprimirArbol(raiz.derecho, prefijo + "     ", false);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese una expresión aritmética: ");
        String expresion = sc.nextLine();
        sc.close();

        Nodo raiz = crearArbol(expresion);
        System.out.println("Árbol de Expresiones: ");
        imprimirArbol(raiz, "", false);
    }
}
