package stu.ilexa.testjournal1;

import android.content.ClipData;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import stu.ilexa.testjournal1.databinding.ActivityMainBinding;
import stu.ilexa.testjournal1.ui.help.HelpFragment;
import stu.ilexa.testjournal1.ui.tables.TableFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private static final String TAG = "MyMainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");
        SharedPreferences saveData = getPreferences(MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        String group = saveData.getString("Group", "");
        String schedule = saveData.getString("Schedule", "");
        String bigBreakAfter = saveData.getString("BigBreakAfter", "");
        if (!group.equals("")) {
            Group.importSaveData(gson.fromJson(group, Student[].class));
        }
        if (!schedule.equals("")) {
            Schedule.importSaveData(gson.fromJson(schedule, Subject[][][].class));
        }
        if (!bigBreakAfter.equals("")) {
            DateControl.importSaveData(saveData.getInt("FirstDay", 0),
                    saveData.getInt("FirstMonth", 0),
                    saveData.getInt("FirstYear", 0),
                    saveData.getInt("ClassBeginningHour", 0),
                    saveData.getInt("ClassBeginningMinute", 0),
                    saveData.getInt("BreakDurationMinute", 0),
                    saveData.getInt("BigBreakDurationMinute", 0),
                    saveData.getInt("ClassDuration", 0),
                    gson.fromJson(bigBreakAfter, Integer[].class));
        }


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_tables,
                R.id.nav_date_control)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //Log.d(TAG, getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getFragments().toString());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences saveData = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = saveData.edit();
        Gson gson = new Gson();
        String group = gson.toJson(Group.getGroup());
        Log.d(TAG, "onDestroy: "+group);
        String schedule = gson.toJson(Schedule.getSchedule());
        Log.d(TAG, "onDestroy: "+schedule);
        String bigBreakAfter = gson.toJson(DateControl.getBigBreakAfterClass());
        Log.d(TAG, "onDestroy: "+bigBreakAfter);
        prefsEditor.putString("Group", group);
        prefsEditor.putString("Schedule", schedule);
        prefsEditor.putString("BigBreakAfter", bigBreakAfter);
        prefsEditor.putInt("FirstDay", DateControl.getFirstDay());
        prefsEditor.putInt("FirstMonth", DateControl.getFirstMonth());
        prefsEditor.putInt("FirstYear", DateControl.getFirstYear());
        prefsEditor.putInt("ClassBeginningHour", DateControl.getClassBeginningHour());
        prefsEditor.putInt("ClassBeginningMinute", DateControl.getClassBeginningMinute());
        prefsEditor.putInt("BreakDurationMinute", DateControl.getBreakDurationMinute());
        prefsEditor.putInt("BigBreakDurationMinute", DateControl.getBigBreakDurationMinute());
        prefsEditor.putInt("ClassDuration", DateControl.getClassDuration());

        prefsEditor.commit();

        super.onDestroy();
    }

    public void openHelpPage(MenuItem item) {
        /*Fragment fragment = HelpFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.nav_host_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
    }
}