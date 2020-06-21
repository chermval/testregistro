# TEST REGISTRO
Servicio REST que almacena la información del una persona (nombre completo y fecha de cumpleaños) y retorna la información almacenada junto a la edad y mensaje de felicitaciónes en caso de estar de cumpleaños.

## Requisitos
- Java 8

## Instalación
1. Clonar repositorio https://github.com/chermval/testregistro.git 
2. Acceder a la carpeta descargada `cd testregistro`
3. Ejecutar el comando `./gradlew build` 
4. Ejecutar comando `./gradlew bootrun` para deployar en http://localhost:8080 
5. Ejecutar comando `./gradlew test`  para ejecutar solo pruebas 

## Rutas REST
Registros

| METODO  | URI               | EJEMPLO  URL                          | PARAMETROS                 | 
|---------|-------------------|---------------------------------------|----------------------------|
| `POST`  | `/v1/registries`  |  http://localhost:8080/v1/registries/ |body params: { "names":"pablo ", "lastNames": "perez", "birthday":"1990-01-01" } | 
| `GET`   | `/v1/registries`  |  http://localhost:8080/api/v1/persons |                            |
