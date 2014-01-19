package layout.code;

import database.offline.DBHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Login extends Activity 
{

	private EditText userName, pass;
	private Button createButton, loginButton;
	private String userNameString, passString, insertedPass;
	
	private DBHelper dbHelper;
	private Cursor myCursor;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        dbHelper = new DBHelper(this);
        dbHelper.open();
        
        InitView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_, menu);
        return true;
    }
    
    private void InitView()
    {
    	userName = (EditText)findViewById(R.id.user_name_et);
    	pass = (EditText)findViewById(R.id.pass_et);
    	createButton = (Button)findViewById(R.id.create_accountb);
    	loginButton = (Button)findViewById(R.id.login_button);
    	
    	createButton.setOnClickListener(new OnClickListener()
    	{

			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Login.this, EditAccount.class);
				finish();
				startActivity(i);
				
			}
    		
    	});
    	
    	loginButton.setOnClickListener(new OnClickListener()
    	{

			@Override
			public void onClick(View v) 
			{
				userNameString = userName.getText().toString();
		    	insertedPass = pass.getText().toString();
		    	
		    	Intent i = new Intent(Login.this, Insurance_Splash.class);
		    	/*
		    	 * Do the query and make sure that userNameString is contained in the database
		    	 * Then do a check if pass provided matches pass stored with userNameString
		    	 */
		    	myCursor = dbHelper.pullCreds(userNameString);
		    	
		    	Cursor c = myCursor;
				
		    	if(c == null)
		    	{
		    		Toast test = Toast.makeText(Login.this, "You need to create an account first!", Toast.LENGTH_LONG);
		    		test.show();
		    		Log.e("No account", "There is no record of user in database.");
		    	}
		    	
		    	if(c != null)
		    	{
		    		if(c.moveToFirst())
		    		{
		    			userNameString = c.getString(c.getColumnIndexOrThrow(DBHelper.COLUMN_LOGIN));
		    			passString = c.getString(c.getColumnIndexOrThrow(DBHelper.COLUMN_PASS));
		    			
		    			if(!passString.equals(insertedPass))
		    			{
		    				Toast wrong = Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG);
		    				wrong.show();
		    			}
		    			else
		    			{
		    				i.putExtra("user", userNameString);
			    			i.putExtra("pass", passString);
			    			
			    			dbHelper.close();
			    			
			    			finish();
							startActivity(i);
		    			}	
		    		}
		    	}
		    	
			}
    		
    	});
    	
    	
    	
    }

    
}
