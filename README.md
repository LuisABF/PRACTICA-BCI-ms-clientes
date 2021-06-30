# Clientes
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

_Aplicación de creación de usuarios expuesta por API RESTful_

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

### Pre-requisitos 📋

_Para contruir y correr la aplicación usted necesita:_

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

### Autenticación :key:
_Se implementó una capa de seguridad usando **JWT**._

|     RUT     | Password |
|------------------|----------|
|`18180945`         |`password`|

|                                          URL                        | Método |                    Observaciones                    | Respuesta |
|---------------------------------------------------------------------|--------|-----------------------------------------------|---------------------------|
|`localhost:8010/login`                            | POST   |Bearer Token, Actualiza Token generado       | [JSON]         |

#### Ejemplo validación JSON Request Body

##### **/api/auth/login**
```json
{
    "rut":"18180945",
    "password":"password"
}
```

Respuesta
```json
{
    "rut": "18180945",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMTgxODA5NDUiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjI1MDcwODEwLCJleHAiOjE2MjUwNzE0MTB9.ydyQUk_ymGgSW7IKfsSWVFnkwkQsPNlLhWCZNX-MBwocp7GXWDXEpfUjPZ7j_pZD239ksORjk5JTrCGHNEHlog"
}
```


## Construido con 🛠️

_Herramientas para crear el proyecto_

* [IntelliJ IDEA](https://www.jetbrains.com/) - IDE Desarrollo
* [Maven](https://maven.apache.org/) - Manejador de dependencias

## Autores ✒️

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **Luis Bascuñán Flores** - *Trabajo Inicial*

## Licencia 📄

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.

---
⌨️ con ❤️ por [luxooo0](https://github.com/luxooo0) 😊
