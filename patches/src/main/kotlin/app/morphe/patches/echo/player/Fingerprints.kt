package app.morphe.patches.echo.player

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.OpcodesFilter
import com.android.tools.smali.dexlib2.Opcode

/**
 * Fingerprint to find the method that assigns the player background style.
 * Targets the method that checks isLocalMedia and returns DEFAULT or preference.
 */
internal object PlayerBackgroundAssignmentFingerprint : Fingerprint(
    strings = listOf("Background", "Style", "DEFAULT", "GRADIENT", "playerBackground"),
    // Use only string matching, no strict opcode filters (R8 obfuscation changes bytecode)
    filters = emptyList()
)

/**
 * Fingerprint to find the method that sets the bottom sheet background color.
 * Targets the bottom sheet color assignment with isLocalMedia -> Black pattern.
 */
internal object BottomSheetBackgroundFingerprint : Fingerprint(
    strings = listOf("BottomSheet", "bottomSheet", "background", "Color", "Black"),
    filters = emptyList()
)
