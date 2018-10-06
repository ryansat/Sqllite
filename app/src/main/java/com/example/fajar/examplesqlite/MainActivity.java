package com.example.fajar.examplesqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editID,editName,editAlamat;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editID = (EditText) findViewById(R.id.edtid);
        editName = (EditText) findViewById(R.id.edtnama);
        editAlamat = (EditText) findViewById(R.id.alamat);
    }

    public void btnDelete (View v)
    {
        Integer deletedRows = myDb.deleteData(editID.getText().toString());
        if(deletedRows > 0)
            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
    }

    public void btnInsert (View v)
    {
        boolean isInserted = myDb.insertData(editName.getText().toString(),editAlamat.getText().toString());
        if(isInserted == true)
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
    }
    public void btnUpdate (View v)
    {
        boolean isUpdate = myDb.updateData(editID.getText().toString(), editName.getText().toString(),editAlamat.getText().toString());
        if(isUpdate == true)
            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
    }

    public void btnViewData (View v)
    {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("Alamat :"+ res.getString(2)+"\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
