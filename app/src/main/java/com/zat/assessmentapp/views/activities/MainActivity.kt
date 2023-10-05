package com.zat.assessmentapp.views.activities

import android.os.Bundle
import com.zat.assessmentapp.R
import com.zat.assessmentapp.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun attachViewMode() {

    }
}