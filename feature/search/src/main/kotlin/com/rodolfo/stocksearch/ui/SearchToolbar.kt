package com.rodolfo.stocksearch.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit,
) {
    TopAppBar(
        modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        title = title,
        navigationIcon = navigationIcon,
        actions = actions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    query: State<String>,
    navigationIcon: @Composable () -> Unit = {},
    leftActions: @Composable RowScope.() -> Unit = {},
    rightActions: @Composable RowScope.() -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
    title: @Composable () -> Unit,
    onSearch: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val isSearchEnabledState = remember { mutableStateOf(false) }
    val isShowSearchBar by remember {
        derivedStateOf {
            isSearchEnabledState.value || query.value.isNotBlank()
        }
    }

    fun hideSearch() {
        onSearch("")
        focusManager.clearFocus()
        isSearchEnabledState.value = false
    }

    SearchTopAppBar(
        navigationIcon = navigationIcon,
        actions = {
            leftActions()
            if (!isShowSearchBar) {
                IconButton(onClick = {
                    isSearchEnabledState.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Open Search"
                    )
                }
            }
            rightActions()
        },
        title = title,
    )
    if (!isShowSearchBar) {
        bottomContent()
    }
    AnimatedVisibility(
        visible = isShowSearchBar,
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        SearchBar(
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query.value,
                    onQueryChange = {
                        onSearch(it)
                    },
                    onSearch = {
                        focusManager.clearFocus()
                    },
                    expanded = false,
                    onExpandedChange = { },
                    placeholder = { Text("Search for Stocks") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Transit") },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                hideSearch()
                            }
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Close Search")
                        }
                    }
                )
            },
            expanded = false,
            onExpandedChange = {},
            windowInsets = WindowInsets(0.dp),
        ) {}
    }
    LaunchedEffect(isSearchEnabledState.value) {
        if (isSearchEnabledState.value) {
            // Added a delay to prevent multiple animations run at the same time
            delay(200)
            focusRequester.requestFocus()
        }
    }
}