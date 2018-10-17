package as.bwei.com.fenlei.adapter;

import android.content.Context;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import as.bwei.com.fenlei.R;
import as.bwei.com.fenlei.bean.FristUserBean;
import as.bwei.com.fenlei.bean.SeedUserBean;
import as.bwei.com.fenlei.presenter.SeedShowPresenter;
import as.bwei.com.fenlei.view.SeedShowView;

/**
 * Created by HP on 2018/10/16.
 */

public class FristAdapter extends RecyclerView.Adapter <FristAdapter.MyViewHolder> implements SeedShowView {
    private Context context;
    private List<FristUserBean.DataBean> data;
    SeedShowPresenter seedShowPresenter = new SeedShowPresenter(this);
    private List<SeedUserBean.DataBean.ListBean> list1;
    private SeedAdapter seedAdapter;

    public FristAdapter(Context context, List<FristUserBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public FristAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frist_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final FristAdapter.MyViewHolder holder, final int position) {
        holder.frist_text.setText(data.get(position).getName());
        holder.seed_recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        holder.frist_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cid = data.get(position).getCid();
                seedShowPresenter.showSeed(cid+"");
                holder.seed_recyclerView.setAdapter(seedAdapter);
            }
        });
        holder.seed_recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"--------------",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onSeedSuccess(String result) {
        Looper.prepare();
        //Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        SeedUserBean seedUserBean = gson.fromJson(result, SeedUserBean.class);
        List<SeedUserBean.DataBean> list = seedUserBean.getData();
        for (int i = 0 ; i < list.size() ; i++){
            list1 = list.get(i).getList();
            seedAdapter = new SeedAdapter(context, list1);
        }
        Looper.loop();
    }

    @Override
    public void onSeedFailure(String msg) {
        Looper.prepare();
        Looper.loop();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView frist_text;
        private RecyclerView seed_recyclerView;
        public MyViewHolder(View itemView) {
            super(itemView);
            frist_text = (TextView) itemView.findViewById(R.id.frist_text);
            seed_recyclerView = (RecyclerView) itemView.findViewById(R.id.seed_recyclerView);
        }
    }
}
