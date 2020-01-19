package ysn.com.demo.flowlayout2.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import ysn.com.demo.flowlayout2.R;
import ysn.com.demo.flowlayout2.bean.User;
import ysn.com.demo.flowlayout2.utils.ToastUtils;

/**
 * @Author yangsanning
 * @ClassName BaseActivity
 * @Description 一句话概括作用
 * @Date 2020/1/19
 * @History 2020/1/19 author: description:
 */
public abstract class BaseActivity extends AppCompatActivity {

    public void showMsg(String msg) {
        ToastUtils.showNormalToast(this, msg);
    }

    public void startActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }

    protected ArrayList<User> getData() {
        ArrayList<User> dataList = new ArrayList<>();
        String[] array = getResources().getStringArray(R.array.data);
        for (int i = 0; i < array.length; i++) {
            dataList.add(new User(array[i], i));
        }
        return dataList;
    }
}
