package com.hackday.imageSearch._base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<T: ViewDataBinding> : Fragment() {
    abstract val vm: ViewModel
    protected val binding by lazy {
        DataBindingUtil.inflate(inflater, getLayoutRes(), container, false) as T
    }

    private lateinit var inflater: LayoutInflater
    private var container: ViewGroup? = null

    @LayoutRes
    abstract fun getLayoutRes(): Int
    abstract fun setupBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater
        this.container = container

        setupBinding()
        binding.lifecycleOwner = this

        return binding.root
    }

    fun toast(content: String) {
        with(activity) {
            if (this == null) {
                return
            }
            Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
        }
    }

}