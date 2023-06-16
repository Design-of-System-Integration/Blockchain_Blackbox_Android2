package com.example.blockchain_blackbox_android2

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class AlarmVideoPlayActivity : AppCompatActivity() {
    var videoView: VideoView? = null
    var btnStart: Button? = null
    var btnStop: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)
        /**
         * 영상을 출력하기 위한 비디오뷰
         * SurfaceView를 상속받아 만든 클래스
         * 웬만하면 VideoView는 그때 그때 생성해서 추가 후 사용
         * 화면 전환 시 여러 UI가 있을 때 화면에 제일 먼저 그려져서 보기에 좋지 않을 때가 있다
         * 예제에서 xml에 추가해서 해봄
         */
        //레이아웃 위젯 findViewById
        videoView = findViewById<View>(R.id.vv_video_play) as VideoView
        //        btnStart = (Button) findViewById(R.id.btnStart);
//        btnStop = (Button) findViewById(R.id.btnStop);

        //미디어컨트롤러 추가하는 부분
        val controller = MediaController(this@AlarmVideoPlayActivity)
        videoView!!.setMediaController(controller)

        //비디오뷰 포커스를 요청함
        videoView!!.requestFocus()
        val type = URL
        when (type) {
            URL ->                 //동영상 경로가 URL일 경우
                videoView!!.setVideoURI(Uri.parse("https://rlpstorage.blob.core.windows.net/container0/accident1.mp4"))
            SDCARD -> {
                //동영상 경로가 SDCARD일 경우
                val path = Environment.getExternalStorageDirectory()
                    .toString() + "/TestVideo.mp4"
                videoView!!.setVideoPath(path)
            }
        }


        //동영상이 재생준비가 완료되었을 때를 알 수 있는 리스너 (실제 웹에서 영상을 다운받아 출력할 때 많이 사용됨)
        videoView!!.setOnPreparedListener { //                Toast.makeText(VideoPlayActivity.this,
            //                        "동영상이 준비되었습니다. \n'시작' 버튼을 누르세요", Toast.LENGTH_SHORT).show();
            playVideo()
        }

        //동영상 재생이 완료된 걸 알 수 있는 리스너
        videoView!!.setOnCompletionListener { //동영상 재생이 완료된 후 호출되는 메소드
            Toast.makeText(
                this@AlarmVideoPlayActivity,
                "동영상 재생이 완료되었습니다.", Toast.LENGTH_SHORT
            ).show()
        }
    }

    //시작 버튼 onClick Method
    fun StartButton(v: View?) {
        playVideo()
    }

    //정지 버튼 onClick Method
    fun StopButton(v: View?) {
        stopVideo()
    }

    //동영상 재생 Method
    private fun playVideo() {
        //비디오를 처음부터 재생할 때 0으로 시작(파라메터 sec)
        videoView!!.seekTo(0)
        videoView!!.start()
    }

    //동영상 정지 Method
    private fun stopVideo() {
        //비디오 재생 잠시 멈춤
        videoView!!.pause()
        //비디오 재생 완전 멈춤
//        videoView.stopPlayback();
        //videoView를 null로 반환 시 동영상의 반복 재생이 불가능
//        videoView = null;
    }

    companion object {
        const val VIDEO_URL = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4"
        const val URL = 1
        const val SDCARD = 2
    }
}