package com.example.matchinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {






    ImageView viewOne = null;
    private int numOfPairs = 0; //Number of Pairs starts at zero
     int[] images = new int [] {R.drawable.beans, R.drawable.bed, R.drawable.colourpop,
            R.drawable.makingcoffee, R.drawable.orion, R.drawable.phonesanitizer, R.drawable.spinningtop,
            R.drawable.straps, R.drawable.toner, R.drawable.whisk, }; //Array of images

   private static int[] positions = {0,1,2,3,4,5,6,7,8,9,3,1,5,9,0,4,2,8,6,7}; //Array for positions on grid

    //  List<Integer> posList = Arrays.asList(positions);
    //Collections.shuffle(posList);
    //Collections method of shuffling arrays was not working, could not resolve "shuffle" keyword

    int currentPos = -1;


    public static void shuffle(int[] array ) {  //Method that shuffles the images around

        int elements = array.length;

        for(int i = 0; i < elements; i++){

            int mix = i + (int)(Math.random() * (elements - i));

            int second = array[mix];
            array[mix] = array[i];
            array[i] = second;

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridView); //Creating the grid
        ImageAdapter imAdapt = new ImageAdapter(this); //Creating an instance of the ImageAdapter class
        gridview.setAdapter(imAdapt); //Connects the adapter to the gridView
        MainActivity.shuffle(positions); //everytime the app runs, the positions of the pictures are random







        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){ //Listens for clicks on the grid



            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if(currentPos <0){

                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText("Current number of pairs " + String.valueOf(numOfPairs));

                    currentPos = position;
                    viewOne = (ImageView)view;


                    ((ImageView)view).setImageResource(images[positions[position]]); //Reveals image of first position clicked


                }else {

                    if(currentPos == position) //If the same position is hit twice, it reverts back to a question mark
                    {
                       ((ImageView)view).setImageResource(R.drawable.qmark);




                    }else if(positions[currentPos] !=positions[position]){  //If a match is not made

                       ((ImageView)view).setImageResource(images[positions[position]]); //reveals second image/position
                        Toast.makeText(getApplicationContext(),"They don't match, current pairs "+numOfPairs,Toast.LENGTH_SHORT).show();

                        new CountDownTimer(1500, 1000) { //Timer allows the user to see what the second picture is before turning back into a qmark
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {

                                 ((ImageView)view).setImageResource(R.drawable.qmark); //Sets second position back to question mark
                                viewOne.setImageResource(R.drawable.qmark); //sets original picture back to Question mark
                                //Toast.makeText(getApplicationContext(),"They don't match, current pairs "+numOfPairs,Toast.LENGTH_SHORT).show();

                            }


                        }.start();

                    }else{ //if a match IS made


                        ((ImageView)view).setImageResource(images[positions[position]]);
                        numOfPairs++;
                        Toast.makeText(getApplicationContext(),"It's a match, current pairs "+numOfPairs,Toast.LENGTH_SHORT).show();

                        if(numOfPairs==10){ //if player wins AKA 10 pairs matched

                            TextView textViewTwo = (TextView) findViewById(R.id.textViewTwo);
                            textViewTwo.setText("You have found " + String.valueOf(numOfPairs) + " pairs and have won the game");


                        }

                    }

                    currentPos = -1;

                }
            }

    });
  }


    public void onShuffleButton(final View view){ //Shuffle button does not work entierly properly, the board will shuffle but won't reset

        MainActivity.shuffle(positions);

        numOfPairs = 0;

        currentPos = -1;

    }

    }



