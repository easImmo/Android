package com.projet.easimmo.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.common.adapter.CustomAdapter;
import com.projet.easimmo.dto.ImageDTO;
import com.projet.easimmo.dto.display.EdlDetailDisplayDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceAssessments;
import com.viewpagerindicator.CirclePageIndicator;


import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EDLDetailActivity extends AppCompatActivity {

    @Bind(R.id.equipment)
    TextView _equipment;

    @Bind(R.id.equipment_state)
    TextView _equipment_state;

    @Bind(R.id.viewPager_image)
    ViewPager viewPager;

    @Bind(R.id.indicator)
    CirclePageIndicator mIndicator;

    @Bind(R.id.addImageFab)
    FloatingActionButton addImageFab;

    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private EdlDetailDisplayDTO detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edldetail);
        detail = (EdlDetailDisplayDTO) getIntent().getSerializableExtra("detail");
        setTitle(detail.getRoomDTO().getRoomTypeDTO());
        ButterKnife.bind(this);
        _equipment.setText(" "+detail.getEquipmentDTO().getEquipmentTypeDTO());
        _equipment_state.setText(" "+detail.getAssessmentDTO().getEquipmentStateDTO());



        PagerAdapter adapter = new CustomAdapter(EDLDetailActivity.this, detail.getAssessmentDTO().getImageDTOList());

        viewPager.setAdapter(adapter);

        mIndicator.setViewPager(viewPager);

        if (addImageFab != null) {
            addImageFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectImage();
                }
            });
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Prendre une Photo", "Choisir dans la gallerie",
                "Annuler" };

        AlertDialog.Builder builder = new AlertDialog.Builder(EDLDetailActivity.this);
        builder.setTitle("Ajouter Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Prendre une Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choisir dans la gallerie")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Choisir un fichier"),
                            SELECT_FILE);
                } else if (items[item].equals("Annuler")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = thumbnail.getWidth();
        int height = thumbnail.getHeight();
        thumbnail = Bitmap.createScaledBitmap(thumbnail, width, height, true);

        //ivImage.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        System.out.println("DEBUT***************************************");
        Uri selectedImageUri = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        String selectedImagePath;
        Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null,
                null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(filePathColumn[0]);
        selectedImagePath = cursor.getString(idx);
        cursor.close();

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 800;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);

        // Read EXIF Data
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(selectedImagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, options.outWidth, options.outHeight, matrix, true);

        ServiceAssessments serviceAssessments = new ServiceAssessments();

        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(selectedImagePath);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), detail.getAssessmentDTO().getmId());

        System.out.println("call WS ***********************");
        serviceAssessments.postImage(body, description, new ICallback<ImageDTO>() {
            @Override
            public void success(ImageDTO imageDTO) {
                System.out.println("**************************************************");
            }

            @Override
            public void failure(Throwable error) {
                System.out.println(error);
            }

            @Override
            public void unauthorized() {

            }
        });
        // Send image to API
       // UploadService uploadService = new UploadService(getApplicationContext());
        //uploadService.uploadFile(selectedImageUri);

        // Set result in container
        //ivImage.setImageBitmap(rotatedBitmap);

    }


}
