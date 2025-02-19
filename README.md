
<p align="center">
  <img src="https://github.com/user-attachments/assets/caa1a648-f9f1-4327-a500-68c62f8420ea" alt="image">
</p>
<h1 aling = "center" >
  Proyecto Final
</h1>

- Carrera: Ingenieria en Ciencias de la Computación
- Materia: Estructura de Datos
- Integrantes: David Esteban Sisa Buestan  'dsisab@est.ups.edu.ec', Erick Alexander Vizhñay Paucar 'evizhnayp@est.ups.edu.ec, Eduardo Urbano 'eburbanof@est.ups.edu.ec'

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

__Recursión:__
-Recursión: La recursión es una técnica en la que una función se llama a sí misma para resolver un problema dividiéndolo en subproblemas más pequeños. En este proyecto, se utiliza para encontrar rutas en la matriz explorando cada posible dirección hasta llegar al destino. Aunque es un enfoque elegante y sencillo, puede ser menos eficiente en términos de memoria, ya que cada llamada recursiva añade información a la pila de ejecución.

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
  
  Contiene en total 12 metodos entre publicas y privadas:
  

  A medida que se fue programando se fue pensando en nuevas ideas de metodos para dar funcionalidad a los botones de la vista. Y sobre todo a los metodos de
  busqueda.

  Aqui esta la explicación del desarrollo de cada metodo con su respectivo codigo: 
  
  El Metodo  seleccionarMetodos() sin parametros. Contiene Strings y sobretodo JOptionPanes, capaz de enviar mensaje a los usuarios. 
  
<p align="center">
  <img src="https://github.com/user-attachments/assets/aa80e9c8-b3c5-42f2-8fe0-022036f7b2d5" alt="image">
</p>


  El Metodo  actualizarMatriz() con dos parametros Integer filas y columnas. Remueve los objetos que contiene el panel e inicializa las dos variables matrizObj y botones.
  Con bucles para llenar con booleanos la matriz y con colores los botones.

  <p align="center">
  <img src="https://github.com/user-attachments/assets/50c88086-a9e2-4265-8317-d8753e4c7bd1" alt="image">
  </p>

  El Metodo  actualizarMatrizDesdeInput() capaz de llamar a actualizarMatriz() cuando se valide que las filas y columnas sean diferentes de 0, tenga numeros positivos o  no errores.
  Por ello, utiliza un try and catch. Con el error NumberFormatException.

  El Metodo reiniciar() capaz de anular el valor de las variables globales y restaurar los ajustes preterminados. 
  
<p align="center">
  <img src="https://github.com/user-attachments/assets/4b7a3038-262c-4dc2-9ee6-8d1017ef1b52" alt="image">
</p>

  El Metodo  mostrarRuta() capaz de validar el tiempo de ejecución de cada metodo que seleccione el usuario, tambien tiene un switch que se ejecuta cuando la variable metodoSeleccionado
  sea verdadero.
  
<p align="center">
  <img src="https://github.com/user-attachments/assets/5fd14336-fb1b-4924-8017-836e528d17b1" alt="image">
</p>

  El Metodo  mostrarRutaAnimada() cambia el color de los botones, segun el recorrido que hace la matriz, y de que tipo de busqueda. Contiene un sleep para que el programa tenga una 
  impresion de colores moderada y sea visible para el usuario.

 <p align="center">
  <img src="https://github.com/user-attachments/assets/47939e6f-6d20-4fcc-b92f-9e6f6b31d20f" alt="image">
</p>


  El Metodo programaciónDinamica() inicializa una matriz dp donde cada celda almacena la cantidad de caminos posibles hasta ese punto. Si no hay caminos posibles hasta la celda de fin, retorna una lista vacía.
  Para reconstruir el camino, retrocede desde la celda de fin hasta la de inicio, siguiendo las celdas con caminos acumulados.

  Este metodo usa el memo.put() para que se recuerde si una celda lleva al destino o no. Con esto se evita recorrer el mismo camino.
  
  <p align="center">
  <img src="https://github.com/user-attachments/assets/a5dce68a-6f87-4ca6-b08d-4ad13dcd1020" alt="image">
</p>


  El Metodo buscarDFS() utiliza recursión para explorar cada posible dirección desde el punto de inicio, marca las celdas visitadas para evitar ciclos, y si  se alcanza la celda de fin, el camino se agrega a la       lista de resultados. Tiene un metodo llamado DFSHELPER el cual es el metodo que valida recursivamente los visitados y recorridos.

  El metodo usa backtracking, si no encuentra el camino, retrocede , esto estan en el metodo DFSHELPER.
   
  
