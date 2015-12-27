# Go Fact
## Arquitectura del sistema
La arquitecura del sistema es una arquitectura Modelo Vista Controlador (MVC)
y que posee una capa de soporte para proporcionar ayuda a la capa de Controlador
que es la intermediaria entre la Vista (que en el esquema se denomina Presentación)
y el Modelo (en la cual se encuentra la capa de persistencia).
![Arquitectura](arquictura.png)

## Principios
Los principios que regirán la grupo son:

### Integración Contínua
Asegurarnos de que el proyecto siga funcionando cuando se realizan
integraciones de las diferentes partes realizadas por los diferentes desarrolladores.

### Desarrollo Llevado por Pruebas (TDD)
Para poder asegurarnos que al momento de hacer un cambio el sistema
siga funcionando y cumpla con sus tareas.

## Reglas para aceptar un cambio
Se deberán seguir las siguientes reglas antes de realizar un cambio:
- El nuevo cambio siempre debe estar en una rama diferente a la principal.
- El proyecto debe compilar y pasar las pruebas en el servicio de integración continua.
- Se deberán corregir todos los comentarios propuestos por el grupo.

## Versionamiento
Se seguirá el siguiento formato para las versiones:
W.X.Y.Z

- W -> Nueva funcionalidad que añada valor al negocio.
- X -> Funcionalidad sobre funcionalidad, es decir, cualquier cosa que ayude o mejore al punto anterior (e.g. Funcionalidad para exportar a XML).
- Y -> Correcciones de issues, bugs, problemas de lógica, etc.
- Z -> Se agregan nuevas clases de soporte.
