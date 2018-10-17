package as.bwei.com.fenlei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import as.bwei.com.fenlei.adapter.FristAdapter;
import as.bwei.com.fenlei.bean.FristUserBean;
import as.bwei.com.fenlei.presenter.ShowPresenter;
import as.bwei.com.fenlei.view.ShowView;

public class MainActivity extends AppCompatActivity implements ShowView{

    private RecyclerView first_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        ShowPresenter presenter = new ShowPresenter(this);
        presenter.show();
        first_recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
    }

    private void initView() {
        first_recyclerView = (RecyclerView) findViewById(R.id.first_recyclerView);
    }

    @Override
    public void onSuccess(final String result1) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                FristUserBean fristUserBean = gson.fromJson(result1, FristUserBean.class);
                List<FristUserBean.DataBean> data = fristUserBean.getData();
                FristAdapter fristAdapter = new FristAdapter(MainActivity.this, data);
                first_recyclerView.setAdapter(fristAdapter);
            }
        });
    }

    @Override
    public void onFailure(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
