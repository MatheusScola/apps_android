package com.example.aplicativoteste.view.formcadastro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aplicativoteste.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class FormCadastro extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private Button btCadastrar;

    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        InicarComponentes();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String email = txtEmail.getText().toString();
                 String password = txtPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    InicarSnackBar(view, "Preencha todos os campos!");
                } else {
                    CadastrarUsuario(view);
                }
            }
        });

    }

    private void CadastrarUsuario(View view) {

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    InicarSnackBar(view, mensagens[1]);
                    txtEmail.setText("");
                    txtPassword.setText("");
                } else {
                    String erro;
                    try {
                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha com no mínimo 6 caracteres";

                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "Esta conta já foi cadastrada!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "E-mail inválido";

                    } catch (FirebaseNetworkException e ) {
                        erro = "Sem conexão com a internet";

                    }catch (Exception e) {
                        erro = "Erro ao cadastrar usuário";
                    }
                    InicarSnackBar(view, erro);
                }
            }
        });
    }

    private void InicarSnackBar(View view, String mensagem) {
        Snackbar snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(Color.RED);
        snackbar.show();
    }

    private void InicarComponentes() {
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        btCadastrar = findViewById(R.id.bt_cadastrar);
    }

}