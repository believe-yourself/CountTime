package com.example.counttime;

import java.util.Timer;
import java.util.TimerTask;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	private int i=0;
	private EditText etinput;
	private Button gettime,start,stop;
	private TextView tvtime;
	
	private Timer timer=null;
	private TimerTask task=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        
        
        
}
    private void initView(){
    	etinput=(EditText) findViewById(R.id.etinputtime);
        gettime=(Button) findViewById(R.id.btngettime);
        start=(Button) findViewById(R.id.btnStart);
        stop=(Button) findViewById(R.id.btnEnding);
       tvtime=(TextView) findViewById(R.id.tvtime);
       
       gettime.setOnClickListener(this);
       start.setOnClickListener(this);
       stop.setOnClickListener(this);
       
    }
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btngettime:
			tvtime.setText(etinput.getText().toString());
		i=Integer.parseInt(etinput.getText().toString());
			break;

		case R.id.btnStart:
			starttime();
			break;
		case R.id.btnEnding:
			stoptime();
			break;
		}
		
	}
	private Handler hander=new Handler(){
		public void handleMessage(Message msg) {
			tvtime.setText(msg.arg1+"");
			starttime();
		};
	};
	public void starttime(){
		timer=new Timer();
		task=new TimerTask() {
			
			@Override
			public void run() {
			i--;
			Message message=hander.obtainMessage();
			message.arg1=i;
			hander.sendMessage(message);
				
			}
		};
		timer.schedule(task, 1000);
	}
	public void stoptime(){
		timer.cancel();
	}
}