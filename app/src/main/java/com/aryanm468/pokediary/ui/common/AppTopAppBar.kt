package com.aryanm468.pokediary.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.aryanm468.pokediary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes navigationIcon: Int? = null,
    actions: @Composable (RowScope.() -> Unit) = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationIconClick: () -> Unit = {}
) {
    Column {
        TopAppBar(
            title = title,
            modifier = modifier,
            navigationIcon = {
                if (navigationIcon != null) {
                    IconButton(onClick = { onNavigationIconClick() }) {
                        Icon(
                            painter = painterResource(id = navigationIcon),
                            contentDescription = stringResource(R.string.navigation_icon),
                        )
                    }
                }
            },
            actions = actions,
            windowInsets = windowInsets,
            colors = colors,
            scrollBehavior = scrollBehavior
        )
        HorizontalDivider()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    titleText: String,
    modifier: Modifier = Modifier,
    @DrawableRes navigationIcon: Int? = null,
    actions: @Composable (RowScope.() -> Unit) = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationIconClick: () -> Unit = {}
) {
    AppTopAppBar(
        title = {
            Text(
                text = titleText,
                fontWeight = FontWeight.SemiBold
            )
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior,
        onNavigationIconClick = onNavigationIconClick
    )
}