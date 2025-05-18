# Kotlin News App

This is a simple Android app that fetches Reddit news articles and displays them in a list with details view.

---

## Technologies Used

- **Jetpack Compose**  
  Android's modern toolkit for building native UI. It lets you create UIs with declarative Kotlin code, making UI development faster and more intuitive.

- **Kotlin Coroutines**  
  Provides a way to write asynchronous, non-blocking code in a sequential manner. Used here to fetch data from network and database without blocking the main thread.

- **Kotlin Flow**  
  A reactive streams API from Kotlin Coroutines. It lets you emit multiple values asynchronously. Used to observe data streams like articles list and UI states.

- **Room**  
  SQLite database library from Jetpack. It provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. Used here for caching articles locally.

- **Hilt**  
  A dependency injection library for Android based on Dagger. Simplifies DI setup and helps manage dependencies like ViewModels, Repositories, etc.

- **Navigation Component**  
  Jetpack's library to manage app navigation. Simplifies passing data between screens, handling backstack, and deep linking.

---

## Architecture: Model-View-Intent (MVI)

This app uses an **MVI-inspired architecture** for managing UI logic and state:

- **Intent**  
  Represents user actions or events (e.g., refresh news, select article). The View sends intents to the ViewModel to process.

- **UI State**  
  A single, immutable data class that fully describes the UI at any given moment (loading, error, showing articles). The View observes this UI state to render content accordingly.

- **Unidirectional Data Flow**  
  The flow of data follows:  
  `Intent (user action) → ViewModel processes intent → Updates UI State → View renders UI`  
  This clear, predictable flow improves maintainability and testability.

---


