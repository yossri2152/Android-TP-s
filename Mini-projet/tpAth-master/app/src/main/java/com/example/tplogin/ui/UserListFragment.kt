package com.example.tplogin.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tplogin.ApiInterface
import com.example.tplogin.User

import com.example.tplogin.databinding.FragmentUsersListBinding

import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class UserListFragment : Fragment() {
    lateinit var user: FirebaseUser
    lateinit var myAdapter : MyAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding =
            FragmentUsersListBinding.inflate(layoutInflater, container, false)

        setData(binding)
        return binding.root
    }

    private fun setData(binding: FragmentUsersListBinding) {


       // val txt = binding.txt

        val rcl = binding.rcl
        rcl.layoutManager = LinearLayoutManager(context)


//add retrofit builder
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()


        retrofitData.enqueue(object : Callback<List<User>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<User>?>,
                response: Response<List<User>?>
            ) {
               /* val myStringBuilder = StringBuilder()
                val responseBody = response.body()!!

                for (i in responseBody) {
                    myStringBuilder.append(i.id)
                    myStringBuilder.append("\n")
                }*/

              //  txt.text = myStringBuilder
                val responseBody = response.body()!!
                myAdapter = MyAdapter( context!!, responseBody)
                myAdapter.notifyDataSetChanged()
                rcl.adapter = myAdapter




            }

            override fun onFailure(
                call: Call<List<User>?>, t:
                Throwable
            ) {
                i("erreur api", t.message.toString())
            }
        }
        )


    }
}