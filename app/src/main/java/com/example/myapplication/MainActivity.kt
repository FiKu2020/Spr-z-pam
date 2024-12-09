package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

data class ActivityItem(
    val name: String,
    val count: Int,
    val icon: ImageVector
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserScreen(
                userName = "Filip Kujawa",
                activities = listOf(
                    ActivityItem("Committed changes", 22, Icons.Filled.History), // Sync replaced with History
                    ActivityItem("Comment count", 15, Icons.Filled.Chat), // Comment replaced with Chat
                    ActivityItem("Merged pull requests", 8, Icons.Filled.CallMerge), // MergeType replaced with CallMerge
                    ActivityItem("Closed pull requests", 3, Icons.Filled.Close)
                )
            )
        }
    }
}

@Composable
fun UserScreen(userName: String, activities: List<ActivityItem>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.man-person-icon), // Use the correct resource for the icon
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = userName, fontSize = 20.sp)
                Text(text = "Git statistics")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Activities List
        Column {
            Text(text = "Recent Activities", fontSize = 18.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            activities.forEach { activity ->
                ActivityRow(activity)
            }
        }

        // Button
        Button(
            onClick = {
                Toast.makeText(this@MainActivity, "Well done!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Display message")
        }
    }
}

@Composable
fun ActivityRow(activity: ActivityItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = activity.icon,
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = activity.name, modifier = Modifier.weight(1f))
        Text(text = activity.count.toString(), fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserScreen() {
    UserScreen(
        userName = "Filip Kujawa",
        activities = listOf(
            ActivityItem("Committed changes", 22, Icons.Filled.History),
            ActivityItem("Comment count", 15, Icons.Filled.Chat),
            ActivityItem("Merged pull requests", 8, Icons.Filled.CallMerge),
            ActivityItem("Closed pull requests", 3, Icons.Filled.Close)
        )
    )
}