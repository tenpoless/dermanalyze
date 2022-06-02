package com.example.dermanalyze_bangkit_capstone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var rvArticles: RecyclerView
    private val list = ArrayList<Articles>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_main, container, false)
        val view = inflater.inflate(R.layout.fragment_main, container, false)

//        binding.rvArticles.setHasFixedSize(true)
//        list.addAll(listArticles)
////        showRecyclerList()
//        Log.i("TAG", "##### isi list artikel $list")

        rvArticles = view.findViewById(R.id.rv_articles)
        rvArticles.setHasFixedSize(true)
        list.clear()
        list.addAll(listArticles)
        showRecyclerList()

        return  view
    }



    private val listArticles: ArrayList<Articles>
        get() {
            val dataName = resources.getStringArray(R.array.data_title)
            val dataDescription = resources.getStringArray(R.array.readmore)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listArticles = ArrayList<Articles>()
            for (i in dataName.indices) {
                val articles = Articles(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listArticles.add(articles)
            }
            return listArticles
        }

    private fun showRecyclerList() {
        rvArticles.layoutManager = LinearLayoutManager(activity)
        val listArticlesAdapter = ListArticlesAdapter(list)
        rvArticles.adapter = listArticlesAdapter

//        listArticlesAdapter.setOnItemClickCallback(object : listArticlesAdapter.OnItemClickCallback {
//            override fun onItemClicked() {
////                moveActivity(data)
//            }
//        })
        listArticlesAdapter.setOnItemClickCallback(object : ListArticlesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Articles) {
//                showSelectedHero(data)
                moveActivity(data)
                Log.i("TAG", "####### ${data.titleArticles}")
                Log.i("TAG", "####### ${data.photo}")
                Log.i("TAG", "####### ${data.readmorearticle}")
                Toast.makeText(context, "Kamu memilih " + data.titleArticles, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun moveActivity(data: Articles) {
        val intent = Intent(context, DetailMainActivity::class.java)
//        intent.putExtra(DetailMainActivity.EXTRA_USER, data)
        startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}