<p align="center">
  <img src="https://github.com/user-attachments/assets/b0740513-1f9c-40ed-b1e5-19d4f7cee87a" alt="image">
</p>

  El Metodo  buscarBFS() que utiliza una cola para gestionar los nodos por explorar y que en cada iteración, explora las celdas adyacentes y las agrega a la cola si no han sido visitadas.
  Como es por BFS, explora por niveles, y no vuelve a sus pasos. Por ello, no tiene backtracking.
<p align="center">
  <img src="https://github.com/user-attachments/assets/d570b1a7-b0aa-4c26-afce-0e30c5c42019" alt="image">
</p>

  El Metodo encotrarRutaRecurvia() , capaz de ejecutar un metodo privado buscarRutaRecursiva(), donde se utiliza la programación recursiva hasta que se encuentre
  la ruta.

  El metodo aplica backtracking,
  en donde al topar con un obstaculo, se devuelve para poder recorrer otras rutas.

  En este caso intenta avanzar en todas las direcciones posibles.
  
  <p align="center">
  <img src="https://github.com/user-attachments/assets/5fddae7a-8821-4986-9d1c-e7ccde2b4360" alt="image">
  </p>

  En total estos son los metodos pero tambien tenemos los setters y getters de algunos parametros con el fin de que alguna clase necesite utilizarlos, en este caso la vista. En este caso, son los paneles.
  Y cuando los metodos de seleccion son ejecutados.

  En cuanto a la vista, simplemente se carga las librerias de interfaz grafica, y se añade la logica de posicion, color e inputs de cada componente, y se llama los metodos del controlador.
  
<p align="center">
  <img src="https://github.com/user-attachments/assets/2ef7282b-8335-4891-be7b-3cfca2b50cfa" alt="image">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/35c87f85-de17-40a6-a03b-f3c6608802e5" alt="image">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/27ab599f-0bc6-4b9d-b8d1-0a0de1814af1" alt="image">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/aa491b49-60c6-4f8d-957a-1c0ecd71d207" alt="image">
</p>


## Resultados:

![image](https://github.com/user-attachments/assets/68768a6f-de9b-4ebe-8bba-f78b95cfb8ed)

![image](https://github.com/user-attachments/assets/af5447a1-c6eb-46cd-9647-41d403116ba2)

![image](https://github.com/user-attachments/assets/16077d56-a7b5-490d-8a15-c11328eae2a3)




## Conclusiones:
David Sisa: En conclusión, el método más eficaz es el método de busqueda por anchura, debido a su concepto y aplicación dentro del programa. Esto porque garantiza encontrar el camino en término de numerosos pasos, ya que explora todos los nodos a la misma distancia del nodo de inicio antes de moverse más lejos. 

Alexander Vizhñay: Se observará que BFS es el más eficiente para encontrar el camino más corto, mientras que DFS puede ser menos óptimo debido a su exploración profunda sin priorizar distancias. La Recursión ofrece una implementación intuitiva, pero consume más memoria en casos grandes, y la Programación Dinámica optimiza los cálculos almacenando resultados intermedios.

Eduardo Burbano: En este proyecto, implementamos métodos de búsqueda y backtracking para la resolución de laberintos. Uno de los mayores retos fue integrar estos métodos en una interfaz gráfica siguiendo MVC. Sin embargo, logramos concluir exitosamente el desarrollo y analizar el comportamiento de los cuatro métodos de búsqueda implementados. Tras la evaluación de los resultados, determinamos que el método BFS es el más óptimo para este caso, debido a su lógica de exploración sistemática y eficiencia en la búsqueda de la solución.

## Aplicaciones: 

El codigo es una muestra de la eficiencia de cada metodologia de busqueda, en las conclusiones ponemos que en este ejercicio el mas optimo es BFS, debido a su corta ejecución, es por eso que depende de cada situación el uso de las tecnicas de busqueda. En este caso pueden ser aplicados en navegación y mapas, cuando se quiere buscar la ruta mas corta. Al igual que la busqueda de archivos, que tambien es una opción.
Y en contexto de paginas web, las busquedas de las mismas, y busqueda por filtros.











  
  
  








  







