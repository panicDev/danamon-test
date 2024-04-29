package com.danamon.test.feature.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danamon.common.utils.DiffUtils
import com.danamon.domain.model.User
import com.danamon.test.databinding.ItemUsersBinding
import kotlin.properties.Delegates

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>(), DiffUtils {

    var data: List<User> by Delegates.observable(emptyList()) { _, old, new ->
        calculateDiff(old, new) { oldItem, newItem -> oldItem == newItem }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(
        private val binding: ItemUsersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) = with(binding) {
            val id = "ID: ${item.id}"
            val username = "Username: ${item.username}"
            val email = "Email: ${item.email}"
            val role = "Role: ${item.role}"

            textId.text = id
            textUsername.text = username
            textEmail.text = email
            textRole.text = role
        }
    }
}
