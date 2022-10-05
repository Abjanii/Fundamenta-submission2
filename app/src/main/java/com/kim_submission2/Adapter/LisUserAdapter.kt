package com.kim_submission2.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kim_submission2.Response.DataUser
import com.kim_submission2.databinding.ItemRowGithubBinding

class LisUserAdapter : RecyclerView.Adapter<LisUserAdapter.ListViewHolder>() {

    private val listUser = ArrayList<DataUser>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setListUser(users: ArrayList<DataUser>) {
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(val binding: ItemRowGithubBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataUser) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(imgItem)
                Login.text = user.login
                LoginId.text = user.id.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemRowGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked (data: DataUser)
    }
}