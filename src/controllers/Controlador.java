package controllers;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
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

    public Controlador(int filas, int columnas) {
        this.panelMatriz = new JPanel();
        this.matrizObj = new ModeloMatriz(filas, columnas);
        this.vista = new Vista(filas, columnas, this);
    }

    public Controlador(String s) {
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

    public Controlador() {

    }

    public void actualizarMatriz(int filas, int columnas) {
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

    public void actualizarMatrizDesdeInput() {
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
        // Limpiar la ruta encontrada, pero mantener obstáculos, inicio y fin
        for (int i = 0; i < matrizObj.getFilas(); i++) {
            for (int j = 0; j < matrizObj.getColumnas(); j++) {
                // Si la celda no es un obstáculo, ni inicio, ni fin, la restablecemos a su estado inicial
                if (botones[i][j].getBackground() != Color.ORANGE && 
                    !botones[i][j].getText().equals("A") && 
                    !botones[i][j].getText().equals("B")) {
                    botones[i][j].setBackground(Color.WHITE);
                    botones[i][j].setText(" ");
                }
            }
        }
    
        // Reiniciar el método seleccionado
        metodoSeleccionado = null;
    
        // Mostrar un mensaje de confirmación
        JOptionPane.showMessageDialog(vista.getFrame(), "La ruta ha sido reiniciada. Seleccione un método nuevamente.",
                "Reinicio", JOptionPane.INFORMATION_MESSAGE);
    }

    private List<Cell> encontrarRutaRecursiva() {
        List<Cell> ruta = new ArrayList<>();
        List<Cell> path = new ArrayList<>();  // Para almacenar el camino explorado
    
        if (inicio != null && fin != null) {
            boolean[][] visitado = new boolean[matrizObj.getFilas()][matrizObj.getColumnas()];
    
            if (buscarRutaRecursiva(inicio.row, inicio.col, ruta, path, visitado)) {
                return ruta;
            }
        }
        return path;  // Devuelve las celdas exploradas hasta el punto donde se detuvo
    }

    private boolean buscarRutaRecursiva(int row, int col, List<Cell> ruta, List<Cell> path, boolean[][] visitado) {
        // Verificación de límites y obstáculos
        if (row < 0 || col < 0 || row >= matrizObj.getFilas() || col >= matrizObj.getColumnas()
            || !matrizObj.getValor(row, col) || visitado[row][col]) {
            return false;
        }

        ruta.add(new Cell(row, col));  // Añadir la celda al camino actual
        path.add(new Cell(row, col));  // Añadir la celda al camino explorado
        visitado[row][col] = true;

        if (row == fin.row && col == fin.col) {
            return true;
        }

        // Intentar moverse en todas las direcciones posibles
        if (buscarRutaRecursiva(row, col + 1, ruta, path, visitado)
                || // Derecha
                buscarRutaRecursiva(row + 1, col, ruta, path, visitado)
                || // Abajo
                buscarRutaRecursiva(row, col - 1, ruta, path, visitado)
                || // Izquierda
                buscarRutaRecursiva(row - 1, col, ruta, path, visitado)) {  // Arriba
            return true;
        }

        // Si no se encuentra la ruta, deshacer el último paso (pero sin eliminar el recorrido ya realizado)
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

    // Método para mostrar la ruta encontrada de manera animada
public void mostrarRutaAnimada() {
    // Obtener la ruta según el método seleccionado
    List<Cell> ruta = switch (metodoSeleccionado) {
        case "DFS" -> buscarDFS();
        case "BFS" -> buscarBFS();
        case "Recursión" -> encontrarRutaRecursiva();
        case "Programación Dinámica" -> programacionDinamica();
        default -> Collections.emptyList();
    };
    
    // Si no se encontró ruta, se muestra un mensaje y se termina
    if (ruta.isEmpty()) {
        JOptionPane.showMessageDialog(vista.getFrame(), "No se encontró una ruta.");
        return;
    }
    
    // Crear un conjunto con las celdas de la ruta
    Set<Cell> rutaSet = new HashSet<>(ruta);
    
    // Usar SwingWorker para manejar la animación en el hilo de la interfaz de usuario
    SwingWorker<Void, Cell> worker = new SwingWorker<>() {
        @Override
        protected Void doInBackground() throws Exception {
            // Primer loop para animar el recorrido de la ruta
            for (Cell c : ruta) {
                // Publicar la celda para que se actualice en el hilo de la interfaz de usuario
                publish(c);
                // Pinta de verde la celda del recorrido (ruta más rápida)
                botones[c.row][c.col].setBackground(Color.GREEN);
                // Pausa entre los pasos para crear la animación
                Thread.sleep(50); // Ajusta este valor si quieres más o menos retraso
            }
            return null;
        }

        @Override
        protected void process(List<Cell> chunks) {
            // Este método se llama cada vez que se publica una celda
            // Ya estamos manejando la actualización de colores en el hilo de la interfaz de usuario
        }
    };

    // Ejecuta el worker que muestra la ruta animada
    worker.execute();

    // Usamos otro SwingWorker para pintar las celdas no utilizadas después de la animación
    SwingWorker<Void, Void> workerFinal = new SwingWorker<>() {
        @Override
        protected Void doInBackground() throws Exception {
            // Esperar a que el worker de la animación termine
            worker.get();

            // Ahora, pintamos las celdas que no fueron parte de la ruta (y están libres)
            for (int i = 0; i < matrizObj.getFilas(); i++) {
                for (int j = 0; j < matrizObj.getColumnas(); j++) {
                    Cell celdaActual = new Cell(i, j);
                    // Pintar de rojo las celdas que no fueron parte de la ruta
                    if (!rutaSet.contains(celdaActual) && matrizObj.getValor(i, j)) {
                        botones[i][j].setBackground(Color.RED);
                    }
                }
            }
            return null;
        }
    };

    // Ejecuta el worker final que pinta de rojo las celdas no usadas
    workerFinal.execute();
}


private List<Cell> programacionDinamica() {
    List<Cell> ruta = new ArrayList<>();
    List<Cell> path = new ArrayList<>();
    if (inicio == null || fin == null) {
        return ruta;
    }

    boolean[][] visitado = new boolean[matrizObj.getFilas()][matrizObj.getColumnas()];
    Map<Cell, Boolean> memo = new HashMap<>();

    if (pgDinamica(inicio.row, inicio.col, ruta, path, visitado, memo)) {
        return ruta;
    }
    
    return path;
}

private boolean pgDinamica(int row, int col, List<Cell> ruta, List<Cell> path, boolean[][] visitado, Map<Cell, Boolean> memo) {
    Cell currentCell = new Cell(row, col);

    if (row < 0 || col < 0 || row >= matrizObj.getFilas() || col >= matrizObj.getColumnas()
            || !matrizObj.getValor(row, col) || visitado[row][col]) {
        return false;
    }

    if (memo.containsKey(currentCell)) {
        return memo.get(currentCell);
    }

    // Marcar como visitado en memorización
    memo.put(currentCell, false); 

    ruta.add(currentCell);
    path.add(currentCell);
    visitado[row][col] = true;

    if (row == fin.row && col == fin.col) {
        memo.put(currentCell, true);
        return true;
    }

    boolean foundDinamica = pgDinamica(row, col + 1, ruta, path, visitado, memo) || // Derecha
                            pgDinamica(row + 1, col, ruta, path, visitado, memo) || // Abajo
                            pgDinamica(row, col - 1, ruta, path, visitado, memo) || // Izquierda
                            pgDinamica(row - 1, col, ruta, path, visitado, memo);   // Arriba

    memo.put(currentCell, foundDinamica); // Guardar el resultado

    if (!foundDinamica) {
        ruta.remove(ruta.size() - 1);
    }

    return foundDinamica;
}


private List<Cell> buscarDFS() {
    List<Cell> ruta = new ArrayList<>();
    List<Cell> path = new ArrayList<>(); // Para almacenar el camino explorado
    if (inicio == null || fin == null) {
        return ruta;
    }

    boolean[][] visitado = new boolean[matrizObj.getFilas()][matrizObj.getColumnas()];
    if (dfsHelper(inicio.row, inicio.col, ruta, path, visitado)) {
        return ruta; // Devuelve la ruta si se encuentra
    }
    return path; // Devuelve el camino explorado si no hay ruta
}

private boolean dfsHelper(int row, int col, List<Cell> ruta, List<Cell> path, boolean[][] visitado) {
    if (row < 0 || col < 0 || row >= matrizObj.getFilas() || col >= matrizObj.getColumnas()
            || !matrizObj.getValor(row, col) || visitado[row][col]) {
        return false;
    }

    ruta.add(new Cell(row, col)); // Añadir a la ruta actual
    path.add(new Cell(row, col)); // Añadir al camino explorado
    visitado[row][col] = true; // Marcar como visitado

    if (row == fin.row && col == fin.col) {
        return true; // Se encontró la meta
    }

    // Intentar moverse en todas las direcciones posibles
    if (dfsHelper(row - 1, col, ruta, path, visitado) // Arriba
            || dfsHelper(row, col + 1, ruta, path, visitado) // Derecha
            || dfsHelper(row + 1, col, ruta, path, visitado) // Abajo
            || dfsHelper(row, col - 1, ruta, path, visitado)) { // Izquierda
        return true;
    }

    // Si no se encuentra la ruta, deshacer el último paso (pero sin eliminar el recorrido ya realizado)
    ruta.remove(ruta.size() - 1);
    return false;
}

private List<Cell> buscarBFS() {
    List<Cell> ruta = new ArrayList<>();
    List<Cell> path = new ArrayList<>(); // Para almacenar las celdas exploradas
    if (inicio == null || fin == null) {
        return ruta;
    }

    boolean[][] visitado = new boolean[matrizObj.getFilas()][matrizObj.getColumnas()];
    Queue<Cell> cola = new LinkedList<>();
    Map<Cell, Cell> parentMap = new HashMap<>(); // Para reconstruir la ruta

    cola.add(inicio);
    visitado[inicio.row][inicio.col] = true;
    parentMap.put(inicio, null); // Nodo inicial sin padre

    int[][] direcciones = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Derecha, Abajo, Izquierda, Arriba

    while (!cola.isEmpty()) {
        Cell actual = cola.poll();
        path.add(actual); // Registrar nodo explorado

        if (actual.row == fin.row && actual.col == fin.col) {
            return reconstruirCamino(parentMap, fin); // Reconstruir camino al final
        }

        for (int[] dir : direcciones) {
            int newRow = actual.row + dir[0];
            int newCol = actual.col + dir[1];

            if (newRow >= 0 && newRow < matrizObj.getFilas()
                    && newCol >= 0 && newCol < matrizObj.getColumnas()
                    && matrizObj.getValor(newRow, newCol) && !visitado[newRow][newCol]) {

                visitado[newRow][newCol] = true;
                Cell vecino = new Cell(newRow, newCol);
                parentMap.put(vecino, actual); // Guardar el nodo anterior
                cola.add(vecino);
            }
        }
    }

    return path; // Si no se encuentra un camino, devolver las celdas exploradas
}

// Método para reconstruir el camino desde el final hasta el inicio
private List<Cell> reconstruirCamino(Map<Cell, Cell> parentMap, Cell fin) {
    List<Cell> ruta = new ArrayList<>();
    for (Cell actual = fin; actual != null; actual = parentMap.get(actual)) {
        ruta.add(actual);
    }
    Collections.reverse(ruta); // Se invierte la lista para obtener el orden correcto
    return ruta;
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
