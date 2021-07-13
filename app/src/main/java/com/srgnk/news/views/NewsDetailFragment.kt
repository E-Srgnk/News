package com.srgnk.news.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.srgnk.news.AppActivity
import com.srgnk.news.commons.*
import com.srgnk.news.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = arguments?.getString(ITEM_TITLE)
        val urlToImage = arguments?.getString(ITEM_URL_TO_IMAGE)
        val url = arguments?.getString(ITEM_URL)
        val publishedAt = arguments?.getString(ITEM_PUBLISHED_AT)
        val content = arguments?.getString(ITEM_CONTENT)

        Picasso.get().load(urlToImage).into(binding.image)
        binding.title.text = title
        binding.date.text = publishedAt
        binding.content.text = content

        binding.showMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}