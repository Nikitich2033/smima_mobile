# Smima Mobile App Architecture

## Overview
Smima is a smart baby formula preparation system consisting of a physical device and mobile applications. The system enables precise formula preparation, health tracking, and device management through a secure, real-time connection. The system focuses on providing accurate temperature control, precise measurements, and safe formula preparation for infants.

## System Components

### 1. Physical Device
- **Microcontroller**: ESP32 with Wi-Fi capabilities
- **Features**:
  - Precision temperature control (±0.5°C accuracy)
  - Ultrasonic water level sensors
  - Load cell for powder measurement (±0.1g accuracy)
  - UV-C sterilization system
  - Infrared bottle detection
  - Electronic child-lock mechanism
  - 3.5" TFT LCD touch display
  - Water filtration system
  - Heating element (1100W)
  - Powder dispensing mechanism
- **Communication**: 
  - Wi-Fi 802.11 b/g/n for cloud connectivity
  - Bluetooth LE 4.2 for direct device setup
- **Physical Specifications**:
  - Water tank capacity: 1.2L
  - Powder container capacity: 600g
  - Power supply: 220V-240V AC
  - Preparation time: 1-3 minutes

### 2. Cloud Infrastructure
- **Backend Services** (AWS):
  - API Gateway for RESTful endpoints
  - Lambda functions for serverless computing
  - DynamoDB for device data and real-time status
  - RDS (PostgreSQL) for user data and health records
  - IoT Core for MQTT device communication
  - Cognito for user authentication
  - S3 for firmware updates and media storage
  - CloudWatch for monitoring and alerts
  - SNS for push notifications
  - Route53 for DNS management

### 3. Mobile Applications
- **Android Native App**
- **iOS Native App**
- **Shared Features**:
  - User authentication and profile management
  - Multi-device management
  - Formula preparation with presets
  - Health tracking and analytics
  - Device maintenance notifications
  - Firmware update management
  - Offline operation capability

## Database Schema

### User Database (PostgreSQL)
```sql
-- Users table
CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    password_hash VARCHAR(255),
    phone_number VARCHAR(20),
    preferred_language VARCHAR(10),
    notification_settings JSONB,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Children profiles
CREATE TABLE children (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    name VARCHAR(255),
    birth_date DATE,
    weight_at_birth DECIMAL(5,2),
    current_weight DECIMAL(5,2),
    height DECIMAL(5,2),
    allergies TEXT[],
    notes TEXT,
    updated_at TIMESTAMP
);

-- Feeding logs
CREATE TABLE feeding_logs (
    id UUID PRIMARY KEY,
    child_id UUID REFERENCES children(id),
    device_id VARCHAR(255),
    timestamp TIMESTAMP,
    formula_type VARCHAR(255),
    formula_brand VARCHAR(255),
    volume_ml INTEGER,
    temperature_celsius DECIMAL(4,1),
    preparation_duration INTEGER,
    success BOOLEAN,
    error_code VARCHAR(50),
    notes TEXT
);

-- Health logs
CREATE TABLE health_logs (
    id UUID PRIMARY KEY,
    child_id UUID REFERENCES children(id),
    timestamp TIMESTAMP,
    weight DECIMAL(5,2),
    height DECIMAL(5,2),
    temperature DECIMAL(4,1),
    stool_status VARCHAR(50),
    sleep_hours DECIMAL(4,1),
    notes TEXT
);

-- Formula presets
CREATE TABLE formula_presets (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    name VARCHAR(255),
    formula_type VARCHAR(255),
    temperature_celsius DECIMAL(4,1),
    volume_ml INTEGER,
    powder_grams DECIMAL(5,1),
    notes TEXT,
    created_at TIMESTAMP
);
```

### Device Database (DynamoDB)
```json
{
  "Devices": {
    "device_id": "string (primary key)",
    "user_id": "string",
    "name": "string",
    "model": "string",
    "status": {
      "online": "boolean",
      "water_level": "number",
      "powder_level": "number",
      "current_temperature": "number",
      "target_temperature": "number",
      "sterilization_status": "string",
      "filter_life_remaining": "number",
      "last_sterilized": "timestamp",
      "last_maintenance": "timestamp",
      "error_code": "string"
    },
    "settings": {
      "default_temperature": "number",
      "child_lock": "boolean",
      "auto_sterilize": "boolean",
      "night_mode": "boolean",
      "sound_enabled": "boolean",
      "maintenance_reminder": "boolean"
    },
    "statistics": {
      "total_preparations": "number",
      "total_volume_prepared": "number",
      "total_operation_time": "number",
      "last_filter_change": "timestamp",
      "last_descaling": "timestamp"
    },
    "firmware": {
      "current_version": "string",
      "update_available": "boolean",
      "last_update_check": "timestamp",
      "update_status": "string"
    },
    "network": {
      "wifi_ssid": "string",
      "wifi_strength": "number",
      "ip_address": "string",
      "last_connected": "timestamp"
    }
  }
}
```

