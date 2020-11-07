package www.runa.Prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class USornitologo extends AppCompatActivity {

    public Button buttonAgregarAves;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_sornitologo);

        buttonAgregarAves = (Button) findViewById(R.id.btnAgregarAves);

        buttonAgregarAves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FichaUsuarios.class);
                startActivity(intent);
            }
        });
    }
}