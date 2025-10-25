Actividad Semanal 12 - Patrones de Diseño
Objetivo
Construir una aplicación de consola en Java que simule el funcionamiento básico de una tienda online aplicando los patrones de diseño:

Observer — para notificaciones automáticas.
Decorator — para agregar características a productos dinámicamente.
Facade — para simplificar el proceso de compra (checkout).
Singleton — para manejo centralizado de logs y configuración.
Estructura del Proyecto
src/
├── tienda/
│   ├── main/
│   ├── models/
│   ├── observer/
│   ├── decorator/
│   ├── facade/
│   ├── singleton/
│   ├── util/
│   └── Cart.java / Inventory.java

Instrucciones de Ejecucion
Abrir NetBeans y crear un nuevo proyecto Java.

Copiar la estructura de carpetas y clases anteriores.

Compilar y ejecutar el archivo principal:

javac tienda/main/Main.java
java tienda.main.Main
Menu
Menu

Aplicacion de los Patrones
Observer
Implementado para actualizar el total del carrito automáticamente cada vez que se agrega o elimina un producto.

También genera alertas cuando el stock se agota.

Se usó una interfaz Observer con el método update() y una clase Cart como Subject.

Ejemplo salida
Observer

Decorator
Permite agregar funcionalidades extra a un producto sin modificar su clase base.

Decoradores creados:

GiftWrapDecorator: agrega envoltura de regalo (+Q5.00)

WarrantyDecorator: agrega garantía extendida (+10%)

DiscountDecorator: aplica descuento (-X%)

Ejemplo salida
Decorator

Facade
La clase CheckoutFacade simplifica el proceso de compra, agrupando varios pasos internos:

Revisión del carrito

Aplicación de descuentos

Cobro del pago

Generación del recibo

Registro del evento

Ejemplo salida
Facade

Singleton
Se creó la clase AppLogger como instancia única del sistema para registrar eventos.

Garantiza que todo el programa use una sola instancia del logger.

Se usa para registrar acciones relevantes como agregar productos o finalizar compras.

Ejemplo Salida
Singleton



Reflexion Final
El patrón más difícil de aplicar fue el Decorator, ya que requiere una buena comprensión de cómo extender dinámicamente objetos sin modificar la clase original. En este proyecto, los patrones se complementan entre sí:

El Observer mantiene actualizada la información en tiempo real,

El Decorator añade flexibilidad a los productos,

El Facade simplifica el flujo del proceso de compra,

Y el Singleton asegura un registro centralizado de todos los eventos del sistema.

En conjunto, hacen que la aplicación sea modular, escalable y fácil de mantener.



https://drive.google.com/file/d/1oZFEX0IBZ7hq5W4KXgCu_hRjhfGFVdIm/view?usp=drive_link

