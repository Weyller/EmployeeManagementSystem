package com.pranaykumar.dbmsproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EnterActivity extends AppCompatActivity {

    MyDbHandler dbHandler;
    EditText emp_id, emp_name, phone, gender, email;
    String EmpId, EmpName, EmpPhone, EmpGender, EmpEmail;
    int databaseEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        emp_id=(EditText)findViewById(R.id.emp_id);
        emp_name=(EditText)findViewById(R.id.emp_name);
        phone=(EditText)findViewById(R.id.phone);
        gender=(EditText)findViewById(R.id.gender);
        email=(EditText)findViewById(R.id.email);

        dbHandler=new MyDbHandler(this, null, null, 1);
    }

    public void submitData(View view) {
        EmpId = emp_id.getText().toString();
        EmpName = emp_name.getText().toString();
        EmpPhone = phone.getText().toString();
        EmpGender = gender.getText().toString();
        EmpEmail = email.getText().toString();

        boolean b = dbHandler.userExists(EmpId);
        if(b) {
            Toast.makeText(getApplicationContext(), "Employee id already exists", Toast.LENGTH_SHORT).show();
        }
        else {
            if(EmpId.length()==0 || EmpName.length()==0 || EmpPhone.length()==0 || EmpGender.length()==0 || EmpEmail.length()==0){
                Toast.makeText(getApplicationContext(),"Complete the form", Toast.LENGTH_SHORT).show();
            }
            else {
                SharedPreferences sharedpreference = this.getSharedPreferences(
                        getString(R.string.preference_key), Context.MODE_PRIVATE);
                databaseEmpty = sharedpreference.getInt(getString(R.string.empty_status), 0);
                databaseEmpty++;
                SharedPreferences.Editor editor = sharedpreference.edit();
                editor.putInt(getString(R.string.empty_status), databaseEmpty);
                editor.apply();

                dbHandler.addRow(new EmployeeDetails(EmpId, EmpName, EmpPhone, EmpGender, EmpEmail));

                Toast.makeText(getApplicationContext(), "Data successfully entered", Toast.LENGTH_SHORT).show();

                emp_id.setText("");
                emp_name.setText("");
                phone.setText("");
                gender.setText("");
                email.setText("");
            }

        }
    }
}
