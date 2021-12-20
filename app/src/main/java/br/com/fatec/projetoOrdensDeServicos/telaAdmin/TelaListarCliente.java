package br.com.fatec.projetoOrdensDeServicos.telaAdmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.fatec.projetoOrdensDeServicos.R;
import br.com.fatec.projetoOrdensDeServicos.fragment.SelecionarStatusClienteFragment;
import br.com.fatec.projetoOrdensDeServicos.fragment.TodosClientesFragment;

public class TelaListarCliente extends AppCompatActivity {
    BottomNavigationView bNVStatusConta;
    Bundle statusConta = new Bundle();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);
        bNVStatusConta = findViewById(R.id.bNVStatusConta);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container_recyclerview, new TodosClientesFragment()).commit();
        }
        bNVStatusConta.setOnItemSelectedListener(item -> {
            Fragment selecionarfragmento = null;
            switch (item.getItemId()) {
                case R.id.itmTodos:
                    selecionarfragmento = new TodosClientesFragment();
                    break;
                case R.id.itmDesbloqueado:
                    statusConta.putString("statusConta", "Desbloqueado");
                    selecionarfragmento = new SelecionarStatusClienteFragment();
                    selecionarfragmento.setArguments(statusConta);
                    break;
                case R.id.itmBloqueado:
                    statusConta.putString("statusConta", "Bloqueado");
                    selecionarfragmento = new SelecionarStatusClienteFragment();
                    selecionarfragmento.setArguments(statusConta);
                    break;
            }
            assert selecionarfragmento != null;
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container_recyclerview, selecionarfragmento).commit();
            return true;
        });
    }
}