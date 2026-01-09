# Tragamonedas 🎰
Juego de tragamonedas con interfaz Swing, persistencia MySQL.

## Autores 👥
- Angelo Emmanuel Flores Montes
- Luis Enrique Machorro Tronco

## Requisitos ✅
- Java 21 (o compatible)
- Apache Ant
- VS Code (opcional) con extensiones de Java (Java Pack)

## Construir el proyecto 🛠️
```bash
ant -f Tragamonedas/build.xml clean jar
```
Esto genera el ejecutable en `Tragamonedas/dist/Tragamonedas.jar` y copia las dependencias a `Tragamonedas/dist/lib/`.

## Ejecutar (JAR ejecutable) 🚀
```bash
cd Tragamonedas
java -jar dist/Tragamonedas.jar
```
Estructura de distribución:
- `dist/Tragamonedas.jar`
- `dist/lib/AbsoluteLayout.jar`
- `dist/lib/mysql-connector-j-8.3.0.jar`

También tienes un paquete listo para compartir:
- `Tragamonedas/Tragamonedas-dist.zip` (contiene `dist/` y un README de uso).


## Recursos (imágenes y sonidos) 🖼️🔊
- Se cargan con `getClass().getResource(...)` para que funcionen dentro del JAR.
- Si agregas nuevos archivos:
  - Imágenes: `Tragamonedas/src/Imagenes/`
  - Figuras carretes (1..7.jpeg): `Tragamonedas/src/Figuras/`
  - Sonidos (WAV): `Tragamonedas/src/sonidos/`
- Vuelve a construir el JAR para incluirlos.

## Problemas comunes (y solución) 🧩
- `ClassNotFoundException: org.netbeans.lib.awtextra.AbsoluteLayout` → Ejecuta con el JAR de distribución o asegúrate de tener `AbsoluteLayout.jar` en `dist/lib/` (Ant lo copia automáticamente).
- Imágenes que no aparecen → Verifica que estén en las carpetas anteriores y reconstruye con Ant.
