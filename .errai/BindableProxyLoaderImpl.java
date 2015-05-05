package org.jboss.errai.databinding.client;

import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.Locale;
import org.yournamehere.client.Customer;

public class BindableProxyLoaderImpl implements BindableProxyLoader { public void loadBindableProxies() {
    class org_yournamehere_client_CustomerProxy extends Customer implements BindableProxy {
      private BindableProxyAgent<Customer> agent;
      public org_yournamehere_client_CustomerProxy(InitialState initialState) {
        this(new Customer(), initialState);
      }

      public org_yournamehere_client_CustomerProxy(Customer target, InitialState initialState) {
        agent = new BindableProxyAgent<Customer>(this, target, initialState);
        agent.propertyTypes.put("nom", new PropertyType(String.class, false, false));
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
        clone.setNom(agent.target.getNom());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof org_yournamehere_client_CustomerProxy) {
          obj = ((org_yournamehere_client_CustomerProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public String getNom() {
        return agent.target.getNom();
      }

      public void setNom(String nom) {
        String oldValue = agent.target.getNom();
        agent.target.setNom(nom);
        agent.updateWidgetsAndFireEvent("nom", oldValue, nom);
      }

      public Object get(String property) {
        if (property.equals("nom")) {
          return getNom();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("nom")) {
          agent.target.setNom((String) value);
          return;
        }
        throw new NonExistingPropertyException(property);
      }
    }
    BindableProxyFactory.addBindableProxy(Customer.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new org_yournamehere_client_CustomerProxy((Customer) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new org_yournamehere_client_CustomerProxy(state);
      }
    });
    class org_jboss_errai_ui_shared_api_LocaleProxy extends Locale implements BindableProxy {
      private BindableProxyAgent<Locale> agent;
      public org_jboss_errai_ui_shared_api_LocaleProxy(InitialState initialState) {
        this(new Locale(), initialState);
      }

      public org_jboss_errai_ui_shared_api_LocaleProxy(Locale target, InitialState initialState) {
        agent = new BindableProxyAgent<Locale>(this, target, initialState);
        agent.propertyTypes.put("locale", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("label", new PropertyType(String.class, false, false));
        agent.copyValues();
      }

      public BindableProxyAgent getAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public Locale unwrap() {
        return agent.target;
      }

      public Locale deepUnwrap() {
        final Locale clone = new Locale();
        clone.setLocale(agent.target.getLocale());
        clone.setLabel(agent.target.getLabel());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof org_jboss_errai_ui_shared_api_LocaleProxy) {
          obj = ((org_jboss_errai_ui_shared_api_LocaleProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public String getLocale() {
        return agent.target.getLocale();
      }

      public void setLocale(String locale) {
        String oldValue = agent.target.getLocale();
        agent.target.setLocale(locale);
        agent.updateWidgetsAndFireEvent("locale", oldValue, locale);
      }

      public String getLabel() {
        return agent.target.getLabel();
      }

      public void setLabel(String label) {
        String oldValue = agent.target.getLabel();
        agent.target.setLabel(label);
        agent.updateWidgetsAndFireEvent("label", oldValue, label);
      }

      public Object get(String property) {
        if (property.equals("locale")) {
          return getLocale();
        }
        if (property.equals("label")) {
          return getLabel();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("locale")) {
          agent.target.setLocale((String) value);
          return;
        }
        if (property.equals("label")) {
          agent.target.setLabel((String) value);
          return;
        }
        throw new NonExistingPropertyException(property);
      }
    }
    BindableProxyFactory.addBindableProxy(Locale.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new org_jboss_errai_ui_shared_api_LocaleProxy((Locale) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new org_jboss_errai_ui_shared_api_LocaleProxy(state);
      }
    });
  }
}