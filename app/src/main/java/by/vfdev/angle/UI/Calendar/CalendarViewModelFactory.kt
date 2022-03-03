package by.vfdev.angle.UI.Calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.vfdev.angle.Repository.Repository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CalendarViewModelFactory @Inject constructor(val repository: Repository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CalendarViewModel(repository) as T
    }
}