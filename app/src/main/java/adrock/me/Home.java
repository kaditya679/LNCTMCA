package adrock.me;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.util.HashMap;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnTouchListener,BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    CircleMenu circleMenu;

    private SliderLayout mDemoSlider;

    TextSliderView textSliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RelativeLayout relativeLayout;
        relativeLayout= (RelativeLayout) findViewById(R.id.content_lay);
        relativeLayout.setOnTouchListener(this);

//Drawer Initialization
        initialize();

//CircleMenu
        cMenu();

//ImageSlider
        imageSlider();

    }




    void initialize() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }
    public void imageSlider()
    {
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("LNCT MCA",R.drawable.lnct44);
        file_maps.put("LNCT VIEW",R.drawable.lnct6);
        file_maps.put("LNCT MAIN",R.drawable.lnct11);
        file_maps.put("LNCT TOP VIEW", R.drawable.lnct33);

        for(String name : file_maps.keySet()){
            textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.RotateDown);

        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
        //mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

    }
    public void cMenu() {
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
//#CDCDCD
        //#30A400
        circleMenu.setMainMenu(Color.parseColor("#258CFF"), R.mipmap.icon_home, R.mipmap.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.mipmap.icon_menu)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.icon_search)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.icon_notify)
                .addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.icon_setting)
                .addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.icon_gps);



        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index) {
                                                         case 0:
                                                             Toast.makeText(Home.this, "Home Button Clicked", Toast.LENGTH_SHORT).show();
                                                             break;
                                                         case 1:
                                                             Toast.makeText(Home.this, "Search button Clicked", Toast.LENGTH_SHORT).show();
                                                             break;
                                                         case 2:
                                                             Toast.makeText(Home.this, "Notify button Clciked", Toast.LENGTH_SHORT).show();
                                                             break;
                                                         case 3:
                                                             Toast.makeText(Home.this, "Settings button Clcked", Toast.LENGTH_SHORT).show();
                                                             break;
                                                         case 4:
                                                             Toast.makeText(Home.this, "GPS button Clicked", Toast.LENGTH_SHORT).show();
                                                             break;
                                                     }
                                                 }
                                             }

        );


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(circleMenu.isOpened()) {
                circleMenu.closeMenu();
                Toast.makeText(this, "Menu closed", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Activity closed", Toast.LENGTH_SHORT).show();
                //finish();
                super.onBackPressed();
            }

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Toast.makeText(Home.this, item.getTitle() + "", Toast.LENGTH_SHORT).show();
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

            //startActivity(new Intent(this,Home.class));


        } else if (id == R.id.nav_faculty) {

        } else if (id == R.id.nav_courses) {


        } else if (id == R.id.nav_placement) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    @Override
    public void onSliderClick(BaseSliderView slider) {


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
       //Toast.makeText(this, "Activity Clicked!", Toast.LENGTH_SHORT).show();

        if(circleMenu.isOpened()) {
            circleMenu.closeMenu();
            Toast.makeText(this, "Menu closed", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}

