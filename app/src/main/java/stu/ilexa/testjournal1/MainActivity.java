package stu.ilexa.testjournal1;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import stu.ilexa.testjournal1.databinding.ActivityMainBinding;
import stu.ilexa.testjournal1.databinding.FragmentSlideshowBinding;
import stu.ilexa.testjournal1.ui.slideshow.SlideshowFragment;
import stu.ilexa.testjournal1.ui.slideshow.SlideshowViewModel;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);
        /*binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Твой быдлокод нас огорчает", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //Log.d(TAG, getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getFragments().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d(TAG, getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getFragments().toString());
    }

    public void setButtons(){
        Log.d(TAG, getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getFragments().get(0).getActivity().toString());
        SlideshowFragment slideshowFragment = (SlideshowFragment) getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getFragments().get(0);
        FragmentSlideshowBinding fragmentSlideshowBinding = slideshowFragment.getBinding();
        fragmentSlideshowBinding.ButtonWeekDay1.setOnClickListener(slideshowFragment);
        fragmentSlideshowBinding.ButtonWeekDay2.setOnClickListener(slideshowFragment);
        fragmentSlideshowBinding.ButtonWeekDay3.setOnClickListener(slideshowFragment);
        fragmentSlideshowBinding.ButtonWeekDay4.setOnClickListener(slideshowFragment);
        fragmentSlideshowBinding.ButtonWeekDay5.setOnClickListener(slideshowFragment);
        fragmentSlideshowBinding.ButtonWeekDay6.setOnClickListener(slideshowFragment);
        fragmentSlideshowBinding.imageButtonWeekNext.setOnClickListener(slideshowFragment);
        fragmentSlideshowBinding.imageButtonWeekPrevious.setOnClickListener(slideshowFragment);

        /*this.findViewById(R.id.imageButtonWeekNext).setOnClickListener(slideshowFragment);
        this.findViewById(R.id.imageButtonWeekPrevious).setOnClickListener(slideshowFragment);
        this.findViewById(R.id.ButtonWeekDay1).setOnClickListener(slideshowFragment);
        this.findViewById(R.id.ButtonWeekDay2).setOnClickListener(slideshowFragment);
        this.findViewById(R.id.ButtonWeekDay3).setOnClickListener(slideshowFragment);
        this.findViewById(R.id.ButtonWeekDay4).setOnClickListener(slideshowFragment);
        this.findViewById(R.id.ButtonWeekDay5).setOnClickListener(slideshowFragment);
        this.findViewById(R.id.ButtonWeekDay6).setOnClickListener(slideshowFragment);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}