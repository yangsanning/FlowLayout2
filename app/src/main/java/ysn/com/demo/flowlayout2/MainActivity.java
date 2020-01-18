package ysn.com.demo.flowlayout2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import ysn.com.view.flowlayout2.FlowLayout2;
import ysn.com.view.flowlayout2.base.BaseFlowLayout2Adapter;
import ysn.com.view.flowlayout2.base.BaseFlowLayout2ViewHolder;

public class MainActivity extends AppCompatActivity {

    FlowLayout2Adapter flowLayout2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlowLayout2 flowLayout2 = findViewById(R.id.main_activity_flow_layout_2);

        flowLayout2Adapter = new FlowLayout2Adapter();
        flowLayout2Adapter.setOnItemClickListener(new BaseFlowLayout2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseFlowLayout2Adapter adapter, View view, int position) {
                showMsg("点击" + flowLayout2Adapter.getDatas().get(position).getName());
            }
        }).setOnItemLongClickListener(new BaseFlowLayout2Adapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(BaseFlowLayout2Adapter adapter, View view, int position) {
                showMsg("长按" + flowLayout2Adapter.getDatas().get(position).getName());
            }
        });
        flowLayout2.setAdapter(flowLayout2Adapter);

        flowLayout2Adapter.setEmptyLayoutRes(R.layout.layout_empty)
            .getEmptyView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout2Adapter.setNewData(getData());
            }
        });
    }

    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private ArrayList<User> getData() {
        ArrayList<User> dataList = new ArrayList<>();
        String[] array = getResources().getStringArray(R.array.data);
        for (int i = 0; i < array.length; i++) {
            dataList.add(new User(array[i], i));
        }
        return dataList;
    }

    private class FlowLayout2Adapter extends BaseFlowLayout2Adapter<User, BaseFlowLayout2ViewHolder> {

        protected FlowLayout2Adapter() {
            super(R.layout.item_flow_layout_2);
        }


        @Override
        public void convert(BaseFlowLayout2ViewHolder holder, int position, User item) {
            holder.setText(R.id.flow_layout_2_item_text, item.getName());
        }
    }
}
