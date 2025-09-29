# Sistema de Batalla RPG
Sistema de combate por turnos desarrollado en Java que implementa conceptos de Programación Orientada a Objetos.

## Descripción
Simulador de batalla RPG que permite enfrentar jugadores contra enemigos en combate por turnos. El sistema implementa herencia, polimorfismo y el patrón MVC para una arquitectura limpia y escalable.

## Características
- Sistema de combate por turnos
- Dos roles de jugador: Guerrero y Explorador
- Múltiples tipos de enemigos con versiones normales y jefe
- Habilidades especiales únicas por tipo de combatiente
- Sistema de inventario e ítems consumibles
- Carga de datos desde archivos CSV
- Patrón de diseño MVC

## Instalación y Ejecución

1. Clonar el repositorio
git clone https://github.com/ElDaVz/Ejercicio_4.git
cd Ejercicio_4

Compilar el proyecto
javac src/src/*.java -d out

Ejecutar
bashjava -cp out App

Formato de Archivos CSV:

combatientes.csv
csvtipo,nombre,vida,ataque,esJefe
Orco,Grom,60,18,false
Esqueleto,Bonehead,40,12,false

items.csv
csvnombre,tipo,efecto,descripcion
Poción de Vida,curacion,50,Restaura 50 HP
Poción de Fuerza,ataque,15,Aumenta ataque temporalmente


MVC

Modelo: Batalla, Combatiente, Item
Vista: Vista, Menu
Controlador: ControladorBatalla
