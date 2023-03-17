/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1883.frameworki;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ITU
 */
public class Utilitaire {
    public String[] decomposer(HttpServletRequest request){
        String url=request.getPathInfo();  
        String[] tableau=url.split("/");
        return tableau;
    }   
}