## Tech Stack

### Android Implementation
- **Language**: Java
- **Architecture**: Fragment-based with Material Design
- **Components**:
  - AndroidX AppCompat for compatibility
  - Material Components for UI
  - Navigation Component for fragment management
  - ViewModel for UI state management
  - LiveData for observable data patterns
  - ViewBinding for view access
  - SharedPreferences for local storage
  - Room for offline data caching
  - WorkManager for background tasks
  - Android Bluetooth LE API for device communication
  - ZXing for formula barcode scanning
  - Firebase Cloud Messaging for push notifications
- **Key Features**:
  - Biometric authentication
  - Device pairing and management
  - Formula preparation workflow
  - Real-time device status monitoring
  - Health tracking interface
  - Offline operation support
  - Multi-language support
- **Project Structure**:
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/smima/
│   │   │   ├── auth/           # Authentication components
│   │   │   │   ├── AuthActivity.java
│   │   │   │   ├── AuthPagerAdapter.java
│   │   │   │   ├── LoginFragment.java
│   │   │   │   └── SignupFragment.java
│   │   │   ├── home/           # Home screen components
│   │   │   │   ├── HomeFragment.java
│   │   │   │   └── RecentActivityAdapter.java
│   │   │   ├── preparation/    # Formula preparation
│   │   │   │   └── PreparationFragment.java
│   │   │   ├── health/         # Health tracking
│   │   │   │   ├── HealthFragment.java
│   │   │   │   └── HealthLogActivity.java
│   │   │   ├── device/         # Device management
│   │   │   │   ├── DeviceFragment.java
│   │   │   │   └── AddDeviceActivity.java
│   │   │   ├── settings/       # App settings
│   │   │   │   └── SettingsActivity.java
│   │   │   ├── MainActivity.java
│   │   │   └── SplashActivity.java
│   │   ├── res/
│   │   │   ├── drawable/       # Icons and images
│   │   │   ├── layout/         # UI layouts
│   │   │   ├── menu/          # Menu definitions
│   │   │   ├── values/        # Resources (strings, colors, etc.)
│   │   │   └── xml/           # Other XML resources
│   │   └── AndroidManifest.xml
│   └── test/                  # Unit tests
└── build.gradle              # App-level build config
```

### iOS Implementation
- **Language**: Swift
- **Architecture**: MVVM + Clean Architecture
- **Components**:
  - SwiftUI for UI
  - Combine for reactive programming
  - URLSession for API communication
  - Core Data for local storage
  - Core Bluetooth for device communication
  - AVFoundation for barcode scanning
- **Testing**:
  - XCTest for unit tests
  - XCUITest for UI tests

## Device Communication Protocol

### 1. Device Setup
```sequence
Mobile App -> Device: BLE Discovery
Device -> Mobile App: Device Info
Mobile App -> Device: Wi-Fi Credentials
Device -> Cloud: Connect & Register
Cloud -> Mobile App: Device Ready
```

### 2. Formula Preparation
```sequence
Mobile App -> Cloud: Preparation Request
Cloud -> Device: Preparation Instructions
Device -> Cloud: Status Updates
Cloud -> Mobile App: Real-time Progress
Device -> Cloud: Completion Status
Cloud -> Mobile App: Preparation Complete
```

## Security Measures

### Device Security
- Secure boot
- Encrypted firmware updates
- Secure element for key storage
- TLS 1.3 for all communications
- Certificate-based authentication

### Mobile App Security
- Biometric authentication
- OAuth 2.0 + OpenID Connect
- Certificate pinning
- Encrypted local storage
- Secure key storage (Keychain/KeyStore)

### Cloud Security
- AWS WAF for API protection
- VPC isolation
- KMS for key management
- CloudTrail for audit logging
- Regular security assessments

## Error Handling

### Device Errors
- Connection loss recovery
- Sensor malfunction detection
- Emergency stop procedures
- Auto-recovery mechanisms

### App Errors
- Offline mode capabilities
- Retry mechanisms
- User-friendly error messages
- Error reporting to analytics

## Monitoring and Analytics

### Device Metrics
- Water quality
- Temperature accuracy
- Component health
- Usage patterns

### App Analytics
- User engagement
- Feature usage
- Error rates
- Performance metrics

## Future Enhancements

### Phase 1 (Q2 2024)
- Voice commands
- Smart home integration
- Multiple device synchronization

### Phase 2 (Q3 2024)
- AI-powered recommendations
- Predictive maintenance
- Community features

### Phase 3 (Q4 2024)
- Healthcare provider integration
- Advanced analytics
- International market support 