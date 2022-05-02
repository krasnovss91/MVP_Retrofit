package com.example.mvp_retrofit.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_retrofit.R
import com.example.mvp_retrofit.entity.ActivityViewState

interface OnRequestSelected {
    fun onSelected(requestViewState: ActivityViewState)
}

interface OnRequestDeleted {
    fun onDeleted(requestViewState: ActivityViewState)
}

class RequestAdapter(
    private val listener: OnRequestSelected,
    private val deleteListener: OnRequestDeleted
) : RecyclerView.Adapter<RequestAdapter.MyViewHolder>() {

    private var requestList = emptyList<ActivityViewState>()

    fun setRequests(requestsViewStates: List<ActivityViewState>?) {
        if (requestsViewStates != null) {
            this.requestList = requestsViewStates
        }
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(activityViewState: ActivityViewState) {

            itemView.findViewById<TextView>(R.id.requestInfo).text = activityViewState.activity
            itemView.setOnClickListener {
                listener.onSelected(activityViewState)
            }

            itemView.findViewById<View>(R.id.deleteRequestInfoButton).setOnClickListener {
                deleteListener.onDeleted(activityViewState)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.request_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val request = requestList[position]
        holder.setData(request)
    }

    override fun getItemCount(): Int = requestList.size
}