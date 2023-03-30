package com.example.submissionreal3.ui.detail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submissionreal3.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME ="extra_username"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)


        if (username != null) {
            viewModel.setUserDetail(username)
        }



        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    Glide.with(this@DetailActivity)
                        .load(it.avatar_url)
                        .into(detailImgview)
                }
                showLoading(false)
            }
        }

        viewModel.isLoading.observe(this){
            showLoading(it)
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            if (viewPager != null){
                TabLayoutMediator(tabs, viewPager) { tab, position ->
                    tab.text = resources.getString(sectionPagerAdapter.getTabTitle(position))
                }.attach()
            }
        }
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}


