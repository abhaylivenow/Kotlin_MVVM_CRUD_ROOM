package com.eazyalgo.mvvmpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eazyalgo.mvvmpractice.db.SubscriberRepository
import java.lang.IllegalArgumentException

// this factory class should also have same parameter as ViewModel class
class SubscriberViewModelFactory(private val repository: SubscriberRepository): ViewModelProvider.Factory {
    // boiler-plate, same on every viewModelFactory
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}