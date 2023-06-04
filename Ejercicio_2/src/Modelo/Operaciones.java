/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Daniel
 */
public class Operaciones {

    private double a;
    private double b;

    public Operaciones(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double Sumar() {
        return a + b;
    }

    public double restar() {
        return a - b;
    }

    public double division() {
        return a / b;
    }

    public double multiplicacion() {
        return a * b;
    }

    public double getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
