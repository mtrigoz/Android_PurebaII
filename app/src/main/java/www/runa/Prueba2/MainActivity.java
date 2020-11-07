package www.runa.Prueba2;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText NuevoUsuario_edit, Nuevapassword_edit, Rpassword_edit;
    Button btnResgistrar, btnLogin;
    ConexUsr DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NuevoUsuario_edit = (EditText) findViewById(R.id.editNuevoUsuario);
        Nuevapassword_edit = (EditText) findViewById(R.id.editNuevapassword);
        Rpassword_edit = (EditText) findViewById(R.id.editRpassword);
        btnResgistrar = (Button) findViewById(R.id.btnResgistrar);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        DB = new ConexUsr(this);

        btnResgistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user  = NuevoUsuario_edit.getText().toString();
                String pass = Nuevapassword_edit.getText().toString();
                String repass = Rpassword_edit.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this, "Llena todos los campos", Toast.LENGTH_LONG).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkusername = DB.checkusername(user);
                        if(checkusername==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), USornitologo.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registro imposible", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Veo que ya estas registrado", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Contraseña no son la misma", Toast.LENGTH_LONG).show();
                    }
                } }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InicioUsuario.class);
                startActivity(intent);
            }
        });
    }
}