package model;

import bean.Myuser;
import intent.ApiServices;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 笔片 on 2017/11/23.
 */

public class usermodel implements Imodel{

    OnFinishLisenter onFinishLisenter;

    public void setOnFinishLisenter(OnFinishLisenter onFinishLisenter) {
        this.onFinishLisenter = onFinishLisenter;
    }

    @Override
    public void getUrl(String url) {
        //  创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        // 通过动态代理得到网络接口对象
        ApiServices apiservice = retrofit.create(ApiServices.class);

        Observable<Myuser> user = apiservice.getNoParams();
        user.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Myuser>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Myuser user) {
                        onFinishLisenter.OnLinsenter(user);
                    }
                });
    }
}
