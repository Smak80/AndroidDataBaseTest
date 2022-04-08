package ru.smak.databasetest.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.smak.databasetest.R
import ru.smak.databasetest.databinding.AddFragmentBinding

class AddFragment : Fragment() {

    companion object {
        fun newInstance() = AddFragment()
    }

    private lateinit var binding: AddFragmentBinding
    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddFragmentBinding.inflate(inflater, container, false)
        binding.imageButton.setOnClickListener(::onViewChangeClick)
        binding.imageButton2.setOnClickListener(::onSaveData)
        return binding.root
    }

    private fun onSaveData(view: View?) {
        viewModel.person = Person(
            binding.etPersonName.text.toString(),
            binding.etAge.text.toString().toInt(),
            binding.etSalary.text.toString().toFloat()
        )
        activity?.let{
            viewModel.save(it.applicationContext)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)
    }

    fun onViewChangeClick(view: View){
        activity?.apply{
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}