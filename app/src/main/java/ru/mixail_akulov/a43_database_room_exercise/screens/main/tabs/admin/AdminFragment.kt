package ru.mixail_akulov.a43_database_room_exercise.screens.main.tabs.admin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.mixail_akulov.a43_database_room_exercise.R
import ru.mixail_akulov.a43_database_room_exercise.Repositories
import ru.mixail_akulov.a43_database_room_exercise.databinding.FragmentAdminTreeBinding
import ru.mixail_akulov.a43_database_room_exercise.utils.resources.ContextResources
import ru.mixail_akulov.a43_database_room_exercise.utils.viewModelCreator

/**
 * Contains only RecyclerView which displays the whole tree of data from the database
 * starting from accounts and ending with box settings.
 */
class AdminFragment : Fragment(R.layout.fragment_admin_tree) {

    private lateinit var binding: FragmentAdminTreeBinding

    private val viewModel by viewModelCreator {
        AdminViewModel(Repositories.accountsRepository, ContextResources(requireContext()))
    }

    private lateinit var adapter: AdminItemsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAdminTreeBinding.bind(view)

        val layoutManager = LinearLayoutManager(requireContext())
        adapter = AdminItemsAdapter(viewModel)

        binding.adminTreeRecyclerView.layoutManager = layoutManager
        binding.adminTreeRecyclerView.adapter = adapter

        observeTreeItems()
    }

    private fun observeTreeItems() {
        viewModel.items.observe(viewLifecycleOwner) { treeItems ->
            adapter.renderItems(treeItems)
        }
    }

}