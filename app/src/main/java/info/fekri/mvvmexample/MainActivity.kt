package info.fekri.mvvmexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.fekri.mvvmexample.databinding.ActivityMainBinding
import info.fekri.mvvmexample.model.Blog
import info.fekri.mvvmexample.viewModel.MainViewModel
import info.fekri.mvvmexample.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        binding.btnSubmit.setOnClickListener { addData() }

        initializeAdapter()
    }

    private fun initializeAdapter() {
        binding.recyclerBlogs.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        observeData()
    }

    private fun observeData() {
        viewModel.lst.observe(this) {
            binding.recyclerBlogs.adapter = NoteRecyclerAdapter(viewModel, it, this)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addData() {
        var txtplce = binding.edtTitle
        var title=txtplce.text.toString()
        if(title.isNullOrBlank()){
            Toast.makeText(this,"Enter value!",Toast.LENGTH_LONG).show()
        }else{
            var blog= Blog(title)
            viewModel.add(blog)
            txtplce.text!!.clear()
            binding.recyclerBlogs.adapter?.notifyDataSetChanged()
        }

    }

}