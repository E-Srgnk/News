package com.srgnk.news.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.srgnk.news.NewsState
import com.srgnk.news.adapters.NewsAdapter
import com.srgnk.news.data.GoogleNewsItem
import com.srgnk.news.databinding.FragmentNewsBinding
import com.srgnk.news.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(), NewsAdapter.ItemClickListener {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()
    private val newsAdapter by lazy { NewsAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility = View.VISIBLE

        binding.newsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        viewModel.newsState.observe(viewLifecycleOwner) { state ->
            state?.let {
                when (it) {
                    is NewsState.Success -> {
                        newsAdapter.setNews(it.googleNews.news)
                        binding.progressBar.visibility = View.GONE
                    }
                    is NewsState.Error -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        viewModel.fetchNews()
    }

    override fun itemClicked(item: GoogleNewsItem) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}