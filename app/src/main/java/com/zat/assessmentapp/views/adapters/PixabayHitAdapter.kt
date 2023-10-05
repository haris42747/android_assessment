package com.zat.assessmentapp.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zat.assessmentapp.R
import com.zat.assessmentapp.databinding.ItemPixabayHitBinding
import com.zat.assessmentapp.models.Hit
import java.lang.StringBuilder

class PixabayHitAdapter(
    var context: Context,
    var onClick: (String) -> Unit
) : RecyclerView.Adapter<PixabayHitAdapter.ViewHolder>() {

    private var mList = ArrayList<Hit>()

    inner class ViewHolder(private val binding: ItemPixabayHitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(model: Hit) {
            Glide.with(context).load(model.largeImageURL).placeholder(R.drawable.img_dummy)
                .into(binding.imgPixabay)

            binding.txtUser.text = model.user
            binding.txtLikes.text = model.likes.toString()
            binding.txtTags.text = model.tags

            binding.root.setOnClickListener {
                onClick(model.largeImageURL)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPixabayHitBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var itemData = mList[position]

        holder.setData(itemData)

    }


    @SuppressLint("NotifyDataSetChanged")
    fun clearList() {
        mList.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<Hit>) {
        mList = list
        notifyDataSetChanged()
    }

}