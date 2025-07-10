#!/bin/bash

# Gradle wrapper script
JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export JAVA_HOME

# Get the directory of this script
APP_HOME="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
GRADLE_WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

# Check if Java is available
if [ ! -x "$JAVA_HOME/bin/java" ]; then
    echo "Error: Java not found at $JAVA_HOME/bin/java"
    exit 1
fi

# Check if wrapper JAR exists
if [ ! -f "$GRADLE_WRAPPER_JAR" ]; then
    echo "Error: Gradle wrapper JAR not found at $GRADLE_WRAPPER_JAR"
    exit 1
fi

# Run Gradle with the wrapper
exec "$JAVA_HOME/bin/java" -classpath "$GRADLE_WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"