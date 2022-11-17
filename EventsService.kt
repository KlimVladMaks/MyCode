package com.example.spbgo

import com.example.spbgo.Event
import java.util.*

typealias EventsListener = (events: List<Event>) -> Unit

class EventsService {

    private var events = mutableListOf<Event>()

    private val listeners = mutableListOf<EventsListener>()

    init {
        events = listOf<Event>(
            Event(1, "Мероприятие №1", "None", "11.12.2022", "Понедельник", true),
            Event(2, "Мероприятие №2", "None", "12.12.2022", "Вторник", true),
            Event(3, "Мероприятие №3", "None", "13.12.2022", "Среда", true),
            Event(4, "Мероприятие №4", "None", "14.12.2022", "Четверг", true),
            Event(5, "Мероприятие №5", "None", "15.12.2022", "Пятница", true)
        ).toMutableList()
    }

    fun getEvents(): List<Event> {
        return events
    }

    fun deleteEvent(event: Event) {
        val indexToDelete = events.indexOfFirst { it.id == event.id }
        if (indexToDelete != 1) {
            events.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun moveEvent(event: Event, moveBy: Int) {
        val oldIndex = events.indexOfFirst { it.id == event.id }
        if (oldIndex == 1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= events.size) return
        Collections.swap(events, oldIndex, newIndex)
        notifyChanges()
        }

    fun addListener(listener: EventsListener) {
        listeners.add(listener)
        listener.invoke(events)
    }

    fun removeListener(listener: EventsListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(events) }
    }

}
