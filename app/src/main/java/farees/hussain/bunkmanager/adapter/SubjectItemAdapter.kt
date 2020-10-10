package farees.hussain.bunkmanager.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import farees.hussain.bunkmanager.databinding.ItemSubjectsBinding
import farees.hussain.bunkmanager.db.model.Subject

class SubjectItemAdapter() : ListAdapter<Subject, SubjectItemAdapter.ClassHolder>(SubjectItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ClassHolder(ItemSubjectsBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ClassHolder, position: Int) {
        var subject = getItem(position)
        subject.percentageAttendance = if(subject.totalClasses == 0) 0.0 else Math.round((subject.classesAttended.toDouble()*100/subject.totalClasses).toDouble() * 10.0)/10.0
        holder.binding.tvPercent.text = "${subject.percentageAttendance}%"
        holder.binding.tvSubject.text = subject.subjectName
        holder.binding.tvCurrentAttendance.text = "Current Attendance : ${subject.currentAttendance}"

        /*attendance status if less than 75 and if greater than 75%*/
        fun changeStatus() {
            if(subject.totalClasses == 0){
                holder.binding.tvStatus.text = "Attendance Not Yet Started"
                return
            }
            holder.binding.tvCurrentAttendance.text = "Current Attendance : ${subject.classesAttended}/${subject.totalClasses}"
            if (subject.percentageAttendance!! < subject.requiredPercentageAttendance) {
                var noOfClassesToAttend = 3 * subject.totalClasses - 4 * subject.classesAttended
                if (noOfClassesToAttend < 0) noOfClassesToAttend++
                subject.classesMustAttend = noOfClassesToAttend
                holder.binding.tvStatus.text =
                    "To get More Than ${subject.requiredPercentageAttendance}% Attend $noOfClassesToAttend classes"
                holder.binding.tvStatus.setTextColor(Color.RED)
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
                holder.binding.tvStatus.text = if(noOfClassesCanBeBunked>0) "You can now Bunk $noOfClassesCanBeBunked classes" else "You Can't Bunk any Class Now"
                holder.binding.tvStatus.setTextColor(Color.MAGENTA)
            }
        }
        changeStatus()
        holder.binding.buBunk.setOnClickListener {
            subject.totalClasses++
            subject.currentAttendance = "Current Attendance ${subject.classesAttended}/${subject.totalClasses}"
            var percentage = subject.classesAttended.toDouble() * 100.0/subject.totalClasses
            subject.percentageAttendance = Math.round(percentage * 10.0)/10.0

            holder.binding.tvCurrentAttendance.text = "Current Attendance : ${subject.classesAttended}/${subject.totalClasses}"
            holder.binding.tvPercent.text = "${subject.percentageAttendance}%"
            changeStatus()
        }
        holder.binding.buAttend.setOnClickListener {
            subject.classesAttended++
            subject.totalClasses++
            subject.currentAttendance = "Current Attendance : ${subject.classesAttended}/${subject.totalClasses}"
            var percentage = subject.classesAttended.toDouble() * 100.0/subject.totalClasses
            subject.percentageAttendance = Math.round(percentage * 10.0)/10.0

            holder.binding.tvCurrentAttendance.text = subject.currentAttendance
            holder.binding.tvPercent.text = "${subject.percentageAttendance}%"
            changeStatus()
        }
    }


    inner class ClassHolder(val binding: ItemSubjectsBinding):RecyclerView.ViewHolder(binding.root)
}

class SubjectItemDiffCallBack : DiffUtil.ItemCallback<Subject>(){
    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return  oldItem == newItem
    }
}