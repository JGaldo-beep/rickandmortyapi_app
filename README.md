# 📱 Rick and Morty App

Una aplicación Android desarrollada en **Jetpack Compose** que consume datos de la **Rick and Morty API**, mostrando personajes con su imagen, nombre, especie y género.

Esta app está construida siguiendo una **arquitectura limpia**: `UI ➔ ViewModel ➔ Repository ➔ API`, con **inyección de dependencias manual** y conexión a internet usando **Retrofit** + **Kotlinx Serialization**.

---

## 📸 Capturas de Pantalla

| 🔃 Loading | ✅ Success | ❌ Error |
|--------------|------------------|-------------|
| <img src="https://github.com/user-attachments/assets/ffc24f91-fb40-4ddd-8255-8b94f51295a3" width="200"/> | <img src="https://github.com/user-attachments/assets/64a4eedd-7be3-43a5-94d3-e7008f51f756" width="200"/> | <img src="https://github.com/user-attachments/assets/16942d7e-e464-42d3-9f26-beb6e83cd0b8" width="200"/> |

---

## 🛠️ ¿Qué puedes hacer en esta aplicación?

- 🔍 Conectarte a internet y traer una lista en vivo de personajes.
- 📄 Mostrar nombre, especie, género e imagen de cada personaje.
- 🚀 Cargar imágenes de manera eficiente desde URL.
- 🛡️ Manejar errores y estados de carga de forma segura.

---

## ✨ Tecnologías utilizadas

- **Jetpack Compose** - UI declarativa moderna para Android
- **ViewModel + StateFlow** - Gestión de estado reactivo
- **Retrofit** - Cliente HTTP para conectarse a la API REST
- **Kotlinx Serialization** - Conversión automática de JSON a objetos de Kotlin
- **Coil** - Carga de imágenes en Compose
- **Inyección de dependencias manual** - `AppContainer` + `Application`
- **Rick and Morty API** - Fuente de datos públicos gratuitos

---

## 🧩 Arquitectura del proyecto
Application 

│ 

├── AppContainer (Inyección manual) 

  │ 

  └── Retrofit + Repository 

│ 

├── ViewModel 

  │ 
  
  └── Lógica de UI (Loading, Success, Error) 

│ 

├── UI (Compose) 

│ 

  └── LazyColumn que muestra personajes 
  
  │ 

└── API pública 

└── Rick and Morty API

---

## 📚 Recursos útiles
[Rick and Morty API docs](https://rickandmortyapi.com/documentation/)

---

## 🚀 Cómo correr el proyecto

1. 📦 Clona el repositorio:
   ```bash
    git clone https://github.com/JGaldo-beep/rickandmortyapi_app
   ```
2. 📁 Abre el archivo con Android Studio o clona desde el IDE (Preferible):

   Clone Repository > `Inserta la URL del repositorio` > Clone

3. ▶️ Corre la app en un Emulador o tu Móvil

---

## 👀 Ten en cuenta

1. Son muchos datos, puedes cortar el internet en medio del cargado y verás la foto del cargado (loading).
2. Si quieres ver la pantalla de error de conexión, puedes colocar en modo avión tu teléfono antes de correr la app.
3. Desde la pantalla de error de conexión, vuelve a conectarte a internet y dale click al botón "Retry". Se vuelven a cargar las imágenes.
