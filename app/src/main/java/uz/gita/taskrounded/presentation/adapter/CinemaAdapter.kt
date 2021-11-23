package uz.gita.taskrounded.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.taskrounded.R
import uz.gita.taskrounded.app.App
import uz.gita.taskrounded.data.response.ResultsItem

class CinemaAdapter(private val list: List<ResultsItem>) : RecyclerView.Adapter<CinemaAdapter.VH>() {
    private var listener: ((ResultsItem) -> Unit)? = null

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val imageUrl = view.findViewById<ImageView>(R.id.imageUrl)
        private val status = view.findViewById<TextView>(R.id.status)
        private val species = view.findViewById<TextView>(R.id.species)
        private val title = view.findViewById<TextView>(R.id.title)

        init {
            itemView.setOnClickListener {
                listener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() {
            Glide.with(App.instance)
                .load(list[absoluteAdapterPosition].image)
                .into(imageUrl)
            status.text = list[absoluteAdapterPosition].status
            species.text = list[absoluteAdapterPosition].species
            title.text = list[absoluteAdapterPosition].name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_cinema, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()
    override fun getItemCount(): Int = list.size

    fun setListener(f: (ResultsItem) -> Unit) {
        listener = f
    }
}