package com.example.demo.controllers;


import com.example.demo.dao.UsuarioDao;
import com.example.demo.models.Usuario;
import com.example.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable Long id){
       Usuario usuario = new Usuario();
       usuario.setId(id);
       usuario.setApellido("Moy");
       usuario.setNombre("Moy");
       usuario.setEmail("g@gmail.com");
       usuario.setTelefono("232323");
       return usuario;

    }
    @GetMapping("asdas")
    public Usuario editarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setApellido("Moy");
        usuario.setNombre("Moy");
        usuario.setEmail("g@gmail.com");
        usuario.setTelefono("232323");
        return usuario;

    }

    @DeleteMapping("/{id}")
    public Usuario Eliminar(@RequestHeader(value = "Authorization") String token,@PathVariable Long id){
        if (!validarToken(token)){return null;}

        return usuarioDao.eliminar(id);

    }

    @GetMapping("asdasd")
    public Usuario buscar(){
        Usuario usuario = new Usuario();
        usuario.setApellido("Moy");
        usuario.setNombre("Moy");
        usuario.setEmail("g@gmail.com");
        usuario.setTelefono("232323");
        return usuario;

    }
    @GetMapping("")
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){
        if (!validarToken(token)){return null;}
        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @PostMapping("/register")
    public Usuario registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash =argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        return usuarioDao.registrar(usuario);

    }



}
