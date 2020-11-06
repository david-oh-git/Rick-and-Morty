/*
 * MIT License
 *
 * Copyright (c) 2020 David Osemwota.
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
import io.audioshinigami.characters_list.R.navigation.nav_characters_list_graph
import io.audioshinigami.favourites_list.R.navigation.nav_favourites_list_graph
import io.audioshinigami.home.databinding.FragmentHomeBinding
import io.audioshinigami.home.di.inject
import io.audioshinigami.ui.extentions.setupWithNavController

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val navGraphIds = listOf(
        nav_characters_list_graph,
        nav_favourites_list_graph
    )

    private val _viewModel: HomeViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
        if (savedInstanceState == null)
            setupBottomNavigationBar()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        setupBottomNavigationBar()
    }

    private fun setupToolbar() {
        when (requireActivity()) {
            is AppCompatActivity -> {
                (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
            }
        }
    }

    private fun setupBottomNavigationBar() {
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
