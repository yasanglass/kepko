package glass.yasan.kepko.resource.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import glass.yasan.kepko.resource.Res
import glass.yasan.kepko.resource.allDrawableResources
import org.jetbrains.compose.resources.painterResource

/**
 * @return a map of all icon painters with their names as keys.
 */
@Composable
public fun getAllIcons(): Map<String, Painter> = Res.allDrawableResources
    .filterKeys { it.startsWith("ic_") }
    .entries
    .sortedBy { it.key }
    .associate { (key, resource) ->
        val name = key.removePrefix("ic_")
            .split("_")
            .joinToString(" ") { word -> word.replaceFirstChar { it.uppercase() } }
        name to painterResource(resource)
    }
