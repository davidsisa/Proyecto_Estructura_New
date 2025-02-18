
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

Dividir en un total de 5 clases, dos modelos, un modelo ModeloMatriz, para que la clase Controlador use para el control de la ruta de la interfaz, que a su vez, la clase Vista llamara cuando se 
interactue con un boton. El otro modelo Cell para definir desde que punto se inicia el recorrido y tambien donde termina.

Finalmente, la clase App, que llama a la clase Controlador, instanciandola como objeto, y ejecutando tanto la clase Vista como el modelo ModeloMatriz.

El controlador manejara variables unicas como una variable capaz de saber cuando un usuario toca un boton y ejecutar una orden, en el caso de ser la selección de inicio y fin de recorrido serian 
de tipo Cell.

Las dos Instancias de la clase Vista y Modelos, para conectarlos y al llamarlos en el App, se puedan iniciar directamente.

Dos variables booleanas capaces de activar metodos, cuando el usuario pulse un boton cuando quiera poner obstaculos o seleccionar el tipo de busqueda. Y finalmente, un String para manejar que metodo
selecciono el usuario.

En la Vista, simplemente se cargan botones, paneles, y ventanas. Y tiene un constructor que inicializa un objeto tipo Controlador, capaz de llamar a los metods publicos de esta clase. E implementar
en la interfaz.



Se va a utilizar el lenguaje de programación `Java`, donde se aprovechara sus librerias `javax.swing.*`, para el desarrollo de la interfaz grafica. Tambien, los ArrayList, Colecciones, y Listas,
para el desarrollo de la logica del programa, y tambien los modelos, los cuales se encuentra en la libreria `java.util.*`. Y de usar `JOptionPane` para mandar alertas al usuario y mejorar su experiencia.
Aparte de librerias de excepciones y errores para mandar alertas al usuario.

Todo esto se desarrolla en el editor de codigo VSCode, con esto planeamos el lugar donde se desarrollara el programa.

### Criterio por estudiantes :
- David Sisa: Planteo que para la matriz y las celdas que se usan en el mismo, pueden ser programadas en Java, ahorrandonos la conexión a otros lenguajes UI, ademas de ello, nos facilitaria el uso
de estas clases como modelos en el controlador para su propio uso.

*AQUI VA EL CRITERIO DE CADA UNO DE USTEDES*

### Desarrollo :
  El modelo ModeloMatriz: 
  
  Contiene un atributo privado tipo matriz booleano.
  
  La clase ModeloMatriz, como modelo, usa getters y setters, para obtener las filas y columnas, y tambien para agregar valores en los mismos.
  Tambien tiene un modelo llamado LlenarMatriz() que usado para llenar la matriz de un valor booleano.
  
  En el constructor, se inicializan con parametros Integer, filas columnas,  que dan a lugar una matriz booleana con las filas y columnas con las 
  que se instancian en otra clase.
  
  Tal y como se ven en la imagen :


<p align="center">
  <img src="https://github.com/user-attachments/assets/8787be79-4302-4a85-8b53-59f10e5455ad" alt="image">
</p>

  El modelo Cell:

  Contiene dos atributos publicos que son arrows y colums.

  Solo tiene un constructor, donde tiene parametros Integer para arrow y col, donde se guardan en las variables del modelo.

<p align="center">
  <img src="https://github.com/user-attachments/assets/5a3fe024-d213-4c32-99fd-811ad34f702c" alt="image">
</p>
  La clase Controlador:

  Contiene en total 8 variables privadas :
  
  Las variables vista y matrizObj, son instancias de las clases Vista y ModeloMatriz, respectivamente. Usadas para cargar el contenido de otras clases, y unirlos.
  
  Las variables, ambas instancias de Cell, son inicio y fin inicializadas como nulo, usadas en metodos de selección.
  
  La variable panelMatriz y botones, son instancias de las clases pertenecientes a la libreria javax.swing.*, JPanel y JButton respectivamente.
  
  Las dos variables booleanas para la selecion inicializadas como falsas ambas, modoObstaculos, y modoSeleccion.

  Y el constructor que inicializa vista y modelo.
  
  Contiene en total 12 metodos entre publicas y privadas:
  
  Cada metodo se programo con el fin de llamarlos desde la vista, aqui va un resumen de la funcionalidad de cada metodo explicado del controlador.

  A medida que se fue programando se fue pensando en nuevas ideas de metodos para dar funcionalidad a los botones de la vista. Y sobre todo a los metodos de
  busqueda.

  Aqui esta la explicación del desarrollo de cada metodo con su respectivo codigo: 
  
  El Metodo  seleccionarMetodos() sin parametros. Contiene Strings y sobretodo JOptionPanes, capaz de enviar mensaje a los usuarios.
  ![image](https://github.com/user-attachments/assets/320d6627-e12b-46a3-b858-7ba22e1b8cfe)


  El Metodo  actualizarMatriz() con dos parametros Integer filas y columnas. Remueve los objetos que contiene el panel e inicializa las dos variables matrizObj y botones.
  Con bucles para llenar con booleanos la matriz y con colores los botones.

  El Metodo  actualizarMatrizDesdeInput() capaz de llamar a actualizarMatriz() cuando se valide que las filas y columnas sean diferentes de 0, tenga numeros positivos o  no errores.
  Por ello, utiliza un try and catch. Con el error NumberFormatException.

  El Metodo reiniciar() capaz de anular el valor de las variables globales y restaurar los ajustes preterminados. 


  El Metodo  mostrarRuta() capaz de validar el tiempo de ejecución de cada metodo que seleccione el usuario, tambien tiene un switch que se ejecuta cuando la variable metodoSeleccionado
  sea verdadero.

  El Metodo  mostrarRutaAnimada() cambia el color de los botones, segun el recorrido que hace la matriz, y de que tipo de busqueda. Contiene un sleep para que el programa tenga una 
  impresion de colores moderada y sea visible para el usuario.

  El Metodo programaciónDinamica() inicializa una matriz dp donde cada celda almacena la cantidad de caminos posibles hasta ese punto. Si no hay caminos posibles hasta la celda de fin, retorna una lista vacía.
  Para reconstruir el camino, retrocede desde la celda de fin hasta la de inicio, siguiendo las celdas con caminos acumulados.

  El Metodo buscarDFS() utiliza recursión para explorar cada posible dirección desde el punto de inicio, marca las celdas visitadas para evitar ciclos, y si  se alcanza la celda de fin, el camino se agrega a la       lista de resultados. Tiene un metodo llamado DFSHELPER el cual es el metodo que valida recursivamente los visitados y recorridos

  El Metodo  buscarBFS() que utiliza una cola para gestionar los nodos por explorar y que en cada iteración, explora las celdas adyacentes y las agrega a la cola si no han sido visitadas.
  
  El Metodo encotrarRutaRecurvia() , capaz de ejecutar un metodo privado buscarRutaRecursiva(), donde se utiliza la programación recursiva hasta que se encuentre
  la ruta.

  En total estos son los metodos pero tambien tenemos los setters y getters de algunos parametros con el fin de que alguna clase necesite utilizarlos, en este caso la vista. En este caso, son los paneles.
  Y cuando los metodos de seleccion son ejecutados.
  ![image](https://github.com/user-attachments/assets/b3539834-1b63-4637-b476-db96692663fe)


  

  
  
  








  







