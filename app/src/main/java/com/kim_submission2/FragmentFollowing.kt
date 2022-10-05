package com.kim_submission2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kim_submission2.Adapter.LisUserAdapter
import com.kim_submission2.ViewModel.DetailViewModel
import com.kim_submission2.databinding.ItemRowFragmentBinding

class FragmentFollowing : Fragment(R.layout.item_row_fragment) {
    private var _binding : ItemRowFragmentBinding? = null
    private val binding get() =  _binding!!
    private lateinit var followingViewModel: DetailViewModel
    private lateinit var followingAdapter: LisUserAdapter
    private lateinit var username : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg = arguments
        username = requireActivity().intent.getStringExtra(DetailActivity.DETAIL_USER).toString()
        _binding= ItemRowFragmentBinding.bind(view)

        followingAdapter = LisUserAdapter()
        followingAdapter.notifyDataSetChanged()

        binding.apply {
            rvGithub.setHasFixedSize(true)
            rvGithub.layoutManager = LinearLayoutManager(activity)
            rvGithub.adapter = followingAdapter
        }
        showLoading(true)
        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        followingViewModel.setDataListFollowing(username)
        followingViewModel.getDataListFollowing().observe(viewLifecycleOwner) {
            if (it != null) {
                followingAdapter.setListUser(it)
                showLoading(false)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressBar.visibility= View.VISIBLE
        }
        else {
            binding.progressBar.visibility= View.GONE
        }
    }
}