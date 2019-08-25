package com.lina.kolina.javisapp;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lina.kolina.javisapp.fragment.HomeFragment;
import com.lina.kolina.javisapp.fragment.PelayananFragment;
import com.lina.kolina.javisapp.fragment.ProfilFragment;
import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavView bottomNavView;
    VectorMasterView fab, fab1, fab2;
    RelativeLayout lin_id;
    PathModel outline;
    Button btnorderr;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        find

        bottomNavView = (BottomNavView) findViewById(R.id.bottom_nav);
        fab = (VectorMasterView) findViewById(R.id.fab);
        fab1 = (VectorMasterView) findViewById(R.id.fab1);
        fab2 = (VectorMasterView) findViewById(R.id.fab2);

        lin_id = (RelativeLayout) findViewById(R.id.lin_id);

        bottomNavView.inflateMenu(R.menu.main_menu);
        bottomNavView.setSelectedItemId(R.id.action_maps);

        bottomNavView.setOnNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        //Untuk inisialisasi fragment pertama kali
        fragmentManager.beginTransaction().replace(R.id.fl_container, new PelayananFragment()).commit();


    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.action_home:

//                animasi
                draw(6);

                lin_id.setX(bottomNavView.mFirstCurveControlPoint1.x);
                fab.setVisibility(View.VISIBLE);
                fab1.setVisibility(View.GONE);
                fab2.setVisibility(View.GONE);
                drawAnimation(fab);
                fragment = new HomeFragment();


                break;

            case R.id.action_maps:

//                animasi
                draw(2);
                lin_id.setX(bottomNavView.mFirstCurveControlPoint1.x);
                fab.setVisibility(View.GONE);
                fab1.setVisibility(View.VISIBLE);
                fab2.setVisibility(View.GONE);
                drawAnimation(fab1);
                fragment = new PelayananFragment();
                break;

            case R.id.action_profile:

//                animasi
                draw();
                lin_id.setX(bottomNavView.mFirstCurveControlPoint1.x);
                fab.setVisibility(View.GONE);
                fab1.setVisibility(View.GONE);
                fab2.setVisibility(View.VISIBLE);
                drawAnimation(fab2);
                fragment = new ProfilFragment();
                break;


        }
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_container, fragment).commit();
        return true;
    }

    private void draw() {


        bottomNavView.mFirstCurveStartPoint.set((bottomNavView.mNavigationBarWidth * 10/12) - (bottomNavView.CURVE_CIRCLE_RADIUS * 2) - (bottomNavView.CURVE_CIRCLE_RADIUS / 3), 0);
        // koordinta (x,y)
        bottomNavView.mFirstCurveEndPoint.set(bottomNavView.mNavigationBarWidth  * 10/12, bottomNavView.CURVE_CIRCLE_RADIUS + (bottomNavView.CURVE_CIRCLE_RADIUS / 4));

        bottomNavView.mSecondCurveStartPoint = bottomNavView.mFirstCurveEndPoint;
        bottomNavView.mSecondCurveEndPoint.set((bottomNavView.mNavigationBarWidth  * 10/12) + (bottomNavView.CURVE_CIRCLE_RADIUS * 2) + (bottomNavView.CURVE_CIRCLE_RADIUS / 3), 0);


        bottomNavView.mFirstCurveControlPoint1.set(bottomNavView.mFirstCurveStartPoint.x + bottomNavView.CURVE_CIRCLE_RADIUS + (bottomNavView.CURVE_CIRCLE_RADIUS / 4), bottomNavView.mFirstCurveStartPoint.y);

        bottomNavView.mFirstCurveControlPoint2.set(bottomNavView.mFirstCurveEndPoint.x - (bottomNavView.CURVE_CIRCLE_RADIUS * 2) + bottomNavView.CURVE_CIRCLE_RADIUS, bottomNavView.mFirstCurveEndPoint.y);

        bottomNavView.mSecondCurveControlPoint1.set(bottomNavView.mSecondCurveStartPoint.x + (bottomNavView.CURVE_CIRCLE_RADIUS * 2) - bottomNavView.CURVE_CIRCLE_RADIUS, bottomNavView.mSecondCurveStartPoint.y);
        bottomNavView.mSecondCurveControlPoint2.set(bottomNavView.mSecondCurveEndPoint.x - (bottomNavView.CURVE_CIRCLE_RADIUS + (bottomNavView.CURVE_CIRCLE_RADIUS / 4)), bottomNavView.mSecondCurveEndPoint.y);
    }
    private void drawAnimation(final VectorMasterView fab) {
        outline = fab.getPathModelByName("outline");
        outline.setStrokeColor(Color.WHITE);
        outline.setTrimPathEnd(0.0f);

//        value animasi
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                outline.setTrimPathEnd((Float) valueAnimator.getAnimatedValue());
                fab.update();
            }
        });
        valueAnimator.start();
    }

    private void draw(int i) {


        bottomNavView.mFirstCurveStartPoint.set((bottomNavView.mNavigationBarWidth / i) - (bottomNavView.CURVE_CIRCLE_RADIUS * 2) - (bottomNavView.CURVE_CIRCLE_RADIUS / 3), 0);

        bottomNavView.mFirstCurveEndPoint.set(bottomNavView.mNavigationBarWidth / i, bottomNavView.CURVE_CIRCLE_RADIUS + (bottomNavView.CURVE_CIRCLE_RADIUS / 4));

        bottomNavView.mSecondCurveStartPoint = bottomNavView.mFirstCurveEndPoint;
        bottomNavView.mSecondCurveEndPoint.set((bottomNavView.mNavigationBarWidth / i) + (bottomNavView.CURVE_CIRCLE_RADIUS * 2) + (bottomNavView.CURVE_CIRCLE_RADIUS / 3), 0);


        bottomNavView.mFirstCurveControlPoint1.set(bottomNavView.mFirstCurveStartPoint.x + bottomNavView.CURVE_CIRCLE_RADIUS + (bottomNavView.CURVE_CIRCLE_RADIUS / 4), bottomNavView.mFirstCurveStartPoint.y);

        bottomNavView.mFirstCurveControlPoint2.set(bottomNavView.mFirstCurveEndPoint.x - (bottomNavView.CURVE_CIRCLE_RADIUS * 2) + bottomNavView.CURVE_CIRCLE_RADIUS, bottomNavView.mFirstCurveEndPoint.y);

        bottomNavView.mSecondCurveControlPoint1.set(bottomNavView.mSecondCurveStartPoint.x + (bottomNavView.CURVE_CIRCLE_RADIUS * 2) - bottomNavView.CURVE_CIRCLE_RADIUS, bottomNavView.mSecondCurveStartPoint.y);
        bottomNavView.mSecondCurveControlPoint2.set(bottomNavView.mSecondCurveEndPoint.x - (bottomNavView.CURVE_CIRCLE_RADIUS + (bottomNavView.CURVE_CIRCLE_RADIUS / 4)), bottomNavView.mSecondCurveEndPoint.y);

    }
}
