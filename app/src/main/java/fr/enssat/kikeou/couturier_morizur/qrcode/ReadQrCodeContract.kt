package fr.enssat.kikeou.couturier_morizur.qrcode

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class ReadQrCodeContract: ActivityResultContract<Void?, String>() {
    override fun createIntent(context: Context, input: Void?): Intent =
        Intent(context, ReadQrCodeActivity::class.java)

    override fun parseResult(resultCode: Int, result: Intent?): String? {
        if (resultCode != Activity.RESULT_OK) {
            return null;
        }
        var jsonData = result?.getStringExtra("jsonData")

        return if(jsonData != null) {
            return jsonData
        } else {
            null
        }
    }
}
