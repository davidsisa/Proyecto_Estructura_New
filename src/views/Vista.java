package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import controllers.Controlador;

public class Vista  {
    private JFrame frame;
    private JLabel lblResultados;
    
    private JTextField inputFilas, inputColumnas;
    private Controlador control;

    public Vista(int filas, int columnas, Controlador control){
        
        this.control = control;
        inicializarUi(filas, columnas);
    }
    public void inicializarUi(int filas, int columnas){
        frame = new JFrame("Matriz de Botones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        control.actualizarMatriz(filas, columnas);
        // Crear el JLabel para mostrar resultados
        lblResultados = new JLabel("Resultados aparecerán aquí");
        frame.add(lblResultados, BorderLayout.SOUTH);

        JButton botonObstaculos = new JButton("Ingresar Obstáculos");
        botonObstaculos.addActionListener(e -> {
            control.setModoObstaculos(true);
            control.setModoSeleccion(false);
        });

        JButton botonSeleccion = new JButton("Seleccionar A y B");
        botonSeleccion.addActionListener(e -> {
            control.setModoObstaculos(false);
            control.setModoSeleccion(true);
        });

        JButton botonRecorrido = new JButton("Iniciar Recorrido");
        botonRecorrido.addActionListener(e -> {
            if (control.getMetodoSeleccionado() == null) {
                JOptionPane.showMessageDialog(frame, "Método no seleccionado. Por favor, elija un método de búsqueda.");
            } else {
                control.mostrarRutaAnimada(); 
            }
        });

        JButton botonTiempo = new JButton("Tiempo de Ejecucion");
        botonTiempo.addActionListener(e -> {
            if (control.getMetodoSeleccionado()== null) {
                JOptionPane.showMessageDialog(frame, "Método no seleccionado. Por favor, elija un método de búsqueda.");
            } else {
                control.mostrarRuta();  
            }
        });

        JButton botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.addActionListener(e -> control.reiniciar());

        JButton botonSeleccionMetodo = new JButton("Seleccionar Método");
        botonSeleccionMetodo.addActionListener(e -> control.seleccionarMetodo());

        JLabel jlFilas = new JLabel("Filas: ");
        jlFilas.setForeground(Color.WHITE);
        JLabel jlColumnas = new JLabel("Columnas: ");
        jlColumnas.setForeground(Color.white);

        inputFilas = new JTextField(5);
        inputColumnas = new JTextField(5);
        JButton botonActualizar = new JButton("Crear");
        botonActualizar.addActionListener(e -> control.actualizarMatrizDesdeInput());

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
        frame.add(control.getPanelMatriz(), BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.SOUTH);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getInputFilas() {
        return inputFilas;
    }
    public void setInputFilas(JTextField inputFilas) {
        this.inputFilas = inputFilas;
    }
    public JTextField getInputColumnas() {
        return inputColumnas;
    }
    public void setInputColumnas(JTextField inputColumnas) {
        this.inputColumnas = inputColumnas;
    }
    

}
