package cn.mtjsoft.www.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.mtjsoft.www.app.databinding.ActivityMainBinding
import cn.mtjsoft.www.audiorecord.AudioRecordActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            audioRecordBtn.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.audioRecordBtn -> {
                startActivity(Intent(this, AudioRecordActivity::class.java))
            }
        }
    }
}