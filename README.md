# Clientes
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

_Aplicación de creación de usuarios expuesta por API RESTful_

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

### Pre-requisitos 📋

_Para contruir y correr la aplicación usted necesita:_

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle](https://gradle.org/)

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
## REST API

Ejemplos uso de Rest Api

### Listar usuarios
### Request

`GET /usuarios`

    localhost:8010/usuarios

### Response

    HTTP/1.1 200 OK
    {
        "rut": 18180945,
        "nombre": "Arturo",
        "clave": "12323434",
        "email": "luis@algo.cl",
        "contactos": []
    }

### Nuevo usuario

### Request

`POST /usuarios`

    localhost:8010/usuarios


```json
{
    "rut": 18180947,
    "nombre":"Arturo",
    "clave":"Luis22",
    "email":"",
    "contactos":[
        {
            "numero":"8903475",
            "codigoCiudad":"9",
            "codigoPais":"56"
        },
                {
            "numero":"1103475",
            "codigoCiudad":"9",
            "codigoPais":"56"
        }
    ]
}
```   

### Response

    HTTP/1.1 201 Created


## Obtener usuario específico 

### Request

`GET /usuarios/id`

     localhost:8010/usuarios/18180947

### Response
```json
    {
    "rut": 18180947,
    "nombre": "Arturo",
    "clave": "Luis22",
    "email": "luis@algo.cl",
    "contactos": [
        {
            "numero": "8903475",
            "codigoCiudad": "9",
            "codigoPais": "56"
        },
        {
            "numero": "1103475",
            "codigoCiudad": "9",
            "codigoPais": "56"
        }
    ]
}
``` 

## Eliminar usuario específico 

### Request

`DELETE /usuarios/id`

     localhost:8010/usuarios/18180947

### Response
`HTTP/1.1 204 No Content`

## Si el usuario no existe

### Request

`GET /usuarios/id`

    localhost:8010/usuarios/18180947

### Response
```json
{
    "statusCode": "404",
    "message": "No existe el recurso"
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
