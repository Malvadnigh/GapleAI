#!/bin/bash

echo "Building Gaple Strategist Android App..."
echo

echo "Cleaning previous builds..."
./gradlew clean

echo "Building debug APK..."
./gradlew assembleDebug

echo "Building release APK..."
./gradlew assembleRelease

echo
echo "Build completed!"
echo "Debug APK: app/build/outputs/apk/debug/app-debug.apk"
echo "Release APK: app/build/outputs/apk/release/app-release.apk"
echo