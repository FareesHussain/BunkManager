package farees.hussain.bunkmanager.db.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import farees.hussain.bunkmanager.db.model.Subject

interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectItem(subjectItem: Subject)

    @Delete
    suspend fun deleteSubjectItem(subjectItem: Subject)

    @Query("select * from subject_items")
    fun observeAllShoppingItems(): LiveData<List<Subject>>

    @Query("select SUM(classesMustAttend) from subject_items")
    fun observeTotalMustAttendClasses():LiveData<Float>

    @Query("select SUM(classesCanBeBunked) from subject_items")
    fun observeTotalCanBeBunkedClasses():LiveData<Float>
}
