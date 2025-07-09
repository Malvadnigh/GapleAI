# Instruksi Build Aplikasi Gaple Strategist

## Status Project
✅ **Project telah lengkap dan siap untuk di-build!**

Semua file telah dibuat dengan lengkap:
- 12 file Java (MainActivity, Models, Fragments, Adapters)
- 10 file layout XML
- 10 file drawable/icon
- File konfigurasi (gradle, manifest, dll)
- Resource files (strings, colors, menus)

## Cara Build APK

### Opsi 1: Menggunakan Android Studio (Recommended)

1. **Download & Install Android Studio**
   - Download dari: https://developer.android.com/studio
   - Install dengan SDK Android 15 (API 35)

2. **Buka Project**
   - File → Open
   - Pilih folder "Gaple_Strategist_Android_Project"
   - Tunggu Gradle sync selesai

3. **Build APK**
   - Build → Build Bundle(s)/APK(s) → Build APK(s)
   - Tunggu hingga selesai
   - APK akan tersimpan di: `app/build/outputs/apk/debug/`

### Opsi 2: Command Line (Untuk Advanced Users)

**Windows:**
```cmd
# Jalankan script otomatis
build.bat

# Atau manual:
gradlew.bat clean
gradlew.bat assembleDebug
```

**Linux/Mac:**
```bash
# Jalankan script otomatis
./build.sh

# Atau manual:
./gradlew clean
./gradlew assembleDebug
```

## Lokasi File APK

Setelah build berhasil, APK dapat ditemukan di:
- **Debug APK**: `app/build/outputs/apk/debug/app-debug.apk`
- **Release APK**: `app/build/outputs/apk/release/app-release.apk`

## Install APK ke Android

### Via ADB (Android Debug Bridge)
```bash
# Pastikan USB debugging aktif di Android
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Via File Manager
1. Copy file APK ke Android device
2. Buka file manager
3. Tap file APK
4. Allow "Install from unknown sources" jika diminta
5. Tap Install

## System Requirements

### Development:
- **OS**: Windows 10/11, macOS 10.14+, Linux
- **RAM**: Minimum 8GB (Recommended 16GB)
- **Storage**: 4GB free space
- **Java**: JDK 11 atau lebih baru

### Target Device:
- **Android**: 8.0+ (API 26+)
- **RAM**: Minimum 2GB
- **Storage**: 50MB free space
- **Camera**: Required untuk fitur scan domino

## Troubleshooting

### Gradle Sync Failed
```bash
# Clear gradle cache
./gradlew clean
# Atau hapus folder .gradle dan rebuild
```

### Build Failed
- Pastikan SDK Android 15 (API 35) ter-install
- Check koneksi internet untuk download dependencies
- Restart Android Studio

### APK Install Failed
- Enable "Unknown Sources" di Android settings
- Check apakah device Android 8.0+
- Pastikan ada space storage yang cukup

## Features yang Tersedia

### ✅ Sudah Implemented
- Modern UI dengan Material Design 3
- Multi-tab navigation (Game, Score, Rules)
- Camera integration untuk scan domino
- Game logic lengkap untuk Gaple
- Score tracking dan history
- Rules dan strategy tips
- Domino value reference
- Data persistence dengan SharedPreferences

### 🚧 Future Enhancements
- Machine Learning untuk domino recognition
- Online multiplayer
- Tournament mode
- Advanced statistics
- Sound effects

## Struktur Aplikasi

```
📱 Gaple Strategist
├── 🎮 Game Tab
│   ├── Camera view untuk scan domino
│   ├── AI strategy advisor
│   ├── Game state tracking
│   └── Manual input option
├── 📊 Score Tab  
│   ├── Multi-player scoring
│   ├── Real-time updates
│   ├── Game history
│   └── Win statistics
└── 📚 Rules Tab
    ├── Complete gaple rules
    ├── Strategy tips
    ├── Domino values
    └── Scoring system
```

## Support

Jika mengalami masalah dalam build atau ada pertanyaan:
1. Check README.md untuk informasi lengkap
2. Pastikan semua requirements terpenuhi
3. Coba clean dan rebuild project

---
**Happy Building! 🚀**

Aplikasi Gaple Strategist siap membantu Anda menguasai permainan domino!