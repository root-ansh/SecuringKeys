package work.curioustools.securingkeys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*


//https://guides.codepath.com/android/Storing-Secret-Keys-in-Android
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val key = BuildConfig.KEY_USER

        tv_info?.text = key




    }
}