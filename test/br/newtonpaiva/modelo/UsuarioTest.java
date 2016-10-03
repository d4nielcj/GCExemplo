/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excecoes.LoginException;
import br.newtonpaiva.modelo.excecoes.SenhaException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tarle
 */
public class UsuarioTest {
    
    public UsuarioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    private Usuario u;
    
    @Before
    public void setUp() throws SQLException {
        
    }
    
    @After
    public void tearDown() throws SQLException {
        
    }

    /**
     * Test of validar method, of class Usuario.
     */
    @Test
    public void testValidar() throws SQLException {
        boolean valor = Usuario.validar("admin", "123");
        Assert.assertTrue(valor);
    }
    
    @Test(expected = LoginException.class)
    public void testLoginException() throws SQLException {
        Usuario.validar("adm", "1234");
        Assert.fail();
    }
    
    @Test(expected = SenhaException.class)
    public void testSenhaException() throws SQLException {
        Usuario.validar("admin", "1234");
    }
    
    @Test
    public void testSalvar() {
        Usuario usu1 = new Usuario();
        usu1.setLogin("tarley1");
        usu1.setNome("tarley1");
        usu1.setSenha("123");
        usu1.setEmail("tarley@gmail.com1");
        
        try {
            // Inserir
            usu1.salvar();            
            Assert.assertNotNull(usu1.getId());
            
            // Atualizar
            usu1.setLogin("tarley2");
            usu1.setSenha("1234");
            usu1.setEmail("tarley.lana@gmail.com");
            usu1.salvar();
            
            Usuario usu2 = Usuario.buscarPorId(usu1.getId());
            Assert.assertEquals("tarley2", usu2.getLogin());
            Assert.assertNull(usu2.getSenha());
            Assert.assertEquals("tarley.lana@gmail.com", usu2.getEmail());
            Assert.assertEquals("tarley1", usu2.getNome());
            Assert.assertEquals(usu1.getId(), usu2.getId());
            
            
            int numLinhasExcluidas = Usuario.excluir(usu1.getId());
            Assert.assertEquals(1, numLinhasExcluidas);
        } catch (SQLException ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
