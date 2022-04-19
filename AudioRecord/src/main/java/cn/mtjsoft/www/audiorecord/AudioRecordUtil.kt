package cn.mtjsoft.www.audiorecord

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import cn.mtjsoft.www.baseres.extentions.RxLifeScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

/**
 * AudioRecord  录制 PCM音频
 */
class AudioRecordUtil {

    companion object {
        private const val audioSource = MediaRecorder.AudioSource.MIC
        private const val sampleRateInHz = 44100
        private const val channelConfig = AudioFormat.CHANNEL_IN_MONO
        private const val audioFormat = AudioFormat.ENCODING_PCM_16BIT

        val init: AudioRecordUtil by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            AudioRecordUtil()
        }
    }

    private var status = Status.STATUS_READY

    /**
     * 获得缓冲区字节大小
     */
    private val recordBuffSize: Int by lazy {
        AudioRecord.getMinBufferSize(
            sampleRateInHz,
            channelConfig,
            audioFormat
        )
    }

    private val audioRecord: AudioRecord by lazy {
        AudioRecord(
            audioSource,
            sampleRateInHz,
            channelConfig,
            audioFormat,
            recordBuffSize
        )
    }

    @Synchronized
    fun startRecord(savePath: String) {
        RxLifeScope().launch({
            withContext(Dispatchers.IO) {
                File(savePath).also {
                    it.exists() && it.delete()
                }
                audioRecord.startRecording()
                val audioData = ByteArray(recordBuffSize)
                val bos = BufferedOutputStream(FileOutputStream(savePath))
                while (status == Status.STATUS_START) {
                    val readSize = audioRecord.read(audioData, 0, recordBuffSize)
                    if (readSize > 0) {
                        bos.write(audioData, 0, recordBuffSize)
                    }
                }
                bos.flush()
                bos.close()
            }
        }, {
            // 错误
            it.printStackTrace()
            release()
        }, {
            status = Status.STATUS_START
        }, {
            stopRecord()
        })
    }

    fun stopRecord() {
        status = Status.STATUS_STOP
        audioRecord.stop()
    }

    fun release() {
        stopRecord()
        status = Status.STATUS_RELEASE
        audioRecord.release()
    }
}