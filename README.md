# 🧱 Microservicios: Product y Orders

Este proyecto implementa una arquitectura de microservicios utilizando **Java 17** y **Spring Boot**. Se divide en dos servicios principales:

- **Product Service**: gestiona productos (creación, consulta, actualización y eliminación).
- **Orders Service**: permite registrar órdenes, verificando previamente si hay stock disponible para los productos seleccionados.

Ambos servicios se comunican entre sí a través de **HTTP REST** y están protegidos mediante **autenticación básica**. Se utilizó **Docker** para la configuración de la base de datos PostgreSQL.

---

## 📋 Requisitos

- Java 17+
- Maven
- Docker (opcional pero recomendado)
- PostgreSQL (si no se usa Docker, instalar localmente)

---

## 🚀 Configuración del proyecto

1. **Clonar el repositorio**

```bash
git clone https://github.com/tuusuario/tu-repo.git
```

2. **Levantar la base de datos con Docker**

Desde la raíz del proyecto ejecutar:

```bash
docker-compose up -d
```

> Asegurate de que el puerto 5431 no esté ocupado y que los nombres de los contenedores no choquen con otros proyectos.

3. **Compilar y ejecutar los microservicios**

En IntelliJ o vía consola:

- `cd product`
- `mvn spring-boot:run`

Y en otra terminal:

- `cd orders`
- `mvn spring-boot:run`

---

## ✅ Documentación Swagger

Ambos microservicios tienen documentación interactiva con Swagger:

- 📦 **Product Service**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  
- 🧾 **Orders Service**: [http://localhost:8084/swagger-ui/index.html](http://localhost:8084/swagger-ui/index.html)  

> Usuario: `admin`  
> Contraseña: `0000`

---

## 📌 Uso de los microservicios

### 🛒 Product Service

Permite gestionar productos con los siguientes campos:

- `name`
- `description`
- `price`
- `stock`
- `status`
- `categoryId`

### 📦 Orders Service

Permite registrar órdenes solo si el producto existe y tiene stock suficiente.

### 🧪 Ejemplo de orden válida

```json
[
  {
    "name": "Pedido de Jimena",
    "productId": 3,
    "stock": 5
  }
]
```

---

## 💫 Comunicación entre microservicios

Orders se comunica con Product usando **RestTemplate**. Al crear una orden:

- Verifica si el producto existe:  
  `GET http://localhost:8080/api/products/{id}`
- Valida si el stock disponible es suficiente.

Si el producto no existe o no tiene stock, la orden **no se crea** y se devuelve un error.

---

## 🔐 Seguridad

- Ambos servicios usan **autenticación básica** (`admi:0000`).
- Configurado mediante Spring Security con usuarios en memoria.

---

## ✨ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- Docker
- Swagger

---

## 🛠 Estructura del proyecto

```
microservicesTF/
├── product/
│   └── src/main/java/com/ucc/product/
├── orders/
│   └── src/main/java/com/ucc/orders/
├── docker-compose.yml
├── pom.xml (padre)
```

---

## 👥 Autores

Proyecto realizado por: **Jimena Reverendo y Fabrizio Fasoli**
