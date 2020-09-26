package farees.hussain.bunkmanager.repositories

import androidx.lifecycle.LiveData
import farees.hussain.bunkmanager.db.database.SubjectDao
import farees.hussain.bunkmanager.db.model.Subject
import javax.inject.Inject

class DefaultSubjectRepository @Inject constructor(
    private val subjectDao: SubjectDao
) : SubjectRepository{
    override suspend fun insertSubjectItem(subjectItem: Subject) {
        subjectDao.insertSubjectItem(subjectItem)
    }

    override suspend fun deleteSubjectItem(subjectItem: Subject) {
        subjectDao.deleteSubjectItem(subjectItem)
    }

    override suspend fun observeAllSubjectItems() = subjectDao.observeAllSubjectItems()

    override suspend fun observeTotalMustAttend() = subjectDao.observeTotalMustAttendClasses()

    override suspend fun observeTotalCanBunk() = subjectDao.observeTotalCanBunkClasses()

    override suspend fun observeTotalClassesAttended() = subjectDao.observeTotalClassesAttended()

    override suspend fun observeTotalClassesBunked() = subjectDao.observeTotalClassesBunked()
}