package stpious.student.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import stpious.employee.R
import stpious.employee.utills.customFonts.Text_Bold

class WebviewActivity : AppCompatActivity() {

    private var mTitle: String = ""
    private var mUrl: String = ""
    private lateinit var ivBack: ImageView
    private lateinit var llback: LinearLayout
    private lateinit var tvHeadertitle: Text_Bold
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_layout)

        mUrl = intent.getStringExtra("url").toString()
        mTitle = intent.getStringExtra("title").toString()

        initialise()

        webView.webViewClient = WebViewClient()
        webView.loadUrl(mUrl)
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
        llback.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

    }

    private fun initialise() {

        webView = findViewById(R.id.webView)
        llback = findViewById(R.id.llback)
        ivBack = findViewById(R.id.ivBack)
        tvHeadertitle = findViewById(R.id.tvHeadertitle)

        ivBack.visibility=View.VISIBLE
        tvHeadertitle.text = mTitle

        llback.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(R.anim.move_right_enter, R.anim.move_right_exit)
    }
}
