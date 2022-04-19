package cn.mtjsoft.www.audiorecord

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.mtjsoft.www.audiorecord.databinding.ActivityAudiorecordBinding
import com.permissionx.guolindev.PermissionX

class AudioRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAudiorecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudiorecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PermissionX.init(this).permissions(Manifest.permission.RECORD_AUDIO)
            .request { allGranted, grantedList, deniedList ->
            }
        binding.apply {
            startBtn.setOnClickListener {
                AudioRecordUtil.init.startRecord(getExternalFilesDir("audios")!!.absolutePath + "/audio.pcm")
            }

            stopBtn.setOnClickListener {
                AudioRecordUtil.init.stopRecord()
            }
        }
    }


    override fun onDestroy() {
        AudioRecordUtil.init.release()
        super.onDestroy()
    }
}