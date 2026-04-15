package com.markicob.Project_ANMP.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.markicob.Project_ANMP.model.Habit

class ListViewModel: ViewModel() {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    fun refresh() {
        loadingLD.value = true 			// progress bar start muncul
        habitLoadErrorLD.value = false  	// tidak ada error

        habitsLD.value = arrayListOf(
//            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100"
//                    + ".jpg/cc0000/ffffff"),
//            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
//                    ".jpg/5fa2dd/ffffff"),
//            Student("11204","Dinny","1994/10/07","6827808747",
//                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        )

        habitLoadErrorLD.value = false
        loadingLD.value = false
    }

}