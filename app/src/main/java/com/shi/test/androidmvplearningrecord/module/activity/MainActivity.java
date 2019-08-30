package com.shi.test.androidmvplearningrecord.module.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.shi.test.androidmvplearningrecord.R;
import com.shi.test.androidmvplearningrecord.module.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;


/**
 * author 施镇杰
 * email 452239676@qq.com
 * created 2019/8/20 下午3:36
 */
public class MainActivity extends BaseActivity{
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        rxPermissionTest();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,TestActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void initInjector() {
        //DaggerPhotoSetComponent.builder()
        //                       .photoSetModule(new PhotoSetModule(this, mPhotoSetId))
        //                       .build()
        //                       .inject(this);

    }

    @Override
    protected void initData() {
    }

    private void rxPermissionTest() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(granted -> {
            if (granted) {
                // 打开相机
            } else {
                showErrorIconToast(MainActivity.this, "App申请使用的权限被拒绝会影响部分功能的使用,下次打开记得同意当前权限!");
            }
        });
    }

    @Override
    public void showError(String s) {

    }

    //@Override
    //public void loadData(HttpResult<List<WelfarePhotoInfo>> listHttpResult) {
    //    String tv1 = "";
    //    for (int i = 0; i < listHttpResult.getResults().size(); i++) {
    //        tv1 = tv1 + listHttpResult.getResults().get(i).getUrl() + "\n";
    //    }
    //    tv.setText(tv1);
    //}
}
