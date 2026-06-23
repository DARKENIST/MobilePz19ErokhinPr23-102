package com.example.mobilepz19erokhinpr23_102;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView timePick;
    Button btnTime, btnDate;
    Calendar dateAndTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePick = findViewById(R.id.timePick);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);

        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);

        setInitialDateTime();
    }

    // Метод для установки начальных даты и времени в текстовое поле
    private void setInitialDateTime() {
        timePick.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnDate) {
            new DatePickerDialog(MainActivity.this, d,
                    dateAndTime.get(Calendar.YEAR),
                    dateAndTime.get(Calendar.MONTH),
                    dateAndTime.get(Calendar.DAY_OF_MONTH))
                    .show();
        } else if (view.getId() == R.id.btnTime) {
            new TimePickerDialog(MainActivity.this, t,
                    dateAndTime.get(Calendar.HOUR_OF_DAY),
                    dateAndTime.get(Calendar.MINUTE), true)
                    .show();
        }
    }

    // Обработчик выбора времени, обновляет объект Calendar и экран
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    // Обработчик выбора даты, обновляет объект Calendar и экран
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}
