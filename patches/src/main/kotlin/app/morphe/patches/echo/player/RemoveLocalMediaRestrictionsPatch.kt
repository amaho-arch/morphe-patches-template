package app.morphe.patches.echo.player

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.extensions.InstructionExtensions.getInstruction
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.echo.shared.Constants
import com.android.tools.smali.dexlib2.Opcode

private const val EXTENSION_CLASS =
    "Lapp/morphe/extension/echo/player/RemoveLocalMediaRestrictionsPatch;"

@Suppress("unused")
val removeLocalMediaRestrictionsPatch = bytecodePatch(
    name = "Remove local media restrictions on player styling",
    description = "Removes the isLocalMedia check so the selected player background style applies to all media, and removes the black background fallback for local media.",
) {
    compatibleWith(Constants.COMPATIBILITY_ECHO_MUSIC)

    execute {
        // Use the instruction matches from the fingerprint
        val playerBackgroundMatches = PlayerBackgroundAssignmentFingerprint.instructionMatches
        if (playerBackgroundMatches.isNotEmpty()) {
            val enumSgetIndex = playerBackgroundMatches.first().index
            PlayerBackgroundAssignmentFingerprint.method.apply {
                addInstructions(
                    enumSgetIndex,
                    """
                        invoke-static { }, $EXTENSION_CLASS->overridePlayerBackground()V
                    """.trimIndent()
                )
            }
        }

        val bottomSheetMatches = BottomSheetBackgroundFingerprint.instructionMatches
        if (bottomSheetMatches.isNotEmpty()) {
            val insertIndex = bottomSheetMatches.first().index
            BottomSheetBackgroundFingerprint.method.apply {
                addInstructions(
                    insertIndex,
                    """
                        invoke-static { }, $EXTENSION_CLASS->overrideBottomSheetBackground()V
                    """.trimIndent()
                )
            }
        }
    }
}