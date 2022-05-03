package com.example.appnext.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.appnext.R
import com.example.appnext.databinding.FragmentDetailsBinding
import com.example.appnext.extensions.dp
import com.example.appnext.ui.adapters.StateAdapter
import com.example.appnext.ui.adapters.decorators.GroupVerticalItemDecoration
import com.example.appnext.ui.viewModels.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FragmentDetails : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    private val stateAdapter by lazy {
        StateAdapter()
    }


    val viewModel: MainViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        with(binding.activityDetails) {
            adapter = stateAdapter
            addItemDecoration(
                GroupVerticalItemDecoration(
                    R.layout.item_daily_result,
                    32.dp().toInt(),
                    0
                )
            )
        }

        viewModel.state.onEach {
            stateAdapter.setItems(it.detailsItems)
        }
            .launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}