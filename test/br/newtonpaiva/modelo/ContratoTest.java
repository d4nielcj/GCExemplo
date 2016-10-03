/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excecoes.ContratoInvalidoException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tarle
 */
public class ContratoTest {
    
    public ContratoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // Teste com quantidade de horas semanais maior que 30
    @Test(expected = ContratoInvalidoException.class)
    public void testeMaisQue30HorasSemanais() throws ContratoInvalidoException, SQLException{
        Contrato c = new Contrato();        
        c.setValorCargaHorariaSemanal(new BigDecimal("31.0"));
        c.salvar();
    }
    // Teste com quantidade de horas semanais menor que 30
    @Test
    public void testeMenosQue30HorasSemanais() throws SQLException {
        Contrato c = new Contrato();
        c.setValorCargaHorariaSemanal(new BigDecimal("29.9"));
        try{
            c.salvar();
        }catch(ContratoInvalidoException ex) {
            fail(ex.getMessage());
        }
    }
    
    // Teste com quantidade de horas semanais igual a 30
     @Test
    public void testeIgual30HorasSemanais() throws SQLException {
        Contrato c = new Contrato();
        c.setValorCargaHorariaSemanal(new BigDecimal("30.0"));
        try{
            c.salvar();
        }catch(ContratoInvalidoException e) {
            fail();
        }
    }
}
