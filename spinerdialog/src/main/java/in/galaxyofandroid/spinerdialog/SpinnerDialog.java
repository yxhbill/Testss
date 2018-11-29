package in.galaxyofandroid.spinerdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Md Farhan Raja on 2/23/2017.
 */

public class SpinnerDialog {
    ArrayList<ContentEntity> items;
    Activity context;
    String dTitle;
    String type;//单选或者多选
    OnSpinerItemClick onSpinerItemClick;
//    AlertDialog alertDialog;
    int pos;
    int style;



    public SpinnerDialog(Activity activity,ArrayList<ContentEntity> items,String dialogTitle,String type) {
        this.items = items;
        this.context = activity;
        this.dTitle=dialogTitle;
        this.type=type;
    }

    public SpinnerDialog(Activity activity,ArrayList<ContentEntity> items,String dialogTitle,int style) {
        this.items = items;
        this.context = activity;
        this.dTitle=dialogTitle;
        this.style=style;
    }

    public void bindOnSpinerListener(OnSpinerItemClick onSpinerItemClick1) {
        this.onSpinerItemClick = onSpinerItemClick1;
    }

    public void showSpinerDialog() {
//        AlertDialog.Builder adb = new AlertDialog.Builder(context);

        View v = context.getLayoutInflater().inflate(R.layout.dialog_layout, null);
        TextView rippleViewClose = (TextView) v.findViewById(R.id.close);
        TextView title = (TextView) v.findViewById(R.id.spinerTitle);
        title.setText(dTitle);
        //判断是多选还是单选
        if (type.equals("单选")){
            rippleViewClose.setText("关闭");
        }else{
            rippleViewClose.setText("完成");
        }
        final ListView listView = (ListView) v.findViewById(R.id.list);
        final EditText searchBox = (EditText) v.findViewById(R.id.searchBox);
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.items_view, items);
        final DialogAdapter adapter1 = new DialogAdapter(context,items,type);
        listView.setAdapter(adapter1);
        final MyDialog builder = new MyDialog(context,0,0,v,R.style.DialogTheme);
        builder.setCancelable(false);

//        adb.setView(v);
//        alertDialog = adb.create();
//        alertDialog.getWindow().getAttributes().windowAnimations = style;//R.style.DialogAnimations_SmileWindow;
//        alertDialog.setCancelable(false);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                TextView t=(TextView)view.findViewById(R.id.text1);
                TextView bm=(TextView)view.findViewById(R.id.text2);
                Log.d("qqqqqq", "onItemClick: "+bm.getText().toString());
                for(int j=0;j<items.size();j++)
                {
                    if(bm.getText().toString().equalsIgnoreCase(items.get(j).getId()))
                    {
                        pos=j ;
                    }
                }
                if (type.equals("单选")){
                    onSpinerItemClick.onClick(t.getText().toString(),pos);
                    builder.dismiss();
                }else{
                    if (items.get(pos).isSelecte()){
                        items.get(pos).setSelecte(false);
                    }else{
                        items.get(pos).setSelecte(true);
                    }
                    adapter1.notifyDataSetChanged();
                }
            }
        });

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                adapter1.getFilter().filter(searchBox.getText().toString());
                List<ContentEntity> list = new ArrayList();
                String value = searchBox.getText().toString();
                if (value==null || value.equals("")){
                    list.addAll(items);
                }else{
                    for (ContentEntity name:items) {
                        int num = name.getContent().indexOf(value);
                        if (num !=-1){
                            list.add(name);
                        }
                    }
                }
                adapter1.initData(list);
            }
        });

        rippleViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                alertDialog.dismiss();
                onSpinerItemClick.onDismiss();
                builder.dismiss();

            }
        });
//        alertDialog.show();
        builder.show();
    }

}
