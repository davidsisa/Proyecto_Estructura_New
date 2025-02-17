
<p align="center">
  <img src="https://github.com/user-attachments/assets/caa1a648-f9f1-4327-a500-68c62f8420ea" alt="image">
</p>
<h1 aling = "center" >
  Proyecto Final
</h1>

- Carrera: Ingenieria en Ciencias de la Computación
- Materia: Estructura de Datos
- Integrantes: David Esteban Sisa Buestan  'dsisab@est.ups.edu.ec'

## Descripción
- Se plantea hacer un programa donde el usuario pueda crear una matriz, donde pueda seleccionar el metodo de busqueda, los
obstaculos, y tambien el punto de partida, y donde debe terminar. Al final, al iniciar el recorrido debera buscar el 
camino mas adecuado para poder llegar al punto final.

## Propuesta de solución: 
### Marco Teórico : 

- Busqueda en Profundidad:
El método buscarDFS() utiliza búsqueda en profundidad  para explorar el camino de manera recursiva. Se mueve en todas las direcciones posibles y retrocede si encuentra un obstáculo o un camino sin salida. Es eficiente en grafos pequeños pero puede no ser óptimo en encontrar el camino más corto.

- Búsqueda en Anchura:
El método buscarBFS() usa búsqueda en anchura , explorando primero los caminos más cercanos a la celda de inicio. Utiliza una cola  para procesar las celdas en orden de distancia. Garantiza encontrar la ruta más corta si todas las celdas tienen el mismo costo.

- Programación Dinámica:
El método programacionDinamica() construye una matriz de valores acumulativos , donde cada celda almacena la cantidad de formas en que puede alcanzarse desde el punto de inicio. Luego, reconstruye la ruta óptima retrocediendo desde el punto de destino. Es útil en casos donde se busca contar caminos en lugar de solo encontrar uno.




