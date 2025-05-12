package com.example.tplogin.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tplogin.ApiInterface
import com.example.tplogin.data.ImageType
import com.example.tplogin.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private  val binding get() = _binding!!

    lateinit var myAdapter : ImageAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }


    private fun setData() {



        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.unsplash.com/")
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getImage()


        retrofitData.enqueue(object : Callback<List<ImageType>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<ImageType>?>,
                response: Response<List<ImageType>?>
            ) {

                Log.i("erreur image api", response.body().toString())

                val responseBody = response.body()!!
                myAdapter = ImageAdapter( context!!, responseBody)
                binding.imageRecyclerView.adapter = myAdapter
                myAdapter.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<List<ImageType>?>, t:
                Throwable
            ) {
                Log.i("erreur image api", t.message.toString())
            }
        }
        )


    }

}