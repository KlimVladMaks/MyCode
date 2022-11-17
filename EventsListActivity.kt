package com.example.spbgo

import android.location.GnssAntennaInfo.Listener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spbgo.databinding.ActivityEventsListBinding
import com.example.spbgo.databinding.ItemEventBinding
import java.util.EventListener


// Activity со списком мероприятий
class EventsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventsListBinding
    private lateinit var adapter: EventsAdapter

    private val eventsService: EventsService
        get() = (applicationContext as EventsApp).eventsService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Убираем верхний заголовок с названиме приложения
        supportActionBar?.hide()

        binding = ActivityEventsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = EventsAdapter()

        val layoutManager = LinearLayoutManager(this)
        binding.eventsList.layoutManager = layoutManager
        binding.eventsList.adapter = adapter

        eventsService.addListener(eventsListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        eventsService.removeListener(eventsListener)
    }

    private val eventsListener: EventsListener = {
        adapter.events = it
    }

}
