package com.example.final_projects;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Air_tickets extends AppCompatActivity {

    private RecyclerView Air_recycler;

    @Override
    protected void onCreate(Bundle air_savedInstanceState) {
        super.onCreate(air_savedInstanceState);
        setContentView(R.layout.activity_air_tickets);
        sendRequestWithHttpURLConnection();
    }
    ArrayList<Air_Post> data = new ArrayList<>();
    private void sendRequestWithHttpURLConnection() {
        //開啟線程發起網路請求
        new Thread(new Runnable() {
            @Override
            public void run () {
                HttpURLConnection connection = null;
                BufferedReader reader;
                try {
                    //獲取HttpURLConnection實例
                    URL url = new URL("https://ptx.transportdata.tw/MOTC/v2/Air/GeneralSchedule/International?$top=100&$format=JSON");
                    connection = (HttpURLConnection) url.openConnection();
                    //設置請求方法和自由定制
                    connection.setUseCaches(false);
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(15000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    connection.connect();

                    InputStream in = connection.getInputStream();
                    //對輸入流進行讀取
                    reader = new BufferedReader(new InputStreamReader(in));

                    //解析JSON多層資料
                    JSONArray array = new JSONArray(reader.readLine());
                    for (int i=0; i<10; i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        String departure_air = jsonObject.getString("DepartureAirportID");
                        String arrival_air = jsonObject.getString("ArrivalAirportID");
                        String start_date =  jsonObject.getString("ScheduleStartDate");
                        String end_date = jsonObject.getString("ScheduleEndDate");
                        String start_time =  jsonObject.getString("DepartureTime");
                        String end_time = jsonObject.getString("ArrivalTime");
                        String air_id =  jsonObject.getString("FlightNumber");
                        String update_time = jsonObject.getString("UpdateTime");

                        //顯示各元件取得的資料
                        data.add(new Air_Post(
                                "出發機場: "+departure_air,
                                "抵達機場: "+arrival_air,
                                "開始日期: "+start_date,
                                "結束日期: "+ end_date,
                                "出發時間: "+start_time,
                                "抵達時間: "+end_time,
                                "航班號: "+air_id,
                                "更新時間: "+update_time));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
        // 連結元件
        Air_recycler = (RecyclerView) findViewById(R.id.Air_recycler);
        // 設置RecyclerView為列表型態
        Air_Adapter adapter = new Air_Adapter(data);
        Air_recycler.setLayoutManager(new LinearLayoutManager(this));
        // 設置格線
        Air_recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Air_recycler.setAdapter(adapter);
    }
}
