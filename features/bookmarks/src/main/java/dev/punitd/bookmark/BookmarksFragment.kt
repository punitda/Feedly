package dev.punitd.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dev.punitd.base.android.extensions.viewBinding
import dev.punitd.bookmark.databinding.FragmentBookmarksBinding

@AndroidEntryPoint
class BookmarksFragment : Fragment() {

    private val binding by viewBinding(FragmentBookmarksBinding::inflate)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root
}
