package com.example.do_an_cs3.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.do_an_cs3.Database.DatabaseManager;
import com.example.do_an_cs3.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddJobActivity extends AppCompatActivity {
    private Button nextButton;
    private EditText nameProject;

    private TextInputEditText descriptionProject;

    private TextView deadlineTime;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjob);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        MenuItem jobaddMenuItem = bottomNavigationView.getMenu().findItem(R.id.add_job);
        jobaddMenuItem.setChecked(true);
        DatabaseManager dbManager = new DatabaseManager(this);
        // Thiết lập nghe sự kiện cho BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                Intent homeIntent = new Intent(AddJobActivity.this, MainActivity.class);
                startActivity(homeIntent);
                return true;
            }
        });

        // Tìm và gán MenuItem tương ứng với "home"
        MenuItem homeMenuItem = bottomNavigationView.getMenu().findItem(R.id.home);

        // Thiết lập nghe sự kiện khi menu "home" được chọn
        homeMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Nếu đang ở trong JobActivity, chuyển đến MainActivity
                if (AddJobActivity.this instanceof AddJobActivity) {
                    Intent homeIntent = new Intent(AddJobActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                }
                return true;
            }
        });

        nameProject = findViewById(R.id.editAddNameProject);
        descriptionProject = findViewById(R.id.textInputEditTextDescription);
        deadlineTime = findViewById(R.id.textViewDate);

        nextButton = findViewById(R.id.butonThenext);
        nextButton.setOnClickListener(v -> {
            String name = nameProject.getText().toString();
            String description = descriptionProject.getText().toString();
            String deadline = deadlineTime.getText().toString();

            if (name.isEmpty() || description.isEmpty() || deadline.isEmpty()) {
                Toast.makeText(this, "Please check the information again ", Toast.LENGTH_SHORT).show();
            } else {
                long insertedId = dbManager.addProject(name, description, deadline);
                if (insertedId != -1) {
                    Toast.makeText(this, "Add successfully " + name, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Error " + name, Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Trong phương thức onCreate hoặc trong một phương thức khác phù hợp
        Button buttonAddTime = findViewById(R.id.buttonaddTime);
        buttonAddTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo và hiển thị DatePickerDialog để chọn ngày
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddJobActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Lưu giá trị ngày được chọn và tạo và hiển thị TimePickerDialog để chọn giờ
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, month, dayOfMonth);

                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(AddJobActivity.this, new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Lưu giá trị giờ được chọn và hiển thị trên giao diện người dùng
                                // Ví dụ: Thứ 7, 11 tháng 5 2024, 17:00
                                String dateTime = String.format("Thứ %d, %d tháng %d %d:%02d", selectedCalendar.get(Calendar.DAY_OF_WEEK), dayOfMonth, month + 1, year, hourOfDay, minute);
                                // Hiển thị trên giao diện người dùng
                                // Ví dụ: Thứ 7, 11 tháng 5 2024, 17:00
                                TextView textView = findViewById(R.id.textViewDate);
                                textView.setText(dateTime);
                            }
                        }, hour, minute, true);
                        timePickerDialog.show();
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });


        MenuItem settingMenuItem = bottomNavigationView.getMenu().findItem(R.id.setting);
        // Thiết lập nghe sự kiện khi menu "home" được chọn
        settingMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Nếu đang ở trong JobActivity, chuyển đến settingActivity
                if (AddJobActivity.this instanceof AddJobActivity) {
                    Intent jobIntent = new Intent(AddJobActivity.this, SettingActivity.class);
                    startActivity(jobIntent);
                }
                return true;
            }
        });
        MenuItem jobMenuItem = bottomNavigationView.getMenu().findItem(R.id.job);
        // Thiết lập nghe sự kiện khi menu "home" được chọn
        jobMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Nếu đang ở trong JobActivity, chuyển đến AddJobActivity
                if (AddJobActivity.this instanceof AddJobActivity) {
                    Intent jobIntent = new Intent(AddJobActivity.this, JobActivity.class);
                    startActivity(jobIntent);
                }
                return true;
            }
        });
        MenuItem perMenuItem = bottomNavigationView.getMenu().findItem(R.id.personnal);
        // Thiết lập nghe sự kiện khi menu "home" được chọn
        perMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Nếu đang ở trong JobActivity, chuyển đến AddJobActivity
                if (AddJobActivity.this instanceof AddJobActivity) {
                    Intent perIntent = new Intent(AddJobActivity.this, PersonnalActivity.class);
                    startActivity(perIntent);
                }
                return true;
            }
        });
    }
}

