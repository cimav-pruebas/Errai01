package org.jboss.errai.databinding.client;

import java.util.Date;
import org.cimav.client.Customer;
import org.jboss.errai.databinding.client.api.InitialState;

public class BindableProxyLoaderImpl implements BindableProxyLoader { public void loadBindableProxies() {
    class org_cimav_client_CustomerProxy extends Customer implements BindableProxy {
      private BindableProxyAgent<Customer> agent;
      public org_cimav_client_CustomerProxy(InitialState initialState) {
        this(new Customer(), initialState);
      }

      public org_cimav_client_CustomerProxy(Customer target, InitialState initialState) {
        agent = new BindableProxyAgent<Customer>(this, target, initialState);
        agent.propertyTypes.put("fecha", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.copyValues();
      }

      public BindableProxyAgent getAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public Customer unwrap() {
        return agent.target;
      }

      public Customer deepUnwrap() {
        final Customer clone = new Customer();
        clone.setFecha(agent.target.getFecha());
        clone.setName(agent.target.getName());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof org_cimav_client_CustomerProxy) {
          obj = ((org_cimav_client_CustomerProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public Date getFecha() {
        return agent.target.getFecha();
      }

      public void setFecha(Date fecha) {
        Date oldValue = agent.target.getFecha();
        agent.target.setFecha(fecha);
        agent.updateWidgetsAndFireEvent("fecha", oldValue, fecha);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
      }

      public Object get(String property) {
        if (property.equals("fecha")) {
          return getFecha();
        }
        if (property.equals("name")) {
          return getName();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("fecha")) {
          agent.target.setFecha((Date) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        throw new NonExistingPropertyException(property);
      }
    }
    BindableProxyFactory.addBindableProxy(Customer.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new org_cimav_client_CustomerProxy((Customer) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new org_cimav_client_CustomerProxy(state);
      }
    });
  }
}