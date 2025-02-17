
<p align="center">
  <img src="https://github.com/user-attachments/assets/caa1a648-f9f1-4327-a500-68c62f8420ea" alt="image">
</p>
<h1 aling = "center" >
  Proyecto Final
</h1>

- Carrera: Ingenieria en Ciencias de la Computación
- Materia: Estructura de Datos
- Integrantes: David Esteban Sisa Buestan  'dsisab@est.ups.edu.ec', *Borrar Esto - Escribir Aqui correo y nombres*

## Descripción
 Se plantea hacer un programa donde el usuario pueda crear una matriz, donde pueda seleccionar el metodo de busqueda, los
obstaculos, y tambien el punto de partida, y donde debe terminar. Al final, al iniciar el recorrido debera buscar el 
camino mas adecuado para poder llegar al punto final.

## Propuesta de solución: 
### Marco Teórico : 

**Busqueda en Profundidad:**

- El algoritmo DFS es un método de recorrido de grafos y arboles que explora cada rama completamente antes de retroceder. Utiliza una pila para recordar los nodos visitados.
Este metodo puede aplicarse de forma recursiva.

**Busqueda en Anchura:**

- El algoritmo BFS  recorre un grafo o arbol nivel por nivel, explorando todos los nodos vecinos antes de profundizar. Utiliza una cola para gestionar los nodos por explorar.
Este metodo puede aplicarse de forma recursiva.

__Programación Dinamica:__

 - La Programación Dinámica es una técnica de optimización para resolver problemas dividiéndolos en subproblemas más pequeños y almacenando los resultados para evitar cálculos repetitivos.

### Propuesta  :
<p style="text-align: justify;">
Dividir en un total de 5 clases, dos modelos, un modelo ModeloMatriz, para que la clase Controlador use para el control de la ruta de la interfaz, que a su vez, la clase Vista llamara cuando se 
interactue con un boton. El otro modelo Cell para definir desde que punto se inicia el recorrido y tambien donde termina.
Finalmente, la clase App, que llama a la clase Controlador, instanciandola como objeto, y ejecutando tanto la clase Vista como el modelo ModeloMatriz.
</p>


Se va a utilizar el lenguaje de programación `Java`, donde se aprovechara sus librerias `javax.swing.*`, para el desarrollo de la interfaz grafica. Tambien, los ArrayList, Colecciones, y Listas,
para el desarrollo de la logica del programa, y tambien los modelos, los cuales se encuentra en la libreria `java.util.*`. Todo esto se desarrolla en el editor de codigo VSCode, con esto planeamos
el lugar donde se desarrollara el programa.

### Criterio por estudiantes :
- David Sisa: Planteo que para la matriz y las celdas que se usan en el mismo, pueden ser programadas en Java, ahorrandonos la conexión a otros lenguajes UI, ademas de ello, nos facilitaria el uso
de estas clases como modelos en el controlador para su propio uso.

*AQUI VA EL CRITERIO DE CADA UNO DE USTEDES*

### Desarrollo :
<p style="text-align: justify;">
  La clase ModeloMatriz, como modelo, usa getters y setters, para obtener las filas y columnas, y tambien para agregar valores en los mismos.
  Tambien tiene un modelo llamado LlenarMatriz() que usado para llenar la matriz de un valor booleano.
  En el constructor, se inicializan con parametros Integer, filas columnas,  que dan a lugar una matriz booleana con las filas y columnas con las 
  que se instancian en otra clase. Tan y como se ven en la imagen :
</p>






