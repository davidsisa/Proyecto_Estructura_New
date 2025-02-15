package controllers;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import models.Cell;
import models.ModeloMatriz;
import views.Vista;

public class Controlador {
    private Vista vista;
    private ModeloMatriz matrizObj;
    private JPanel panelMatriz;
    private Cell inicio = null, fin = null;
    private JButton[][] botones;
    private boolean modoObstaculos = false;
    private boolean modoSeleccion = false;
    private String metodoSeleccionado = null;
    public Controlador(int filas, int columnas){
        this.panelMatriz = new JPanel();
        this.matrizObj = new ModeloMatriz(filas, columnas);
        this.vista = new Vista(filas, columnas, this); 
    }
    public Controlador(String s){
        System.out.println("Control O : " + s);
    }


    public void seleccionarMetodo() {
        String[] metodos = {"DFS", "BFS", "Recursión", "Programación Dinámica"};
        String metodoElegido = (String) JOptionPane.showInputDialog(
                vista.getFrame(),
                "Seleccione un método de búsqueda:",
                "Método de Búsqueda",
                JOptionPane.QUESTION_MESSAGE,
                null,
                metodos,
                metodos[0] 
        );

        if (metodoElegido != null) {
            metodoSeleccionado = metodoElegido;
            JOptionPane.showMessageDialog(vista.getFrame(), "Método seleccionado: " + metodoSeleccionado,
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void actualizarMatriz(int filas, int columnas){
        panelMatriz.removeAll();
        panelMatriz.setLayout(new GridLayout(filas, columnas));
       
        matrizObj = new ModeloMatriz(filas, columnas);
        
        botones = new JButton[filas][columnas];

        for (int i = 0; i < filas; i++) { 
            for (int j = 0; j < columnas; j++) {
                
                matrizObj.llenarMatriz(true);
                botones[i][j] = new JButton(" ");
                botones[i][j].setBackground(Color.WHITE);
                final int row = i, col = j;

                botones[i][j].addActionListener(e -> {
                    if (modoObstaculos) {
                        matrizObj.setValor(row, col, !matrizObj.getValor(row, col));
                        botones[row][col].setBackground(matrizObj.getValor(row, col) ? Color.WHITE : Color.ORANGE);
                        
                    } else if (modoSeleccion) {
                        if (inicio == null) {
                            inicio = new Cell(row, col);
                            botones[row][col].setText("A");
                        } else if (fin == null) {
                            fin = new Cell(row, col);
                            botones[row][col].setText("B");
                        }
                    }
                });
                panelMatriz.add(botones[i][j]);
            }
        }
        panelMatriz.revalidate();
        panelMatriz.repaint();
    }
    public void actualizarMatrizDesdeInput(){
        try {
            int filas = Integer.parseInt(vista.getInputFilas().getText());
            int columnas = Integer.parseInt(vista.getInputColumnas().getText());
            if (filas > 0 && columnas > 0) {
                //actualizarMatriz(filas, columnas);
                actualizarMatriz(filas, columnas);
            } else {
                JOptionPane.showMessageDialog(vista.getFrame(), "Ingrese valores positivos.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista.getFrame(), "Ingrese un número válido.");
        }
    }
    public void reiniciar() {
        actualizarMatriz(matrizObj.getFilas(), matrizObj.getColumnas());
        inicio = null;
        fin = null;
        modoObstaculos = false;
        modoSeleccion = false;
        metodoSeleccionado = null; 
        JOptionPane.showMessageDialog(vista.getFrame(), "El programa se ha reiniciado. Seleccione un método nuevamente.",
                "Reinicio", JOptionPane.INFORMATION_MESSAGE);
    }

    public List<Cell> encontrarRutaRecursiva() {
        List<Cell> ruta = new ArrayList<>();
        if (inicio != null && fin != null) {
            // Utiliza visitadoObj en lugar de visitado
            boolean[][] visitadoObj = new boolean[matrizObj.getFilas()][matrizObj.getColumnas()];
            if (buscarRutaRecursiva(inicio.row, inicio.col, ruta, visitadoObj)) {
                return ruta;
            }
        }
        return Collections.emptyList();
    }
    private boolean buscarRutaRecursiva(int row, int col, List<Cell> ruta, boolean[][] visitado) {
       
        if (row < 0 || col < 0 || row >= matrizObj.getFilas() || col >= matrizObj.getColumnas() || 
            !matrizObj.getValor(row, col) || visitado[row][col]) {
            return false;
        }
        
        
        ruta.add(new Cell(row, col));
        visitado[row][col] = true;
        
        
        if (row == fin.row && col == fin.col) {
            return true;
        }
        
        
        if (buscarRutaRecursiva(row, col + 1, ruta, visitado)
                || buscarRutaRecursiva(row + 1, col, ruta, visitado)
                || buscarRutaRecursiva(row, col - 1, ruta, visitado)
                || buscarRutaRecursiva(row - 1, col, ruta, visitado)) {
            return true;
        }
        
        
        ruta.remove(ruta.size() - 1);
        return false;
    }

    public void mostrarRuta() {
        if (metodoSeleccionado == null) {
            JOptionPane.showMessageDialog(vista.getFrame(), "Debe seleccionar un método antes de ejecutar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        long inicioTiempo = System.nanoTime(); 

        List<Cell> ruta = switch (metodoSeleccionado) {
            case "DFS" ->
                buscarDFS();
            case "BFS" ->
                buscarBFS();
            case "Recursión" ->
                encontrarRutaRecursiva();
            case "Programación Dinámica" ->
                programacionDinamica();
            default ->
                Collections.emptyList();
        };

        long finTiempo = System.nanoTime(); 
        long tiempoEjecucion = finTiempo - inicioTiempo; 
        if (ruta == null || ruta.isEmpty()) {
            JOptionPane.showMessageDialog(vista.getFrame(), "No se encontró una ruta.", "Información", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (Cell c : ruta) {
            if (!c.equals(inicio) && !c.equals(fin)) {
                botones[c.row][c.col].setBackground(new Color(49, 139, 49)); 
            }
        }
        String tiempoFormateado = String.format("Tiempo de ejecución: %.2f ms", tiempoEjecucion / 1_000_000.0);
        JOptionPane.showMessageDialog(vista.getFrame(), tiempoFormateado, "Tiempo de Ejecución", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarRutaAnimada() {
        List<Cell> ruta = encontrarRutaRecursiva();
        if (ruta.isEmpty()) {
            JOptionPane.showMessageDialog(vista.getFrame(), "No se encontró una ruta.");
            return;
        }

        SwingWorker<Void, Cell> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (Cell c : ruta) {
                    publish(c);
                    Thread.sleep(300); 
                }
                return null;
            }

            @Override
            protected void process(List<Cell> chunks) {
                for (Cell c : chunks) {
                    botones[c.row][c.col].setBackground(Color.GREEN);
                }
            }
        };

        worker.execute();
    }
        private List<Cell> programacionDinamica() { 
        int filas = matrizObj.getFilas();
        int columnas = matrizObj.getColumnas();
        int[][] dp = new int[filas][columnas];
        if (!matrizObj.getValor(inicio.row, inicio.col) || !matrizObj.getValor(fin.row, fin.col)) {
            return Collections.emptyList();
        }

        dp[inicio.row][inicio.col] = 1;

        for (int i = inicio.row; i < filas; i++) {
            for (int j = inicio.col; j < columnas; j++) {
                if (matrizObj.getValor(i, j)) { 
                    if (i > 0) {
                        dp[i][j] += dp[i - 1][j];
                    }
                    if (j > 0) {
                        dp[i][j] += dp[i][j - 1];
                    }
                }
            }
        }
        
        if (dp[fin.row][fin.col] == 0) {
            return Collections.emptyList();
        }
        
        List<Cell> ruta = new ArrayList<>();
        int i = fin.row, j = fin.col;
        while (i != inicio.row || j != inicio.col) {
            ruta.add(new Cell(i, j));
            if (i > 0 && dp[i - 1][j] > 0) {
                i--;
            } else {
                j--;
            }
        }
        ruta.add(inicio);
        Collections.reverse(ruta);
        return ruta;
    }

    private List<Cell> buscarDFS() {
        List<Cell> ruta = new ArrayList<>();
        if (inicio == null || fin == null) {
            return ruta;
        }
        
        boolean[][] visitado = new boolean[matrizObj.getFilas()][matrizObj.getColumnas()];
        if (dfsHelper(inicio.row, inicio.col, ruta, visitado)) {
            return ruta;
        }
        return Collections.emptyList();
        }
        
    private boolean dfsHelper(int row, int col, List<Cell> ruta, boolean[][] visitado) {
            if (row < 0 || col < 0 || row >= matrizObj.getFilas() || col >= matrizObj.getColumnas() || 
                !matrizObj.getValor(row, col) || visitado[row][col]) {
                return false;
            }
        
            ruta.add(new Cell(row, col));
            visitado[row][col] = true;
            if (row == fin.row && col == fin.col) {
                return true;
            }
        
            if (dfsHelper(row + 1, col, ruta, visitado)
                    || dfsHelper(row - 1, col, ruta, visitado)
                    || dfsHelper(row, col + 1, ruta, visitado)
                    || dfsHelper(row, col - 1, ruta, visitado)) {
                return true;
            }
        
            ruta.remove(ruta.size() - 1);
            return false;
    }
    

    private List<Cell> buscarBFS() {
        List<Cell> ruta = new ArrayList<>();
        if (inicio == null || fin == null) {
            return ruta;
        }
    
        boolean[][] visitado = new boolean[matrizObj.getFilas()][matrizObj.getColumnas()];
        Queue<List<Cell>> cola = new LinkedList<>();
        List<Cell> inicioRuta = new ArrayList<>();
        inicioRuta.add(inicio);
        cola.add(inicioRuta);
        visitado[inicio.row][inicio.col] = true;
    
        while (!cola.isEmpty()) {
            List<Cell> camino = cola.poll();
            Cell actual = camino.get(camino.size() - 1);
    
            if (actual.row == fin.row && actual.col == fin.col) {
                return camino;
            }
    
            int[][] direcciones = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : direcciones) {
                int newRow = actual.row + dir[0];
                int newCol = actual.col + dir[1];
    
                if (newRow >= 0 && newCol >= 0 && newRow < matrizObj.getFilas() && newCol < matrizObj.getColumnas()
                        && matrizObj.getValor(newRow, newCol) && !visitado[newRow][newCol]) {
    
                    List<Cell> nuevoCamino = new ArrayList<>(camino);
                    nuevoCamino.add(new Cell(newRow, newCol));
                    cola.add(nuevoCamino);
                    visitado[newRow][newCol] = true;
                }
            }
        }
        return Collections.emptyList();
    }
    


    

    public boolean isModoObstaculos() {
        return modoObstaculos;
    }
    public void setModoObstaculos(boolean modoObstaculos) {
        this.modoObstaculos = modoObstaculos;
    }
    public boolean isModoSeleccion() {
        return modoSeleccion;
    }
    public void setModoSeleccion(boolean modoSeleccion) {
        this.modoSeleccion = modoSeleccion;
    }
    public Cell getInicio() {
        return inicio;
    }
    public void setInicio(Cell inicio) {
        this.inicio = inicio;
    }
    public Cell getFin() {
        return fin;
    }
    public void setFin(Cell fin) {
        this.fin = fin;
    }
    public String getMetodoSeleccionado() {
        return metodoSeleccionado;
    }
    public void setMetodoSeleccionado(String metodoSeleccionado) {
        this.metodoSeleccionado = metodoSeleccionado;
    }
    public JPanel getPanelMatriz() {
        return panelMatriz;
    }
    public void setPanelMatriz() {
        this.panelMatriz = panelMatriz;
    }
    
    
}
