package as.bwei.com.fenlei.okhttputils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import as.bwei.com.fenlei.model.ShowModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HP on 2018/10/16.
 */

public class OkHttpUtil {
    public static OkHttpUtil okHttpUtil;
    public OkHttpClient okHttpClient;

    public OkHttpUtil(){
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public static OkHttpUtil getInstence(){
        if (okHttpUtil == null){
            synchronized (OkHttpUtil.class){
                if (okHttpUtil == null){
                    okHttpUtil = new OkHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }
    public void postData(String url, HashMap<String,String> parms, final ShowModel.ShowCallBack showCallBack){
        FormBody.Builder formBody = new FormBody.Builder();
        if (parms !=null && parms.size()>0){
            for (Map.Entry<String,String> stringStringEntry: parms.entrySet()
                    ) {
                formBody.add(stringStringEntry.getKey(),stringStringEntry.getValue());
            }

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody.build())
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
    }
}
