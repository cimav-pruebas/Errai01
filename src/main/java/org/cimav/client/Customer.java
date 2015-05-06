/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimav.client;

import java.util.Date;
import org.jboss.errai.databinding.client.api.Bindable;

/**
 *
 * @author juan.calderon
 */
@Bindable
public class Customer {
    
  private String name;
  private Date fecha;

    public Customer() {
    }
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
  
}
