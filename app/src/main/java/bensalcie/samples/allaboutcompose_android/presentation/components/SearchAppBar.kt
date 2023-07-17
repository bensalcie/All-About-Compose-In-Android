package bensalcie.samples.allaboutcompose_android.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist.FoodCategory
import bensalcie.samples.allaboutcompose_android.presentation.ui.recipe.recipelist.getAllFoodCategories
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    scrollPosition: Int,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onChangeScrollPosition: (Int) -> Unit

) {

    Surface(
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth(),
        color = Color.White

    ) {

        val keyboardController = LocalSoftwareKeyboardController.current
        val scrollState = rememberLazyListState()

        val scope = rememberCoroutineScope()

        Column {


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextField(
                    value = query,
                    modifier = Modifier
                        .fillMaxWidth(

                        )
                        .padding(8.dp),
                    label = { Text(text = "Search") },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),


                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },


                    keyboardActions = KeyboardActions(onSearch =

                    {
                        onExecuteSearch()

                        keyboardController?.hide()


                    }),

                    onValueChange = { newValue -> onQueryChanged(newValue) })


            }
            LaunchedEffect("scroll-effect") {
                // the key define when the block is relaunched
                // Your coroutine code here
                scope.launch { scrollState.animateScrollToItem(scrollPosition) }

            }


            //Scrollable Row
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(start = 8.dp, bottom = 8.dp), state = scrollState
            ) {
                itemsIndexed(items = getAllFoodCategories()) {

                        index, item ->
                    FoodCategoryChip(
                        category = item.value,
                        isSelected = selectedCategory == item,
                        onExecuteSearch = {
                            onExecuteSearch()
                        },
                        onSelectedCategoryChanged = {
                            onSelectedCategoryChanged(it)
                            onChangeScrollPosition(index)
                        })


                }

            }

        }


    }

}