package layout.code;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FileClaim extends Activity 
{
	
	private EditText claim_desc, claim_comm, policy_num2, preport;
	private Button cam, cancel, submit;
	private Spinner companies;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_claim);
        
        InitView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.file_claim, menu);
        return true;
    }

    private void InitView()
    {
    	claim_desc = (EditText)findViewById(R.id.claim_et_desc);
    	claim_comm = (EditText)findViewById(R.id.claim_et_com);
    	policy_num2 = (EditText)findViewById(R.id.policy_numb2);
    	preport = (EditText)findViewById(R.id.report_num);
    	
    	cam = (Button)findViewById(R.id.cam_b);
    	cancel = (Button)findViewById(R.id.back_button);
    	submit = (Button)findViewById(R.id.submit_button);
    	
    	companies = (Spinner)findViewById(R.id.other_comp_sp);
    }
    
}
