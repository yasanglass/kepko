package glass.yasan.kepko.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.Icon
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.util.getAllIcons

@Composable
internal fun IconScreen(onBackClick: () -> Unit) {
    val entries = getAllIcons().toList()

    Scaffold(
        title = "Icons",
        onBackClick = onBackClick,
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding),
        ) {
            items(entries, key = { it.first }) { (name, painter) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                ) {
                    Icon(
                        painter = painter,
                        contentDescription = name,
                        size = KepkoTheme.dimensions.iconSize,
                    )
                    Text(text = name)
                }
            }
        }
    }
}
