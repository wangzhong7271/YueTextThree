package com.bwie.test.yuetextthree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import api.Api;
import bean.MessageEvent;
import bean.Myuser;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.HomeAdapter;
import presenter.userpresenter;
import view.Iview;

public class MainActivity extends AppCompatActivity implements Iview {
    HomeAdapter adapter;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.mxr)
    XRecyclerView mxr;
    userpresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new userpresenter(this);
        presenter.getUrl(Api.URL);
    }

    @Override
    public void getJson(final Myuser user) {
        mxr.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeAdapter(user,MainActivity.this);
        mxr.setAdapter(adapter);
        adapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                String url = user.getData().get(position-1).getVedio_url();
                Toast.makeText(MainActivity.this, "点击", Toast.LENGTH_SHORT).show();
                //发送粘性事件//////////////////
                EventBus.getDefault().postSticky(new MessageEvent(url));
                // 跳转到视频下载播放页面
                Intent intent = new Intent(MainActivity.this, VidoActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "长恩", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
