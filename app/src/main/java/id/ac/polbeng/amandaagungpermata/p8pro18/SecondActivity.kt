package id.ac.polbeng.amandaagungpermata.p8pro18

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btnSecondActivity.setOnClickListener {
            startActivity(Intent(this@SecondActivity, MainActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val filename = "ourfile.txt"
        Thread(Runnable {
            val input = openFileInput(filename)
            input.use {
                var buffer = StringBuilder()
                var bytes_read = input.read()
                while (bytes_read != -1) {
                    buffer.append(bytes_read.toChar())
                    bytes_read = input.read()
                }
                runOnUiThread(Runnable {
                    tvSaja.setText(buffer.toString())
                })
            }
        }).start()
    }
}