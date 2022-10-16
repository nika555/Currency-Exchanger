package com.example.currencyexchanger.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.currencyexchanger.ui.navigation.bottom_sheet.BottomSheetScreen
import com.example.currencyexchanger.ui.navigation.bottom_sheet.SheetLayout
import com.example.currencyexchanger.ui.navigation.navhosts.MainNavHost
import com.example.currencyexchanger.ui.theme.BottomSheetShape
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalStdlibApi
@Composable
fun SetupNavGraph(
    rootNavController: NavHostController,
) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    val scope = rememberCoroutineScope()

    var currentBottomSheet: BottomSheetScreen? by remember {
        mutableStateOf(null)
    }

    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) currentBottomSheet = null

    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) currentBottomSheet = null

    val closeSheet: () -> Unit = {
        scope.launch {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
    }

    val openSheet: (BottomSheetScreen) -> Unit = {
        scope.launch {
            currentBottomSheet = it
            bottomSheetScaffoldState.bottomSheetState.expand()
        }
    }

    BottomSheetScaffold(sheetPeekHeight = 0.dp,
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = BottomSheetShape,
        sheetContent = {
            Spacer(modifier = Modifier.height(1.dp))

            currentBottomSheet?.let { currentSheet ->
                SheetLayout(currentSheet, closeSheet)
            }
        }) {
        Scaffold(
            content = { padding ->
                MainNavHost(
                    padding = padding,
                    rootNavController = rootNavController,
                    openSheet = openSheet,
                    closeSheet = closeSheet,
                )
            }
        )
    }
}


