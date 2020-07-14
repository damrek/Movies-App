package com.app.moviesapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.app.moviesapp.AppDatabase
import com.app.moviesapp.R
import com.app.moviesapp.data.DataSource
import com.app.moviesapp.domain.RepoImpl
import com.app.moviesapp.ui.viewmodel.MainViewModel
import com.app.moviesapp.ui.viewmodel.VMFactory
import com.app.moviesapp.vo.Resource

class FavoritesFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(
            RepoImpl(
                DataSource(
                    AppDatabase.getDatabase(requireActivity().applicationContext)
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Log.d("FAVORITE LIST", "${result.data}")
                }
                is Resource.Failure -> {

                }
            }
        })
    }
}