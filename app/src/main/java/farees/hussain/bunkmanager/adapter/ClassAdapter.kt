package farees.hussain.bunkmanager.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import farees.hussain.bunkmanager.R
import farees.hussain.bunkmanager.db.model.Subject
import kotlinx.android.synthetic.main.classes_item.view.*

class ClassAdapter(var subjects:List<Subject>) : RecyclerView.Adapter<ClassAdapter.ClassHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ClassHolder(LayoutInflater.from(parent.context).inflate(R.layout.classes_item,parent,false))

    override fun onBindViewHolder(holder: ClassHolder, position: Int) {
        var subject = subjects[position]
        holder.view.tvPercent.text = subject.subjectName
        holder.view.tvPercent.text = "${subject.percentageAttendance}%"
        holder.view.tvCurrentAttendance.text = "Current Attendance ${subject.currentAttendance}"

        /*attendance status if less than 75 and if greater than 75%*/
        fun changeStatus() {
            if(subject.totalClasses == 0){
                holder.view.tvStatus.text = "Attendance Not Yet Started"
                return
            }
            if (subject.percentageAttendance < 75) {
                var noOfClassesToAttend = 3 * subject.totalClasses - 4 * subject.classesAttended
                if (noOfClassesToAttend < 0) noOfClassesToAttend++
                subject.classesMustAttend = noOfClassesToAttend
                holder.view.tvStatus.text =
                    "To get More Than 75% Attend $noOfClassesToAttend classes"
                holder.view.tvStatus.setTextColor(Color.RED)
            } else {
                var noOfClassesCanBeBunked = 0
                var a = subject.classesAttended
                var t = subject.totalClasses
                while(a*100/t > 75){
                    noOfClassesCanBeBunked++
                    t++
                }
                if(a*100/t<75)noOfClassesCanBeBunked--
                subject.classesCanBeBunked = noOfClassesCanBeBunked
                holder.view.tvStatus.text = if(noOfClassesCanBeBunked>0) "You can now Bunk $noOfClassesCanBeBunked classes" else "You Can't Bunk any Class Now"
                holder.view.tvStatus.setTextColor(Color.GREEN)
            }
        }
        changeStatus()
        holder.view.buBunk.setOnClickListener {
            subject.classesBunked++
            subject.totalClasses++
            subject.currentAttendance = "Current Attendance ${subject.classesAttended}/${subject.totalClasses}"
            subject.percentageAttendance = subject.classesAttended * 100 / subject.totalClasses


            holder.view.tvCurrentAttendance.text = "Current Attendance ${subject.classesAttended}/${subject.totalClasses}"
            holder.view.tvPercent.text = "${subject.classesAttended * 100 / subject.totalClasses}%"
            changeStatus()
        }
        holder.view.buAttend.setOnClickListener {
            subject.classesAttended++
            subject.totalClasses++
            subject.currentAttendance = "Current Attendance ${subject.classesAttended}/${subject.totalClasses}"
            subject.percentageAttendance = subject.classesAttended * 100 / subject.totalClasses

            holder.view.tvCurrentAttendance.text = subject.currentAttendance
            holder.view.tvPercent.text = "${subject.percentageAttendance}%"
            changeStatus()
        }
    }

    override fun getItemCount() = subjects.size

    inner class ClassHolder(val view: View):RecyclerView.ViewHolder(view)
}