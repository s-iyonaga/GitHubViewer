package com.siyonaga.practice.githubviewer.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.siyonaga.practice.githubviewer.model.Order

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopAppBar(
    order: Order,
    onSortButtonClicked: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        actions = {
            IconButton(
                onClick = onSortButtonClicked,
                modifier = Modifier.size(48.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.sort),
                    contentDescription = order.toContentDescription(),
                    modifier = order.toFlipModifier(),
                )
            }
        },
    )
}

@Composable
private fun Order.toContentDescription(): String {
    return if (this == Order.ASC) {
        stringResource(R.string.change_to_desc)
    } else {
        stringResource(R.string.change_to_asc)
    }
}

private fun Order.toFlipModifier(): Modifier {
    return if (this == Order.ASC) {
        Modifier.graphicsLayer { this.scaleY = -1.0f }
    } else {
        Modifier
    }
}
