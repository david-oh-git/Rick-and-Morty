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
package io.audioshinigami.characters_list.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import io.audioshinigami.characters_list.databinding.FragmentCharactersListBinding
import io.audioshinigami.characters_list.detail.models.CharacterDetailMapper
import io.audioshinigami.characters_list.list.adapter.CharactersListAdapter
import io.audioshinigami.characters_list.list.adapter.CharactersListAdapterState
import io.audioshinigami.characters_list.list.di.inject
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.ui.extentions.gridLayoutManager
import io.audioshinigami.ui.extentions.observe
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

/**
 * [Fragment]
 * Displays a list of characters on the characters tab.
 */
@ExperimentalCoroutinesApi
class CharactersListFragment : Fragment() {

    lateinit var viewAdapter: CharactersListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val _viewModel: CharactersListViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentCharactersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init dagger
        inject(this)
        viewAdapter = CharactersListAdapter(_viewModel, ::onCharacterItemClickAction)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCharactersListBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = _viewModel
            includeList.charactersList.apply {
                adapter = viewAdapter
                gridLayoutManager?.spanSizeLookup = viewAdapter.getSpanSizeLookup()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(_viewModel.state, ::onViewStateChange)
        observe(_viewModel.data, ::onViewDataChange)
        observe(_viewModel.event, ::onViewEvent)
    }

    /**
     * Observer view state change on [CharactersListViewModel].
     *
     * @param viewState State of characters list.
     */
    private fun onViewStateChange(viewState: CharactersListViewState) {

        when (viewState) {
            is CharactersListViewState.Loaded ->
                viewAdapter.submitState(CharactersListAdapterState.Added)
            is CharactersListViewState.AddLoading ->
                viewAdapter.submitState(CharactersListAdapterState.AddLoading)
            is CharactersListViewState.AddError ->
                viewAdapter.submitState(CharactersListAdapterState.AddError)
            is CharactersListViewState.NoMoreElements ->
                viewAdapter.submitState(CharactersListAdapterState.NoMore)

            else -> {
            }
        }
    }

    /**
     * Observe view data change on [CharactersListViewModel].
     *
     * @param viewData Paged list of characters.
     */
    private fun onViewDataChange(viewData: PagedList<Character>) {
        viewAdapter.submitList(viewData)
    }

    /**
     * Observe [CharactersListViewEvent] for each recyclerView item.
     * @param viewEvent Event class for the recyclerView item.
     */
    private fun onViewEvent(viewEvent: CharactersListViewEvent) {
        when (viewEvent) {
            is CharactersListViewEvent.OpenCharacterDetail -> {
                runBlocking {
                    val action = CharactersListFragmentDirections
                        .actionCharactersListFragmentToCharacterDetailFragment(
                            CharacterDetailMapper().transform(viewEvent.character)
                        )
//                    val extras = FragmentNavigatorExtras()
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun onCharacterItemClickAction(imageView: ImageView, character: Character) {
        runBlocking {
            val action = CharactersListFragmentDirections
                .actionCharactersListFragmentToCharacterDetailFragment(
                    CharacterDetailMapper().transform(character)
                )
            val extras = FragmentNavigatorExtras(
                imageView to character.image
            )
            findNavController().navigate(action, extras)
        }
    }
}
