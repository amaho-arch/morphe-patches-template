package app.morphe.patches.echo.player

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.OpcodesFilter
import com.android.tools.smali.dexlib2.Opcode

/**
 * Fingerprint to find the method that assigns the player background style.
 * Targets the method that checks isLocalMedia and returns DEFAULT or preference.
 * Uses specific strings found in v5.2.7 APK that are in the target methods.
 */
internal object PlayerBackgroundAssignmentFingerprint : Fingerprint(
    strings = listOf(
        "isLocal=",
        "miniPlayerEnabled=",
        "miniPlayerBackgroundStyle",
        "playerBackgroundStyle",
        "DEFAULT",
        "GRADIENT"
    ),
    // No opcode filters - R8 obfuscation changes bytecode patterns
    filters = emptyList()
)

/**
 * Fingerprint to find the method that sets the bottom sheet background color.
 * Targets the bottom sheet color assignment with isLocalMedia -> Black pattern.
 * Uses specific strings found in v5.2.7 APK.
 */
internal object BottomSheetBackgroundFingerprint : Fingerprint(
    strings = listOf(
        "isLocal=",
        "miniPlayerEnabled=",
        "Unknown BottomSheet anchor",
        "BottomSheetBehavior",
        "Color",
        "Black"
    ),
    filters = emptyList()
)
