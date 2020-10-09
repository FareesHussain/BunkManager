package farees.hussain.bunkmanager.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import farees.hussain.bunkmanager.R
import farees.hussain.bunkmanager.db.model.Subject
import kotlinx.android.synthetic.main.classes_item.view.*

class SubjectItemAdapter(var subjects:List<Subject>) : RecyclerView.Adapter<SubjectItemAdapter.ClassHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ClassHolder(LayoutInflater.from(parent.context).inflate(R.layout.classes_item,parent,false))

    override fun onBindViewHolder(holder: ClassHolder, position: Int) {
        var subject = subjects[position]
        subject.percentageAttendance = if(subject.totalClasses == 0) 0.0 else Math.round((subject.classesAttended.toDouble()*100/subject.totalClasses).toDouble() * 10.0)/10.0
        holder.view.tvPercent.text = "${subject.percentageAttendance}%"
        holder.view.tvSubject.text = subject.subjectName
        holder.view.tvCurrentAttendance.text = "Current Attendance : ${subject.currentAttendance}"

        /*attendance status if less than 75 and if greater than 75%*/
        fun changeStatus() {
            if(subject.totalClasses == 0){
                holder.view.tvStatus.text = "Attendance Not Yet Started"
                return
            }
            holder.view.tvCurrentAttendance.text = "Current Attendance : ${subject.classesAttended}/${subject.totalClasses}"
            if (subject.percentageAttendance!! < subject.requiredPercentageAttendance) {
                var noOfClassesToAttend = 3 * subject.totalClasses - 4 * subject.classesAttended
                if (noOfClassesToAttend < 0) noOfClassesToAttend++
                subject.classesMustAttend = noOfClassesToAttend
                holder.view.tvStatus.text =
                    "To get More Than ${subject.requiredPercentageAttendance}% Attend $noOfClassesToAttend classes"
                holder.view.tvStatus.setTextColor(Color.RED)
            } else {
                var noOfClassesCanBeBunked = 0
                var a = subject.classesAttended
                var t = subject.totalClasses
                while(a*100/t >= subject.requiredPercentageAttendance.toDouble()){
                    noOfClassesCanBeBunked++
                    t++
                }
                if(a*100/t<subject.requiredPercentageAttendance)noOfClassesCanBeBunked--
                subject.classesCanBeBunked = noOfClassesCanBeBunked
                holder.view.tvStatus.text = if(noOfClassesCanBeBunked>0) "You can now Bunk $noOfClassesCanBeBunked classes" else "You Can't Bunk any Class Now"
                holder.view.tvStatus.setTextColor(Color.GREEN)
            }
        }
        changeStatus()
        holder.view.buBunk.setOnClickListener {
            subject.totalClasses++
            subject.currentAttendance = "Current Attendance ${subject.classesAttended}/${subject.totalClasses}"
            var percentage = subject.classesAttended.toDouble() * 100.0/subject.totalClasses
            subject.percentageAttendance = Math.round(percentage * 10.0)/10.0

            holder.view.tvCurrentAttendance.text = "Current Attendance : ${subject.classesAttended}/${subject.totalClasses}"
            holder.view.tvPercent.text = "${subject.percentageAttendance}%"
            changeStatus()
        }
        holder.view.buAttend.setOnClickListener {
            subject.classesAttended++
            subject.totalClasses++
            subject.currentAttendance = "Current Attendance : ${subject.classesAttended}/${subject.totalClasses}"
            var percentage = subject.classesAttended.toDouble() * 100.0/subject.totalClasses
            subject.percentageAttendance = Math.round(percentage * 10.0)/10.0

            holder.view.tvCurrentAttendance.text = subject.currentAttendance
            holder.view.tvPercent.text = "${subject.percentageAttendance}%"
            changeStatus()
        }
    }

    override fun getItemCount() = subjects.size

    inner class ClassHolder(val view: View):RecyclerView.ViewHolder(view)
}