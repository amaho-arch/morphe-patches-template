package app.morphe.patches.echo.player

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
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
        PlayerBackgroundAssignmentFingerprint.method.apply {
            val enumSgetIndex = indexOfFirstInstructionOrThrow(Opcode.SGET_OBJECT)

            addInstructions(
                enumSgetIndex,
                """
                    invoke-static { }, $EXTENSION_CLASS->overridePlayerBackground()V
                """.trimIndent()
            )
        }

        BottomSheetBackgroundFingerprint.method.apply {
            val insertIndex = indexOfFirstInstructionOrThrow(Opcode.IPUT_OBJECT)

            addInstructions(
                insertIndex,
                """
                    invoke-static { }, $EXTENSION_CLASS->overrideBottomSheetBackground()V
                """.trimIndent()
            )
        }
    }
}