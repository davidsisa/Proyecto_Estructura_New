# Informe - Proyecto Final

Integrantes: Alex Paucar, Eduardo Burbano, David Sisa
## Objetivo
Generar una interfaz grafica interactiva, en la que se demuestre la efectividad y eficiencia de metodos de busquedas no 
lineales, donde se efectua los 4 metodosaprendidos a lo largo del interciclo, Recursividad, Programación Dinamica, BFS,
y DFS.
## Introducción
Se plantea hacer un programa donde el usuario pueda crear una matriz, donde pueda seleccionar el metodo de busqueda, los
obstaculos, y tambien el punto de partida, y donde debe terminar. Al final, al iniciar el recorrido debera buscar el 
camino mas adecuado para poder llegar al punto final.
## Estructura de Carpetas

Las siguientes carpetas contienen: 

- `src`: La carpeta contiene todo el codigo fuente, los modelos, la vista, y el controlador, junto a la clase de inicialización App.
- `src/controllers`: La carpeta contiene la clase Controlador, que maneja los metodos, y logica principal de la vista y los modelos.
- `src/models`: La carpeta contiene las clases Cell, y ModeloMatriz, el cual son dos clases usadas tanto por el Controlador como la Vista.
- `src/views`: La carpeta contiene la clase Vista, el cual se encarga de inicializar JFrame y sus componentes necesarios para el GUI.

La carpeta bin, y vscode, son carpetas independientes que no afectan al codigo fuente, mas que solamente cargar componetes adicionales.

## Clases e Interfaces

### Modelos: 
-`Cell`: El modelo Cell representa una celda en una matriz y es usado para el almacenamiento de una posición espeficia de una matriz. Es un 
-modelo exclusivo del Controlador, el cual lo usa para metodos como buscarRutaRecursiva.
-`ModeloMatriz`: El modeo ModeloMatriz es la representación de una matriz, en formato clase, si bien, se pudo hacer una variable de ese tipo,
pero es mejor estructurarlo en una clase. Sirve para instanciar un objeto tipo Matriz, el cual nos facilita el bloqueo o desbloqueo de una 
celda para la creación de obstaculos, puntos de incicio, o recorrer la ruta. Tambien es usada como el modelo de metodos de tipos de recorridos
como Recursiva y BFS.

### Vistas: 
-`Vista`: La clase Vista maneja todas las interfaces graficas, y metodos que tengan que ver con ello, la mayoria de codigo, es gracias a la 
libreria javax.swing.*, que permite manejar botones, ventanas, paneles, y scrolls, para la ejecución de la logica general.
Tiene una instancia de la clase Controlador, esto para invocar metodos del mismo, y añadirlos a los botones con el ActionListener().

### Controladores:

-`Controlador`: La clase Controlador es el pilar detras de la logica de las clases Vistas y Modelos, es capaz de unirlos para manipularlos, 
y asi crear la estructura que rige en todo el programa. Contiene muchas variables exclisivas como las variables booleanas que usan metodos,
que se incian al usar un boton, y tambien las instancias de ModeloMatriz, y la Vista. Mas adelante se profundizara en los metodos usados en
ella.
