# Smima Mobile

A native Android mobile application for smart milk maker device control and monitoring.

## Prerequisites

- Android Studio (latest version)
- JDK 8 or newer
- Android SDK with minimum API level 24 (Android 7.0)
- Android device or emulator running Android 7.0 or newer

## Setup Instructions

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Open the project in Android Studio:
- Launch Android Studio
- Select "Open an existing project"
- Navigate to the project directory and click "OK"

3. Let Android Studio sync the project with Gradle files

4. Run the app:
- Select your target device (emulator or connected physical device)
- Click the "Run" button or press Shift + F10

## Project Structure

```
app/
  src/
    main/
      java/com/example/smima/
        auth/           # Authentication related classes
        device/         # Device connection and control
        health/         # Health logging functionality
        settings/       # App and device settings
        MainActivity.java
        SplashActivity.java
      res/
        layout/        # XML layout files
        values/        # Resources (strings, colors, etc.)
        drawable/      # Images and icons
```

## Main Features

1. **Authentication**
   - Login with email and password
   - User registration
   - Password recovery

2. **Device Management**
   - Wi-Fi device connection
   - Device status monitoring
   - Multiple device support

3. **Main Functionality**
   - Milk powder scanning (barcode)
   - Water temperature control
   - Mixture volume settings
   - Preparation timing

4. **Settings**
   - Water level monitoring
   - Temperature control
   - Device maintenance
   - Child lock settings

5. **Health Logging**
   - Multiple children profiles
   - Feeding schedule tracking
   - Growth monitoring
   - Health status logging

## Technical Implementation

### Activities
- `SplashActivity`: App entry point with logo display
- `AuthActivity`: Handles login and registration with ViewPager2
- `MainActivity`: Main app interface with bottom navigation
- `AddDeviceActivity`: Wi-Fi device setup wizard
- `SettingsActivity`: App and device configuration
- `HealthLogActivity`: Child health monitoring

### Key Components
- Material Design components for UI
- ViewPager2 for auth screen navigation
- ConstraintLayout for responsive layouts
- Room Database for local storage
- Retrofit for API communication
- ZXing for barcode scanning

### Data Architecture
- MVVM (Model-View-ViewModel) pattern
- LiveData for reactive UI updates
- Repository pattern for data management
- SharedPreferences for app settings

### Security
- Encrypted local storage
- Secure API communication
- OAuth2 authentication
- Device verification

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

[Add your license information here]
