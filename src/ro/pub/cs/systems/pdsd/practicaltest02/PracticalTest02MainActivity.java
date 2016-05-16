package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import ro.pub.cs.systems.pdsd.practicaltest02.general.Constants;
import ro.pub.cs.systems.pdsd.practicaltest02.general.Utilities;

public class PracticalTest02MainActivity extends Activity {
	
	
	private EditText serverPort;
	private EditText clientAddr;
	private Button connect;
	private Button getWeather;
	private EditText clientPort;
	private EditText oras;
	private Spinner type;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test02_main);
		
		serverPort = (EditText)findViewById(R.id.server_port_edit_text);
		clientAddr = (EditText)findViewById(R.id.client_address_edit_text);
		connect = (Button)findViewById(R.id.connect_button);
		getWeather = (Button)findViewById(R.id.get_weather_forecast_button);
		clientPort = (EditText)findViewById(R.id.client_port_edit_text);
		oras = (EditText)findViewById(R.id.city_edit_text);
		type = (Spinner)findViewById(R.id.information_type_spinner);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test02_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
