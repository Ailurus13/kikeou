package fr.enssat.kikeou.couturier_morizur.qrcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import fr.enssat.kikeou.couturier_morizur.databinding.ActivityQrCodeBinding

class ReadQrCodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQrCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQrCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient(
            BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
                .build()
        )
    }
}
