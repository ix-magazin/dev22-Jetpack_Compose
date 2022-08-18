//////////////////////////////////////////
// Listing 1: Eine einfache Compose-App //
//////////////////////////////////////////

package com.thomaskuenneth.ixdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
        Greeting()
      }
    }
}

@Composable
fun Greeting() {
    Text(text = "Hello iX")
}


/////////////////////////////////////
// Listing 2: Composables anordnen //
/////////////////////////////////////

@Preview
@Composable
fun LayoutDemo() {
    Column(modifier = Modifier.fillMaxSize()) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .weight(0.5F)
      ) {
        Text(text = "1", modifier = Modifier.weight(0.3F))
        Text(text = "2", modifier = Modifier.weight(0.7F))
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .weight(0.5F)
      ) {
        Text("3")
        Text("4")
      }
    }
}


//////////////////////////////////////////////////////////////////////////////
// Listing 3: Mit drawOnYellow ein gelbes Rechteck unter Composables setzen //
//////////////////////////////////////////////////////////////////////////////

fun Modifier.drawOnYellow() = this.drawBehind {
  drawRect(color = Color.Yellow)
}

Text(
  modifier = Modifier.drawOnYellow(),
  text = "Hello Compose"
)


////////////////////////////////////////
// Listing 4: Ein zweizeiliger Button //
////////////////////////////////////////

Button(onClick = {
  println("clicked")
}) {
  Column {
    Text("Zeile 1")
    Text("Zeile 2")
  }
}


/////////////////////////////////////////////////////////////
// Listing 5: Mit onValueChange auf Texteingaben reagieren //
/////////////////////////////////////////////////////////////

@Composable
fun TextFieldDemo() {
    var input by remember { mutableStateOf(TextFieldValue()) }
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      TextField(
        modifier = Modifier.fillMaxWidth(),
        value = input,
        onValueChange = {
          input = it
        }
      )
      Button(
        onClick = {
          println(input.text)
        },
        enabled = input.text.isNotEmpty()
      ) {
        Text(stringResource(id = R.string.click))
      }
    }
}


//////////////////////////////////////
// Listing 6: Eine scrollbare Liste //
//////////////////////////////////////

@Composable
fun ListDemo() {
    val callback = { index: Int -> println("$index selected") }
    val list = arrayListOf("1", "2", "3")
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
      itemsIndexed(list) { index, s ->
        Text(
          text = s,
          modifier = Modifier
            .fillMaxSize()
            .clickable { callback(index) },
          style = MaterialTheme.typography.subtitle2
        )
      }
    }
}


/////////////////////////////////////////////
// Listing 7: Animationen einfach umsetzen //
/////////////////////////////////////////////

@ExperimentalAnimationApi
@Composable
fun AnimationDemo() {
    var visible by remember {
      mutableStateOf(false)
    }
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Button(onClick = {
        visible = !visible
      }) {
        Text(stringResource(id = R.string.click))
      }
      AnimatedVisibility(
        visible = visible
      ) {
        Text(
          text = stringResource(id = R.string.app_name),
          style = MaterialTheme.typography.h1
        )
      }
    }
}