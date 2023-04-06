package ro.pub.cs.systems.eim.practicaltest01var04;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {
    EditText nameText, groupText;
    CheckBox nameCheckbox, groupCheckbox;
    Button navigateButton, infoButton;
    TextView infoText;

    private final MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        nameText = findViewById(R.id.name_text);
        groupText = findViewById(R.id.group_text);
        nameCheckbox = findViewById(R.id.name_checkbox);
        groupCheckbox = findViewById(R.id.group_checkbox);
        navigateButton = findViewById(R.id.navigate_button);
        infoButton = findViewById(R.id.info_button);
        infoText = findViewById(R.id.info_text);


        infoButton.setOnClickListener(it -> {
            StringBuilder text = new StringBuilder();

            if (nameCheckbox.isChecked()) {
                if (nameText.getText().toString().isEmpty()) {
                    Toast.makeText(this, "ERROR: empty name field", Toast.LENGTH_SHORT).show();
                } else {
                    text.append(nameText.getText().toString()).append(" ");
                }
            }


            if (groupCheckbox.isChecked()) {
                if (groupText.getText().toString().isEmpty()) {
                    Toast.makeText(this, "ERROR: empty group field", Toast.LENGTH_SHORT).show();
                } else {
                    text.append(groupText.getText().toString()).append(" ");
                }
            }

            infoText.setText(text.toString());
            startPracticalTestService();
        });

        navigateButton.setOnClickListener(it -> {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
            intent.putExtra(Constants.NAME_FIELD, nameText.getText().toString());
            intent.putExtra(Constants.GROUP_FIELD, groupText.getText().toString());

            startActivityForResult(intent, Constants.REQUEST_CODE);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.NAME_FIELD, nameText.getText().toString());
        outState.putString(Constants.GROUP_FIELD, groupText.getText().toString());
        outState.putString(Constants.INFO_FIELD, infoText.getText().toString());
        outState.putBoolean(Constants.NAME_CHECKED, nameCheckbox.isChecked());
        outState.putBoolean(Constants.GROUP_CHECKED, groupCheckbox.isChecked());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        nameText.setText(savedInstanceState.getString(Constants.NAME_FIELD));
        groupText.setText(savedInstanceState.getString(Constants.GROUP_FIELD));
        infoText.setText(savedInstanceState.getString(Constants.INFO_FIELD));
        nameCheckbox.setChecked(savedInstanceState.getBoolean(Constants.NAME_CHECKED));
        groupCheckbox.setChecked(savedInstanceState.getBoolean(Constants.GROUP_CHECKED));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Result OK", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Result CANCELED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
        getApplicationContext().stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION);
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }

    private void startPracticalTestService() {
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
        intent.putExtra(Constants.NAME_FIELD, nameText.getText().toString());
        intent.putExtra(Constants.GROUP_FIELD, groupText.getText().toString());

        getApplicationContext().startService(intent);
    }

    private static class MessageBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.DATA, intent.getStringExtra(Constants.DATA));
        }
    }
}