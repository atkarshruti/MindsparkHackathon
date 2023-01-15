package com.example.tensorflow;

import java.util.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



import com.example.tensorflow.ml.Plant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.image.ops.Rot90Op;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button camera, gallery,precautions;
    ImageView imageView;
    TextView result;
    int imageSize = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.button);
        gallery = findViewById(R.id.button2);
        precautions=findViewById(R.id.button3);

        result = findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 3);
                }else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cameraIntent, 1);
            }
        });

        precautions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent precaution_intent=new Intent(MainActivity.this,MainActivity2.class);
                String res=result.getText().toString();
                String res1=res.substring(8);
                String[] res2=res1.split("\n");
                precaution_intent.putExtra("message_key",res2[0]);
                startActivity(precaution_intent);
                finish();

            }
        });
    }



    public void classifyImage(Bitmap image){

        try {
            Plant model = Plant.newInstance(getApplicationContext());

            Plant.Outputs outputs = model.process(TensorImage.fromBitmap(image));

            TensorBuffer outputFeature= (TensorBuffer) outputs.getProbabilityAsTensorBuffer();

            float[] confidences1 = outputFeature.getFloatArray();

            Log.d("Array", Arrays.toString(confidences1));
            // find the index of the class with the biggest confidence.
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences1.length; i++) {
                if (confidences1[i] > maxConfidence) {
                    maxConfidence = confidences1[i];
                    maxPos = i;
                }
            }


            String[] classes = {
                    "Tomato Healthy", "Tomato Septoria Leaf Spot", "Tomato Bacterial Spot", "Tomato Blight", "Cabbage Healthy", "Tomato Spider Mite", "Tomato Leaf Mold", "Tomato_Yellow Leaf Curl Virus", "Soy_Frogeye_Leaf_Spot", "Soy_Downy_Mildew", "Maize_Ravi_Corn_Rust", "Maize_Healthy", "Maize_Grey_Leaf_Spot", "Maize_Lethal_Necrosis", "Soy_Healthy", "Cabbage Black Rot"
            };

            //displays the final result
            result.setText("Disease: "+classes[maxPos]+"\n \nProbability: "+maxConfidence);

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 3){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, true);
                classifyImage(image);
            }else{
                Uri dat = data.getData();
                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, true);
                classifyImage(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}