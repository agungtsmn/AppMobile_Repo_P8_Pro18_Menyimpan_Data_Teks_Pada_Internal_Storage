package id.ac.polbeng.amandaagungpermata.p8pro18

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileNotFoundException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMainActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }
    }

    private fun saveData() {
        val fileName = "ourfile.txt"
        Thread(Runnable {
            try {
                val out = openFileOutput(fileName, Context.MODE_PRIVATE)
                out.use {
                    out.write(inputText.text.toString().toByteArray())
                }
                runOnUiThread(Runnable {
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                })
            } catch (ioe: IOException) {
                Log.w(TAG, "Error while saving ${fileName} : ${ioe}")
            }
        }).start()
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val fileName = "ourfile.txt"
        Thread(Runnable {
            try {
                val input = openFileInput(fileName)
                input.use {
                    var buffer = StringBuilder()
                    var bytes_read = input.read()
                    while (bytes_read != -1) {
                        buffer.append(bytes_read.toChar())
                        bytes_read = input.read()
                    }
                    runOnUiThread(Runnable {
                        inputText.setText(buffer.toString())
                    })
                }
            } catch (fnfe: FileNotFoundException) {
                Log.w(TAG, "file not found, occurs only once")
            } catch (ioe: IOException) {
                Log.w(TAG, "IOException : $ioe")
            }
        }).start()
    }
}