package io.audioshinigami.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.ui.NavigationUI
import io.audioshinigami.home.databinding.FragmentHomeBinding
import io.audioshinigami.home.di.inject
import io.audioshinigami.ui.extentions.setupWithNavController


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val navGraphIds = listOf(
        R.navigation.nav_characters_list_graph
    )

    private val _viewModel: HomeViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = _viewModel
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        if( savedInstanceState == null)
            setupBottomNavigationBar()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        setupBottomNavigationBar()
    }

    private fun setupToolbar(){
        when(requireActivity()){
            is AppCompatActivity -> (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        }
    }

    private fun setupBottomNavigationBar(){
        val navController = binding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.home_nav_host_container,
            intent = requireActivity().intent
        )

        navController.observe(viewLifecycleOwner, Observer {
            _viewModel.navigationControllerChanged(it)
            NavigationUI.setupActionBarWithNavController(
                requireActivity() as AppCompatActivity,
                it
            )
        })
    }

}