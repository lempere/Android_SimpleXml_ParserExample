package org.androidconnect.simplexmlparser;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidSimpleXmlParser_ExampleActivity extends Activity implements OnClickListener {
	
	private static final String url_xml = "http://lamevaip.info/index.php/xml";
	
	private TextView text_utc;
	private TextView text_ip;
	private TextView text_agent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button download = (Button) findViewById(R.id.download);
        download.setOnClickListener(this);
        text_utc = (TextView) findViewById(R.id.text_utc);
        text_ip = (TextView) findViewById(R.id.text_ip);
        text_agent = (TextView) findViewById(R.id.text_agent);
    }
    
    public void updateIpInfo(LaMevaIp ip){
    	
    	text_utc.setText(ip.utc);
    	text_ip.setText(ip.ip);    	
    	text_agent.setText(ip.agent);    	
    }
    
	public void onClick(View v) {
		if(v.getId() == R.id.download){
			new getIpXmlAsync().execute();
		}
	}    
    
    //AsyncTaskVariables<doInBackground,onProgressUpdater,onPostExecute>
    private class getIpXmlAsync extends AsyncTask<Void, Void, Void>{

        /* Will be filled and displayed later. */
    	String ipString = null;
    	LaMevaIp ip_externa = null;

   	 protected void onPreExecute() {}

   	 	 @Override
         protected Void doInBackground(Void... urls) {

             try {
            	 
    	   	   URL myURL = new URL( url_xml); 
    	       /* Open a connection to that URL. */ 
    	       URLConnection ucon = myURL.openConnection(); 
    	
    	       /* Define InputStreams to read 
    	        * from the URLConnection. */ 
    	       InputStream is = ucon.getInputStream(); 
               Serializer serializer = new Persister();
     		   ip_externa = serializer.read(LaMevaIp.class, is );
     		   ipString = ip_externa.ip;
     		} catch (Exception e) {
     			e.printStackTrace();
     		}
             
             Log.d("org.androidconnect.simplexmlparser", "Get ip from web: "+ipString);
             
             return null;
         }

         protected void onProgressUpdate (Void... valores) {
         }

         protected void onPostExecute(Void bytes) {
        	 if(ipString == null){
        		 Toast.makeText(getApplicationContext(), "Can't connect ..", 
        				 Toast.LENGTH_LONG ).show();
        		 return;
        	 }

        	 updateIpInfo(ip_externa);
         }
   }
 
}