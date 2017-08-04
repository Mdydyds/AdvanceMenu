package com.menu.advance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.view.LayoutInflater;
import android.support.v7.app.AlertDialog;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.content.Intent;
import android.content.Context;

public class MenuActivity extends AppCompatActivity
{
	ListView lsmenu;
	String[] items;
	View instance;
	AlertDialog ad;
	ChooseAdapter ca;
	int nowplatform = 0;
	boolean[] bl;
	Button b1;
	Button b2;
	Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.aboot);
        instance=LayoutInflater.from(this).inflate(R.layout.menu,null);
		ad=new AlertDialog.Builder(this).create();
		ad.setTitle(R.string.app_name);
		ad.setView(instance);
		init();
		ad.show();
		lsmenu.setOnItemClickListener(new OnItemClickListener()
			{

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					int before = findCheck(bl);
					bl[before] = false;
					bl[p3] = true;
					ca=new ChooseAdapter(context,items,bl);
					lsmenu.setAdapter(ca);
					nowplatform = p3;
				}
				
			
		});
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ad.dismiss();
				recycle();
				MenuActivity.this.finish();
			}
		});
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				platform(nowplatform);
				ad.dismiss();
				recycle();
				MenuActivity.this.finish();
			}
		});
    }
	private void init()
	{
		lsmenu=(ListView)instance.findViewById(R.id.menulist);
        b1=(Button) instance.findViewById(R.id.button1);
		b2=(Button) instance.findViewById(R.id.button2);
		context=MenuActivity.this;
		items=new String[10];
		items[0] = getString(R.string.power_off);
		items[1] = getString(R.string.reboot_b);
		items[2] = getString(R.string.reboot_r);
		items[3] = getString(R.string.reboot_rst);
		items[4] = getString(R.string.reboot_s);
		items[5] = getString(R.string.reboot_u);
		items[6] = getString(R.string.reboot_h);
		items[7] = getString(R.string.reboot_ms);
		items[8] = getString(R.string.reboot_off);
		items[9] = getString(R.string.help_t);
		bl = new boolean[]{true,false,false,false,false,false,false,false,false,false};
		ca = new ChooseAdapter(context,items,bl);
		lsmenu.setAdapter(ca);
	}
	private void platform(int type)
	{
		switch (type)
		{
			case 0:RootTool.execRootCmdSilent("reboot -p"); break;
			case 1:RootTool.execRootCmdSilent("reboot bootloader"); break;
			case 2:RootTool.execRootCmdSilent("reboot recovery"); break;
			case 3:RootTool.execRootCmdSilent("am restart"); break;
			case 4:RootTool.execRootCmdSilent("reboot"); break;
			case 5:RootTool.execRootCmdSilent("busybox pkill com.android.systemui"); break;
			case 6:RootTool.execRootCmdSilent("setprop ctl.restart zygote"); break;
			case 7:RootTool.execRootCmdSilent("setprop persist.sys.safemode 1"); break;
			case 8:RootTool.execRootCmdSilent("input keyevent 26"); break;
			case 9:Aboot();
		}
	}

	@Override
	public void onBackPressed()
	{
		MenuActivity.this.finish();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}
	private void Aboot()
	{
		Intent it=new Intent();
		it.setClass(MenuActivity.this,AboutActivity.class);
		startActivity(it);
		it=null;
		MenuActivity.this.finish();
	}
	private int findCheck(boolean[] z)
	{
		if (z!=null)
		{
			for (int i=0;i<=9;i++)
			{
				if (z[i]==true)
				{
					return  i;
				}
				else {
					continue;
				}
			}
		}
		return -1;
	}
	private void recycle()
	{
		lsmenu=null;
		items=null;
		instance=null;
		ad=null;ca=null;
		nowplatform=0;
		bl=null;
		b1=null;
		b2=null;
		context=null;
	}
}
