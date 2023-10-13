package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.ActivityNewsappBinding
import com.google.android.material.snackbar.Snackbar


class NewsAppActivity :AppCompatActivity(){

    private lateinit var viewModel : NewsAppViewModel
    private lateinit var binding: ActivityNewsappBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityNewsappBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(NewsAppViewModel::class.java)

        viewModel.newsDataState.observe(this, Observer { state ->
            when(state) {
                is NewsDataState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rvNews.visibility = View.GONE
                }
                is NewsDataState.Success -> {
                    binding.progress.visibility = View.GONE
                    binding.rvNews.apply {
                        visibility = View.VISIBLE
                        state.data?.let {
                            adapter = NewsAdapter(it, object : NewsAdapter.ItemClickListener{
                                override fun onItemClick(position: Int) {
                                    val intent = Intent(binding.root.context, NewsAppDetailActivity::class.java)
                                    intent.putExtra("newsUrl", it.get(position).content)
                                    // 调用 startActivity 启动目标 Activity
                                    startActivity(intent)
                                }
                            });
                        }
                        layoutManager = LinearLayoutManager(binding.root.context)
                    }

                }
                is NewsDataState.Error -> {
                    binding.progress.visibility = View.GONE
                    Snackbar.make(binding.root, "出错了！",Snackbar.LENGTH_LONG).show()
                }
            }
        })
        viewModel.loadData()

    }
}