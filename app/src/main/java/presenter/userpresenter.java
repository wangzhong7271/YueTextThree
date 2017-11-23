package presenter;

import bean.Myuser;
import model.OnFinishLisenter;
import model.usermodel;
import view.Iview;

/**
 * Created by 笔片 on 2017/11/23.
 */

public class userpresenter {
    Iview iview;
    usermodel usermodel;

    public userpresenter(final Iview iview) {
        this.iview = iview;
        this.usermodel = new usermodel();
        usermodel.setOnFinishLisenter(new OnFinishLisenter() {
            @Override
            public void OnLinsenter(Myuser bean) {
                iview.getJson(bean);
            }
        });
    }
    public void getUrl(String url){
        usermodel.getUrl(url);
    }
}
