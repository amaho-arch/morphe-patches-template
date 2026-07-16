# Echo Music Patches for Morphe

Morphe patches for Echo Music (iad1tya.echo.music).

## Patches

| Patch | Description |
|-------|-------------|
| Remove local media restrictions | Removes the isLocalMedia check so the selected player background style applies to all media, and removes the black background fallback for local media. |

## Building

1. Fork [morphe-patches-template](https://github.com/MorpheApp/morphe-patches-template)
2. Copy the `patches/src/main/kotlin/app/morphe/patches/echo/` directory into your fork
3. Update `patches/build.gradle.kts` with your project info
4. Push to GitHub — the release workflow will build the `.mpp` file automatically
5. Add your GitHub repo as a patch source in Morphe Manager

## Usage

1. Install Morphe Manager on your Android device
2. Add this repo as a patch source (use the GitHub URL)
3. In Expert Mode, select "Remove local media restrictions" patch
4. Provide the Echo Music APK (v5.2.7)
5. Patch and install
