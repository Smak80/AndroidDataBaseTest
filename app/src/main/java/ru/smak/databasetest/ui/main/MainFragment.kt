package ru.smak.databasetest.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.smak.databasetest.R
import ru.smak.databasetest.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var viewModel: MainViewModel? = null
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.imageButton.setOnClickListener(::onViewChangeClick)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel?.let { v ->
            v.data.observe(viewLifecycleOwner){
                activity?.let{ act ->
                    it.forEach {
                        val tv = TextView(act.baseContext)
                        tv.setText(it.toString())
                        binding.dbView.addView(tv)
                    }
                }
            }
        }
    }

    fun onViewChangeClick(view: View){
        activity?.apply{
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddFragment.newInstance())
                .commitNow()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onResume() {
        super.onResume()
        activity?.let{
            viewModel?.update(it.applicationContext)
        }
    }
}