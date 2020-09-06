package farees.hussain.bunkmanager.model

data class Class(
    var subjectName:String,
    var percentageAttendance:Int,
    var currentAttendance: String,
    var status: String,
    var classesAttended:Int,
    var classesBunked: Int,
    var totalClasses: Int
)