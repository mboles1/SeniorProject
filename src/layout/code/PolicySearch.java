package layout.code;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;

public class PolicySearch extends Activity {

	private ListView policy_list;
	private Button home_b;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.policy_search);
        
        InitView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.policy_search, menu);
        return true;
    }
    
    private void InitView()
    {
    	policy_list = (ListView)findViewById(R.id.policy_list);
    	home_b = (Button)findViewById(R.id.back_button);
    }

    
}
