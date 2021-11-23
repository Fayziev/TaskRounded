package uz.gita.taskrounded.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.taskrounded.R
import uz.gita.taskrounded.data.response.ResultsItem
import uz.gita.taskrounded.databinding.FragmentInfoBinding

class InfoFragment : Fragment(R.layout.fragment_info) {

    private val binding by viewBinding(FragmentInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = requireArguments()
        val data = bundle.getSerializable("data") as ResultsItem
        Glide.with(requireActivity())
            .load(data.image)
            .into(binding.imageUrl)
        binding.name.text = data.name
        binding.date.text = data.created
        binding.speciesInfo.text = data.species
        binding.status.text = data.status
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}