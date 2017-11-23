package intent;

import bean.Myuser;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by 笔片 on 2017/11/23.
 */

public interface ApiServices {
    @GET("iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=vedio")
    Observable<Myuser> getNoParams();
}
