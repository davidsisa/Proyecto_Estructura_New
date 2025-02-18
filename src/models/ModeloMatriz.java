package models;

public class ModeloMatriz {
    private boolean[][] matriz;
    
    //Crear una Matriz desde otra clase
    public ModeloMatriz(int filas, int columnas) {
        this.matriz = new boolean[filas][columnas];
    }

    public boolean getValor(int fila, int columna) {
        return matriz[fila][columna];
    }
    //Agregar Valor en la matriz
    public void setValor(int fila, int columna, boolean valor) {
        matriz[fila][columna] = valor;
    }

    public int getFilas() {
        return matriz.length;
    }

    public int getColumnas() {
        return matriz[0].length;
    }

    public boolean[][] getMatriz() {
        return matriz;
    }
    //LLENAR MATRIZ
    public void llenarMatriz(boolean valor) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = valor;
            }
        }
    }
    
}
