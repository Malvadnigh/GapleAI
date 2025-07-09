# Gaple Strategist - Aplikasi Android 15 untuk Bermain Gaple/Domino

Aplikasi lengkap untuk membantu bermain gaple (domino) baik di dalam game maupun di dunia nyata. Dilengkapi dengan AI strategy advisor, pengenalan domino melalui kamera, dan sistem scoring yang komprehensif.

## Fitur Utama

### 🎮 Game Assistant
- **Kamera Recognition**: Scan domino secara real-time untuk mendapat saran strategi
- **AI Strategy Advisor**: Rekomendasi langkah terbaik berdasarkan situasi permainan
- **Game State Tracking**: Melacak ujung kiri dan kanan permainan
- **Manual Input**: Input domino secara manual jika diperlukan

### 📊 Score Management
- **Multi-Player Scoring**: Mendukung 2-4 pemain
- **Real-time Score Update**: Update skor secara langsung
- **Game History**: Menyimpan riwayat permainan
- **Win Statistics**: Statistik kemenangan per pemain

### 📚 Rules & Strategy
- **Aturan Lengkap**: Panduan lengkap bermain gaple
- **Strategy Tips**: Tips dan trik bermain gaple
- **Domino Values**: Referensi nilai semua domino
- **Scoring System**: Penjelasan sistem penilaian

### 🎨 Modern UI/UX
- **Material Design 3**: Antarmuka modern sesuai Android 15
- **Dark/Light Theme**: Mendukung tema gelap dan terang
- **Responsive Layout**: Optimal untuk berbagai ukuran layar
- **Intuitive Navigation**: Navigasi yang mudah dipahami

## Screenshots
*(Aplikasi siap digunakan dengan antarmuka yang intuitif)*

## Instalasi & Build

### Prasyarat
- **Android Studio**: Arctic Fox atau lebih baru
- **Android SDK**: API Level 35 (Android 15)
- **Java**: JDK 11 atau lebih baru
- **Gradle**: 8.0+

### Langkah Build

1. **Clone Repository**
   ```bash
   git clone <repository-url>
   cd Gaple_Strategist_Android_Project
   ```

2. **Buka di Android Studio**
   - Buka Android Studio
   - File → Open
   - Pilih folder project

3. **Sync Dependencies**
   - Android Studio akan otomatis sync Gradle
   - Tunggu hingga selesai

4. **Build APK**
   ```bash
   # Debug build
   ./gradlew assembleDebug
   
   # Release build
   ./gradlew assembleRelease
   ```

5. **Install ke Device**
   ```bash
   # Via ADB
   adb install app/build/outputs/apk/debug/app-debug.apk
   
   # Atau via Android Studio
   Run → Run 'app'
   ```

### Build APK Manual

1. **Prepare Release**
   - Buat keystore untuk signing
   - Update `app/build.gradle` dengan signing config

2. **Generate APK**
   ```bash
   ./gradlew assembleRelease
   ```

3. **Lokasi APK**
   ```
   app/build/outputs/apk/release/app-release.apk
   ```

## Struktur Project

```
app/
├── src/main/
│   ├── java/com/gaplestrategist/
│   │   ├── MainActivity.java
│   │   ├── model/
│   │   │   ├── Domino.java
│   │   │   ├── Player.java
│   │   │   ├── GapleGame.java
│   │   │   └── GameHistory.java
│   │   ├── fragments/
│   │   │   ├── GameFragment.java
│   │   │   ├── ScoreFragment.java
│   │   │   └── RulesFragment.java
│   │   └── adapters/
│   │       ├── ViewPagerAdapter.java
│   │       ├── ScoreAdapter.java
│   │       ├── GameHistoryAdapter.java
│   │       └── DominoValueAdapter.java
│   ├── res/
│   │   ├── layout/
│   │   ├── drawable/
│   │   ├── values/
│   │   └── menu/
│   └── AndroidManifest.xml
├── build.gradle
└── proguard-rules.pro
```

## Permissions

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.INTERNET" />
```

## Target Devices

- **Minimum SDK**: API 26 (Android 8.0)
- **Target SDK**: API 35 (Android 15)
- **Architecture**: ARM64, ARMv7
- **RAM**: Minimum 2GB

## Teknologi yang Digunakan

- **Language**: Java
- **UI**: Material Design 3
- **Camera**: CameraX
- **Storage**: SharedPreferences + Gson
- **Architecture**: Fragment-based with ViewPager2
- **Navigation**: Bottom Navigation + Drawer

## Cara Penggunaan

### 1. Game Mode
- Buka tab "Game"
- Tap "New Game" untuk memulai
- Arahkan kamera ke domino untuk mendapat saran
- Ikuti rekomendasi strategi yang diberikan

### 2. Score Tracking
- Buka tab "Score"
- Input skor pemain secara real-time
- Lihat riwayat permainan di bagian bawah

### 3. Rules & Reference
- Buka tab "Rules"
- Pelajari aturan dan strategi gaple
- Lihat referensi nilai domino

## Kontribusi

Proyek ini terbuka untuk kontribusi. Silakan:
1. Fork repository
2. Buat feature branch
3. Commit changes
4. Push ke branch
5. Buat Pull Request

## License

MIT License - Lihat file LICENSE untuk detail lengkap.

## Contact

Untuk pertanyaan atau dukungan, silakan hubungi:
- Email: [your-email]
- GitHub: [your-github]

---
**Gaple Strategist** - Master the art of domino strategy! 🎯