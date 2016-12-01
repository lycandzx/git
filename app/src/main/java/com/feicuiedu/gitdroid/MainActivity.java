package com.feicuiedu.gitdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.commons.LogUtils;
import com.feicuiedu.gitdroid.github.HotRepoFragement;
import com.feicuiedu.gitdroid.login.LoginActivity;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityUtils mActivityUtils;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawer;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    Button mButLogin;
    ImageView mImgLogin;
    HotRepoFragement mHotRepoFragement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前视图(更改了当前视图内容,将导致onContentChanged方法触发)
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);
        mHotRepoFragement=new HotRepoFragement();
        replaceFragement(mHotRepoFragement);
//ToolBar
//DrawerLayout
//NavigationView
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawer.addDrawerListener(toggle);
        mNavigationView.setNavigationItemSelectedListener(this);
        mButLogin = ButterKnife.findById(mNavigationView.getHeaderView(0), R.id.btnLogin);
        mImgLogin = ButterKnife.findById(mNavigationView.getHeaderView(0), R.id.ivIcon);
        mButLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnLogin:
                        mActivityUtils.startActivity(LoginActivity.class);
                          finish();
                          break;
                    case R.id.ivIcon:

                        break;


                }


            }
        });
    }

    public void replaceFragement(Fragment fragment){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
    //主要做了我们基本登录信息的改变
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.isChecked()) {
            item.setChecked(false);
        }
        switch (item.getItemId()) {
            case R.id.github_hot_coder:
                break;
            case R.id.github_hot_repo:
                if(mHotRepoFragement.isAdded()){
                    replaceFragement(mHotRepoFragement);

                }

                break;
            case R.id.github_trend:

                break;
            case R.id.gank_item:
                break;
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        }
    }
}
