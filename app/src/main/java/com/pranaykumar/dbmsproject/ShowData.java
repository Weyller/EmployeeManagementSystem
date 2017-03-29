package com.pranaykumar.dbmsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowData extends AppCompatActivity {

    TextView empl_id, empl_name, ephone, egender, eemail;
    MyDbHandler dbHandler;
    String EmpId, EmpName, EmpPhone, EmpGender, EmpEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        dbHandler=new MyDbHandler(this, null, null, 1);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
            EmpId = bundle.getString("eid");
        EmpName = dbHandler.getName(EmpId);
        EmpPhone = dbHandler.getPhone(EmpId);
        EmpGender = dbHandler.getGender(EmpId);
        EmpEmail = dbHandler.getEmail(EmpId);

        empl_id=(TextView)findViewById(R.id.empl_id);
        empl_id.setText("EID : " + EmpId);
        empl_name=(TextView)findViewById(R.id.empl_name);
        empl_name.setText("Name : " + EmpName);
        ephone=(TextView)findViewById(R.id.ephone);
        ephone.setText("Phone : " + EmpPhone);
        egender=(TextView)findViewById(R.id.egender);
        egender.setText("Gender : " + EmpGender);
        eemail=(TextView)findViewById(R.id.eemail);
        eemail.setText("Email : " + EmpEmail);
    }
}
