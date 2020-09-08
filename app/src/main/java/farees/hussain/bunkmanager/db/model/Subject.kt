package farees.hussain.bunkmanager.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_items")
data class Subject(
    var subjectName:String,
    var currentAttendance: String,
    var status: String,
    var classesAttended:Int,
    var totalClasses: Int,
    var classesCanBeBunked:Int?=null,
    var classesMustAttend:Int?=null,
    var attendanceCheckedToday:Boolean ?=false,
    var classesBunked: Int = totalClasses - classesAttended,
    var percentageAttendance:Int?=(classesAttended*100/totalClasses).toInt(),
    var requiredPercentageAttendance:Int?=75,

    @PrimaryKey(autoGenerate = true)
    val id: Int?=null
)