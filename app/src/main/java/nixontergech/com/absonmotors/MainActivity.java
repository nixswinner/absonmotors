package nixontergech.com.absonmotors;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import nixontergech.com.absonmotors.utils.CompanyInfo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainFragment.OnFragmentInteractionListener,
        CompanyInfo.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState ==null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            MainFragment mainFragment =  MainFragment.newInstance().newInstance();
            ft.addToBackStack("main");
            ft.add(R.id.container,mainFragment);
            ft.commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            MainFragment mainFragment =  MainFragment.newInstance().newInstance();
            ft.addToBackStack("main");
            ft.replace(R.id.container,mainFragment);
            ft.commit();
        } else if (id == R.id.nav_logbook) {

        } else if (id == R.id.nav_ownership) {

        } else if (id == R.id.nav_companyinfo) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            CompanyInfo mainFragment =  CompanyInfo.newInstance();
            ft.addToBackStack("companyinfo");
            ft.replace(R.id.container,mainFragment);
            ft.commit();

        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Abson Motors is the best place for you need " +
                    "Motor Bikes and 3 wheeled vehicles.For more informations kindly visit: http://absonmotors.com");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AbsonMotors");
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

        } else if (id == R.id.nav_send) {
            new LovelyTextInputDialog(MainActivity.this)
                    .setTopColorRes(R.color.colorPrimaryDark)
                    .setTitle("Your FeedBack")
                    .setMessage("AbsonMotors Ltd Values your feedback\nKindly give us your feedback and " +
                            "include your contact if you do not mind" +
                            "Thank you ")
                    .setInputFilter("Error.No Feedback provide Kindly give us your feedback",
                            new LovelyTextInputDialog.TextFilter() {
                                @Override
                                public boolean check(String text) {
                                    return text.matches("\\w+");
                                }
                            })
                    .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                        @Override
                        public void onTextInputConfirmed(String text) {
                            Toast.makeText(MainActivity.this,"Thank your feedback ",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
