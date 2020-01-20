package ysn.com.demo.flowlayout2;

import android.os.Bundle;
import android.view.View;

import ysn.com.demo.flowlayout2.base.BaseActivity;
import ysn.com.demo.flowlayout2.bean.User;
import ysn.com.view.flowlayout2.FlowLayout2;
import ysn.com.view.flowlayout2.base.BaseFlowLayout2Adapter;
import ysn.com.view.flowlayout2.base.BaseFlowLayout2Type;
import ysn.com.view.flowlayout2.base.BaseFlowLayout2ViewHolder;

/**
 * @Author yangsanning
 * @ClassName MultiTypeFlowLayout2Activity
 * @Description FlowLayout2多类型演示
 * @Date 2020/1/19
 * @History 2020/1/19 author: description:
 */
public class MultiTypeFlowLayout2Activity extends BaseActivity {

    MultiTypeFlowLayout2Adapter multiTypeFlowLayout2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_flow_layout_2);

        final FlowLayout2 flowLayout2 = findViewById(R.id.default_flow_layout_2_activity_flow_layout_2);

        findViewById(R.id.default_flow_layout_2_activity_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flowLayout2.isLastAvg()) {
                    flowLayout2.setLastAvg(Boolean.FALSE);
                } else {
                    flowLayout2.setLastAvg(Boolean.TRUE);
                }
            }
        });

        multiTypeFlowLayout2Adapter = new MultiTypeFlowLayout2Adapter();
        multiTypeFlowLayout2Adapter.setOnItemClickListener(new BaseFlowLayout2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseFlowLayout2Adapter adapter, View view, int position) {
                showMsg("点击" + multiTypeFlowLayout2Adapter.getDatas().get(position).getName());
            }
        }).setOnItemLongClickListener(new BaseFlowLayout2Adapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(BaseFlowLayout2Adapter adapter, View view, int position) {
                showMsg("长按" + multiTypeFlowLayout2Adapter.getDatas().get(position).getName());
            }
        });

        flowLayout2.setAdapter(multiTypeFlowLayout2Adapter);
        multiTypeFlowLayout2Adapter.setNewData(getData());
    }

    private class MultiTypeFlowLayout2Adapter extends BaseFlowLayout2Adapter<User, BaseFlowLayout2ViewHolder> {

        protected MultiTypeFlowLayout2Adapter() {
            super();

            // 注意: 一定要先设置FlowLayout2Type, 方可进行相关操作
            setFlowLayout2Type(new BaseFlowLayout2Type<User>() {
                @Override
                protected int getItemType(User user) {
                    return user.getId() % 2 == 1 ? 0 : 1;
                }
            });

            // 通过获取FlowLayout2Type进行类型绑定
            getFlowLayout2Type().addItemType(0, R.layout.item_multi_type_flow_layout_2_1);

            // 直接进行类型绑定
            addItemType(1, R.layout.item_multi_type_flow_layout_2_2);
        }

        @Override
        public void convert(BaseFlowLayout2ViewHolder holder, int position, User item) {
            if (getItemType(position) == 0) {
                holder.setText(R.id.multi_type_flow_layout_2_1_item_text, item.getName());
            } else {
                holder.setText(R.id.multi_type_flow_layout_2_2_item_text, item.getName());
                holder.setOnClickListener(R.id.multi_type_flow_layout_2_2_item_icon, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showMsg("点击了图片");
                    }
                });
            }
        }
    }
}
