package layout.code;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class EditAccount extends Activity {

	private EditText policyAmount, policyNum;
	private ListView itemList;
	private Button editItem, cancel, submit;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_account, menu);
        return true;
    }
    
    private void InitView()
    {
    	policyAmount = (EditText)findViewById(R.id.policy_amount);
    	policyNum = (EditText)findViewById(R.id.add_policy_number);
    	itemList = (ListView)findViewById(R.id.add_policy_itemList);
    	editItem = (Button)findViewById(R.id.edit_button);
    	cancel = (Button)findViewById(R.id.policy_cancel);
    	submit = (Button)findViewById(R.id.policy_submit);
    }

    
}
