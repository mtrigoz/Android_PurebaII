package www.runa.Prueba2;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FichaUsuarios extends AppCompatActivity {


    Button button_Actualizar,button_Eliminar,button_Guardar,button_Ver;
    EditText editAves,editComentarios,editId,editActualizarId,editActualizarAves,editActualizarComentarios;
    ConexAves conexdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_usuarios);

        conexdb = new ConexAves(this);

        editAves = (EditText)findViewById(R.id.editAves);
        editComentarios = (EditText)findViewById(R.id.editComentarios);
        editId = (EditText)findViewById(R.id.editId);
        editActualizarId = (EditText)findViewById(R.id.editActualizarId);
        editActualizarAves = (EditText)findViewById(R.id.editActualizarAves);
        editActualizarComentarios = (EditText)findViewById(R.id.editActualizarComentarios);

        button_Guardar = (Button)findViewById(R.id.btnGuardar);
        button_Ver = (Button)findViewById(R.id.btnVer);
        button_Eliminar = (Button)findViewById(R.id.btnEliminar);
        button_Actualizar = (Button)findViewById(R.id.btnActualizar);


        ActualizarDatos();
        EliminarDatos();
        verTodo();
        AgregarDatos();
    }

    private void ActualizarDatos() {
        button_Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editActualizarId.getText().toString();
                boolean ADS = conexdb.ActualizarDatos(id,editActualizarComentarios.getText().toString(),editActualizarAves.getText().toString());
                if (editActualizarId.getText().toString().isEmpty()){
                    editActualizarId.setError("Error.. Ingrese un id");
                    editActualizarId.requestFocus();
                }
                else
                {
                    if(ADS)
                    {
                        Toast.makeText(FichaUsuarios.this, "Actulizado", Toast.LENGTH_SHORT).show();
                        editActualizarId.setText("");
                        editActualizarAves.setText("");
                        editActualizarComentarios.setText("");
                    }
                    else
                    {
                        Toast.makeText(FichaUsuarios.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void EliminarDatos (){

        button_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString();
                Integer ED = conexdb.EliminarDatos(id);
                if (ED > 0){
                    Toast.makeText(FichaUsuarios.this, "Eliminar",Toast.LENGTH_SHORT).show();
                    editId.setText("");
                }else{
                    if (editId.getText().toString().isEmpty()){
                        editId.setError("Ingrese su id, por favor");
                        editId.requestFocus();
                    }
                }
            }
        });
    }

    private void verTodo() {
        button_Ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor ver = conexdb.verTodo();
                if(ver.getCount()==0)
                {
                    MostrarMensajes("Nop","No se encontro nada");
                }
                StringBuffer buffer=new StringBuffer();
                while (ver.moveToNext())
                {
                    buffer.append("Id: "+ver.getString(0)+"\n");
                    buffer.append("Ave: "+ver.getString(1)+"\n");
                    buffer.append("Comentario: "+ver.getString(2)+"\n");


                }
                MostrarMensajes("Datos",buffer.toString());
            }
        });
    }

    private void AgregarDatos() {
        button_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Nombres = editAves.getText().toString();
                String Celular = editComentarios.getText().toString();

                if(validation())
                {
                    boolean AGGD=conexdb.AgregarDatos(Nombres,Celular);
                    if(AGGD)
                    {
                        Toast.makeText(getApplicationContext(),"Datos Ingresados",Toast.LENGTH_SHORT).show();
                        editAves.setText("");
                        editComentarios.setText("");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Datos no ingresados",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void MostrarMensajes(String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public boolean validation()
    {
        if(editAves.getText().toString().isEmpty())
        {
            editAves.setError("Ingrese sus  Nombres");
            editAves.requestFocus();
            return false;
        }
        if(editComentarios.getText().toString().isEmpty())
        {
            editComentarios.setError("Celular no ingresado");
            editComentarios.requestFocus();
            return false;
        }
        else
        {
            return true;
        }
    }
}
