/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.util.Calendar;

/**
 *
 * @author tarle
 */
public class BdUtil {
    public static java.sql.Date converter(Calendar data) {
        long timeInMillis = data.getTimeInMillis();
        return new java.sql.Date(timeInMillis);
    }
}
