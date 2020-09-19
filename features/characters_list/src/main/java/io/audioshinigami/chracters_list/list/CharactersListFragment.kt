package io.audioshinigami.chracters_list.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.audioshinigami.chracters_list.R

/**
 * A simple [Fragment] subclass.
 * Use the [CharactersListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharactersListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters_list, container, false)
    }

}