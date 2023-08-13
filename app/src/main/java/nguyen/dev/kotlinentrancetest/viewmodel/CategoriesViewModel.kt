package nguyen.dev.kotlinentrancetest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nguyen.dev.kotlinentrancetest.repository.models.Category
import nguyen.dev.kotlinentrancetest.repository.repo.CategoryRepository

class CategoriesViewModel : ViewModel() {

    var getCategoriesSuccessLiveData = MutableLiveData<List<Category>?>()
    var getCategoriesFailLiveData = MutableLiveData<String?>()

    fun getListCategories() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                CategoryRepository.getListCategory().collect {
                    val gson = GsonBuilder().create()
                    val respModel = gson.fromJson(it.arrData.toString(),Array<Category>::class.java).toList()
                    Log.d("getListCategories", "$respModel")
                    getCategoriesSuccessLiveData.postValue(respModel)
                }
            }

        } catch (e: Exception) {
            getCategoriesFailLiveData.postValue(e.message)
        }
    }

    fun clear() {
        getCategoriesSuccessLiveData.value = null
        getCategoriesFailLiveData.value = null
    }

}