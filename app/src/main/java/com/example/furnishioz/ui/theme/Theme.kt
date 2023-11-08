package com.example.furnishioz.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


val md_theme_light_primary = Color(545454)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFE8E970)
val md_theme_light_onPrimaryContainer = Color(0xFF1C1D00)
val md_theme_light_secondary = Color(0xFF775A00)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFFFDF98)
val md_theme_light_onSecondaryContainer = Color(0xFF251A00)
val md_theme_light_tertiary = Color(0xFF2F6B26)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFB1F49D)
val md_theme_light_onTertiaryContainer = Color(0xFF002200)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFFFBFF)
val md_theme_light_onBackground = Color(0xFF24005A)
val md_theme_light_surface = Color(0xFFFFFBFF)
val md_theme_light_onSurface = Color(0xFF24005A)
val md_theme_light_surfaceVariant = Color(0xFFE6E3D1)
val md_theme_light_onSurfaceVariant = Color(0xFF48473A)
val md_theme_light_outline = Color(0xFF797869)
val md_theme_light_inverseOnSurface = Color(0xFFF6EDFF)
val md_theme_light_inverseSurface = Color(0xFF3A1D71)
val md_theme_light_inversePrimary = Color(0xFFCBCC58)
val md_theme_light_surfaceTint = Color(0xFF616200)
val md_theme_light_outlineVariant = Color(0xFFC9C7B6)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFFCBCC58)
val md_theme_dark_onPrimary = Color(0xFF323200)
val md_theme_dark_primaryContainer = Color(0xFF494900)
val md_theme_dark_onPrimaryContainer = Color(0xFFE8E970)
val md_theme_dark_secondary = Color(0xFFEFC048)
val md_theme_dark_onSecondary = Color(0xFF3E2E00)
val md_theme_dark_secondaryContainer = Color(0xFF5A4300)
val md_theme_dark_onSecondaryContainer = Color(0xFFFFDF98)
val md_theme_dark_tertiary = Color(0xFF95D784)
val md_theme_dark_onTertiary = Color(0xFF003A00)
val md_theme_dark_tertiaryContainer = Color(0xFF15520F)
val md_theme_dark_onTertiaryContainer = Color(0xFFB1F49D)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF201E1E)
val md_theme_dark_onBackground = Color(0xFFEADDFF)
val md_theme_dark_surface = Color(0xFF100127)
val md_theme_dark_onSurface = Color(0xFFEADDFF)
val md_theme_dark_surfaceVariant = Color(0xFF48473A)
val md_theme_dark_onSurfaceVariant = Color(0xFFC9C7B6)
val md_theme_dark_outline = Color(0xFF939182)
val md_theme_dark_inverseOnSurface = Color(0xFF120229)
val md_theme_dark_inverseSurface = Color(0xFFEADDFF)
val md_theme_dark_inversePrimary = Color(0xFF616200)
val md_theme_dark_surfaceTint = Color(0xFFCBCC58)
val md_theme_dark_outlineVariant = Color(0xFF48473A)
val md_theme_dark_scrim = Color(0xFF000000)



private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

@Composable
fun FurnishiozTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.LightGray.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}