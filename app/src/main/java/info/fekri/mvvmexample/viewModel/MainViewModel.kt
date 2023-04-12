package info.fekri.mvvmexample.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.fekri.mvvmexample.model.Blog

class MainViewModel : ViewModel() {
    var lst = MutableLiveData<ArrayList<Blog>>()
    var newList = arrayListOf<Blog>()

    fun add(blog: Blog) {
        newList.add(blog)
        lst.value = newList
    }

    fun remove(blog: Blog) {
        newList.remove(blog)
        lst.value = newList
    }

}