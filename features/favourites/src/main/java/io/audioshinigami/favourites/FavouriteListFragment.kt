/*
 * MIT License
 *
 * Copyright (c) $today.day/$today.month/2020 $today.hour24:$today.minute   David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.audioshinigami.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import io.audioshinigami.core.data.CharacterFavourite
import io.audioshinigami.favourites.adaptor.CharacterFavouriteAdaptor
import io.audioshinigami.favourites.databinding.FragmentFavouriteListBinding
import io.audioshinigami.favourites.di.inject
import io.audioshinigami.favourites.adaptor.CharacterFavouriteTouchHelper
import io.audioshinigami.ui.extentions.observe
import javax.inject.Inject

/**
 * A [Fragment] that displays a list of favourite characters on the favourites tab.
 */
class FavouriteListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val _viewModel: FavouriteListViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var binding: FragmentFavouriteListBinding
    private val _adaptor by lazy {
        CharacterFavouriteAdaptor()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init Dagger
        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteListBinding.inflate(inflater)
        binding.apply {
            viewModel = _viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(_viewModel.data, ::onViewDataChange)
        initRecyclerView()
    }

    /**
     * Initialise the recyclerView and add [ItemTouchHelper] for swipe to delete
     * action.
     */
    private fun initRecyclerView() {
        binding.includeList.favouritesList
            .apply {
                adapter = _adaptor

                ItemTouchHelper(
                    CharacterFavouriteTouchHelper {
                        _viewModel.deleteCharacterFavourite(_adaptor.currentList[it].id)
                    }
                ).attachToRecyclerView(this)
            }
    }

    /**
     * Observes data change on [FavouriteListViewModel]
     * @param data List of favourite characters.
     */
    private fun onViewDataChange(data: List<CharacterFavourite>) {
        _adaptor.submitList(data.reversed())
    }
}
