package nguyen.dev.kotlinentrancetest.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kotlin.reflect.KClass

fun <T : ViewModel> ViewModelStoreOwner.viewModel(cls: KClass<T>): T {
    return ViewModelProvider(this).get(cls.java)
}