# Tragamonedas
Pequeño juego de tragamonedas con interfaz Swing.

## Ejecutar desde VS Code
- Ya está configurado para incluir librerías:
  - `.vscode/settings.json` referencia `Tragamonedas/lib/**/*.jar`.
  - También existe `Run Principal with AbsoluteLayout` en `.vscode/launch.json`.
- Abre `vista/Principal.java` y usa el botón Run en el método `main`.

## Notas de recursos
- Imágenes y audios se cargan con `getClass().getResource(...)`.
- Si agregas nuevas imágenes/sonidos, colócalas en:
  - `src/Imagenes/` (íconos y fondos)
  - `src/Figuras/` (figuras de carretes 1..7.jpeg)
  - `src/sonidos/` (WAV)
- Vuelve a ejecutar el build para empaquetarlas en el JAR.

## Autores
- Angelo Emmanuel Flores Montes
- Luis Enrique Machorro Tronco
