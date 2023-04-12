package info.fekri.mvvmexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import info.fekri.mvvmexample.databinding.ItemRecyclerBinding
import info.fekri.mvvmexample.model.Blog
import info.fekri.mvvmexample.viewModel.MainViewModel

class NoteRecyclerAdapter(
    private val viewModel: MainViewModel,
    private val data: ArrayList<Blog>,
    private val context: Context
) :
    RecyclerView.Adapter<NoteRecyclerAdapter.NotesViewHolder>() {
    private lateinit var binding: ItemRecyclerBinding

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(blog: Blog) {
            binding.txtTitle.text = blog.title
            binding.btnDelete.setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(data.indexOf(blog))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerBinding.inflate(inflater, parent, false)
        return NotesViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        if (data.size == 0) {
            Toast.makeText(context, "List is empty!", Toast.LENGTH_SHORT).show()
        }
        return data.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(data[position])
    }

}