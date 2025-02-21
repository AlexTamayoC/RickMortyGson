package com.example.rickmortygson.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickmortygson.R
import com.example.rickmortygson.databinding.ActivityMainBinding
import com.example.rickmortygson.ui.states.AppStates

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private val viewModel: CharacterViewModel by lazy {
        CharacterViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel.fetchCharacters()
        observeDataChanges()
    }

    private fun setupRecyclerView() {
        adapter = CharacterAdapter()
        binding.charactersView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CharacterActivity.adapter
        }
    }

    private fun observeDataChanges() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is
                AppStates.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show()
                }

                AppStates.Loading -> {
                    binding.charactersView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                AppStates.None -> {}
                is AppStates.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.charactersView.visibility = View.VISIBLE
                    adapter.loadUsers(state.users)
                }
            }
        }
    }
}