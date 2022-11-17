package com.example.spbgo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spbgo.databinding.ItemEventBinding

// Создание адаптера для добавления элементов в RecyclerView
class EventsAdapter: RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    // Создаём список мероприятий
    var events: List<Event> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    // Узнать количество мероприятий в списке
    override fun getItemCount(): Int = events.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEventBinding.inflate(inflater, parent, false)
        return EventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = events[position]
        with(holder.binding) {
            eventTitleTextView.text = event.title
            weekdayTextView.text = event.dayOfWeek
            dateTextView.text = event.date
            // Нужно ещё реализовать добавление изображения
        }
    }

    class EventsViewHolder(
        val binding: ItemEventBinding
    ): RecyclerView.ViewHolder(binding.root)

}
