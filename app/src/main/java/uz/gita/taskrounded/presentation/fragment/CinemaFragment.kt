package uz.gita.taskrounded.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.taskrounded.R
import uz.gita.taskrounded.data.response.ResultsItem
import uz.gita.taskrounded.databinding.FragmentCinemaBinding
import uz.gita.taskrounded.presentation.adapter.CinemaAdapter
import uz.gita.taskrounded.presentation.viewmodel.impl.CinemaViewModelImpl
import uz.gita.taskrounded.utils.showToast
import uz.gita.taskrounded.utils.visible

class CinemaFragment : Fragment(R.layout.fragment_cinema) {
    private val binding by viewBinding(FragmentCinemaBinding::bind)
    private val list = ArrayList<ResultsItem>()
    private val adapter = CinemaAdapter(list)
    private val viewModel: CinemaViewModelImpl by viewModels()
    private var currentPage = 1
    private var availablePage = 42

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.listView.adapter = adapter
        binding.listView.layoutManager = LinearLayoutManager(requireContext())
        adapter.setListener { data ->
            val bundle = Bundle()
            bundle.putSerializable("data", data)
            findNavController().navigate(R.id.action_cinemaFragment_to_infoFragment, bundle)
        }

        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.connectionLiveData.observe(viewLifecycleOwner, connectionObserver)
        viewModel.allDataLiveData.observe(viewLifecycleOwner, allDataLiveObserver)

        binding.listView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (binding.listView.canScrollVertically(1)) {
                    if(currentPage<=availablePage){
                        currentPage++
                        pages()
                    }
                }
            }
        })
        pages()
    }

    private fun pages() {
        viewModel.getAllDataPage(currentPage)
    }

    private val allDataLiveObserver = Observer<List<ResultsItem>> {
        val oldSize = list.size
        list.addAll(it)
        adapter.notifyItemRangeInserted(oldSize, list.size)
    }

    private val connectionObserver = Observer<String> { showToast(it) }
    private val progressObserver = Observer<Boolean> { binding.progress.visible(it) }
}