package com.example.ejerciciosemana2;

public class Pregunta {

    private int A;
    private int B;
    private String operador;
    private String[] operandos = {"+","-","x","/"};

    public Pregunta(){

        this.A = (int)(Math.random()*11);
        this.B = (int)(Math.random()*11);
        int operadorRandom = (int)(Math.random()*4);
        this.operador = operandos[operadorRandom];

    }

    public String getPregunta(){
        return A+" "+operador+" "+B;
    }

    public int getRespuesta(){
        int respuesta = 0;
        switch(operador){
            case "+":
                respuesta = A + B;
               break;
            case "-":
                respuesta = A - B;
                break;
            case "x":
                respuesta = A * B;
                break;
            case "/":
                respuesta = A / B;
                break;
        }
        return respuesta;
    }

}
