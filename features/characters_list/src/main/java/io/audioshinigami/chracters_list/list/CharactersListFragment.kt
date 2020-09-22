package io.audioshinigami.chracters_list.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import io.audioshinigami.chracters_list.databinding.FragmentCharactersListBinding
import io.audioshinigami.chracters_list.list.adapter.CharactersListAdapter
import io.audioshinigami.chracters_list.list.adapter.CharactersListAdapterState
import io.audioshinigami.core.network.responses.characters.Character

/**
 * A simple [Fragment] subclass.
 * Use the [CharactersListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharactersListFragment : Fragment() {

    lateinit var viewAdapter: CharactersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCharactersListBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
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



}