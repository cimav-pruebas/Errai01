package org.yournamehere.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.TextBox;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.jboss.errai.databinding.client.BindableProxyLoader;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.Model;

@Dependent
public class MainEntryPoint implements EntryPoint {

    public MainEntryPoint() {
        
        // Errai Binding
        BindableProxyLoader proxyLoader = GWT.create(BindableProxyLoader.class);
        proxyLoader.loadBindableProxies();        
        
    }

    @Inject @Model private Customer customer;
    
    @Inject @AutoBound private DataBinder<Customer> dataBinder;
    @Inject @Bound(property = "nom") private Label nameLabel;
    @Inject @Bound(property = "nom") private TextBox nameTextBox;    

    
    @Override
    public void onModuleLoad() {
        
//        this.nameTextBox = new TextBox();
//        this.nameLabel = new Label("Two");
//
//        this.dataBinder = DataBinder.forType(Customer.class);
//        this.customer = dataBinder.getModel();
        
        final Label label = new Label("Hello, GWT!!!");
        final Button button = new Button("Click me!");
        
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                label.setVisible(!label.isVisible());
            }
        });
        
        
//        DataBinder<Customer> dataBinder = DataBinder.forType(Customer.class);
//        customer = dataBinder
//        .bind(nameTextBox, "name")
//        .bind(nameLabel, "name")
//        .getModel();
        
        RootPanel.get().add(button);
        RootPanel.get().add(label);
        RootPanel.get().add(nameTextBox);
        RootPanel.get().add(nameLabel);
    }
    
    @PostConstruct
    private void init() {
        GWT.log("Just Hello Injection!");
    }
    
}
