package com.ganiprasetyo.bkpm.tgs2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.DatePickerDialog;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private EditText Fullname;
    Button Tombol;
    TextView Tampil;
    EditText TanggalLahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fullname = findViewById(R.id.txtNamaLengkap);
        Tombol = (Button) findViewById(R.id.Btnsub);
        Tampil = (TextView) findViewById(R.id.txtnama);
        TanggalLahir = findViewById(R.id.Tanggal);
        String[] genderArray = {"Laki-laki", "Perempuan", "Lainnya"};
        Spinner spinnerGender = findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.gender_array, android.R.layout.simple_spinner_item);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = parent.getItemAtPosition(position).toString();
                // Lakukan sesuatu dengan nilai yang dipilih
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ketika tidak ada pilihan yang dipilih
            }
        });


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TanggalLahir.setOnClickListener(v -> showDatePickerDialog());
        spinnerGender.setAdapter(adapter);
        // Mengatur teks pada EditText
        // Fullname.setText("gani");

        Tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nama = Fullname.getText().toString();
                Tampil.setText(Nama);
            }
        });

        // Mengatur padding berdasarkan sistem insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format tanggal untuk ditampilkan
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    TanggalLahir.setText(date);
                },
                year, month, day);

        datePickerDialog.show();
    }
}
