package com.sujan.twiteerclone;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.StrictMode;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.sujan.twiteerclone.activity.LoginActivity;
import com.sujan.twiteerclone.apis.TwiteerApi;
import com.sujan.twiteerclone.model.CreateUser;
import com.sujan.twiteerclone.url.BaseUrl;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class TwiteerDashActivity extends AppCompatActivity {
    ImageView imageView;
    TextView username,email;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twiteer_dash);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        username =hView.findViewById( R.id.username );
        email =hView.findViewById( R.id.email );
        imageView =hView.findViewById( R.id.imageView );

        user();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    private void user() {


        BaseUrl baseUrl = new BaseUrl();
        Call<CreateUser> call = baseUrl.getInstance().create(TwiteerApi.class).getEmployeeByName(LoginActivity.userr);
        StrictMode();

        try {
            Response<CreateUser> createUserResponse = call.execute();
            String url="http://10.0.2.2:3112/profile/";
            username.setText(createUserResponse.body().getUsername());
            String em=createUserResponse.body().getEmail();
            String ph=createUserResponse.body().getPhone();
            email.setText(em);
//            if(!em.isEmpty()){
//            email.setText(em);}
//            if(!ph.isEmpty()) {
//                email.setText(ph);
//            }

            Picasso.get().load(url+createUserResponse.body().getProfileImage()).resize(150, 150).centerCrop().into(imageView);
            //imageView.setImageURI(Uri.parse(url+createUserResponse.body().getProfileImage()));
        } catch (IOException e) {
            Toast.makeText(TwiteerDashActivity.this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.twiteer_dash, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
