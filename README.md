# Smima Mobile


# React Native App Plan 

## Dependencies

- React Navigation (for navigation between screens)
- Redux or Context API (for state management)
- Firebase/Backend API (for authentication and data storage)
- Axios (for API calls)
- Native Modules (for Wi-Fi connection and device integration)

## Folder Structure:

```
src/
  components/ (Reusable components)
  screens/ (All screen components)
  navigation/ (React Navigation configuration)
  redux/ (State management files)
  utils/ (Helper functions)
  assets/ (Images and fonts)
```

## Main Screens and Functionalities

1. **SplashScreen.js**

   - Displays the company logo during app load.
2. **AuthScreen.js**

   - Options for "Log In" and "Sign Up".
   - **Log In:**
     - Fields: Email, Password.
     - Button: "Log In", Link: "Forgot Password".
   - **Sign Up:**
     - Fields: First Name, Last Name, Phone (optional), Country, Email, Confirm Email, Password.
     - Checkbox for Terms of Service.
     - Button: "Continue".
3. **AddDeviceScreen.js**

   - Step 1: Select Wi-Fi network.
   - Step 2: Device connection process screen with progress indication.
   - Step 3: Completion screen with options to name the device and set it as default.
   - Button: "Confirm".
4. **MainScreen.js (Home Screen)**

   - Inputs for:
     - Milk powder details (scan barcode or manual input).
     - Water temperature and mixture volume (auto-set based on powder details).
     - Time for preparation.
   - Button: "Start Preparation".
5. **SettingsScreen.js**

   - Displays:
     - Water level, temperature, and milk powder quantity.
     - Device status (Wi-Fi signal, child lock, presence of bottle, scale buildup).
   - Button: "Run Self-Diagnostics" (checks device parameters).
6. **HealthLogScreen.js**

   - Allows entry of:
     - Child's name and birth date (supports multiple children).
     - Feeding details (time, volume, weight, body temperature, stool status).
   - Automatically logs feeding times based on timers.

## Navigation

- **Stack Navigator:**
  - SplashScreen -> AuthScreen -> Main App
- **Bottom Tab Navigator (inside Main App):**
  - MainScreen, AddDeviceScreen, SettingsScreen, HealthLogScreen

## Redux State Structure (Example):

```json
{
  "user": {
    "id": "string",
    "name": "string",
    "email": "string"
  },
  "devices": [
    {
      "id": "string",
      "name": "string",
      "default": "boolean",
      "status": {
        "waterLevel": "number",
        "temperature": "number",
        "milkQuantity": "number",
        "wifiSignal": "boolean",
        "childLock": "boolean",
        "bottlePresent": "boolean",
        "scaleBuildup": "boolean"
      }
    }
  ],
  "healthLogs": {
    "[childId]": [
      {
        "date": "string",
        "volume": "number",
        "weight": "number",
        "bodyTemp": "number",
        "stoolStatus": "string"
      }
    ]
  }
}
```

## Example Code for Screens

### SplashScreen.js

```javascript
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const SplashScreen = () => (
  <View style={styles.container}>
    <Text style={styles.logo}>Company Logo</Text>
  </View>
);

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: '#fff' },
  logo: { fontSize: 32, fontWeight: 'bold' },
});

export default SplashScreen;
```
