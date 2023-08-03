package com.example.uas_economicnews.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.uas_economicnews.DetailBeritaActivity
import com.example.uas_economicnews.databinding.FragmentCryptoBinding
import com.example.uas_economicnews.ui.adapter.BeritaAdapter
import com.example.uas_economicnews.ui.adapter.GainerAdapter
import com.example.uas_economicnews.ui.api.FetchDataBeritaCrypto
import com.example.uas_economicnews.ui.api.FetchDataGainerCrypto
import com.example.uas_economicnews.ui.api.RetrofitClient
import com.example.uas_economicnews.ui.response.BeritaResponse
import com.google.gson.Gson

class CryptoFragment : Fragment(), BeritaAdapter.OnItemClickListener {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var _binding: FragmentCryptoBinding? = null
    private val gainerAdapter = GainerAdapter()
    private val beritaAdapter = BeritaAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCryptoBinding.inflate(inflater, container, false)

        binding.listBerita.layoutManager = LinearLayoutManager(requireContext())

        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        beritaAdapter.setOnItemClickListener(this)

        getGainerList()
        getBeritaList()

        binding.topGainer.adapter = gainerAdapter
        binding.listBerita.adapter = beritaAdapter
    }

    fun getGainerList() {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(FetchDataGainerCrypto::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getData()
                if (response.isSuccessful()) {
                    gainerAdapter.differ.submitList(response.body()?.top_gainers)
                } else {
                    Log.d("gagal", response.toString())
                }
            }catch (Ex:Exception){
                Log.e("Error",Ex.localizedMessage)
            }
        }
    }

    fun getBeritaList() {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(FetchDataBeritaCrypto::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getData()
                if (response.isSuccessful()) {
                    beritaAdapter.differ.submitList(response.body()?.feed)
                } else {
                    Log.d("gagal", response.toString())
                }
            }catch (Ex:Exception){
                Log.e("Error",Ex.localizedMessage)
            }
        }
    }

    override fun onItemClick(response: BeritaResponse.Berita) {
        val intent = Intent(activity, DetailBeritaActivity::class.java)
        intent.putExtra("res", Gson().toJson(response))
        startActivity(intent)
    }

    private fun refreshData() {
        // Perform the actual refresh operation here (e.g., fetch new data from a server)
        // For this example, we will simulate a delay using postDelayed
        binding.listBerita.postDelayed({
            // Clear the existing data and add new data to dataList
            getBeritaList()

            // Notify the adapter that the data has changed
            beritaAdapter.notifyDataSetChanged()

            // Complete the refresh animation
            binding.swipeRefreshLayout.isRefreshing = false
        }, 2000) // Simulating a 2-second delay for the refresh operation
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): CryptoFragment {
            return CryptoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}