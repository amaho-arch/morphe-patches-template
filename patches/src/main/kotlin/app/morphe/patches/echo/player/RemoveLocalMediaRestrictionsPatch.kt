package app.morphe.patches.echo.player

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.extensions.InstructionExtensions.getInstruction
import app.morphe.patcher.extensions.InstructionExtensions.indexOfFirstInstruction
import app.morphe.patcher.extensions.InstructionExtensions.indexOfFirstInstructionOrThrow
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
            val enumSgetIndex = indexOfFirstInstruction {
                opcode == Opcode.SGET_OBJECT
            }

            if (enumSgetIndex >= 0) {
                addInstructions(
                    enumSgetIndex,
                    """
                        invoke-static { }, $EXTENSION_CLASS->overridePlayerBackground()V
                    """.trimIndent()
                )
            }
        }

        BottomSheetBackgroundFingerprint.method.apply {
            val insertIndex = indexOfFirstInstructionOrThrow {
                opcode == Opcode.IPUT_OBJECT ||
                    opcode == Opcode.IPUT_BOOLEAN ||
                    opcode == Opcode.IPUT
            }

            if (insertIndex >= 0) {
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
