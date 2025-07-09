@echo off
echo Building Gaple Strategist Android App...
echo.

echo Cleaning previous builds...
gradlew.bat clean

echo Building debug APK...
gradlew.bat assembleDebug

echo Building release APK...
gradlew.bat assembleRelease

echo.
echo Build completed!
echo Debug APK: app\build\outputs\apk\debug\app-debug.apk
echo Release APK: app\build\outputs\apk\release\app-release.apk
echo.
pause