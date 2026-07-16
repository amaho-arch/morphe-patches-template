package app.morphe.patches.echo.shared

import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

internal object Constants {
    val COMPATIBILITY_ECHO_MUSIC = Compatibility(
        name = "Echo Music",
        packageName = "iad1tya.echo.music",
        appIconColor = 0x6750A4,
        signatures = emptySet(),
        targets = listOf(
            AppTarget(version = "5.2.7", minSdk = 26),
            AppTarget(version = "5.2.6", minSdk = 26),
            AppTarget(version = "5.2.5", minSdk = 26),
        ),
    )
}
