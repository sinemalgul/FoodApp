package com.sinemalgul.foodapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.data.model.Restaurant
import com.sinemalgul.foodapp.databinding.ImageContainerBinding
import com.sinemalgul.foodapp.databinding.ItemRestaurantsBinding

class CampaignAdapter(private val campaignList: ArrayList<Int>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<CampaignAdapter.CampaignViewHolder>() {

    var onCampaignClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val binding =
            ImageContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CampaignViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
        holder.bind(campaignList[position])
        if (position == campaignList.size - 1) {
            viewPager2.post(runnable)
        }
    }

    inner class CampaignViewHolder(val binding: ImageContainerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.imageContainer.setImageResource(image)

            binding.root.setOnClickListener {
                onCampaignClick(image)
            }
        }
    }

    override fun getItemCount() = campaignList.size

    private val runnable = Runnable {
        campaignList.addAll(campaignList)
        notifyItemRangeChanged(0, campaignList.size)
    }
}