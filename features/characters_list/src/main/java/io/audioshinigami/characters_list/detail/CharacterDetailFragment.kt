package io.audioshinigami.characters_list.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.audioshinigami.characters_list.databinding.FragmentCharacterDetailBinding
import io.audioshinigami.ui.extentions.observe

/**
 * Displays a character's details.
 */
class CharacterDetailFragment : Fragment() {

    private val _viewModel: CharacterDetailViewModel by viewModels()
    private lateinit var binding: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterDetailBinding.inflate(inflater)
            .apply { 
                lifecycleOwner = viewLifecycleOwner
                viewModel = _viewModel
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(_viewModel.state, ::onViewStateChange)

        _viewModel.setData(args.characterDetail)
    }

    /**
     * Observer view state change on [CharacterDetailViewState].
     *
     * @param viewState State of character detail.
     */
    private fun onViewStateChange(viewState: CharacterDetailViewState){
        when(viewState){
            is CharacterDetailViewState.Dismiss -> findNavController().navigateUp()
        }
    }
}