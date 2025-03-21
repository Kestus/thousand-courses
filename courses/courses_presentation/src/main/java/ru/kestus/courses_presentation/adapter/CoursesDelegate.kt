package ru.kestus.courses_presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import ru.kestus.courses_domain.entities.CourseItem
import ru.kestus.design.databinding.CardCourseItemBinding
import javax.inject.Inject

class CoursesDelegate @Inject constructor() : AdapterDelegate<List<RecyclerItem>>() {

    var onMoreButtonClickListener: ((CourseItem) -> Unit)? = null
    var onFavouriteClickListener: ((CourseItem) -> Unit)? = null

    override fun isForViewType(items: List<RecyclerItem>, position: Int): Boolean {
        val item = items[position]
        return item is RecyclerItem.Course
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = CardCourseItemBinding.inflate(LayoutInflater.from(parent.context))
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: List<RecyclerItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as RecyclerItem.Course
        (holder as CourseViewHolder).bind(item)
    }

    private inner class CourseViewHolder(private val binding: CardCourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecyclerItem.Course) {
            val course = item.value
            binding.apply {
                cardItemTitle.text = course.title
                cardItemInfo.text = course.text
                cardItemPrice.text = course.price + " ₽"
                cardItemRating.text = course.rate
                cardItemDate.text = course.publishDate
                cardBtnFavourite.setImageResource(
                    if (course.hasLike) ru.kestus.design.R.drawable.ic_fav_filled
                    else ru.kestus.design.R.drawable.ic_fav_outlined
                )

                cardBtnMore.setOnClickListener {
                    onMoreButtonClickListener?.invoke(course)
                }
                cardBtnFavourite.setOnClickListener {
                    onFavouriteClickListener?.invoke(course)
                }
            }
        }
    }
}