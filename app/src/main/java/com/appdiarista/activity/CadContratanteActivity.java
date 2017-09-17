package com.appdiarista.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.ContratanteDao;
import com.appdiarista.model.Contratante;
import com.appdiarista.util.BuscaEndereco;
import com.appdiarista.util.DataSelecionada;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.Calendar;

import static com.appdiarista.appdiarista.R.id.etNascimento;

public class CadContratanteActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    private  int dia,mes,ano;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText nascimento;
    private EditText cpf;
    private EditText telefone;
    private TextView tvLogitude;
    private TextView tvLatitude;
    //private EditText endereco;
    private double latitude;
    private double longitude;
    private EditText sobreMim;
    private GoogleApiClient mGoogleApiClient;
    private TextView  tvEnderecoresposta;
    private ImageButton ibEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_contratante);


        nome= (EditText)findViewById(R.id.editNome);
        email=(EditText)findViewById(R.id.editEmail);
        senha=(EditText)findViewById(R.id.editSenha);
        nascimento=(EditText)findViewById(R.id.editNascimento);
        cpf=(EditText)findViewById(R.id.editCpf);
        telefone=(EditText)findViewById(R.id.editTelefone);
        sobreMim=(EditText)findViewById(R.id.editSobreMim);
        tvEnderecoresposta=(TextView)findViewById(R.id.tvEnderecoresposta);
        ibEndereco=(ImageButton) findViewById(R.id.ibEndereco);
        callConnection();


    }


    public void cadastrar(View v){

        Contratante contratante= criarContrantanteView();
        ContratanteDao dao = new ContratanteDao(this);

        try{
            dao.inserir(contratante);
            Intent it = new Intent(this,LoginActivity.class);
            startActivity(it);
            Toast.makeText(this,"Cadastro realizado com sucesso",Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(this,"Erro ao inserir no banco de dados",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,"Ocorreu um erro",Toast.LENGTH_SHORT).show();
        }


    }

    @NonNull
    public Contratante criarContrantanteView(){
        String EtNome = nome.getText().toString();
        String EtEmail = email.getText().toString();
        String EtSenha = senha.getText().toString();
        String EtNascimento = nascimento.getText().toString();
        String EtCpf = cpf.getText().toString();
        String EtTelefone = telefone.getText().toString();

        String EtsobreMim = sobreMim.getText().toString();

        return new Contratante(EtNome,EtTelefone,EtNascimento,EtCpf,EtEmail,EtsobreMim,latitude,longitude);

    }

    private void criarDatePicker(EditText et) {
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DataSelecionada(et),ano,mes,dia);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    public void showCalendario(View v){
        criarDatePicker(nascimento);
    }

    private synchronized void callConnection(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        Location l = LocationServices
                .FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if(l != null){
            Log.i("LOG", "latitude: "+l.getLatitude());
            Log.i("LOG", "longitude: "+l.getLongitude());
            longitude = l.getLongitude();
            latitude = l.getLatitude();
        }else{
            Log.i("LOG", "coordenadas nulas");
        }

    }

    @Override
    public void onConnectionSuspended(int i) {Log.i("LOG", "onConnectionSuspended(" + i + ")");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.i("LOG", "onConnectionFailed("+connectionResult+")");

    }


    public void buscarEndereco(View v){

        ibEndereco.setVisibility(View.GONE);
        new BuscaEndereco(tvEnderecoresposta).execute(String.valueOf(latitude),String.valueOf(longitude));
    }
}
