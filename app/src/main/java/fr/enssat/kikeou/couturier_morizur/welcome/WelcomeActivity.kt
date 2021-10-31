package fr.enssat.kikeou.couturier_morizur.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.enssat.kikeou.couturier_morizur.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
