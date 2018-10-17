package as.bwei.com.fenlei.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HP on 2018/10/16.
 */

public class ShowModel {
    private OkHttpClient okHttpClient = new OkHttpClient();
    public void show(final ShowCallBack showCallBack){
        Request request = new Request.Builder()
                .url("https://www.zhaoapi.cn/product/getCatagory")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showCallBack.onFailure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                showCallBack.onSuccess(call,response);
            }
        });
    }
    public interface ShowCallBack{
        void onSuccess(Call call, Response response);
        void onFailure(Call call , IOException e);
    }
}
