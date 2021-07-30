package com.eazyalgo.mvvmpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyalgo.mvvmpractice.databinding.ActivityMainBinding
import com.eazyalgo.mvvmpractice.db.Subscriber
import com.eazyalgo.mvvmpractice.db.SubscriberDao
import com.eazyalgo.mvvmpractice.db.SubscriberDatabase
import com.eazyalgo.mvvmpractice.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get instance of Dao for initializing repository class
        val dao:SubscriberDao = SubscriberDatabase.getInstance(applicationContext).subscriberDao
        // init repository
        val repository = SubscriberRepository(dao)
        // init factory
        val factory = SubscriberViewModelFactory(repository)
        // regular stuff with viewModel ans viewModelFactory
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        // data binding
        binding.myViewModel = subscriberViewModel

        binding.lifecycleOwner = this

        displaySubsList()
    }

    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displaySubsList()
    }

    private fun displaySubsList(){
        subscriberViewModel.subscriber.observe(this, Observer {
            Log.i("here", it.toString())
//            binding.subscriberRecyclerView.adapter = RecyclerViewAdapter(it,{selectedItem:Subscriber->listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(subscriber: Subscriber){
        Toast.makeText(this,"${subscriber.name} clicked",Toast.LENGTH_SHORT).show()
    }
}