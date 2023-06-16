package com.example.blockchain_blackbox_android2

import android.content.Context
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blockchain_blackbox_android2.databinding.ActivityAccidentHelpBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AccidentHelpActivity : AppCompatActivity(){
    private lateinit var binding: ActivityAccidentHelpBinding
    var helpVideoList: ArrayList<Video> = ArrayList<Video>()
    var nearCarVideoList: ArrayList<Video> = ArrayList<Video>()
    lateinit var helpVideoRVAdapter: HelpVideoRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccidentHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvGallery.setOnClickListener{

            //갤러리에서 동영상 가져오기
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "video/*"
            startActivityForResult(intent, 1)
        }

        binding.tvAccidentPlaceInfo.setOnClickListener{
            val intent = Intent(applicationContext, MapRequestActivity::class.java)
            startActivity(intent)
        }

        binding.tvBtnOk.setOnClickListener{
            Toast.makeText(applicationContext, "도움이 접수되었습니다", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        helpVideoRVAdapter = HelpVideoRVAdapter(helpVideoList, this)
        helpVideoRVAdapter.setOnItemClickListener(object : HelpVideoRVAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int) {
                val intent = Intent(applicationContext, VideoPlayActivity::class.java)
                intent.putExtra("uri", helpVideoList[pos].uri.toString())
                Log.d("uri:", helpVideoList[pos].uri.toString())
                startActivity(intent)
            }
        })

        val helpVideoLinearLayoutManager = LinearLayoutManager(this)
        helpVideoLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvMyVideo.layoutManager = helpVideoLinearLayoutManager
        binding.rvMyVideo.adapter = helpVideoRVAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1 -> {
                if (resultCode == RESULT_OK) {
                    val uri: Uri? = data?.data
                    Log.d("uri", uri.toString())
                    // imageView.setImageURI(uri)
                    helpVideoList.add(Video(uri, "hello1", ""))
                    helpVideoRVAdapter.notifyDataSetChanged()

                    binding.tvAccidentDateInfo.text = extractDate(this, uri.toString()).toString()
                }
            }
        }
    }

    fun extractDate(activity: Context, path: String): Date?{
        var dateStr: String? = null
        var dateFormat: Date? = null
        var mediaMetadataRetriever: MediaMetadataRetriever? = null

        try {
            mediaMetadataRetriever = MediaMetadataRetriever()
            mediaMetadataRetriever.setDataSource(activity, Uri.parse(path))

            var dateStr = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE)
            if (dateStr != null) {
                val formatter = SimpleDateFormat("yyyyMMdd'T'HHmmss.SSS'Z'", Locale.getDefault())
                dateFormat = formatter.parse(dateStr)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mediaMetadataRetriever?.release()
        }
        return dateFormat
    }

}