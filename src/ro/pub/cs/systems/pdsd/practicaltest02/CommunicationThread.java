package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import android.util.Log;
import ro.pub.cs.systems.pdsd.practicaltest02.general.Constants;
import ro.pub.cs.systems.pdsd.practicaltest02.general.Utilities;

public class CommunicationThread extends Thread {
	
	private ServerThread serverThread;
	private Socket       socket;
	
	public CommunicationThread(ServerThread serverThread, Socket socket) {
		this.serverThread = serverThread;
		this.socket       = socket;
	}
	
	@Override
	public void run() {
		if (socket != null) {
			try {
				HashMap<String, WeatherForecastInformation> data = serverThread.getData();
				BufferedReader reader = Utilities.getReader(socket);
				PrintWriter    printWriter    = Utilities.getWriter(socket);
				WeatherForecastInformation weatherForecastInformation = null;
				if (reader != null && printWriter != null) {
					String city            = reader.readLine();
					String informationType = reader.readLine();
					if (data.containsKey(city)) {
						weatherForecastInformation = data.get(city);
					} else {
						//get data
						try {
							  HttpClient httpClient = new DefaultHttpClient();        
							  HttpPost httpPost = new HttpPost("http://www.server.com");
							 
							  List<NameValuePair> params = new ArrayList<NameValuePair>();        
							  params.add(new BasicNameValuePair("attribute1", "value1"));
							  params.add(new BasicNameValuePair("attributen", "valuen"));
							 
							  UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
							  httpPost.setEntity(urlEncodedFormEntity);
							 
							  HttpResponse httpPostResponse = httpClient.execute(httpPost);  
							  HttpEntity httpPostEntity = httpPostResponse.getEntity();  
							  if (httpPostEntity != null) {
							    // do something with the response
							    Log.i(Constants.TAG, EntityUtils.toString(httpPostEntity));
							   }
							} catch (Exception exception) {
							  Log.e(Constants.TAG, exception.getMessage());
							  if (Constants.DEBUG) {
							    exception.printStackTrace();
							  }
							}
					}
				}
			} catch (Exception e) {
				
			}
		}
	}
}
