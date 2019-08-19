package com.mario.molitvenik.core.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mario.molitvenik.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationCallback {

  @BindView(R.id.bottom_navigation_bar) BottomNavigationView bottomNavigationView;
  @BindView(R.id.nav_view) NavigationView navigationView;
  @BindView(R.id.main_drawer) DrawerLayout mainDrawer;
  private NavHostFragment navHostFragment;
  private NavController navController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    Logger.addLogAdapter(new AndroidLogAdapter());
    loadViews();
    plugFragmentsWithBottomView();
    // TODO remove
    navHostFragment.getNavController().navigate(R.id.alarmFragment);
  }

  private void loadViews() {
    bottomNavigationView.setItemIconTintList(null);
    navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    navigationView.setNavigationItemSelectedListener(this);
  }

  private void plugFragmentsWithBottomView() {
    NavigationUI.setupWithNavController(bottomNavigationView, navController);
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  @Override
  public void onBackPressed() {
    if (mainDrawer.isDrawerOpen(GravityCompat.START)) {
      mainDrawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    menuItem.setChecked(true);
    mainDrawer.closeDrawers();
    if (menuItem.getItemId() == R.id.settingsFragment) {
      navController.navigate(R.id.settingsFragment);
    } else if (menuItem.getItemId() == R.id.alarmFragment) {
      navController.navigate(R.id.alarmFragment);
    }
    return true;
  }

  @Override
  public void setVisibility(int isVisible) {
    if(bottomNavigationView != null) {
      bottomNavigationView.setVisibility(isVisible);
    }
  }
}