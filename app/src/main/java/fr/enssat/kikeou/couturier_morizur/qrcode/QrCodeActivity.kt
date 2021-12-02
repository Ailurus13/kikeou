package fr.enssat.kikeou.couturier_morizur.qrcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.enssat.kikeou.couturier_morizur.databinding.ActivityQrCodeBinding
import net.glxn.qrgen.android.QRCode

class QrCodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQrCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("data")
        val bitmap = QRCode.from(data).withSize(500,500).bitmap()
        binding.qrCodeImageView.setImageBitmap(bitmap)

        binding.title.text = intent.getStringExtra("title")
    }
}
