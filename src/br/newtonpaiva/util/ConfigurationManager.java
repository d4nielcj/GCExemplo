/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tarle
 */
public class ConfigurationManager {
    private static final Properties config = new Properties();
    
    static {
        try {
            config.loadFromXML(new FileInputStream("config.xml"));
            DB_URL = config.getProperty("db.url");
            DB_USUARIO = config.getProperty("db.usuario");
            DB_SENHA = config.getProperty("db.senha");
            
            USUARIO_INSERT = config.getProperty("usuario.insert");
            USUARIO_DELETE = config.getProperty("usuario.delete");
            USUARIO_BUSCAR_TODOS = config.getProperty("usuario.buscarTodos");
            USUARIO_BUSCAR_POR_ID = config.getProperty("usuario.buscarPorId");
            USUARIO_UPDATE = config.getProperty("usuario.update");
            USUARIO_AUTENTICAR = config.getProperty("usuario.autenticar");
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String DB_URL;
    public static String DB_USUARIO;
    public static String DB_SENHA; 
    
    /*
        SQL da entidade Usuario
    */
    public static String USUARIO_INSERT;
    public static String USUARIO_DELETE;
    public static String USUARIO_BUSCAR_TODOS;
    public static String USUARIO_BUSCAR_POR_ID;
    public static String USUARIO_UPDATE;
    public static String USUARIO_AUTENTICAR;
}









