package com.udemy.picasso;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> images;
    private String[] animals;
    private int[] parties;

    private AnimalAdapter animalAdapter;
    private PartyAdapter partyAdapter;
    private ImagesAdapter imagesAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private final int PERMISSION_READ_EXTERNAL_MEMORY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animals = getAnimalsLinks();
        parties = getPartyPics();
        images = getImagesPath();

        animalAdapter = new AnimalAdapter(this,animals,R.layout.image_layout);
        partyAdapter = new PartyAdapter(this,parties,R.layout.image_layout);
        imagesAdapter = new ImagesAdapter(this,images,R.layout.image_layout);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(animalAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.links_adapter:
                recyclerView.setAdapter(animalAdapter);
                return true;
            case R.id.resources_adapter:
                recyclerView.setAdapter(partyAdapter);
                return true;
            case R.id.memory_adapter:
                images.clear();
                images.addAll(getImagesPath());
                recyclerView.setAdapter(imagesAdapter);
                imagesAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_EXTERNAL_MEMORY:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    images.clear();
                    images.addAll(getImagesPath());
                    imagesAdapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    public String[] getAnimalsLinks() {
        String[] links = {
                "https://static.pexels.com/photos/86462/red-kite-bird-of-prey-milan-raptor-86462.jpeg",
                "https://static.pexels.com/photos/67508/pexels-photo-67508.jpeg",
                "https://static.pexels.com/photos/55814/leo-animal-savannah-lioness-55814.jpeg",
                "https://static.pexels.com/photos/40745/red-squirrel-rodent-nature-wildlife-40745.jpeg",
                "https://static.pexels.com/photos/33392/portrait-bird-nature-wild.jpg",
                "https://static.pexels.com/photos/62640/pexels-photo-62640.jpeg",
                "https://static.pexels.com/photos/38438/rattlesnake-toxic-snake-dangerous-38438.jpeg",
                "https://static.pexels.com/photos/33149/lemur-ring-tailed-lemur-primate-mammal.jpg",
                "https://static.pexels.com/photos/1137/wood-animal-dog-pet.jpg",
                "https://static.pexels.com/photos/40731/ladybug-drop-of-water-rain-leaf-40731.jpeg",
                "https://static.pexels.com/photos/40860/spider-macro-insect-arachnid-40860.jpeg",
                "https://static.pexels.com/photos/63282/crab-yellow-ocypode-quadrata-atlantic-ghost-crab-63282.jpeg",
                "https://static.pexels.com/photos/45246/green-tree-python-python-tree-python-green-45246.jpeg",
                "https://static.pexels.com/photos/39245/zebra-stripes-black-and-white-zoo-39245.jpeg",
                "https://static.pexels.com/photos/92000/pexels-photo-92000.jpeg",
                "https://static.pexels.com/photos/121445/pexels-photo-121445.jpeg",
                "https://static.pexels.com/photos/112603/pexels-photo-112603.jpeg",
                "https://static.pexels.com/photos/52961/antelope-nature-flowers-meadow-52961.jpeg",
                "https://static.pexels.com/photos/36450/flamingo-bird-pink-nature.jpg",
                "https://static.pexels.com/photos/20861/pexels-photo.jpg",
                "https://static.pexels.com/photos/54108/peacock-bird-spring-animal-54108.jpeg",
                "https://static.pexels.com/photos/24208/pexels-photo-24208.jpg"
        };
        return links;
    }

    public int[] getPartyPics() {
        int[] values = {
                R.drawable.r1,
                R.drawable.r2,
                R.drawable.r3,
                R.drawable.r1,
                R.drawable.r2,
                R.drawable.r3,
                R.drawable.r1,
                R.drawable.r2,
                R.drawable.r3
        };
        return values;
    }

    public List<String> getImagesPath() {
        List<String> listOfAllImages = new ArrayList<String>();

        if (hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};

            Cursor cursor = getContentResolver()
                    .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, MediaStore.Images.Media._ID);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        listOfAllImages.add(path);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        }
        return listOfAllImages;
    }

    private boolean hasPermission(String readExternalStorage) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, P);
            return false;
        }
        return true;
    }
}
