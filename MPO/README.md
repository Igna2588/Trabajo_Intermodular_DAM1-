Ampliación de Programación
Aplicación de Gestión de Gastos Familiares
Autor: Ignacio Arroyo


¿Qué mejoras he aplicado respecto al módulo de Programación?

En este módulo he mejorado el diseño del proyecto aplicando conceptos 
de Programación Orientada a Objetos y buenas prácticas de desarrollo.

1. Arquitectura por capas

He separado el proyecto en capas con responsabilidades claras:

model: Clases que representan los datos (Miembro, Movimiento, Categoria)
controller: Lógica de negocio y conexión con la base de datos
resources/gastos: Interfaz de usuario con FXML y CSS separados del código Java
Main: Punto de entrada de la aplicación

2. Diseño orientado a objetos

He aplicado los siguientes conceptos de POO:

Encapsulación: Todos los atributos son privados con getters
Constructores: Cada clase tiene su constructor completo
Métodos con responsabilidad clara: Cada método hace una sola cosa
Separación de responsabilidades: La vista no contiene lógica de negocio

3. Separación de estilos con CSS

Los estilos visuales están en un archivo CSS separado del código Java.
Esto hace el código más limpio y fácil de mantener.

4. Uso de FXML

La interfaz de usuario está definida en un archivo FXML separado del 
código Java, siguiendo el patrón MVC (Modelo Vista Controlador).

5. Mejoras en la base de datos

Se ha añadido la columna es_fijo para distinguir gastos fijos de variables
Se ha creado la tabla saldo_acumulado para llevar un control del ahorro

6. Funcionalidades extra

Gastos fijos que se registran una vez y se mantienen cada mes
Separación visual de ingresos y gastos en listas distintas
Los ahorros cambian de color según sean positivos o negativos
Fecha automática al registrar un movimiento
