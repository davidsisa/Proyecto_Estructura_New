
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.*;
import models.Cell;

public class App {

    private JFrame frame;
    private JPanel panelMatriz;
    private JButton[][] botones;
    private boolean[][] matriz;
    private boolean modoObstaculos = false;
    private boolean modoSeleccion = false;
    private Cell inicio = null, fin = null;
    private JTextField inputFilas, inputColumnas;
    private String metodoSeleccionado = null;
    private JLabel lblResultados;

    public App(int filas, int columnas) {
        inicializarUI(filas, columnas);
    }

    private void seleccionarMetodo() {
        String[] metodos = {"DFS", "BFS", "Recursión", "Programación Dinámica"};
        String metodoElegido = (String) JOptionPane.showInputDialog(
                frame,
                "Seleccione un método de búsqueda:",
                "Método de Búsqueda",
                JOptionPane.QUESTION_MESSAGE,
                null,
                metodos,
                metodos[0] 
        );

        if (metodoElegido != null) {
            metodoSeleccionado = metodoElegido;
            JOptionPane.showMessageDialog(frame, "Método seleccionado: " + metodoSeleccionado,
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void inicializarUI(int filas, int columnas) {
        frame = new JFrame("Matriz de Botones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelMatriz = new JPanel();
        actualizarMatriz(filas, columnas);
        // Crear el JLabel para mostrar resultados
        lblResultados = new JLabel("Resultados aparecerán aquí");
        frame.add(lblResultados, BorderLayout.SOUTH);

        JButton botonObstaculos = new JButton("Ingresar Obstáculos");
        botonObstaculos.addActionListener(e -> {
            modoObstaculos = true;
            modoSeleccion = false;
        });

        JButton botonSeleccion = new JButton("Seleccionar A y B");
        botonSeleccion.addActionListener(e -> {
            modoObstaculos = false;
            modoSeleccion = true;
        });

        JButton botonRecorrido = new JButton("Iniciar Recorrido");
        botonRecorrido.addActionListener(e -> {
            if (metodoSeleccionado == null) {
                JOptionPane.showMessageDialog(frame, "Método no seleccionado. Por favor, elija un método de búsqueda.");
            } else {
                mostrarRutaAnimada(); 
            }
        });

        JButton botonTiempo = new JButton("Tiempo de Ejecucion");
        botonTiempo.addActionListener(e -> {
            if (metodoSeleccionado == null) {
                JOptionPane.showMessageDialog(frame, "Método no seleccionado. Por favor, elija un método de búsqueda.");
            } else {
                mostrarRuta();  
            }
        });

        JButton botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.addActionListener(e -> reiniciar());

        JButton botonSeleccionMetodo = new JButton("Seleccionar Método");
        botonSeleccionMetodo.addActionListener(e -> seleccionarMetodo());

        JLabel jlFilas = new JLabel("Filas: ");
        jlFilas.setForeground(Color.WHITE);
        JLabel jlColumnas = new JLabel("Columnas: ");
        jlColumnas.setForeground(Color.white);

        inputFilas = new JTextField(5);
        inputColumnas = new JTextField(5);
        JButton botonActualizar = new JButton("Crear");
        botonActualizar.addActionListener(e -> actualizarMatrizDesdeInput());

        JPanel panelBotones = new JPanel(new GridLayout(3, 2));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotones.setBackground(Color.DARK_GRAY);
        panelBotones.add(botonObstaculos);
        panelBotones.add(botonSeleccion);
        panelBotones.add(botonRecorrido);
        panelBotones.add(botonReiniciar);
        panelBotones.add(botonTiempo);
        panelBotones.add(botonSeleccionMetodo);

        JPanel panelBotones2 = new JPanel(new GridLayout(1, 5, 10, 10));
        panelBotones2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotones2.setBackground(Color.DARK_GRAY);
        panelBotones2.add(jlFilas);
        panelBotones2.add(inputFilas);
        panelBotones2.add(jlColumnas);
        panelBotones2.add(inputColumnas);
        panelBotones2.add(botonActualizar);

        JPopupMenu menuEmergente = new JPopupMenu();
        JMenuItem recursivo = new JMenuItem("Metodo Recursivo");
        JMenuItem bfs = new JMenuItem("Metodo BFS");
        JMenuItem dp = new JMenuItem("Metodo DP");
        JMenuItem dfs = new JMenuItem("Metodo DFS");

        menuEmergente.add(recursivo);
        menuEmergente.add(bfs);
        menuEmergente.add(dp);
        menuEmergente.add(dfs);

        frame.add(panelBotones2, BorderLayout.NORTH);
        frame.add(panelMatriz, BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.SOUTH);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void actualizarMatriz(int filas, int columnas) {
        panelMatriz.removeAll();
        panelMatriz.setLayout(new GridLayout(filas, columnas));

        matriz = new boolean[filas][columnas];
        botones = new JButton[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = true;
                botones[i][j] = new JButton(" ");
                botones[i][j].setBackground(Color.WHITE);
                final int row = i, col = j;

                botones[i][j].addActionListener(e -> {
                    if (modoObstaculos) {
                        matriz[row][col] = !matriz[row][col];
                        botones[row][col].setBackground(matriz[row][col] ? Color.WHITE : Color.ORANGE);
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

    private void actualizarMatrizDesdeInput() {
        try {
            int filas = Integer.parseInt(inputFilas.getText());
            int columnas = Integer.parseInt(inputColumnas.getText());
            if (filas > 0 && columnas > 0) {
                actualizarMatriz(filas, columnas);
            } else {
                JOptionPane.showMessageDialog(frame, "Ingrese valores positivos.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Ingrese un número válido.");
        }
    }

    private void mostrarRutaAnimada() {
        List<Cell> ruta = encontrarRutaRecursiva();
        if (ruta.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No se encontró una ruta.");
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

    private void mostrarRuta() {
        if (metodoSeleccionado == null) {
            JOptionPane.showMessageDialog(frame, "Debe seleccionar un método antes de ejecutar.",
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
            JOptionPane.showMessageDialog(frame, "No se encontró una ruta.", "Información", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (Cell c : ruta) {
            if (!c.equals(inicio) && !c.equals(fin)) {
                botones[c.row][c.col].setBackground(new Color(49, 139, 49)); 
            }
        }
        String tiempoFormateado = String.format("Tiempo de ejecución: %.2f ms", tiempoEjecucion / 1_000_000.0);
        JOptionPane.showMessageDialog(frame, tiempoFormateado, "Tiempo de Ejecución", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reiniciar() {
        actualizarMatriz(matriz.length, matriz[0].length);
        inicio = null;
        fin = null;
        modoObstaculos = false;
        modoSeleccion = false;
        metodoSeleccionado = null; 
        JOptionPane.showMessageDialog(frame, "El programa se ha reiniciado. Seleccione un método nuevamente.",
                "Reinicio", JOptionPane.INFORMATION_MESSAGE);
    }

    private List<Cell> encontrarRutaRecursiva() {
        List<Cell> ruta = new ArrayList<>();
        if (inicio != null && fin != null) {
            boolean[][] visitado = new boolean[matriz.length][matriz[0].length];
            if (buscarRutaRecursiva(inicio.row, inicio.col, ruta, visitado)) {
                return ruta;
            }
        }
        return Collections.emptyList();
    }

    private boolean buscarRutaRecursiva(int row, int col, List<Cell> ruta, boolean[][] visitado) {
        if (row < 0 || col < 0 || row >= matriz.length || col >= matriz[0].length || !matriz[row][col] || visitado[row][col]) {
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

    private List<Cell> programacionDinamica() {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int[][] dp = new int[filas][columnas];

        if (!matriz[inicio.row][inicio.col] || !matriz[fin.row][fin.col]) {
            return Collections.emptyList();
        }

        dp[inicio.row][inicio.col] = 1;

        for (int i = inicio.row; i < filas; i++) {
            for (int j = inicio.col; j < columnas; j++) {
                if (matriz[i][j]) { 
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

        boolean[][] visitado = new boolean[matriz.length][matriz[0].length];
        if (dfsHelper(inicio.row, inicio.col, ruta, visitado)) {
            return ruta;
        }
        return Collections.emptyList();
    }

    private boolean dfsHelper(int row, int col, List<Cell> ruta, boolean[][] visitado) {
        if (row < 0 || col < 0 || row >= matriz.length || col >= matriz[0].length || !matriz[row][col] || visitado[row][col]) {
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

        boolean[][] visitado = new boolean[matriz.length][matriz[0].length];
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

                if (newRow >= 0 && newCol >= 0 && newRow < matriz.length && newCol < matriz[0].length
                        && matriz[newRow][newCol] && !visitado[newRow][newCol]) {

                    List<Cell> nuevoCamino = new ArrayList<>(camino);
                    nuevoCamino.add(new Cell(newRow, newCol));
                    cola.add(nuevoCamino);
                    visitado[newRow][newCol] = true;
                }
            }
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        new App(1, 1);
    }
}
