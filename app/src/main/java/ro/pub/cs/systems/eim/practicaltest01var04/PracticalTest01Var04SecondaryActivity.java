package ro.pub.cs.systems.eim.practicaltest01var04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {
    TextView nameText, groupText;
    Button okButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);

        nameText = findViewById(R.id.name_text_second);
        groupText = findViewById(R.id.group_text_second);
        okButton = findViewById(R.id.ok_button);
        cancelButton = findViewById(R.id.cancel_button);

        nameText.setText(getIntent().getExtras().getString(Constants.NAME_FIELD));
        groupText.setText(getIntent().getExtras().getString(Constants.GROUP_FIELD));

        okButton.setOnClickListener(it -> {
            setResult(RESULT_OK);
            finish();
        });

        cancelButton.setOnClickListener(it -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                nameText.setText("");
                groupText.setText("");
            } else {
                nameText.setText(extras.getString(Constants.NAME_FIELD));
                groupText.setText(extras.getString(Constants.GROUP_FIELD));
            }
        } else {
            nameText.setText(savedInstanceState.getString(Constants.NAME_FIELD));
            groupText.setText(savedInstanceState.getString(Constants.GROUP_FIELD));
        }
    }
}