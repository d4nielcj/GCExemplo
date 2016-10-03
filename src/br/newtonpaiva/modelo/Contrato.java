/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excecoes.ContratoInvalidoException;
import br.newtonpaiva.util.BdUtil;
import static br.newtonpaiva.util.ConfigurationManager.DB_SENHA;
import static br.newtonpaiva.util.ConfigurationManager.DB_URL;
import static br.newtonpaiva.util.ConfigurationManager.DB_USUARIO;
import static br.newtonpaiva.util.ConfigurationManager.USUARIO_INSERT;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import static br.newtonpaiva.util.BdUtil.*;

/**
 *
 * @author tarle
 */
public class Contrato {
    private Integer id;
    private Aluno aluno;
    private TipoContrato tipo;
    private Entidade entidade;
    private Situacao situacaoAtual;
    private String protocolo;
    private Calendar dataEntrada;
    private Calendar dataInicio;
    private Calendar dataTermino;
    private Calendar dataRescisao;
    private BigDecimal valorBolsa;
    private BigDecimal valorCargaHorariaDiaria;
    private BigDecimal valorCargaHorariaSemanal;
    private String observacao;
    List<TermoAditivo> aditivos;
    private Documento documento;

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
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the tipo
     */
    public TipoContrato getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoContrato tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the entidade
     */
    public Entidade getEntidade() {
        return entidade;
    }

    /**
     * @param entidade the entidade to set
     */
    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    /**
     * @return the situacaoAtual
     */
    public Situacao getSituacaoAtual() {
        return situacaoAtual;
    }

    /**
     * @param situacaoAtual the situacaoAtual to set
     */
    public void setSituacaoAtual(Situacao situacaoAtual) {
        this.situacaoAtual = situacaoAtual;
    }

    /**
     * @return the protocolo
     */
    public String getProtocolo() {
        return protocolo;
    }

    /**
     * @param protocolo the protocolo to set
     */
    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    /**
     * @return the dataEntrada
     */
    public Calendar getDataEntrada() {
        return dataEntrada;
    }

    /**
     * @param dataEntrada the dataEntrada to set
     */
    public void setDataEntrada(Calendar dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     * @return the dataInicio
     */
    public Calendar getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataTermino
     */
    public Calendar getDataTermino() {
        return dataTermino;
    }

    /**
     * @param dataTermino the dataTermino to set
     */
    public void setDataTermino(Calendar dataTermino) {
        this.dataTermino = dataTermino;
    }

    /**
     * @return the dataRescisao
     */
    public Calendar getDataRescisao() {
        return dataRescisao;
    }

    /**
     * @param dataRescisao the dataRescisao to set
     */
    public void setDataRescisao(Calendar dataRescisao) {
        this.dataRescisao = dataRescisao;
    }

    /**
     * @return the valorBolsa
     */
    public BigDecimal getValorBolsa() {
        return valorBolsa;
    }

    /**
     * @param valorBolsa the valorBolsa to set
     */
    public void setValorBolsa(BigDecimal valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    /**
     * @return the valorCargaHorariaDiaria
     */
    public BigDecimal getValorCargaHorariaDiaria() {
        return valorCargaHorariaDiaria;
    }

    /**
     * @param valorCargaHorariaDiaria the valorCargaHorariaDiaria to set
     */
    public void setValorCargaHorariaDiaria(BigDecimal valorCargaHorariaDiaria) {
        this.valorCargaHorariaDiaria = valorCargaHorariaDiaria;
    }

    /**
     * @return the valorCargaHorariaSemanal
     */
    public BigDecimal getValorCargaHorariaSemanal() {
        return valorCargaHorariaSemanal;
    }

    /**
     * @param valorCargaHorariaSemanal the valorCargaHorariaSemanal to set
     */
    public void setValorCargaHorariaSemanal(BigDecimal valorCargaHorariaSemanal) {
        this.valorCargaHorariaSemanal = valorCargaHorariaSemanal;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Contrato{" + "id=" + id + ", aluno=" + aluno + ", tipo=" + tipo + ", entidade=" + entidade + ", situacaoAtual=" + situacaoAtual + ", protocolo=" + protocolo + ", dataEntrada=" + dataEntrada + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + ", dataRescisao=" + dataRescisao + ", valorBolsa=" + valorBolsa + ", valorCargaHorariaDiaria=" + valorCargaHorariaDiaria + ", valorCargaHorariaSemanal=" + valorCargaHorariaSemanal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.protocolo);
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
        final Contrato other = (Contrato) obj;
        if (!Objects.equals(this.protocolo, other.protocolo)) {
            return false;
        }
        return true;
    }

    /**
     * @return the documento
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
    
    public void salvar() throws ContratoInvalidoException, SQLException {
        if(valorCargaHorariaSemanal.doubleValue() > 30.0){
            // TODO Tarley gerar uma exception neste ponto
            throw new ContratoInvalidoException("Carga horária acima do limite permitido. Limite: 30 horas.");
        }
        
        // Salvar contrato
        
        try (Connection con = DriverManager.getConnection(
                DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        USUARIO_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            
                        
            
            stm.setDate(1, converter(getDataEntrada()));
            
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(1));
            } else {
                throw new SQLException("Não foi possivel inserir o usuário");
            }
        }
    }
}
