package farees.hussain.bunkmanager.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import farees.hussain.bunkmanager.other.Constants.TIMETABLE_TABLE_NAME
import java.time.DayOfWeek
import java.time.LocalDateTime

@Entity(tableName = TIMETABLE_TABLE_NAME)
data class TimeTableItem(
    val day: DayOfWeek,
    val subjectName: String,
    val classStartTime: LocalDateTime,

    @PrimaryKey(autoGenerate = true)
    val id: Int?=null
)