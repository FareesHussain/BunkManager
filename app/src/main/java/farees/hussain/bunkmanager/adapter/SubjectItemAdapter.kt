package farees.hussain.bunkmanager.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import farees.hussain.bunkmanager.databinding.ItemSubjectsBinding
import farees.hussain.bunkmanager.db.model.Subject

class SubjectItemAdapter(
    val clickListner: SubjectItemClickListner
) : ListAdapter<Subject, SubjectItemAdapter.ClassHolder>(SubjectItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ClassHolder(ItemSubjectsBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ClassHolder, position: Int) {
        var subject = getItem(position)
        holder.bind(subject,clickListner)
    }


    inner class ClassHolder(val binding: ItemSubjectsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Subject, clickListner: SubjectItemClickListner){
            item.percentageAttendance = if(item.totalClasses == 0) 0.0 else Math.round((item.classesAttended.toDouble()*100/item.totalClasses).toDouble() * 10.0)/10.0
            binding.subject = item
            binding.itemclickListner = clickListner
            binding.executePendingBindings()

            /*attendance status if less than 75 and if greater than 75%*/
            fun changeStatus() {
                if(item.totalClasses == 0){
                    binding.tvStatus.text = "Attendance Not Yet Started"
                    return
                }
                binding.tvCurrentAttendance.text = "Current Attendance : ${item.classesAttended}/${item.totalClasses}"
                if (item.percentageAttendance!! < item.requiredPercentageAttendance) {
                    var noOfClassesToAttend = 3 * item.totalClasses - 4 * item.classesAttended
                    if (noOfClassesToAttend < 0) noOfClassesToAttend++
                    item.classesMustAttend = noOfClassesToAttend
                    binding.tvStatus.text =
                        "To get More Than ${item.requiredPercentageAttendance}% Attend $noOfClassesToAttend classes"
                    binding.tvStatus.setTextColor(Color.RED)
                } else {
                    var noOfClassesCanBeBunked = 0
                    var a = item.classesAttended
                    var t = item.totalClasses
                    while(a*100/t >= item.requiredPercentageAttendance.toDouble()){
                        noOfClassesCanBeBunked++
                        t++
                    }
                    if(a*100/t<item.requiredPercentageAttendance)noOfClassesCanBeBunked--
                    item.classesCanBeBunked = noOfClassesCanBeBunked
                    binding.tvStatus.text = if(noOfClassesCanBeBunked>0) "You can now Bunk $noOfClassesCanBeBunked classes" else "You Can't Bunk any Class Now"
                    binding.tvStatus.setTextColor(Color.MAGENTA)
                }
            }
            changeStatus()
            binding.buBunk.setOnClickListener {
                item.totalClasses++
                item.currentAttendance = "Current Attendance ${item.classesAttended}/${item.totalClasses}"
                var percentage = item.classesAttended.toDouble() * 100.0/item.totalClasses
                item.percentageAttendance = Math.round(percentage * 10.0)/10.0

                binding.tvCurrentAttendance.text = "Current Attendance : ${item.classesAttended}/${item.totalClasses}"
//                binding.tvPercent.text = "${item.percentageAttendance}%"
                changeStatus()
            }
            binding.buAttend.setOnClickListener {
                item.classesAttended++
                item.totalClasses++
                item.currentAttendance = "Current Attendance : ${item.classesAttended}/${item.totalClasses}"
                var percentage = item.classesAttended.toDouble() * 100.0/item.totalClasses
                item.percentageAttendance = Math.round(percentage * 10.0)/10.0

                binding.tvCurrentAttendance.text = item.currentAttendance
//                binding.tvPercent.text = "${item.percentageAttendance}%"
                changeStatus()
                binding.subject = item
            }
        }
    }
}

class SubjectItemDiffCallBack : DiffUtil.ItemCallback<Subject>(){
    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return  oldItem.totalClasses == newItem.totalClasses
    }
}

class SubjectItemClickListner(val clickListener: (subject: Subject)-> Unit){

    //todo add click listeners for bunk, attend and no class today buttons
    fun itemClick(subject: Subject) {
        subject.subjectName = "changed"
        clickListener(subject)
    }
}