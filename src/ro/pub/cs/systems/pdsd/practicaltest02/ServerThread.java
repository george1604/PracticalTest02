package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import android.util.Log;
import ro.pub.cs.systems.pdsd.practicaltest02.general.Constants;

class ServerThread extends Thread {
		 
	    private boolean isRunning;
	    private Integer port;
	    private ServerSocket serverSocket;
	    private HashMap<String, WeatherForecastInformation> data = null;
	 
	    public void startServer(Integer port) {
	      isRunning = true;
	      this.port = port;
	      this.data = new HashMap<String, WeatherForecastInformation>();
	      start();
	    }
	 
	    public synchronized void setData(String city, WeatherForecastInformation weatherForecastInformation) {
			this.data.put(city, weatherForecastInformation);
		}
		
		public synchronized HashMap<String, WeatherForecastInformation> getData() {
			return data;
		}
	    
	    public void stopServer() {
	      isRunning = false;
	      new Thread(new Runnable() {
	        @Override
	        public void run() {
	          try {
	            if (serverSocket != null) {
	              serverSocket.close();
	            }
	            Log.v(Constants.TAG, "stopServer() method invoked "+serverSocket);
	          } catch(IOException ioException) {
	            Log.e(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
	            if (Constants.DEBUG) {
	              ioException.printStackTrace();
	            }
	          }
	        }
	      }).start();
	    }
	 
	    @Override
	    public void run() {
	      try {
	    	  
	    	  serverSocket = new ServerSocket(port);
	        while (isRunning) {
	          Socket socket = serverSocket.accept();
	          Log.v(Constants.TAG, "Connection opened with "+socket.getInetAddress()+":"+socket.getLocalPort());
	          CommunicationThread communicationThread = new CommunicationThread(this, socket);
			  communicationThread.start();
	          socket.close();
	          Log.v(Constants.TAG, "Connection closed");
	        }
	      } catch (IOException ioException) {
	        Log.e(Constants.TAG, "An exception has occurred: "+ioException.getMessage());
	        if (Constants.DEBUG) {
	          ioException.printStackTrace();
	        }
	      }
	    }
	  }