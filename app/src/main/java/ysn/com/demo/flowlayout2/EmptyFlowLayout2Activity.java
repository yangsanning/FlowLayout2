package ysn.com.demo.flowlayout2;

import android.os.Bundle;
import android.view.View;

import ysn.com.demo.flowlayout2.base.BaseActivity;
import ysn.com.demo.flowlayout2.bean.User;
import ysn.com.view.flowlayout2.FlowLayout2;
import ysn.com.view.flowlayout2.base.BaseFlowLayout2Adapter;
import ysn.com.view.flowlayout2.base.BaseFlowLayout2ViewHolder;

/**
 * @Author yangsanning
 * @ClassName EmptyFlowLayout2Activity
 * @Description FlowLayout2为空时演示
 * @Date 2020/1/19
 * @History 2020/1/19 author: description:
 */
public class EmptyFlowLayout2Activity extends BaseActivity {

    FlowLayout2Adapter flowLayout2Adapter;

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

        /**
         * @see BaseFlowLayout2Adapter#getEmptyView()
         */
        flowLayout2Adapter.setEmptyLayoutRes(R.layout.layout_empty)
            .getEmptyView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout2Adapter.setNewData(getData());
            }
        });
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
