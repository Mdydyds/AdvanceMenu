package com.menu.advance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class AbootActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboot);
		final TextView c=(TextView)findViewById(R.id.aboot_s);
		c.setText(getString(R.string.help_c1)+getString(R.string.help_c2)+getString(R.string.help_c3));
	}

	@Override
	public void onBackPressed()
	{
		AbootActivity.this.finish();
	}

	@Override
	protected void onDestroy()
	{
		System.gc();
		super.onDestroy();
	}
	
	
}
