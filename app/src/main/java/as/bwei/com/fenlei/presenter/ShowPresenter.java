package as.bwei.com.fenlei.presenter;

import java.io.IOException;

import as.bwei.com.fenlei.model.ShowModel;
import as.bwei.com.fenlei.view.ShowView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by HP on 2018/10/16.
 */

public class ShowPresenter {
    private ShowView showView;
    private ShowModel showModel;
    private String result1;

    public ShowPresenter(ShowView showView) {
        this.showView = showView;
        showModel = new ShowModel();
    }

    public void show(){
        showModel.show(new ShowModel.ShowCallBack() {
            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    result1 = response.body().string();
                    showView.onSuccess(result1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                showView.onFailure("失败");
            }
        });
    }
}
