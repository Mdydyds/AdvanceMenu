package com.menu.advance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import android.view.View;
import android.view.LayoutInflater;
import android.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.content.Intent;
import android.view.ViewGroup;

public class MenuActivity extends Activity 
{
	ListView lsmenu;
	ArrayList<String> items;
	View instance;
	AlertDialog ad;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        instance=LayoutInflater.from(this).inflate(R.layout.menu,null);
		ad=new AlertDialog.Builder(this).create();
		ad.setTitle(R.string.app_name);
		ad.setView(instance);
		ad.setIcon(R.drawable.ic_launcher);
		init();
		ad.show();
		lsmenu.setOnItemClickListener(new OnItemClickListener()
			{

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					  platform(p3);
					  ad.dismiss();
					  ad=null;
					  instance=null;
					  lsmenu=null;
					  items=null;
					  MenuActivity.this.finish();
				}
				
			
		});
		
    }
	private void init()
	{
		lsmenu=(ListView)instance.findViewById(R.id.menulist);
		items=new ArrayList<String>();
		items.add(getString(R.string.power_off));
		items.add(getString(R.string.reboot_b));
		items.add(getString(R.string.reboot_r));
		items.add(getString(R.string.reboot_rst));
		items.add(getString(R.string.reboot_s));
		items.add(getString(R.string.reboot_u));
		items.add(getString(R.string.reboot_h));
		items.add(getString(R.string.reboot_ms));
		items.add(getString(R.string.reboot_off));
		items.add(getString(R.string.help_t));
		Command cd=new Command("cat /proc/cpuinfo|grep Hardware");
		String infos=cd.execToGainInfos();
		if (infos.contains("Qualcomm")||infos.contains("MSM"))
		{
			items.add(getString(R.string.reboot_a));
		}
		lsmenu.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items));
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
			case 9:Aboot(); break;
			case 10:RootTool.execRootCmdSilent("reboot edl");
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
		System.gc();
		super.onDestroy();
	}
	private void Aboot()
	{
		Intent it=new Intent();
		it.setClass(MenuActivity.this,AbootActivity.class);
		startActivity(it);
		it=null;
	}
	
}
