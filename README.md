
<p align="center">
  <img src="https://github.com/user-attachments/assets/caa1a648-f9f1-4327-a500-68c62f8420ea" alt="image">
</p>
<p align="center">
  <h1>
  Proyecto Final
  </h1>
</p>


- Carrera: Ingenieria en Ciencias de la Computación
- Materia: Estructura de Datos
- Integrantes: David Esteban Sisa Buestan  'dsisab@est.ups.edu.ec'

## Descripción
- Se plantea hacer un programa donde el usuario pueda crear una matriz, donde pueda seleccionar el metodo de busqueda, los
obstaculos, y tambien el punto de partida, y donde debe terminar. Al final, al iniciar el recorrido debera buscar el 
camino mas adecuado para poder llegar al punto final.

## Desarrollo 
Modelos: 
Las clase cell, contiene dos variables publicas Iteger que representan columnas y finasl. Al igual de un constructor donde se inicializan las filas y columas, tal y como se ve en la imagen.
![image](https://github.com/user-attachments/assets/7f676ded-9226-4ddc-b9e8-4a4c3d2e2bd7)


# Metodos de Busqueda:

- Búsqueda en Profundidad:
El método buscarDFS() utiliza búsqueda en profundidad  para explorar el camino de manera recursiva. Se mueve en todas las direcciones posibles y retrocede si encuentra un obstáculo o un camino sin salida. Es eficiente en grafos pequeños pero puede no ser óptimo en encontrar el camino más corto.

- Búsqueda en Anchura:
El método buscarBFS() usa búsqueda en anchura , explorando primero los caminos más cercanos a la celda de inicio. Utiliza una cola  para procesar las celdas en orden de distancia. Garantiza encontrar la ruta más corta si todas las celdas tienen el mismo costo.

- Programación Dinámica:
El método programacionDinamica() construye una matriz de valores acumulativos , donde cada celda almacena la cantidad de formas en que puede alcanzarse desde el punto de inicio. Luego, reconstruye la ruta óptima retrocediendo desde el punto de destino. Es útil en casos donde se busca contar caminos en lugar de solo encontrar uno.

- Búsqueda Recursiva:
El método buscarRutaRecursiva() es una implementación de búsqueda recursiva simple. Intenta moverse en todas las direcciones desde la celda actual y retrocede si encuentra un obstáculo o una celda visitada. Similar a DFS, pero más directo en su estructura.


