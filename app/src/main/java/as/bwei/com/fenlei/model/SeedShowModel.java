package as.bwei.com.fenlei.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HP on 2018/10/16.
 */

public class SeedShowModel {
    private OkHttpClient okHttpClient = new OkHttpClient();
    public void showSeed(String cid, final SeedShowCallBack showSeedCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("cid", cid)
                .build();
        Request request = new Request.Builder()
                .url("https://www.zhaoapi.cn/product/getProductCatagory")
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showSeedCallBack.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                showSeedCallBack.onSuccess(call, response);
            }
        });
    }
    public interface SeedShowCallBack{
        void onSuccess(Call call, Response response);
        void onFailure(Call call, IOException e);
    }
}
