package glass.yasan.concrete.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.concrete.component.TextMono
import glass.yasan.concrete.composeapp.generated.resources.Res
import glass.yasan.concrete.composeapp.generated.resources.app_title
import glass.yasan.concrete.foundation.theme.ConcreteTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SampleApp() {
    ConcreteTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(ConcreteTheme.colors.midground),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ConcreteTheme.colors.foreground)
                    .padding(16.dp),
            ) {
                ColorBox(ConcreteTheme.colors.primaryDark)
                ColorBox(ConcreteTheme.colors.primary)
                ColorBox(ConcreteTheme.colors.primaryLight)
                TextMono(
                    text = stringResource(Res.string.app_title).uppercase(),
                    prominence = SUBTLE,
                    fontSize = 32.sp,
                )
                TextMono(
                    text = stringResource(Res.string.app_title).uppercase(),
                    fontSize = 32.sp,
                )
                TextMono(
                    text = stringResource(Res.string.app_title).uppercase(),
                    prominence = SUBTLE,
                    fontSize = 32.sp,
                )
                ColorBox(ConcreteTheme.colors.secondaryLight)
                ColorBox(ConcreteTheme.colors.secondary)
                ColorBox(ConcreteTheme.colors.secondaryDark)
            }
        }
    }
}

@Composable
private fun ColorBox(color: Color) {
    Box(
        Modifier
            .fillMaxWidth()
            .requiredHeight(64.dp)
            .background(color)
    )
}
