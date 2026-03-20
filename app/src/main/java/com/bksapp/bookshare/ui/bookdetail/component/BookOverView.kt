package com.bksapp.bookshare.ui.bookdetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

@Composable
fun BookOverView(){
    var showBottomSheet by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .clickable(onClick = { showBottomSheet = !showBottomSheet })
            .fillMaxWidth()
            .height(40.dp)
            .background(color = Color(0x92D7E8DC), shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 15.dp), text = "Book Overview"
        )
        Icon(
            modifier = Modifier.padding(end = 15.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = ""
        )
    }

    BottomSheetManager({ showBottomSheet }, { showBottomSheet = false })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetManager(isVisible: () -> Boolean, dismiss: () -> Unit) {
    if (isVisible()) {
        BookBottomSheet(dismiss)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookBottomSheet(dismiss: () -> Unit) {
    val state = rememberModalBottomSheetState()
    val windowHeight = LocalWindowInfo.current.containerDpSize.height
    val screenH = windowHeight - windowHeight / 3
    val scrollState = rememberScrollState()
    ModalBottomSheet(
        onDismissRequest = dismiss,
        sheetState = state
    ) {
        Column(
            modifier = Modifier
                .height(screenH)
                .verticalScroll(scrollState)
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Overview\n\nA book overview is a concise summary of a book's main points, covering the plot, characters, and setting for fiction, or key themes and arguments for non-fiction. It acts as a \"teaser\" helping readers decide whether to read the full text by distilling the author's work into a digestible format." +
                        "\n\nKey Elements of a Book Overview:\n\n" +
                        "Fiction: Typically includes the \"big five\"—main character, goal, obstacles, actions, and outcome.\n" +
                        "Non-Fiction: Focuses on the core argument, central themes, and supporting points, often with actionable insights.\n" +
                        "Purpose: Summaries are used for comprehension, quick learning, or to gauge interest without reading the entire book.\n" +
                        "Structure: Often presented as a brief narrative or through bulleted lists of key takeaways.\n" +
                        "Resources: Platforms like BlinkistShortform, and SparkNotes provide summaries to help readers quickly grasp the essence of books. " +
                        "An effective overview should focus on central messages rather than unnecessary details. "
            )
        }
    }
}