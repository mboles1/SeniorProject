package layout.code;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Insurance_Splash extends Activity 
{
	
	private TextView customer_name;
	private Button c_services, contact, find, help;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_sp);
        
        InitView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_sp, menu);
        return true;
    }

    
    private void InitView()
    {
    	customer_name = (TextView)findViewById(R.id.main_login_text);
    	c_services = (Button)findViewById(R.id.customer_services);
    	contact = (Button)findViewById(R.id.contact);
    	find = (Button)findViewById(R.id.find);
    	help = (Button)findViewById(R.id.help);
    	
    	final Builder customer_claim_dialog = new AlertDialog.Builder(this).setTitle(R.string.Customer_Services_Alert).setItems(R.array.customer_support, new DialogInterface.OnClickListener() 
        {
			
			@Override
			public void onClick(DialogInterface dialog, int i) 
			{
				Intent intent = null;
				switch(i)
				{
				case 0:
					intent = new Intent(Insurance_Splash.this, FileClaim.class);
					break;
				case 1:
					intent = new Intent(Insurance_Splash.this, PolicySearch.class);
					break;
				case 2:
					intent = new Intent(Insurance_Splash.this, EditAccount.class);
					Bundle bundle = new Bundle();
					bundle.putString("type", "edit");
					intent.putExtras(bundle);
					break;
				case 3:
					//Just a different way of doing things.
					String add_policy = "Add Policy";
					Bundle b = new Bundle();
					b.putBoolean("edit", false);
					b.putString("title", add_policy);
					b.putInt("noList", 1);
					intent = new Intent(Insurance_Splash.this, AddPolicy.class);
					intent.putExtras(b);
					break;
				case 4:
					String edit_policy = "Edit Policy";
					b = new Bundle();
					b.putString("title", edit_policy);
					b.putBoolean("edit", true);
					intent = new Intent(Insurance_Splash.this, AddPolicy.class);
					intent.putExtras(b);
					break;
				}
				startActivity(intent);
			}
		});
    	
    	c_services.setOnClickListener(new OnClickListener()

    	{

			@Override
			public void onClick(View v) 
			{
				customer_claim_dialog.show();
			}
    		
    	});
    	
    	contact.setOnClickListener(new OnClickListener()
    	{

			@Override
			public void onClick(View v) 
			{
				try 
				{
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					String phoneTest = "1234567890";
					callIntent.setData(Uri.parse("tel:" + phoneTest));
					startActivity(callIntent);
				} 
				catch (ActivityNotFoundException e)
				{
					Log.e("directory call", "Call failed", e);
				}
			}
    		
    	});
    	
    	find.setOnClickListener(new OnClickListener()
    	{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    	
    	help.setOnClickListener(new OnClickListener()
    	{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    }
    
}
