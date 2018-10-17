package as.bwei.com.fenlei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import as.bwei.com.fenlei.R;
import as.bwei.com.fenlei.bean.SeedUserBean;

/**
 * Created by HP on 2018/10/16.
 */

public class SeedAdapter extends RecyclerView.Adapter <SeedAdapter.MyViewHolder2>{
    private Context context;
    private List<SeedUserBean.DataBean.ListBean> list1;

    public SeedAdapter(Context context, List<SeedUserBean.DataBean.ListBean> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @Override
    public SeedAdapter.MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seed_layout,parent,false);
        MyViewHolder2 myViewHolder2 = new MyViewHolder2(view);
        return myViewHolder2;
    }

    @Override
    public void onBindViewHolder(SeedAdapter.MyViewHolder2 holder, int position) {
        holder.seed_text.setText(list1.get(position).getName());
        Glide.with(context).load(list1.get(position).getIcon()).into(holder.seed_image);
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        private ImageView seed_image;
        private TextView seed_text;
        public MyViewHolder2(View itemView) {
            super(itemView);
            seed_image = (ImageView) itemView.findViewById(R.id.seed_image);
            seed_text = (TextView) itemView.findViewById(R.id.seed_text);
        }
    }
}
