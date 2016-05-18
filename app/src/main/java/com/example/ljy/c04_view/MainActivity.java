package com.example.ljy.c04_view;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
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
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
  private LinearLayout mainLayout;
  private TableLayout tableLayout;
  private Button startButton;
  private TextView timerView;

  private int selectedCount = 1;
  private Random random = new Random();
  private long startTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

    TextView tv = new TextView(this);
    tv.setTextSize(20);
    tv.setText("1 TO 25!");

    mainLayout.addView(tv);

    tableLayout = new TableLayout(this);
    tableLayout.setStretchAllColumns(true);
    tableLayout.setShrinkAllColumns(true);

    ArrayList<Integer> randomNumbs = getRandomNumber();

    createTableRow(tableLayout, randomNumbs);

    tableLayout.setVisibility(View.INVISIBLE);
    mainLayout.addView(tableLayout);

    startButton = new Button(this);
    startButton.setText("START!!");
    startButton.setOnClickListener(getStartButtonListener());
    mainLayout.addView(startButton);

    timerView = new TextView(this);
    timerView.setTextSize(40);
    timerView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    timerView.setText("00:00.000");

    mainLayout.addView(timerView);

    Button stopButton = new Button(this);
    stopButton.setText("Stop!");
    stopButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        stopTimer();
      }
    });

    mainLayout.addView(stopButton);

    Button resetButton = new Button(this);
    resetButton.setText("Reset!");
    resetButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        recreate();
      }
    });

    mainLayout.addView(resetButton);

  }

  public ArrayList<Integer> getRandomNumber() {
    ArrayList<Integer> result = new ArrayList<>();
    int index = 0;
    while (index < 25) {
      int randomNumber = random.nextInt(25) + 1;
      if (!result.contains(randomNumber)) {
        result.add(randomNumber);
        index++;
      }
    }
    return result;
  }

  private void createTableRow(TableLayout tableLayout, ArrayList<Integer> randomNumbs) {
    ArrayList<TableRow> tableRows = new ArrayList<>();
    int rowSize = 5;
    int columnSize = 5;

    addTableRow(tableRows, rowSize);

    int index = 0;
    for (TableRow tableRow : tableRows) {
      for (int i = 0; i < columnSize; i++) {
        addColumn(randomNumbs.get(index), tableRow);
        index++;
      }
      tableLayout.addView(tableRow);
    }
  }

  private void addTableRow(ArrayList<TableRow> tableRows, int row) {
    for (int i=0; i < row; i++) {
      tableRows.add(new TableRow(this));
    }
  }

  private void addColumn(final int finalId, TableRow tableRow) {
    final Button button = getButton(finalId);
    tableRow.addView(button);
  }

  @NonNull
  private Button getButton(final int finalId) {
    final Button button = new Button(this);
    String buttonText = "" + finalId;
    button.setText(buttonText);
    button.setOnClickListener(getListener(button, finalId));
    return button;
  }

  @NonNull
  public View.OnClickListener getListener(final Button button, final int finalId) {
    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (selectedCount == finalId ) {
//              Toast.makeText(getApplicationContext(), button.getText()+" has clicked! ", Toast.LENGTH_SHORT).show();
          selectedCount++;
          button.setClickable(false);
          button.setBackgroundColor(Color.GRAY);
          if (selectedCount == 26) {
            Toast.makeText(getApplicationContext(), " C L E A R ! ! ! ", Toast.LENGTH_SHORT).show();
            stopTimer();
//            recreate();
          }
        }
      }
    };
  }

  public View.OnClickListener getStartButtonListener() {
    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        tableLayout.setVisibility(View.VISIBLE);
        startTimer();
      }
    };
  }

  private boolean STOP;
  private Handler timeHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      timerView.setText(getTime());
      timeHandler.sendEmptyMessage(0);
    }
  };

  private void stopTimer() {
    STOP = true;
    timeHandler.removeMessages(0);
  }

  private void startTimer() {
    startTime = SystemClock.elapsedRealtime();
    timeHandler.sendEmptyMessage(0);
  }

  public String getTime() {
    long now = SystemClock.elapsedRealtime();
    long ell = now - startTime;

    return String.format("%02d:%02d:%02d", ell/1000/60, (ell/1000)%60, (ell%1000)/10);
  }
}
