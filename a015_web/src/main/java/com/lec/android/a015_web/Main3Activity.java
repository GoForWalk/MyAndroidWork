package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/*
 * XML , JSON 파싱 연습
 *
 * ■서울시 지하철 역사 정보
		http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

		샘플url

		XML 버젼
		http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/xml/stationInfo/1/5/서울

		JSON 버젼
		http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/서울



 *  	 statnNm(STring), subwayId(int), subwayNm(String)
 */



public class Main3Activity extends AppCompatActivity {

    public static final String REQ_SERVICE = "stationInfo";
    public static final String API_KEY = "4e74654971736d613438694b614145";

    Button btnXML, btnJSON, btnParse;
    EditText et;
    TextView tvResult;

    int startIndex = 1;
    int endIndex = 4;

    String url_address;
//    StringBuffer sb;

    String statnNm;
    int pw = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnXML = findViewById(R.id.btnXML);
        btnJSON = findViewById(R.id.btnJSON);
        btnParse = findViewById(R.id.btnParse);

        et = findViewById(R.id.editText);

        statnNm = et.getText().toString();

        btnXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            pw = 1;
                try {
                    url_address =  buildUrlAddress("xml", statnNm ,startIndex, endIndex);
                    Log.d("myapp",""+url_address);


                    tvResult.setText(readFromUrl(url_address).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw = 2;
                try {
                    url_address = buildUrlAddress("json", statnNm ,startIndex, endIndex);


                    tvResult.setText(readFromUrl(url_address).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pw == 1){
                   parseXML(tvResult.getText().toString().trim());
                } else if (pw == 2){
                    paresJSON(tvResult.getText().toString().trim());
                }

            }
        });



    } // end onCreate()

    public static String buildUrlAddress(String type, String statnNm, int startIndex, int endIndex) throws IOException {

        String urlStatNm = URLEncoder.encode(statnNm, "UTF-8");

        String url_address = String.format("http://swopenAPI.seoul.go.kr/api/subway/%s/%s/%s/%d/%d/%s",
                API_KEY, type, REQ_SERVICE ,startIndex, endIndex, urlStatNm);

        return url_address;
    }

    public static StringBuffer readFromUrl(String urlAddress){
        StringBuffer sb = new StringBuffer();

        URL url = null;
        HttpsURLConnection conn = null;

        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;

        char[] buf = new char[512];

        try {
            url = new URL(urlAddress);
            conn = (HttpsURLConnection)url.openConnection();
            Log.d("myapp", "connectionOK");

            if(conn != null){
                conn.setConnectTimeout(5000);

                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded); charset=UTF-8");
                conn.setUseCaches(false);

                conn.connect();

                int responseCode = conn.getResponseCode();

                if(responseCode == HttpsURLConnection.HTTP_OK){
                    in = conn.getInputStream();
                    reader = new InputStreamReader(in, "utf-8");
                    br = new BufferedReader(reader);

                    int cnt = 0;
                    while ((cnt = br.read(buf)) != -1){
                        sb.append(buf,0,cnt);
                    }
                } else {
                    // response 실패

                }
            } else {
                // conn null
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
                if(conn != null) conn.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb;
    } //readFromUrl()

    public static String parseXML(String xmlText){

        String msg;
        String result = "";

        try {
            DocumentBuilderFactory documentBuilderFactory;
            DocumentBuilder documentBuilder;

            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();

            InputStream in = new ByteArrayInputStream(xmlText.getBytes("utf-8"));

            Document dom = documentBuilder.parse(in);

            Element docElement = dom.getDocumentElement();

            docElement.normalize();

            // DOM 내 데이터 파싱

            NodeList nodeList = docElement.getElementsByTagName("row");

            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);

                if(node.getNodeType() != Node.ELEMENT_NODE) continue;

                Element rowElement = (Element)node;

                String statnNm = rowElement.getElementsByTagName("statnNm").item(0).getChildNodes().item(0).getNodeValue().trim();
                String subwayNm = rowElement.getElementsByTagName("subwayNm").item(0).getChildNodes().item(0).getNodeValue().trim();
                String subwayId = rowElement.getElementsByTagName("subwayId").item(0).getChildNodes().item(0).getNodeValue().trim();

                msg = String.format("%d: %s %s  %s\n", i , statnNm, subwayId, subwayNm); // TODO Append
                result = msg + "\n";
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return result;
    } // end parseXML()

    public static String paresJSON(String jsonText){

        String msg;
        String result = "";

        try {


            JSONObject jsonObject = null;
            jsonObject = new JSONObject(jsonText);
            JSONArray stationInfo = jsonObject.getJSONArray("stationList");

            for(int i = 0; i < stationInfo.length(); i++){
                JSONObject station = stationInfo.getJSONObject(i);

                String statnNm = station.getString("statnNm");
                String subwayNm = station.getString("subwayNm");
                String subwayId = station.getString("subwayId");

                msg = String.format("%d: %s %s  %s\n", i , statnNm, subwayId, subwayNm);
                result = msg + "\n";
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }


} // end Activity




