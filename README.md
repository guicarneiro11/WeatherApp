# ☁️ Weather App Android
Android application developed in Java for real-time weather data visualization and favorite cities management, integrating with Weather API.

## 🚀 Technologies Used
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-48B983?style=for-the-badge&logo=square&logoColor=white)
![Room](https://img.shields.io/badge/Room-3DDC84?style=for-the-badge&logo=sqlite&logoColor=white)
![Hilt](https://img.shields.io/badge/Hilt-2196F3?style=for-the-badge&logo=dagger&logoColor=white)
![Material Design](https://img.shields.io/badge/Material_Design-757575?style=for-the-badge&logo=material-design&logoColor=white)

## 📋 Description
Native Android application that allows users to check real-time weather data and manage a list of favorite cities. Developed following best practices in architecture and UI/UX.

## 🛠️ Features
- ✅ Real-time weather data consultation
- ✅ Favorite cities management
- ✅ Local cache for offline functionality
- ✅ UI with Material Design

## 🏗️ Architecture
The application follows Clean Architecture + MVVM:
- **data**: Data layer
  - remote: API and DTOs
  - local: Room Database and Entities
  - repository: Repository implementations
- **domain**: Business rules
  - model: Domain models
  - repository: Repository interfaces
  - usecase: Use cases
- **di**: Dependency injection with Hilt
- **presentation**: UI and ViewModels
  - ui: Activities and Fragments
  - viewmodel: ViewModels and states

## ⚙️ Local Setup
1. Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/weather-app.git
```
2. Configure the local API
```bash
# Run Weather API on localhost:8080
```
3. Run the project
```bash
# Open in Android Studio and run
```

## 📦 Main Dependencies
- Retrofit for API communication
- Room for local persistence
- Hilt for dependency injection
- Navigation Component for navigation
- Material Design Components
- LiveData and ViewModel
- Gson for JSON serialization

## 🔒 Error Handling
- Local cache for offline functionality
- Network error handling
- Visual feedback for users
- Input data validation

## 🎨 UI/UX
- Material Design Guidelines
- Smooth animations and transitions
- Immediate visual feedback

## 📈 Next Steps
- [ ] Unit tests implementation
- [ ] UI tests with Espresso
- [ ] Home screen widgets
- [ ] Weather alert notifications

## 👨‍💻 Author
[Guilherme Carneiro](https://github.com/guicarneiro11) - [@guizaokt](https://x.com/guizaokt)

## 🤝 Integration
This application is part of the Weather App ecosystem:
- [Weather API](https://github.com/guicarneiro11/weather-api)
---
