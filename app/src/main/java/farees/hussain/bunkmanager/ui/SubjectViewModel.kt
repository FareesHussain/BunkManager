package farees.hussain.bunkmanager.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import farees.hussain.bunkmanager.db.model.Subject
import farees.hussain.bunkmanager.other.Event
import farees.hussain.bunkmanager.other.Resource
import farees.hussain.bunkmanager.repositories.SubjectRepository
import kotlinx.coroutines.launch

class SubjectViewModel @ViewModelInject constructor(
    private val repository: SubjectRepository
) : ViewModel(){
    val subjectItems = repository.observeAllSubjectItems()
    val totalMustAttend = repository.observeTotalMustAttend()
    val totalCanBunk = repository.observeTotalCanBunk()
    val totalClassesAttended = repository.observeTotalClassesAttended()
    val totalClassesBunked = repository.observeTotalClassesBunked()

    private val _insertSubjectItemStatus = MutableLiveData<Event<Resource<Subject>>>()
    val insertSubjectItemStatus : LiveData<Event<Resource<Subject>>> = _insertSubjectItemStatus

    fun insertSubjectItem(subject: Subject) = viewModelScope.launch {

        repository.insertSubjectItem(subject)
    }
    fun insertShoppingItem(subjectName: String,requiredPercentage:String,classeAttended:String,totalClasses:String){
//        val subjectItem = Subject(
//            subjectName = subjectName,
//            requiredPercentageAttendance = requiredPercentage.toInt(),
//            classesAttended = classeAttended.toInt(),
//            totalClasses = totalClasses.toInt()
//        )
//        _insertSubjectItemStatus.postValue(Event(Resource.success()))
    }


}