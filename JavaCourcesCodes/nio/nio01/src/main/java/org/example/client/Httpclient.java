package org.example.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Httpclient {

    public static void main(String[] args) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8802");
        CloseableHttpResponse response = null;

        try {
            response = client.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            System.out.println(EntityUtils.toString(httpEntity));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
            if (client!=null){
                client.close();
            }
            if (response!=null){
                response.close();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
