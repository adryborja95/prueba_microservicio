# Prueba Microservicio – Simulación de Inversión

**Realizado por:** Adriana Borja  

Este proyecto corresponde al desarrollo de un **microservicio en Spring Boot** que permite gestionar usuarios, productos financieros y realizar simulaciones de inversión, aplicando conceptos de arquitectura de software, persistencia de datos y contenedores Docker.

El sistema calcula automáticamente la mejor combinación de productos financieros que un usuario puede adquirir según su capital disponible, mostrando la ganancia esperada y métricas de rendimiento.

---

## Descripción del Proyecto

El microservicio implementa las siguientes funcionalidades principales:

- Gestión de usuarios con capital disponible.
- Gestión de productos financieros activos.
- Simulación de inversión basada en el capital del usuario.
- Cálculo de ganancias, retorno porcentual y eficiencia del capital.
- Persistencia en base de datos PostgreSQL.
- Despliegue mediante Docker y Docker Compose.

---

## 🛠 Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 4**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL 15**
- **Maven**
- **Docker**
- **Docker Compose**
- **IntelliJ IDEA**

---

## Instalación y Ejecución

### Requisitos Previos
- Docker y Docker Compose instalados
- Git (opcional)

---

### 🔹 Ejecución con Docker (Recomendado)

1. Clonar el repositorio:
```bash
git clone <URL_DEL_REPOSITORIO>
cd prueba_microservicio
```

2. construir y levantar los contenedores
```bash
docker compose up --build
```

la aplicacion estara disponible en http://localhost:8080

## Endpoints de la Aplicación

Base URL:
http://localhost:8080

---

### GET /usuarios
Lista todos los usuarios registrados en el sistema.

URL:
http://localhost:8080/usuarios

Método:
GET

Body:
No requiere body.

Respuesta ejemplo:

[
  {
    "id": "c81f040a-3363-4c4b-811a-dc1848ebc981",
    "nombre": "Juan Pérez",
    "email": "juan.perez@email.com",
    "capitalDisponible": 5000.00
  },
  {
    "id": "a32f0b47-9d0c-4a22-b9f7-6f0c9b1a92c3",
    "nombre": "María García",
    "email": "maria.garcia@email.com",
    "capitalDisponible": 8000.00
  }
]


### GET /productos

Lista todos los productos financieros activos.

URL:
http://localhost:8080/productos

Método:
GET

Body:
No requiere body.

Respuesta ejemplo:
[
  {
    "id": "11111111-1111-1111-1111-111111111111",
    "nombre": "Fondo Acciones Tech",
    "descripcion": "Fondo de inversión en acciones tecnológicas",
    "costo": 1000.00,
    "porcentajeRetorno": 8.50,
    "activo": true
  },
  {
    "id": "22222222-2222-2222-2222-222222222222",
    "nombre": "Bonos Corporativos AAA",
    "descripcion": "Bonos corporativos de alta calificación",
    "costo": 500.00,
    "porcentajeRetorno": 5.25,
    "activo": true
  }
]

### POST SIMULACIONES

Realiza una simulación de inversión en base al capital disponible y los productos enviados.

URL:
http://localhost:8080/simulaciones

Método:
POST

Headers:
Content-Type: application/json

Body:

{
  "usuarioId": "c81f040a-3363-4c4b-811a-dc1848ebc981",
  "capitalDisponible": 3000.00,
  "productos": [
    {
      "nombre": "Fondo Acciones Tech",
      "precio": 1000.00,
      "porcentaje_ganancia": 8.50
    },
    {
      "nombre": "Bonos Corporativos AAA",
      "precio": 500.00,
      "porcentaje_ganancia": 5.25
    },
    {
      "nombre": "ETF Global",
      "precio": 1500.00,
      "porcentaje_ganancia": 12.00
    },
    {
      "nombre": "Fondo de Dividendos",
      "precio": 800.00,
      "porcentaje_ganancia": 6.75
    }
  ]
}


RESPUESTA EJEMPLO:

{
  "id": "b30e1a04-e3b0-4cc8-89fa-cc8805e98ec9",
  "usuarioId": "c81f040a-3363-4c4b-811a-dc1848ebc981",
  "fechaSimulacion": "2026-02-02T21:04:04.0539714",
  "capitalDisponible": 3000.00,
  "productosSeleccionados": [
    {
      "nombre": "ETF Global",
      "precio": 1500.00,
      "porcentaje_ganancia": 12.00,
      "ganancia_esperada": 180.00
    },
    {
      "nombre": "Fondo Acciones Tech",
      "precio": 1000.00,
      "porcentaje_ganancia": 8.50,
      "ganancia_esperada": 85.00
    }
  ],
  "costoTotal": 2500.00,
  "capitalRestante": 500.00,
  "gananciaTotal": 265.00,
  "retornoTotalPorcentaje": 10.60,
  "eficienciaCapital": 83.33,
  "mensaje": "Simulación exitosa con ganancias óptimas"
}


### GET /simulaciones/{usuarioId}

Consulta el historial de simulaciones realizadas por un usuario.

URL:
http://localhost:8080/simulaciones/c81f040a-3363-4c4b-811a-dc1848ebc981

Método:
GET

Body:
No requiere body.

Respuesta ejemplo:

[
  {
    "id": "b30e1a04-e3b0-4cc8-89fa-cc8805e98ec9",
    "fechaSimulacion": "2026-02-02T21:04:04.0539714",
    "capitalDisponible": 3000.00,
    "gananciaTotal": 265.00
  }
]



