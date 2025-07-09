package com.gaplestrategist;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.gaplestrategist.fragments.GameFragment;
import com.gaplestrategist.fragments.RulesFragment;
import com.gaplestrategist.fragments.ScoreFragment;
import com.gaplestrategist.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity 
        implements NavigationView.OnNavigationItemSelectedListener {
    
    private DrawerLayout drawerLayout;
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigation;
    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeViews();
        setupToolbar();
        setupViewPager();
        setupBottomNavigation();
        setupNavigationDrawer();
    }
    
    private void initializeViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.viewPager);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    
    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    
    private void setupViewPager() {
        pagerAdapter = new ViewPagerAdapter(this);
        pagerAdapter.addFragment(new GameFragment(), "Game");
        pagerAdapter.addFragment(new ScoreFragment(), "Score");
        pagerAdapter.addFragment(new RulesFragment(), "Rules");
        
        viewPager.setAdapter(pagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigation.getMenu().getItem(position).setChecked(true);
            }
        });
    }
    
    private void setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_game) {
                viewPager.setCurrentItem(0);
                return true;
            } else if (itemId == R.id.nav_score) {
                viewPager.setCurrentItem(1);
                return true;
            } else if (itemId == R.id.nav_rules) {
                viewPager.setCurrentItem(2);
                return true;
            }
            return false;
        });
    }
    
    private void setupNavigationDrawer() {
        // Additional navigation setup if needed
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        
        if (itemId == R.id.nav_home) {
            viewPager.setCurrentItem(0);
        } else if (itemId == R.id.nav_calculator) {
            // Open calculator activity/fragment
        } else if (itemId == R.id.nav_statistics) {
            // Open statistics activity/fragment
        } else if (itemId == R.id.nav_settings) {
            // Open settings activity/fragment
        } else if (itemId == R.id.nav_tutorial) {
            // Open tutorial activity/fragment
        } else if (itemId == R.id.nav_about) {
            // Open about activity/fragment
        }
        
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}