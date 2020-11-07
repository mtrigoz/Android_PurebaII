package www.runa.Prueba2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InicioUsuario extends AppCompatActivity {

    EditText editUsuario, editContraseña;
    Button button_Ingresar;
    ConexUsr DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_usuario);

            editUsuario = (EditText) findViewById(R.id.editUsuario);
            editContraseña = (EditText) findViewById(R.id.editContraseña);
            button_Ingresar = (Button) findViewById(R.id.btnIngresar);
            DB = new ConexUsr(this);

        button_Ingresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String user  = editUsuario.getText().toString();
                    String pass = editContraseña.getText().toString();

                    if(user.equals("")||pass.equals(""))
                        Toast.makeText(InicioUsuario.this, "Rellenar los campos", Toast.LENGTH_SHORT).show();
                    else{
                        Boolean checkContraseña = DB.checkusernamepassword(user, pass);
                        if(checkContraseña==true){
                            Toast.makeText(InicioUsuario.this, "Credenciales correctas", Toast.LENGTH_SHORT).show();
                            Intent intent  = new Intent(getApplicationContext(), USornitologo.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(InicioUsuario.this, "Credenciales  Incorrectas", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

}