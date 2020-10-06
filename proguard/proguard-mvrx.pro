# BaseMvRxViewModels loads the Companion class via reflection and thus we need to make sure we keep
# the name of the Companion object.
-keepclassmembers class ** extends com.airbnb.mvrx.BaseMvRxViewModel {
    ** Companion;
}

# Members of the Kotlin data classes used as the state in MvRx are read via Kotlin reflection which cause trouble
# with Proguard if they are not kept.
# During reflection cache warming also the types are accessed via reflection. Need to keep them too.
-keepclassmembers,includedescriptorclasses,allowobfuscation class ** implements com.airbnb.mvrx.MvRxState {
   *;
}

# The MvRxState object and the names classes that implement the MvRxState interfrace need to be
# kept as they are accessed via reflection.
-keepnames class com.airbnb.mvrx.MvRxState
-keepnames class * implements com.airbnb.mvrx.MvRxState

# MvRxViewModelFactory is referenced via reflection using the Companion class name.
-keepnames class * implements com.airbnb.mvrx.MvRxViewModelFactory