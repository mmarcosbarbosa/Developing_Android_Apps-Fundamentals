package com.haand.nipper.prototipo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity2 extends AppCompatActivity {

    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_2);

        signInBtn= (Button) findViewById(R.id.email_sign_in_button);
        signInBtn.setFocusable(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login__activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void validateFields(View v) {
        // Pega os valores digitados
        String email = ((AutoCompleteTextView) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        // Obtem o objeto para colocar a mensagem de validação
        TextView msg = (TextView)findViewById(R.id.validation_message_txt);

        if (email.equals("exemplo@shell.com") && password.equals("teste")) {
            String message = getResources().getString(R.string.validation_sucess);
            msg.setText(message);
        } else {
            String message = getResources().getString(R.string.invalid_user_passord);
            msg.setText(message);
        }
    }

    public void callSignUpForm(View v) {
        // Inicia o formulário de Cadastro
        Toast.makeText(getApplicationContext(), "Not implemented yet.", Toast.LENGTH_SHORT).show();

    }
}
