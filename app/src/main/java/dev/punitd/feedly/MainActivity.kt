package dev.punitd.feedly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.punitd.base.android.extensions.viewBinding
import dev.punitd.feedly.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
