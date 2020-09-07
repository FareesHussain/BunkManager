package farees.hussain.bunkmanager.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_items")
data class Subject(
    var subjectName:String,
    var percentageAttendance:Int,
    var currentAttendance: String,
    var status: String,
    var classesAttended:Int,
    var classesBunked: Int,
    var totalClasses: Int,
    var classesCanBeBunked:Int,
    var classesMustAttend:Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int?=null
)