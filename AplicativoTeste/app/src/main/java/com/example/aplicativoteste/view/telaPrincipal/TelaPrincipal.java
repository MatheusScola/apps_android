package com.example.aplicativoteste.view.telaPrincipal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicativoteste.R;
import com.example.aplicativoteste.view.formlogin.FormLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TelaPrincipal extends AppCompatActivity {

    private Button btDeslogar, btSalvar, btLerDados, btAtualizarDados;
    private TextView txtResultado;
    private String nome, sobrenome;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        IniciarComponentes();

        btDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaPrincipal.this, FormLogin.class);
                startActivity(intent);
                finish();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> users = new HashMap<>();
                users.put("nome","Maria");
                users.put("sobrenome","dos Santos");

                DocumentReference documentReference =db.collection("Usuários").document("Maria");
                documentReference.set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("db", "Sucesso ao salvar os dados");
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("db", "Erro ao salvar os dados" + e.toString());
                            }
                        });
            }
        });

        btLerDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AtualizarDados();
//                LerDados("Maria");
            }
        });

        btLerDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LerDados("Maria");
            }
        });

    }

    private void AtualizarDados() {

    }

    private void LerDados(String usuario) {
        db.collection("Usuários").document(usuario)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documento, @Nullable
                    FirebaseFirestoreException error) {
                        if (documento != null) {
                            nome = documento.getString("nome").toString();
                            sobrenome = documento.getString("sobrenome").toString();

                            txtResultado.setText(nome + " " + sobrenome);
                        }
                    }
                });
    }

    private void IniciarComponentes() {
        btDeslogar = findViewById(R.id.bt_deslogar);
        btSalvar = findViewById(R.id.bt_salvar_dados);
        btLerDados = findViewById(R.id.bt_ler_dados);
        btAtualizarDados = findViewById(R.id.bt_atualizar_dados);
        txtResultado = findViewById(R.id.txt_resultado);
    }

}