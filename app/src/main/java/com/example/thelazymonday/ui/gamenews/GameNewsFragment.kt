package com.example.thelazymonday.ui.gamenews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelazymonday.databinding.FragmentGameNewsBinding
import com.example.thelazymonday.vo.Status
import javax.inject.Inject

class GameNewsFragment : Fragment() {

    private lateinit var binding: FragmentGameNewsBinding

    @Inject
    lateinit var viewModel: GameNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGameNewsBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val adapter = GameNewsAdapter

            viewModel.getGameNews().observe(viewLifecycleOwner, { gameNews ->
                if (gameNews != null) {
                    when (gameNews.status) {
                        Status.SUCCESS -> binding.progressBar.visibility = View.GONE

                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                        }

                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    }
                }
            })

            with(binding.rvGameNews) {
                this.layoutManager = LinearLayoutManager(context)
                this.adapter = GameNewsAdapter()
                this.setHasFixedSize(true)

            }
        }
    }

}