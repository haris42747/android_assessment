package com.zat.assessmentapp.views.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zat.assessmentapp.R
import com.zat.assessmentapp.base.BaseFragment
import com.zat.assessmentapp.base.Inflate
import com.zat.assessmentapp.databinding.FragmentImageBinding
import com.zat.assessmentapp.utils.Constants.IMAGE


class ImageFragment : BaseFragment<FragmentImageBinding>() {
    override val inflate: Inflate<FragmentImageBinding>
        get() = FragmentImageBinding::inflate

    private var image: String? = ""

    override fun observeLiveData() {

    }

    override fun init(savedInstanceState: Bundle?) {

        image = getStringArgument(IMAGE)

        Glide.with(currentActivity()).load(image).placeholder(R.drawable.img_dummy)
            .into(binding.imgPixabay)

    }

}