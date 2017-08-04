package com.menu.advance;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
public class AboutActivity extends AppCompatActivity
{
    AlertDialog nad;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboot);
		AlertDialog.Builder ad=new AlertDialog.Builder(this);
		ad.setTitle(R.string.help_t);
		ad.setMessage(getString(R.string.help_c1)+getString(R.string.help_c2)+getString(R.string.help_c3));
		ad.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				nad.dismiss();
				nad=null;
				AboutActivity.this.finish();
			}
		});
		nad=ad.create();
		nad.show();
	}

	@Override
	public void onBackPressed()
	{
		nad.dismiss();
		nad=null;
		AboutActivity.this.finish();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}
	
	
}
