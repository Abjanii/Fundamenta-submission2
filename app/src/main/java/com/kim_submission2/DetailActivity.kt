package com.kim_submission2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kim_submission2.Adapter.ViewPagerAdapter
import com.kim_submission2.ViewModel.DetailViewModel
import com.kim_submission2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailviewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Activity"

        val tabLayout = findViewById<TabLayout>(R.id.tabs_layout_detail)
        val viewPager2= findViewById<ViewPager2>(R.id.viewpager_layout_detail)
        val adapter= ViewPagerAdapter(supportFragmentManager,lifecycle, data = Bundle())

        viewPager2.adapter=adapter
        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="Follower"
                }
                1->{
                    tab.text="Following"
                }
            }
        }.attach()

        val username = intent.getStringExtra(DETAIL_USER)
        val bundle = Bundle()
        bundle.putString(DETAIL_USER, username)

        detailviewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        if (username != null) {
            detailviewModel.setDataUserDetail(username)
        }
        showLoading(true)
        detailviewModel.getDataUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    UserName.text = it.name
                    DetailLogin.text = it.login
                    Id.text = it.id.toString()
                    LocationDetail.text = it.location ?: "Not Found"
                    CompanyDetail.text = it.company ?: "Not Found"
                    Follower.text = it.followers.toString() ?: "Not Found"
                    Following.text = it.following.toString() ?: "Not Found"
                    Glide.with(this@DetailActivity)
                        .load(it.avatar_url)
                        .circleCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView)
                    showLoading(false)
                }
            }
        }
    }
    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressBar.visibility= View.VISIBLE
        }
        else {
            binding.progressBar.visibility= View.GONE
        }
    }
    companion object {
        const val DETAIL_USER = "detail_user"
    }
}