package com.example.tablayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TextView mTextAppName;
    private ImageView mSearch;

    private SectionsPagerAdapterTabs mSectionAdapter;
    //Bottom Navigation
    private BottomNavigationView mBottom;
    MenuItem prevMenuItem;
    //End Bottom Navigation
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar3);
        mTabLayout = findViewById(R.id.tabs3);
        mViewPager = findViewById(R.id.container3);

        mTextAppName = mToolbar.findViewById(R.id.app_name);
        mSearch = mToolbar.findViewById(R.id.toolbar_search);

        handleNavigationIcon();
        handleSearch();

        mTextAppName.setText(R.string.app_name);

        mSectionAdapter = new SectionsPagerAdapterTabs(getSupportFragmentManager());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.setAdapter(mSectionAdapter);
        mViewPager.setOffscreenPageLimit(3);
        //Bottom Navigation
        mBottom = findViewById(R.id.dark_bottom);
        mBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.basic_home:
                        mViewPager.setCurrentItem(0);
                        break;

                    case R.id.basic_camera:
                        mViewPager.setCurrentItem(1);
                        break;

                    case R.id.basic_profile:
                        mViewPager.setCurrentItem(2);
                        break;

                    case R.id.basic_settings:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    mBottom.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                mBottom.getMenu().getItem(position).setChecked(true);
                prevMenuItem = mBottom.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //End Bottom Navigation

        floatingActionButton = findViewById(R.id.fab1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Camera is clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    private void handleNavigationIcon() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Handle", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleSearch() {
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Handle Search", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class SectionsPagerAdapterTabs extends FragmentPagerAdapter {
        public SectionsPagerAdapterTabs(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0) {
                return new FragmentTwoEmptyState1();
            }
            else if(position == 1)
            {
                return new FragmentTwoEmptyState2();
            }
            else if(position == 2)
            {
                return new FragmentTwoEmptyState3();
            }
            else
            {
                return new FragmentTwoEmptyState4();
            }
        }

        @Override
        public int getCount() {
            //0 - Following
            //1 - Popular
            //2 - Recent
            return 4;
        }
    }

}
