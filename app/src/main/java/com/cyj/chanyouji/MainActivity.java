package com.cyj.chanyouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.cyj.chanyouji.fragment.GongLueFrag;
import com.cyj.chanyouji.fragment.MainFragment;
import com.cyj.chanyouji.fragment.SettingFragment;
import com.cyj.chanyouji.fragment.ToolFragment;
import com.cyj.chanyouji.fragment.YouJiFrag;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private FragmentManager manager;
    private  static  boolean isQuit = false  ;
    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_search:
                        Intent intent = new Intent(MainActivity.this,SearchFActivity.class);
                        startActivity(intent);
                }
                return true;
            }
        });
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment,new MainFragment()).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction transaction = manager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.travel) {
            transaction.replace(R.id.fragment,new YouJiFrag());
            transaction.commit();

        } else if (id == R.id.gonglv) {
            transaction.replace(R.id.fragment,new GongLueFrag());
            transaction.commit();

        } else if (id == R.id.tool) {
            transaction.replace(R.id.fragment,new ToolFragment());
            transaction.commit();

        } else if (id == R.id.setting) {
            transaction.replace(R.id.fragment,new SettingFragment());
            transaction.commit();

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            if(isQuit==false)
            {
                isQuit = true;

                Toast.makeText(MainActivity.this, "再按一次返回键退出应用", Toast.LENGTH_SHORT).show();

                //定时器
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {

                        isQuit=false;
                    }
                }, 1000);
            }else
            {
                this.finish();

                System.exit(0);//退出当前应用程序
            }
        }
        return false;
    }

}
