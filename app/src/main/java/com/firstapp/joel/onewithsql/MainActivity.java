package com.firstapp.joel.onewithsql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button mSaveBtn, mShowBtn;
    EditText mUserNameEditText, mPasswordEditText;
    MyDbHelper dbHelper;
    SQLiteDatabase database;
    ArrayList<String> list = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDbHelper(this);
        database = dbHelper.getWritableDatabase();

        mSaveBtn = (Button) findViewById(R.id.buttonSave);
        mShowBtn = (Button) findViewById(R.id.buttonShow);
        mUserNameEditText = (EditText) findViewById(R.id.editTextUserName);
        mPasswordEditText = (EditText) findViewById(R.id.editTextPassword);
        ListView joelslistview = (ListView) findViewById(R.id.joelListView);
        registerForContextMenu(joelslistview);
        final ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        joelslistview.setAdapter(arrayAdapter);

//-----------------------------------------------------------------------------------------------------------------------
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUserNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                ContentValues values = new ContentValues();
                values.put(dbHelper.USERNAME, username);
                values.put(dbHelper.PASSWORD, password);
                database.insert(dbHelper.TABLE_NAME, null, values);
                mUserNameEditText.setText("");
                mPasswordEditText.setText("");
            }
        });
//-------------------------------------------------------------------------------------------------------------------------
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = database.rawQuery
                        ("select * from " + dbHelper.TABLE_NAME, null);
                cursor.moveToFirst();


                do {
                    String username = cursor.getString(cursor.getColumnIndex(dbHelper.USERNAME));
                    String password = cursor.getString(cursor.getColumnIndex(dbHelper.PASSWORD));
                    //String id = cursor.getString(cursor.getColumnIndex(dbHelper.ID));
                    list.add(username + "  " + password);
                    arrayAdapter.notifyDataSetChanged();
//----------------------------------------------------------------------------------------------------------------------
                    //Toast.makeText(MainActivity.this, "USERNAME IS : " + username +
                    //  " .. PASSWORD IS : " + password, Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
            }
        });
//----------------------------------------------------------------------------------------------------------------------
        joelslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String list = String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(MainActivity.this, list, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.mytoast)
        {
            Toast.makeText(this, "TOAST SELECTED", Toast.LENGTH_SHORT).show();
        }
        if(id==R.id.mysec){
            //make it to go to next page
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu ,v, menuInfo);
        getMenuInflater().inflate(R.menu.mymmenu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }
}