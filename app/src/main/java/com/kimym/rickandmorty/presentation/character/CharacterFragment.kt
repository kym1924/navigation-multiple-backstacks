package com.kimym.rickandmorty.presentation.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kimym.rickandmorty.R
import com.kimym.rickandmorty.base.BaseFragment
import com.kimym.rickandmorty.databinding.FragmentCharacterBinding
import com.kimym.rickandmorty.presentation.adapter.CharacterLinearAdapter
import com.kimym.rickandmorty.util.CharacterItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>(R.layout.fragment_character) {
    private val viewModel by viewModels<CharacterViewModel>()
    private val characterAdapter by lazy { CharacterLinearAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initRvCharacter()
        initCharactersCollect()
    }

    private fun initAdapter() {
        binding.adapter = characterAdapter
        binding.executePendingBindings()
    }

    private fun initRvCharacter() {
        binding.rvCharacter.setHasFixedSize(true)
        binding.rvCharacter.addItemDecoration(CharacterItemDecoration())
    }

    private fun initCharactersCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.characters.collectLatest {
                        characterAdapter.submitData(it)
                    }
                }
                launch {
                    characterAdapter.loadStateFlow.collect { loadState ->
                        binding.loadState = loadState
                        binding.executePendingBindings()
                    }
                }
            }
        }
    }
}
