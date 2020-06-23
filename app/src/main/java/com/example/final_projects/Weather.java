package com.example.final_projects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Weather extends AppCompatActivity {

    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        sendRequestWithHttpURLConnection();
    }
    ArrayList<Post> data = new ArrayList<>();
        private void sendRequestWithHttpURLConnection() {
            //開啟線程發起網路請求
            new Thread(new Runnable() {
            @Override
            public void run () {
                HttpURLConnection connection = null;
                BufferedReader reader;
                try {
                    //獲取HttpURLConnection實例
                    URL url = new URL("http://163.17.9.130/weather_15day.php");
                    connection = (HttpURLConnection) url.openConnection();
                    //設置請求方法和自由定制
                    connection.setUseCaches(false);
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    connection.connect();

                    InputStream in = connection.getInputStream();
                    //對輸入流進行讀取
                    reader = new BufferedReader(new InputStreamReader(in));

                    //解析JSON多層資料

                    JSONArray array = new JSONArray(reader.readLine());
                    for (int i=0; i<array.length(); i++) {
                        String day = array.getJSONObject(i).getString("day");
                        String description = array.getJSONObject(i).getString("description");
                        String low = array.getJSONObject(i).getString("low");
                        String high = array.getJSONObject(i).getString("high");
                        String precip = array.getJSONObject(i).getString("precip");
                        String humidity = array.getJSONObject(i).getString("humidity");

                            //顯示各元件取得的資料
                            data.add(new Post(
                                    day,
                                    description,
                                    low+"° /",
                                    high+"°",
                                    precip+"%",
                                    humidity+"%"));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

            }
            }).start();
            // 連結元件
            recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
            // 設置RecyclerView為列表型態
            MyAdapter adapter = new MyAdapter(data);
            recycler_view.setLayoutManager(new LinearLayoutManager(this));
            // 設置格線
            recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            recycler_view.setAdapter(adapter);


        }
    }


