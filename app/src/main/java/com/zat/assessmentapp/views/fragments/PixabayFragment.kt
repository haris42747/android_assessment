package com.zat.assessmentapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.zat.assessmentapp.R
import com.zat.assessmentapp.base.BaseFragment
import com.zat.assessmentapp.base.Inflate
import com.zat.assessmentapp.databinding.FragmentPixabayBinding
import com.zat.assessmentapp.models.Hit
import com.zat.assessmentapp.repositories.NetworkResult
import com.zat.assessmentapp.utils.Constants
import com.zat.assessmentapp.viewModels.PixabayViewModel
import com.zat.assessmentapp.views.adapters.PixabayHitAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PixabayFragment : BaseFragment<FragmentPixabayBinding>() {
    override val inflate: Inflate<FragmentPixabayBinding>
        get() = FragmentPixabayBinding::inflate

    private lateinit var pixabayHitAdapter: PixabayHitAdapter

    private val pixabayViewModel by viewModels<PixabayViewModel>()

    override fun observeLiveData() {

        bindObserverForPixabayImages()

    }

    private fun bindObserverForPixabayImages() {
        pixabayViewModel.pixabayImagesResponseLiveData.observe(viewLifecycleOwner) {
            hideLoadingBar()
            when (it) {
                is NetworkResult.Success -> {

                    it.data?.let { response ->
                        response.hits.let { hits ->
                            pixabayHitAdapter.updateList(hits as ArrayList<Hit>)
                        }
                    }

                    pixabayViewModel.pixabayImagesResponseLiveData.value = null
                }

                is NetworkResult.Error -> {
                    showToast(it.message)
                    pixabayViewModel.pixabayImagesResponseLiveData.value = null
                }

                is NetworkResult.Loading -> {
                    showLoadingBar()
                }
            }
        }
    }

    override fun init(savedInstanceState: Bundle?) {

        adapterFunctionForPixabay()

        pixabayViewModel.getPixabayImages(
            currentActivity(),
            "38437071-5f1d14114d464cb7440d92ebd",
            "kitten",
            "photo",
            true
        )
    }

    private fun adapterFunctionForPixabay() {
        pixabayHitAdapter = PixabayHitAdapter(currentActivity()) {img->
            var bundle = Bundle()
            bundle.putString(Constants.IMAGE,img)
            currentActivity().replaceFragment(R.id.imageFragment,bundle)

        }

        binding.rvPixabay.adapter = pixabayHitAdapter
    }

}