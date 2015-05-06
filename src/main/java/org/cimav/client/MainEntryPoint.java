/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimav.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import org.jboss.errai.databinding.client.BindableProxyLoader;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.databinding.client.api.PropertyChangeEvent;
import org.jboss.errai.databinding.client.api.PropertyChangeHandler;

/**
 * Main entry point.
 *
 * @author juan.calderon
 */
public class MainEntryPoint implements EntryPoint {

    /**
     * Creates a new instance of MainEntryPoint
     */
    public MainEntryPoint() {
        this.fechaDateBox = new DateBox();
        this.nameTextBox = new TextBox(); 
        this.nameLabel = new Label();
    }

    private Customer customer;
    
    private DataBinder<Customer> dataBinder;
    private final Label nameLabel;
    private final TextBox nameTextBox;
    private final DateBox fechaDateBox;
    
    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {
        
        try {
            
            // Errai Binding - Register
            BindableProxyLoader proxyLoader = GWT.create(BindableProxyLoader.class);
            proxyLoader.loadBindableProxies();        
        
            final Label label = new Label("Hello, GWT!!!");
            final Button button = new Button("Click me!");
        
            button.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    label.setVisible(!label.isVisible());
                    customer = new Customer();
                    customer.setName("ReJuan");
                    dataBinder.setModel(customer, InitialState.FROM_MODEL, true);
                }
            });
        
            dataBinder = DataBinder.forType(Customer.class);
            customer = dataBinder
                .bind(nameTextBox, "name")
                .bind(nameLabel, "name")
                .bind(fechaDateBox, "fecha")    
                .getModel();
        
            dataBinder.addPropertyChangeHandler(new PropertyChangeHandler<Object>() {
                @Override
                public void onPropertyChange(PropertyChangeEvent<Object> event) {
                    nameLabel.setText(event.getOldValue() + " ::: " + event.getNewValue());
                }
            });
            
            RootPanel.get().add(button);
            RootPanel.get().add(label);
            RootPanel.get().add(nameTextBox);
            RootPanel.get().add(nameLabel);
            RootPanel.get().add(fechaDateBox);
        } catch (Exception e) {
            GWT.log(">>>> " + e.getMessage());
        }
        
    }
    
}
