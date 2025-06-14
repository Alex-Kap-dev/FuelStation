# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# OSMDroid
-keep class org.osmdroid.** { *; }
-dontwarn org.osmdroid.**

# Keep serialization classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep FuelStation model
-keep class com.epc_it.fuelstation.model.FuelStation { *; }
-keep class com.epc_it.fuelstation.ui.screens.SafeFuelStation { *; }

# OSMDroid specific rules
-keep class org.osmdroid.tileprovider.modules.** { *; }
-keep class org.osmdroid.tileprovider.tilesource.** { *; }
-keep class org.osmdroid.util.** { *; }
-keep class org.osmdroid.views.** { *; }
-keep class org.osmdroid.config.** { *; }

# Prevent obfuscation of classes used by OSMDroid
-keepnames class * extends org.osmdroid.views.overlay.Overlay
-keepnames class * extends org.osmdroid.tileprovider.tilesource.TileSourceFactory

# SQLite (если используется для хранения данных)
-keep class android.database.** { *; }
-keep class org.sqlite.** { *; }