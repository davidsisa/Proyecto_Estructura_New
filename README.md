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
- `Cell`: El modelo Cell representa una celda en una matriz y es usado para el almacenamiento de una posición espeficia de una matriz. Es un 
modelo exclusivo del Controlador, el cual lo usa para metodos como buscarRutaRecursiva.
- `ModeloMatriz`: El modeo ModeloMatriz es la representación de una matriz, en formato clase, si bien, se pudo hacer una variable de ese tipo,
pero es mejor estructurarlo en una clase. Sirve para instanciar un objeto tipo Matriz, el cual nos facilita el bloqueo o desbloqueo de una 
celda para la creación de obstaculos, puntos de incicio, o recorrer la ruta. Tambien es usada como el modelo de metodos de tipos de recorridos
como Recursiva y BFS.

### Vistas: 
- `Vista`: La clase Vista maneja todas las interfaces graficas, y metodos que tengan que ver con ello, la mayoria de codigo, es gracias a la 
libreria javax.swing.*, que permite manejar botones, ventanas, paneles, y scrolls, para la ejecución de la logica general.
Tiene una instancia de la clase Controlador, esto para invocar metodos del mismo, y añadirlos a los botones con el ActionListener().

### Controladores:

- `Controlador`: La clase Controlador es el pilar detras de la logica de las clases Vistas y Modelos, es capaz de unirlos para manipularlos, 
y asi crear la estructura que rige en todo el programa. Contiene muchas variables exclisivas como las variables booleanas que usan metodos,
que se incian al usar un boton, y tambien las instancias de ModeloMatriz, y la Vista. Mas adelante se profundizara en los metodos usados en
ella.

## Atributos y Metodos

### Getters, Setters y Constructores: 

Getters, y Setters son metodos clasicos en la programación con Java, por lo general, abundan en los modelos, ya que las
otras clases las usan para manejar la logica de cada metodo, nada fuera de lo comun, Getters y Setters estan presentes
en Controlador y Vista, ya que ambas clases usan y modifican atributos.

En la clase Controlador, solamente se inicializo `JPanel` esto debido a que Java cargaba esta parte del interfaz como null, y no se iniciaba
correctamente, por eso se desplazo al controlador.
En la clase Vista, se inicializo la clase Control, para tener acceso a sus metodos.

### Controlador - seleccionarMetodo(): 

Logica del boton de selección del tipo de busqueda que elige el usuario, donde simplemente maneja JOptionPanes, para mandar alertar al usuario,
de cual metodo de busqueda eligio, se valida null, si el usuario no escoge, manda un mensaje de alerta.

### Controlador - actualizarMatriz(): 

Logica de actualización de la matriz, y el componente JPanel, donde se remueve objetos sobre el. Se inicializan la matriz, se configuran los botones,
y se llena la matriz con verdadero, al igual de crear botones sobre ellos. Y refresca la iterfaz.

### Controlador - actualizarMatriz(): 

Logica encargado de leer las dimensiones de la matriz desde los JTextField, y si son validos los mismos, llama al metodo actualizarMatriz(), este es
usado en la interfaz.

### Controlador - reiniciar():

Logica del boton de Limpiar, que restaura los valores preterminados de las variables booleanas usados en el controlador, y manda un mensaje.

### Controlador - mostrarRuta():

Logica que manda una alerta al usuario para que seleccione un metodo de busqueda antes de inciar el programa, ademas, de utilizar la varaible metodoSeleccionado
en un switch para ejecutar un metodo segun lo que elija el usuario. Tambien se encarga de manejar el boton de medicion de tiempo del metodo de busqueda elegido.
Ademas, de verificar si se encontro una ruta.

### Controlador - mostrarRutaAnimada():

Logica del Boton de la clase Vista botonRecorrido, donde se aplica colores como el verde cuando se marca el camino, y manda mensajes de alerta si no encuentra el 
camino.


### Modelo - llenarMatriz():

Logica para llenar al objeto Matriz de `true`, esto para ahorrarnos metodos innecesarios en el controlador.

### Vista - iniciarUI():

Logica que es ejecutada directamente al instanciar el objeto tipo Vista en el controlador. Crea metodos, JFrames, y botones que llaman a los metodos del controlador.

# Metodos de Busqueda:

- Búsqueda en Profundidad:
El método buscarDFS() utiliza búsqueda en profundidad  para explorar el camino de manera recursiva. Se mueve en todas las direcciones posibles y retrocede si encuentra un obstáculo o un camino sin salida. Es eficiente en grafos pequeños pero puede no ser óptimo en encontrar el camino más corto.

- Búsqueda en Anchura:
El método buscarBFS() usa búsqueda en anchura , explorando primero los caminos más cercanos a la celda de inicio. Utiliza una cola  para procesar las celdas en orden de distancia. Garantiza encontrar la ruta más corta si todas las celdas tienen el mismo costo.

- Programación Dinámica:
El método programacionDinamica() construye una matriz de valores acumulativos , donde cada celda almacena la cantidad de formas en que puede alcanzarse desde el punto de inicio. Luego, reconstruye la ruta óptima retrocediendo desde el punto de destino. Es útil en casos donde se busca contar caminos en lugar de solo encontrar uno.

- Búsqueda Recursiva:
El método buscarRutaRecursiva() es una implementación de búsqueda recursiva simple. Intenta moverse en todas las direcciones desde la celda actual y retrocede si encuentra un obstáculo o una celda visitada. Similar a DFS, pero más directo en su estructura.


