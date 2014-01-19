package layout.code;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AddPolicy extends Activity {

	private EditText policyAmount, policyNum;
	private ListView itemList;
	private Button addItem, cancel, submit;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_policy);
        
        InitView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_policy_, menu);
        return true;
    }
    
    private void InitView()
    {
    	policyAmount = (EditText)findViewById(R.id.policy_amount);
    	policyNum = (EditText)findViewById(R.id.add_policy_number);
    	itemList = (ListView)findViewById(R.id.add_policy_itemList);
    	addItem = (Button)findViewById(R.id.add_button);
    	cancel = (Button)findViewById(R.id.policy_cancel);
    	submit = (Button)findViewById(R.id.policy_submit);
    }

    
}
