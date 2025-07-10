# 🎮 Gaple Strategist - Download APK

## Aplikasi Android Lengkap Untuk Bermain Gaple

Aplikasi ini telah berhasil dibuat dan dapat membaca kartu Gaple di layar serta memberikan saran strategi terbaik!

### 📱 Fitur Utama

✅ **Camera Recognition** - Scan kartu domino secara real-time  
✅ **AI Strategy Advisor** - Saran langkah terbaik berdasarkan kartu yang dipegang  
✅ **Score Management** - Tracking skor untuk 2-4 pemain  
✅ **Rules & Guide** - Panduan lengkap bermain Gaple  
✅ **Material Design** - UI modern sesuai Android 15  

### 📥 Cara Download APK

#### Option 1: GitHub Actions Build (OTOMATIS)
1. Buka repositori: [https://github.com/Malvadnigh/GapleAI](https://github.com/Malvadnigh/GapleAI)
2. Klik tab **"Actions"**
3. Pilih workflow build yang sedang berjalan atau terakhir
4. Scroll ke bawah ke bagian **"Artifacts"**
5. Download file:
   - `gaple-strategist-debug-apk` (untuk testing)
   - `gaple-strategist-release-apk` (versi final)

#### Option 2: Manual Build
```bash
git clone https://github.com/Malvadnigh/GapleAI
cd GapleAI
./gradlew assembleDebug
# APK akan tersedia di: app/build/outputs/apk/debug/app-debug.apk
```

### 📲 Instalasi APK

1. **Enable Unknown Sources**:
   - Settings → Security → Unknown Sources → Enable
   - Atau Settings → Apps → Special Access → Install Unknown Apps

2. **Install APK**:
   - Transfer APK ke Android device
   - Tap file APK untuk install
   - Allow camera permission saat diminta

### 🎯 Cara Penggunaan

1. **Game Mode**:
   - Buka tab "Game"
   - Tap "New Game" untuk memulai
   - Arahkan kamera ke kartu domino
   - Ikuti saran strategi yang diberikan

2. **Score Tracking**:
   - Buka tab "Score"
   - Input skor pemain secara real-time
   - Lihat riwayat permainan

3. **Rules & Reference**:
   - Buka tab "Rules"
   - Pelajari aturan dan strategi Gaple

### 📊 Spesifikasi Sistem

- **Android Version**: 8.0+ (API 26+)
- **RAM**: Minimum 2GB
- **Storage**: 50MB
- **Camera**: Diperlukan untuk fitur recognition
- **Internet**: Optional (untuk update)

### 🔧 Status Build

**Latest Build**: [![Android CI Build](https://github.com/Malvadnigh/GapleAI/actions/workflows/android-build.yml/badge.svg)](https://github.com/Malvadnigh/GapleAI/actions/workflows/android-build.yml)

Build otomatis akan berjalan setiap ada perubahan code dan menghasilkan APK yang siap download.

### 🎮 Ready to Play!

Aplikasi sudah siap digunakan dengan semua fitur yang diminta:
- ✅ Membaca kartu Gaple dari layar menggunakan kamera
- ✅ Memberikan saran strategi terbaik
- ✅ APK yang bisa didownload
- ✅ GitHub repository lengkap

**Happy Gaming! 🎯**