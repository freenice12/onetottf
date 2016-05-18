package com.example.ljy.c04_view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
  LinearLayout mainLayout;

  private int selectedCount = 1;
  private Random random = new Random();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

    TextView tv = new TextView(this);
    tv.setText("Hello Active View!");

    mainLayout.addView(tv);

    TableLayout tableLayout = new TableLayout(this);
    tableLayout.setShrinkAllColumns(true);

    ArrayList<Integer> randomNumbs = getRandomNumber();

    ArrayList<TableRow> tableRows = new ArrayList<>();
    for (int i=0; i < 5; i++) {
      tableRows.add(new TableRow(this));
    }
    int index = 1;
    for (TableRow tableRow : tableRows) {
      for (int i = 0; i < 5; i++) {
        final Button button = new Button(this);
        final int finalIndex = index;
        button.setText(""+index++);
        button.setOnClickListener(new View.OnClickListener() {
          int id = finalIndex;
          @Override
          public void onClick(View v) {
            if (selectedCount == id ) {
              Toast.makeText(getApplicationContext(), button.getText()+" has clicked! / "+random.nextInt(25), Toast.LENGTH_SHORT).show();
              selectedCount++;
              button.setClickable(false);
              button.setBackgroundColor(Color.RED);
              if (selectedCount == 26) {
                Toast.makeText(getApplicationContext(), " C L E A R ! ! ! ", Toast.LENGTH_SHORT).show();
                recreate();
              }
            }
          }
        });
        tableRow.addView(button);
      }
      tableLayout.addView(tableRow);
    }

    mainLayout.addView(tableLayout);




//
//        Button tv1 = (Button) findViewById(R.id.textView1);
//        tv1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
  }

  public ArrayList<Integer> getRandomNumber() {
    ArrayList<Integer> result = new ArrayList<>();


    return result;
  }
}
