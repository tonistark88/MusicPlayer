package com.example.music
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.music.databinding.RecentLayoutBinding

@Suppress("DEPRECATION")
class RecentActivity : AppCompatActivity() {
    lateinit var binding: RecentLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecentLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recentBackBtnId.setOnClickListener {
            finishAndRemoveTask()
        }

}
}
