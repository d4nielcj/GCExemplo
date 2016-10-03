/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excecoes.LoginException;
import br.newtonpaiva.modelo.excecoes.SenhaException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import static br.newtonpaiva.util.ConfigurationManager.*;
import java.util.ArrayList;

/**
 *
 * @author tarle
 */
public class Usuario {

    private Integer id;
    private String nome;
    private String login;
    private String senha;
    private String email;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Cuidado com esse campo.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", login=" + login + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }

    public void salvar() throws SQLException {
        /*
            Se o usuário não possui ID então o sistema deve inserir.
            Caso contrário, atualiza os dados do usuário.
        */
        
        if (getId() == null) {
            try (Connection con = DriverManager.getConnection(
                    DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            USUARIO_INSERT, Statement.RETURN_GENERATED_KEYS)) {

                stm.setString(1, getNome());
                stm.setString(2, getLogin());
                stm.setString(3, getSenha());
                stm.setString(4, getEmail());

                stm.executeUpdate();

                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    setId(rs.getInt(1));
                } else {
                    throw new SQLException("N�o foi possivel inserir o usu�rio");
                }
            }
        } else {
            try (Connection con = DriverManager.getConnection(
                    DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(USUARIO_UPDATE)) {

                stm.setString(1, getNome());
                stm.setString(2, getLogin());
                stm.setString(3, getSenha());
                stm.setString(4, getEmail());
                stm.setInt(5, getId());

                stm.executeUpdate();
            }
        }
    }

    public static int excluir(Integer id) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(USUARIO_DELETE);) {

            s.setInt(1, id);
            return s.executeUpdate();
        }
    }

    public static Usuario buscarPorId(Integer id) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(USUARIO_BUSCAR_POR_ID)) {

            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {

                if (r.next()) {
                    Usuario u = new Usuario();
                    u.setId(r.getInt(1));
                    u.setNome(r.getString(2));
                    u.setLogin(r.getString(3));
                    u.setEmail(r.getString(4));

                    return u;
                } else {
                    return null;
                }

            }
        }
    }

    public static List<Usuario> buscarTodos() throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(USUARIO_BUSCAR_TODOS)) {

            try (ResultSet r = s.executeQuery()) {
                List<Usuario> lista = new ArrayList<>();

                while (r.next()) {
                    Usuario u = new Usuario();
                    u.setId(r.getInt(1));
                    u.setNome(r.getString(2));
                    u.setLogin(r.getString(3));
                    u.setEmail(r.getString(4));

                    lista.add(u);
                }

                return lista;
            }
        }

    }

    public static boolean validar(String login, String senha) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(USUARIO_AUTENTICAR)) {

            s.setString(1, login);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    if(r.getString(1).equals(senha)) {
                        return true;
                    }
                    throw new SenhaException();
                } else {
                    throw new LoginException();
                }
            }
        }
    }

}
