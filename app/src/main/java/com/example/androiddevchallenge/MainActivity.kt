/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.main)
        findViewById<ButtonPrimary>(R.id.myButton).apply {
            text = "My Compose in XML"
            onClickListener = {
                Toast.makeText(this@MainActivity, "Worked", Toast.LENGTH_LONG).show()
                isEnabled = false
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "Ready... Set... GO!")
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

@Composable
fun ButtonPrimary(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) = CanvasButton(
    enabled = enabled,
    onClick = onClick
) {
    Text(text = text)
}

@Composable
internal fun CanvasButton(
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) = Button(
    modifier = modifier,
    border = rememberUpdatedState(if (enabled) border else null).value,
    enabled = enabled,
    onClick = onClick
) {
    content()
}

class ButtonPrimary @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    var text by mutableStateOf("")
    var onClickListener by mutableStateOf({})
    private var _enabled by mutableStateOf<Boolean>(true)

    @Composable
    override fun Content() {
        MaterialTheme {
            ButtonPrimary(text = text, enabled = _enabled, onClick = onClickListener)
        }
    }

    override fun isEnabled(): Boolean {
        _enabled = super.isEnabled()
        return _enabled
    }
}
