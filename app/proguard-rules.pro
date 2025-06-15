# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# OSMDroid
-keep class org.osmdroid.** { *; }
-keep class org.osmdroid.tileprovider.modules.** { *; }
-keep class org.osmdroid.tileprovider.tilesource.** { *; }
-keep class org.osmdroid.util.** { *; }
-keep class org.osmdroid.views.** { *; }
-keep class org.osmdroid.config.** { *; }
-keep class microsoft.mappoint.** { *; }
-dontwarn org.osmdroid.**

# Keep FuelStation model
-keep class com.epc_it.fuelstation.model.** { *; }
-keepclassmembers class com.epc_it.fuelstation.model.** { *; }

# Moshi
-keep class com.squareup.moshi.** { *; }
-keep interface com.squareup.moshi.** { *; }
-keep @com.squareup.moshi.JsonQualifier interface *
-keepclassmembers @com.squareup.moshi.JsonClass class * {
    <init>(...);
}
-keep class **JsonAdapter {
    <init>(...);
}
-keepnames @com.squareup.moshi.JsonClass class *

# Kotlin
-keep class kotlin.Metadata { *; }
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keep class kotlin.reflect.jvm.internal.** { *; }

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keep class kotlinx.coroutines.android.AndroidExceptionPreHandler { *; }

# Prevent obfuscation of classes used by OSMDroid
-keepnames class * extends org.osmdroid.views.overlay.Overlay
-keepnames class * extends org.osmdroid.tileprovider.tilesource.TileSourceFactory

# Keep serialization classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# SQLite (если используется для хранения данных)
-keep class android.database.** { *; }
-keep class org.sqlite.** { *; }

# Retrofit/OkHttp (на случай будущего использования)
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Compose
-keep class androidx.compose.** { *; }
-keep class androidx.lifecycle.** { *; }


# Может лишнее..
# Moshi
-keep class com.squareup.moshi.** { *; }
-dontwarn com.squareup.moshi.**

# KotlinPoet
-keep class com.squareup.kotlinpoet.** { *; }
-dontwarn com.squareup.kotlinpoet.**

# KSP (Kotlin Symbol Processing)
-keep class com.google.devtools.ksp.** { *; }
-dontwarn com.google.devtools.ksp.**

# Annotation Processing
-keep class javax.lang.model.** { *; }
-dontwarn javax.lang.model.**
-keep class javax.tools.** { *; }
-dontwarn javax.tools.**

# Общие правила
-dontwarn kotlin.**
-keepattributes Signature, InnerClasses, EnclosingMethod