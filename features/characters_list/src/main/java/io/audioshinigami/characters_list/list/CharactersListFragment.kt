package io.audioshinigami.characters_list.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import io.audioshinigami.characters_list.databinding.FragmentCharactersListBinding
import io.audioshinigami.characters_list.list.adapter.CharactersListAdapter
import io.audioshinigami.characters_list.list.adapter.CharactersListAdapterState
import io.audioshinigami.characters_list.list.di.inject
import io.audioshinigami.core.network.responses.characters.Character
import io.audioshinigami.ui.extentions.gridLayoutManager
import io.audioshinigami.ui.extentions.observe
import timber.log.Timber
import javax.inject.Inject

/**
 * [Fragment]
 * Displays a list of characters on the characters tab.
 */
class CharactersListFragment : Fragment() {

    lateinit var viewAdapter: CharactersListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val _viewModel: CharactersListViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init dagger
        inject(this)
        viewAdapter = CharactersListAdapter(_viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCharactersListBinding.inflate(inflater)
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
        Timber.d("ViewState is: $viewState")
        when (viewState) {
            is CharactersListViewState.Loaded ->
                viewAdapter.submitState(CharactersListAdapterState.Added)
            is CharactersListViewState.AddLoading ->
                viewAdapter.submitState(CharactersListAdapterState.AddLoading)
            is CharactersListViewState.AddError ->
                viewAdapter.submitState(CharactersListAdapterState.AddError)
            is CharactersListViewState.NoMoreElements ->
                viewAdapter.submitState(CharactersListAdapterState.NoMore)
        }
    }

    /**
     * Observer view data change on [CharactersListViewModel].
     *
     * @param viewData Paged list of characters.
     */
    private fun onViewDataChange(viewData: PagedList<Character>) {
        viewAdapter.submitList(viewData)
    }

    private fun onViewEvent(viewEvent: CharactersListViewEvent){
        when(viewEvent){
            is CharactersListViewEvent.OpenCharacterDetail -> {
                // TODO
                Timber.d("*** Open character clicked ****")
            }
        }
    }



}