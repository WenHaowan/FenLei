package as.bwei.com.fenlei.presenter;

import java.io.IOException;

import as.bwei.com.fenlei.model.SeedShowModel;
import as.bwei.com.fenlei.view.SeedShowView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by HP on 2018/10/16.
 */

public class SeedShowPresenter {
    private SeedShowView seedShowView;
    private SeedShowModel seedShowModel;

    public SeedShowPresenter(SeedShowView seedShowView) {
        this.seedShowView = seedShowView;
        seedShowModel = new SeedShowModel();
    }

    public void showSeed(String cid){
        seedShowModel.showSeed(cid, new SeedShowModel.SeedShowCallBack() {
            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    seedShowView.onSeedSuccess(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                seedShowView.onSeedFailure("失败");
            }
        });
    }
}
