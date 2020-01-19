package ysn.com.demo.flowlayout2;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import ysn.com.demo.flowlayout2.base.BaseActivity;
import ysn.com.demo.flowlayout2.bean.User;
import ysn.com.view.flowlayout2.FlowLayout2;
import ysn.com.view.flowlayout2.base.BaseFlowLayout2Adapter;
import ysn.com.view.flowlayout2.base.BaseFlowLayout2ViewHolder;

/**
 * @Author yangsanning
 * @ClassName EmptyFlowLayout2Activity
 * @Description 默认FlowLayout2演示
 * @Date 2020/1/19
 * @History 2020/1/19 author: description:
 */
public class DefaultFlowLayout2Activity extends BaseActivity {

    FlowLayout2Adapter flowLayout2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_flow_layout_2);

        FlowLayout2 flowLayout2 = findViewById(R.id.default_flow_layout_2_activity_flow_layout_2);

        flowLayout2Adapter = new FlowLayout2Adapter();
        flowLayout2Adapter.setOnItemClickListener(new BaseFlowLayout2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseFlowLayout2Adapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        showMsg("本页面即为默认演示");
                        break;
                    case 1:
                        startActivity(EmptyFlowLayout2Activity.class);
                        break;
                    case 2:
                        startActivity(MultiTypeFlowLayout2Activity.class);
                        break;
                    default:
                        showMsg("点击" + flowLayout2Adapter.getDatas().get(position).getName());
                        break;
                }
            }
        }).setOnItemLongClickListener(new BaseFlowLayout2Adapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(BaseFlowLayout2Adapter adapter, View view, int position) {
                showMsg("长按" + flowLayout2Adapter.getDatas().get(position).getName());
            }
        });

        flowLayout2.setAdapter(flowLayout2Adapter);
        flowLayout2Adapter.setNewData(getData());
    }

    @Override
    protected ArrayList<User> getData() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(0, new User("默认FlowLayout2演示"));
        userList.add(1, new User("FlowLayout2为空时演示"));
        userList.add(2, new User("FlowLayout2多类型演示"));
        return userList;
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
