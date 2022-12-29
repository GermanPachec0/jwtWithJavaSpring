package com.example.demo.dao;

import com.example.demo.models.Usuario;

import java.util.List;
public interface UsuarioDao {

    List<Usuario> getUsuarios();


    Usuario eliminar(Long id);

    Usuario registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
