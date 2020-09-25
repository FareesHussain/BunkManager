package farees.hussain.bunkmanager.db.database

import androidx.lifecycle.LiveData
import androidx.room.*
import farees.hussain.bunkmanager.db.model.Subject

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectItem(subjectItem: Subject)

    @Delete
    suspend fun deleteSubjectItem(subjectItem: Subject)

    @Query("select * from subject_items")
    fun observeAllSubjectItems(): LiveData<List<Subject>>

    @Query("select SUM(classesMustAttend) from subject_items")
    fun observeTotalMustAttendClasses():LiveData<Int>

    @Query("select SUM(classesCanBeBunked) from subject_items")
    fun observeTotalCanBeBunkedClasses():LiveData<Int>
}
