package fr.enssat.kikeou.couturier_morizur.welcome

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class WelcomeActivityContract: ActivityResultContract<Void?, WelcomeActivityContract.WelcomeContractData>() {
    override fun createIntent(context: Context, input: Void?): Intent =
        Intent(context, WelcomeActivity::class.java)

    override fun parseResult(resultCode: Int, result: Intent?): WelcomeContractData? {
        if (resultCode != Activity.RESULT_OK) {
            return null;
        }
        var firstname = result?.getStringExtra("firstname")
        var lastname = result?.getStringExtra("lastname")

        return if(firstname != null && lastname != null) {
            WelcomeContractData(firstname, lastname)
        } else {
            null
        }
    }

    class WelcomeContractData(
        var firstname:String,
        var lastname:String
    )
}
