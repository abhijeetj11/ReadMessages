package com.droidaxel.sampleread;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView smsListView = (RecyclerView) findViewById(R.id.message_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        smsListView.setLayoutManager(linearLayoutManager);
        SmsAdapter smsAdapter = new SmsAdapter(readSms(), this);
        smsListView.setAdapter(smsAdapter);
    }

    private ArrayList<SmsModel> readSms() {
//        String[] columnDate =   new String[] {"date"};
        Uri uriSMSURI = Uri.parse("content://mms-sms/conversations/");
        Cursor cursor1 = getContentResolver().query(uriSMSURI, null, null, null, "date desc");
        String sms = "";
        String[] columns = new String[]{"address", "person", "date", "body", "type"};

        ArrayList<SmsModel> smsModels = new ArrayList<>();
        while (cursor1.moveToNext()) {

            int dateIndex = cursor1.getColumnIndex("date");
            String msgData1Date = cursor1.getString(dateIndex);

            long millis = Long.parseLong(msgData1Date);
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTimeInMillis(millis);
            String finalDateString = formatter.format(calendar1.getTime());

            SmsModel smsModel = new SmsModel();
            smsModel.setAddress(cursor1.getString(cursor1.getColumnIndex(columns[0])));
            smsModel.setName(cursor1.getString(cursor1.getColumnIndex(columns[1])));

            smsModel.setDate(finalDateString);
            smsModel.setMsg(cursor1.getString(cursor1.getColumnIndex(columns[3])));
            smsModel.setType(cursor1.getString(cursor1.getColumnIndex(columns[4])));
            smsModels.add(smsModel);
        }


        return smsModels;
    }

    @Override
    public void onItemClicked(SmsModel smsModel) {

    }
}