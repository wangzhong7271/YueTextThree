package com.bwie.test.yuetextthree;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import bean.MessageEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VidoActivity extends AppCompatActivity {

    RelativeLayout activityVido;
    String vedio_url;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private Button start;
    private Button pause;


    private TextView total;
    private int max;
    private DownloadUtil mDownloadUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vido);
        ButterKnife.bind(this);
        //注册EventBus
        EventBus.getDefault().register(VidoActivity.this);

        total= (TextView) findViewById(R.id.textView);
        start= (Button) findViewById(R.id.start);
        pause= (Button) findViewById(R.id.delete);
        mProgressBar= (ProgressBar) findViewById(R.id.progressBar);
        String urlString = vedio_url;
        final String localPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/local";
        mDownloadUtil = new DownloadUtil(2, localPath, "adc.mp4", urlString,
                this);
        mDownloadUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {

            @Override
            public void downloadStart(int fileSize) {
                // TODO Auto-generated method stub
                Log.w(TAG, "fileSize::" + fileSize);
                max = fileSize;
                mProgressBar.setMax(fileSize);
            }

            @Override
            public void downloadProgress(int downloadedSize) {
                // TODO Auto-generated method stub
                Log.w(TAG, "Compelete::" + downloadedSize);
                mProgressBar.setProgress(downloadedSize);
                total.setText((int) downloadedSize * 100 / max + "%");
            }

            @Override
            public void downloadEnd() {
                // TODO Auto-generated method stub
                Log.w(TAG, "ENd");
                String url = localPath+"/adc.mp4";
                new PlayerView(VidoActivity.this)
                        .setTitle("什么")
                        .setScaleType(PlayStateParams.fitparent)
                        .hideMenu(true)
                        .forbidTouch(false)
                        .setPlaySource(url)
                        .startPlay();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // TODO Auto-generated method stub
                mDownloadUtil.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mDownloadUtil.pause();
            }
        });

    }



    //事件订阅者处理事件
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onUserEvent(MessageEvent event) {
        vedio_url = event.getUrl();

    }
    //取消注册
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
   }

}
