# Venue Locator

**Venue Locator** is an Android application designed to help users find nearby venues based on their current location. Utilizing modern location services, Hilt for dependency injection, and Coroutines for asynchronous programming, the app provides a seamless experience for discovering places around you.

## Features

- **Location Permissions**: Automatically requests and handles location permissions to ensure the user’s current location can be accessed.
- **Location Fetching**: Utilizes Google's FusedLocationProviderClient to obtain the most accurate location data.
- **Venue Display**: Shows a list of nearby venues with details such as name, address, and category.
- **Error Handling**: Comprehensive error handling for scenarios like permission denial and location fetching failures.
- **User-Friendly UI**: Intuitive user interface with RecyclerView for listing venues and a PopupMenu for detailed venue information.

## Technical Details

- **MVVM Architecture**: Clean separation of concerns using Model-View-ViewModel architecture.
- **Dependency Injection**: Powered by Dagger Hilt for easy management of dependencies.
- **Asynchronous Programming**: Utilizes Kotlin Coroutines for efficient and non-blocking background tasks.
- **LiveData and ViewModel**: Reactive UI updates and lifecycle-aware components.
- **LocationUtil Class**: Handles all location-related tasks, including checking location settings, requesting permissions, and fetching the current location.
- **Resource Handling**: Utilizes a Resource wrapper class to manage the state of network calls and data.

## Getting Started

To get a local copy up and running, follow these simple steps:

1. **Clone the repository**:
   ```sh
   git clone https://github.com/ahmedkenawy/CFH.git

2. **Open the project in Android Studio:**
    File > Open > Select the cloned repository.
    Build and run the project:

3. **Connect an Android device or start an emulator.**
  Click the "Run" button in Android Studio.
