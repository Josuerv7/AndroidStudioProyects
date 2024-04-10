import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView=findViewById<WebView>(R.id.webView1)
        val myWebSettings=webView.settings
        myWebSettings.javaScriptEnabled=true
        myWebSettings.domStorageEnabled=true
        webView.loadUrl("file:///android_asset/index.html")
    }
}