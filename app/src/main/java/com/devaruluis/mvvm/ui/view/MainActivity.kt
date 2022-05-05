package com.devaruluis.mvvm.ui.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.devaruluis.mvvm.databinding.ActivityMainBinding
import com.devaruluis.mvvm.ui.viewmodel.ColorViewModel
import com.devaruluis.mvvm.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val quoteViewModel: QuoteViewModel by viewModels()
    private val colorViewModel: ColorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        quoteViewModel.onCreate()
        colorViewModel.onCreate()

        quoteViewModel.quoteModel.observe(this, Observer {
            binding.tvQuote.text = it?.quote
            binding.tvAuthor.text = it?.author
        })

        colorViewModel.colorModel.observe(this, Observer {
            val color: Int = try {
                Color.parseColor("#" + it?.hex)

            } catch (e: NumberFormatException) {
                Color.parseColor("#000000")
            }
            binding.tvQuote.setTextColor(color)
            binding.tvAuthor.setTextColor(color)
        })

        quoteViewModel.isLoading.observe(this, Observer { binding.loading.isVisible = it })
        colorViewModel.isLoading.observe(this, Observer { binding.loading.isVisible = it })

        binding.viewContainer.setOnClickListener {
            quoteViewModel.randomQuote()
            colorViewModel.randomColor()
        }
    }
}