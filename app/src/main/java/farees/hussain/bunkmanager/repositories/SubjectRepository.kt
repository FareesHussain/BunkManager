package farees.hussain.bunkmanager.repositories

import androidx.lifecycle.LiveData
import farees.hussain.bunkmanager.db.model.Subject

interface SubjectRepository {
    suspend fun insertSubjectItem(subjectItem: Subject)
    suspend fun deleteSubjectItem(subjectItem: Subject)

    suspend fun observeAllSubjectItems() : LiveData<List<Subject>>
    suspend fun observeTotalMustAttend() : LiveData<Int>
    suspend fun observeTotalCanBunk() : LiveData<Int>
    suspend fun observeTotalClassesAttended() : LiveData<Int>
    suspend fun observeTotalClassesBunked() : LiveData<Int>
}