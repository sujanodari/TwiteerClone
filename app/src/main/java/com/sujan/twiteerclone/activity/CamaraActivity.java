package com.sujan.twiteerclone.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.sujan.twiteerclone.R;
import com.sujan.twiteerclone.apis.TwiteerApi;
import com.sujan.twiteerclone.model.CreateUser;
import com.sujan.twiteerclone.responses.ImageResponse;
import com.sujan.twiteerclone.responses.LoginResponses;
import com.sujan.twiteerclone.url.BaseUrl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CamaraActivity extends AppCompatActivity {
ImageView profile;
Button btn_c_login;
    Uri ImageUri;
String Phone,Email,status,ProfileImage,Bio,Interest, Password=PasswordActivity.Password,Username=RegisterActivity.Ruser,EmailORPhone=RegisterActivity.Remail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        profile=findViewById(R.id.profile);
        btn_c_login=findViewById(R.id.btn_c_login);
        checkPermission();
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(CamaraActivity.this,profile);
                popupMenu.getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().toString().equals("Open Camera")){
                            loadCamera();
                        }
                        if(item.getTitle().toString().equals("Open Gallary")){
                            loadGallary();
                        }


                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        if(TextUtils.isDigitsOnly(EmailORPhone)){
            Phone=EmailORPhone;
        }
        else if (android.util.Patterns.EMAIL_ADDRESS.matcher(EmailORPhone).matches()){
            Email=EmailORPhone;
        }

        btn_c_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveImageOnly();
                RegisterUser();

            }
        });
    }

    private void RegisterUser() {

        CreateUser createUser =new CreateUser(Username,Password,Phone,Email,ProfileImage,Bio,Interest);

        BaseUrl baseUrl = new BaseUrl();
        Call<LoginResponses> call = baseUrl.getInstance().create(TwiteerApi.class).registerUser(createUser);
        StrictMode();

        try {
            Response<LoginResponses> loginResponsesResponse = call.execute();
            status=loginResponsesResponse.body().getStatus();
           if(status.equals("201")){
               Intent intent = new Intent(CamaraActivity.this, LoginActivity.class);
               startActivity(intent);
           }
        } catch (IOException e) {
            Toast.makeText(CamaraActivity.this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }





    }

    private void loadGallary() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }



    private  void checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.CAMERA
                    },0);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },0);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0 && resultCode==RESULT_OK){
            Bundle extras= data.getExtras();
            Bitmap imageBitmap=(Bitmap)extras.get("data");
            profile.setImageBitmap(imageBitmap);
            ImageUri= getImageUri();

        }
        if(requestCode==1 && resultCode==RESULT_OK) {

            if (data == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_LONG).show();
            }
            Uri uri = data.getData();
            profile.setImageURI(uri);
            ImageUri=data.getData();

        }
    }

    private Uri getImageUri() {
        Uri m_imgUri = null;
        File m_file;
        try {
            SimpleDateFormat m_sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String m_curentDateandTime = m_sdf.format(new Date());
            String m_imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + m_curentDateandTime + ".jpg";
            m_file = new File(m_imagePath);
            m_imgUri = Uri.fromFile(m_file);
        } catch (Exception p_e) {
        }
        return m_imgUri;
    }

    private  void loadCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,0);
        }

    }
    private  void saveImageOnly(){
        String image = getRealPathFromUri(ImageUri);
        File file = new File(image);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("profileImage", file.getName(), requestBody);
        BaseUrl baseUrl = new BaseUrl();

        Call<ImageResponse> call = baseUrl.getInstance().create(TwiteerApi.class).uploadImage(body);
        StrictMode();

        try {
            Response<ImageResponse> imageModelResponse = call.execute();
            ProfileImage=imageModelResponse.body().getFilename();
          //  Toast.makeText(this, ""+imageModelResponse.body().getFilename(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {

            e.printStackTrace();
            Toast.makeText(this, ""+ e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    private void StrictMode(){
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private String getRealPathFromUri(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_ind = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_ind);
        cursor.close();
        return result;
    }


}
