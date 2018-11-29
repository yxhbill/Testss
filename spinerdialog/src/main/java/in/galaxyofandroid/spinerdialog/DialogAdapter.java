package in.galaxyofandroid.spinerdialog;

import android.app.Activity;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yangxuehao on 2017/9/6.
 */

public class DialogAdapter extends BaseAdapter {
    Activity activity;
    List<ContentEntity> list;
    String type;//判断是多选还是单选
    public DialogAdapter(Activity activity,List<ContentEntity> list ,String type){
        this.activity = activity;
        this.list = list;
        this.type = type;
    }
    public void initData(List<ContentEntity> list){
        this.list = list;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(activity).inflate(R.layout.iteam_layout,null);
        TextView name = (TextView) view.findViewById(R.id.text1);
        TextView bm = (TextView) view.findViewById(R.id.text2);
        ImageView state = (ImageView) view.findViewById(R.id.stateImg);
        name.setText(list.get(position).getContent());
        bm.setText(list.get(position).getId());
        if (type.equals("单选")){
            state.setVisibility(View.GONE);
        }else{
            state.setVisibility(View.VISIBLE);
            if (list.get(position).isSelecte()){
                state.setImageResource(R.drawable.select);
            }else{
                state.setImageResource(R.drawable.unselect);
            }
        }
        return view;
    }
}
