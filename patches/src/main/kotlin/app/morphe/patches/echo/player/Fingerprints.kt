package app.morphe.patches.echo.player

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.OpcodesFilter
import com.android.tools.smali.dexlib2.Opcode

/**
 * Fingerprint to find the method that assigns the player background style.
 * The string "playerBackgroundStyle" is the preference key and survives obfuscation.
 */
internal object PlayerBackgroundAssignmentFingerprint : Fingerprint(
    strings = listOf("playerBackgroundStyle"),
    filters = OpcodesFilter.opcodesToFilters(
        Opcode.CONST_STRING,
        Opcode.SGET_OBJECT,
        Opcode.INVOKE_STATIC,
        Opcode.MOVE_RESULT_OBJECT,
        Opcode.IF_EQZ,
        Opcode.SGET_OBJECT,
        Opcode.GOTO,
    ),
)

/**
 * Fingerprint to find the method that sets the bottom sheet background color.
 * Targets the bottom sheet color assignment with isLocalMedia -> Black pattern.
 */
internal object BottomSheetBackgroundFingerprint : Fingerprint(
    strings = listOf("bottomSheet"),
    filters = OpcodesFilter.opcodesToFilters(
        Opcode.IGET_OBJECT,
        Opcode.IGET_BOOLEAN,
        Opcode.IF_EQZ,
        Opcode.CONST_WIDE_HIGH16,
        Opcode.IPUT_OBJECT,
    ),
)
