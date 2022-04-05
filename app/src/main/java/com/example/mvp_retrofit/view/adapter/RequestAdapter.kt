package com.example.mvp_retrofit.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_retrofit.entity.ActivityViewState

class RequestAdapter: RecyclerView.Adapter<RequestAdapter.MyViewHolder>() {

    private var requestList = emptyList<ActivityViewState>()

    fun setRequests(requestsViewStates: List<ActivityViewState>?){
        if (requestsViewStates != null) {
            this.requestList = requestsViewStates
        }
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun setData(activityViewState: ActivityViewState){

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}