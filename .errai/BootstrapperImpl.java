package org.jboss.errai.ioc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.event.dom.client.HasAllDragAndDropHandlers;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.HasAllGestureHandlers;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasAllTouchHandlers;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;
import com.google.gwt.event.dom.client.HasDragEndHandlers;
import com.google.gwt.event.dom.client.HasDragEnterHandlers;
import com.google.gwt.event.dom.client.HasDragHandlers;
import com.google.gwt.event.dom.client.HasDragLeaveHandlers;
import com.google.gwt.event.dom.client.HasDragOverHandlers;
import com.google.gwt.event.dom.client.HasDragStartHandlers;
import com.google.gwt.event.dom.client.HasDropHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.HasGestureChangeHandlers;
import com.google.gwt.event.dom.client.HasGestureEndHandlers;
import com.google.gwt.event.dom.client.HasGestureStartHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.HasMouseWheelHandlers;
import com.google.gwt.event.dom.client.HasTouchCancelHandlers;
import com.google.gwt.event.dom.client.HasTouchEndHandlers;
import com.google.gwt.event.dom.client.HasTouchMoveHandlers;
import com.google.gwt.event.dom.client.HasTouchStartHandlers;
import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.i18n.client.AutoDirectionHandler.Target;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasAutoHorizontalAlignment;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.user.client.ui.HasDirectionalText;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWordWrap;
import com.google.gwt.user.client.ui.IsRenderable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LabelBase;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.SourcesFocusEvents;
import com.google.gwt.user.client.ui.SourcesKeyboardEvents;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import javax.inject.Provider;
import org.jboss.errai.common.client.api.extension.InitVotes;
import org.jboss.errai.databinding.client.DataBinderProvider;
import org.jboss.errai.databinding.client.DataBindingModuleBootstrapper;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.enterprise.client.cdi.CDIEventTypeLookup;
import org.jboss.errai.enterprise.client.cdi.EventProvider;
import org.jboss.errai.enterprise.client.cdi.InstanceProvider;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.ioc.client.api.ContextualTypeProvider;
import org.jboss.errai.ioc.client.api.builtin.CallerProvider;
import org.jboss.errai.ioc.client.api.builtin.DisposerProvider;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.api.builtin.InitBallotProvider;
import org.jboss.errai.ioc.client.api.builtin.RootPanelProvider;
import org.jboss.errai.ioc.client.container.BeanProvider;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.DestructionCallback;
import org.jboss.errai.ioc.client.container.InitializationCallback;
import org.jboss.errai.ioc.client.container.SimpleCreationalContext;
import org.jboss.errai.ioc.client.container.SyncBeanManager;
import org.jboss.errai.ioc.client.lifecycle.api.Access;
import org.jboss.errai.ioc.client.lifecycle.api.Creation;
import org.jboss.errai.ioc.client.lifecycle.api.Destruction;
import org.jboss.errai.ioc.client.lifecycle.api.LifecycleEvent;
import org.jboss.errai.ioc.client.lifecycle.api.LifecycleListenerRegistrar;
import org.jboss.errai.ioc.client.lifecycle.api.StateChange;
import org.jboss.errai.ioc.client.lifecycle.impl.AccessImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.CreationImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.DestructionImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.LifecycleEventImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.LifecycleListenerRegistrarImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.StateChangeImpl;
import org.jboss.errai.ioc.support.bus.client.BatchCallerProvider;
import org.jboss.errai.ioc.support.bus.client.MessageBusProvider;
import org.jboss.errai.ioc.support.bus.client.RequestDispatcherProvider;
import org.jboss.errai.ioc.support.bus.client.SenderProvider;
import org.jboss.errai.ui.client.local.spi.LessStyle;
import org.jboss.errai.ui.client.local.spi.TranslationServiceProvider;
import org.jboss.errai.ui.client.widget.ListWidgetProvider;
import org.jboss.errai.ui.client.widget.LocaleListBox;
import org.jboss.errai.ui.client.widget.LocaleSelector;
import org.yournamehere.client.Customer;
import org.yournamehere.client.MainEntryPoint;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new DataBindingModuleBootstrapper().run();
  }

  private final SimpleInjectionContext injContext = new SimpleInjectionContext();
  private final SimpleCreationalContext context = injContext.getRootContext();
  private final BeanProvider<TranslationServiceProvider> inj1915_TranslationServiceProvider_creational = new BeanProvider<TranslationServiceProvider>() {
    public TranslationServiceProvider getInstance(final CreationalContext context) {
      final TranslationServiceProvider inj1892_TranslationServiceProvider = new TranslationServiceProvider();
      context.addBean(context.getBeanReference(TranslationServiceProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1892_TranslationServiceProvider);
      return inj1892_TranslationServiceProvider;
    }
  };
  private final TranslationServiceProvider inj1892_TranslationServiceProvider = inj1915_TranslationServiceProvider_creational.getInstance(context);
  private final BeanProvider<DataBinderProvider> inj1916_DataBinderProvider_creational = new BeanProvider<DataBinderProvider>() {
    public DataBinderProvider getInstance(final CreationalContext context) {
      final DataBinderProvider inj1908_DataBinderProvider = new DataBinderProvider();
      context.addBean(context.getBeanReference(DataBinderProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1908_DataBinderProvider);
      return inj1908_DataBinderProvider;
    }
  };
  private final DataBinderProvider inj1908_DataBinderProvider = inj1916_DataBinderProvider_creational.getInstance(context);
  private final BeanProvider<InstanceProvider> inj1917_InstanceProvider_creational = new BeanProvider<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      final InstanceProvider inj1912_InstanceProvider = new InstanceProvider();
      context.addBean(context.getBeanReference(InstanceProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1912_InstanceProvider);
      return inj1912_InstanceProvider;
    }
  };
  private final InstanceProvider inj1912_InstanceProvider = inj1917_InstanceProvider_creational.getInstance(context);
  private final BeanProvider<LocaleSelector> inj1919_LocaleSelector_creational = new BeanProvider<LocaleSelector>() {
    public LocaleSelector getInstance(final CreationalContext context) {
      final LocaleSelector inj1918_LocaleSelector = new LocaleSelector();
      context.addBean(context.getBeanReference(LocaleSelector.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1918_LocaleSelector);
      return inj1918_LocaleSelector;
    }
  };
  private final LocaleSelector inj1918_LocaleSelector = inj1919_LocaleSelector_creational.getInstance(context);
  private final BeanProvider<EventProvider> inj1920_EventProvider_creational = new BeanProvider<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      final EventProvider inj1896_EventProvider = new EventProvider();
      context.addBean(context.getBeanReference(EventProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1896_EventProvider);
      return inj1896_EventProvider;
    }
  };
  private final EventProvider inj1896_EventProvider = inj1920_EventProvider_creational.getInstance(context);
  private final BeanProvider<SenderProvider> inj1921_SenderProvider_creational = new BeanProvider<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      final SenderProvider inj1910_SenderProvider = new SenderProvider();
      context.addBean(context.getBeanReference(SenderProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1910_SenderProvider);
      return inj1910_SenderProvider;
    }
  };
  private final SenderProvider inj1910_SenderProvider = inj1921_SenderProvider_creational.getInstance(context);
  private final BeanProvider<IOCBeanManagerProvider> inj1922_IOCBeanManagerProvider_creational = new BeanProvider<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      final IOCBeanManagerProvider inj1906_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      context.addBean(context.getBeanReference(IOCBeanManagerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1906_IOCBeanManagerProvider);
      return inj1906_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1906_IOCBeanManagerProvider = inj1922_IOCBeanManagerProvider_creational.getInstance(context);
  private final BeanProvider<CallerProvider> inj1923_CallerProvider_creational = new BeanProvider<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      final CallerProvider inj1900_CallerProvider = new CallerProvider();
      context.addBean(context.getBeanReference(CallerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1900_CallerProvider);
      return inj1900_CallerProvider;
    }
  };
  private final CallerProvider inj1900_CallerProvider = inj1923_CallerProvider_creational.getInstance(context);
  private final BeanProvider<Label> inj1926_Label_creational = new BeanProvider<Label>() {
    public Label getInstance(final CreationalContext context) {
      final Label inj1016_Label = new Label();
      context.addBean(context.getBeanReference(Label.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1016_Label);
      return inj1016_Label;
    }
  };
  private final BeanProvider<TextBox> inj1927_TextBox_creational = new BeanProvider<TextBox>() {
    public TextBox getInstance(final CreationalContext context) {
      final TextBox inj567_TextBox = new TextBox();
      context.addBean(context.getBeanReference(TextBox.class, QualifierUtil.DEFAULT_QUALIFIERS), inj567_TextBox);
      return inj567_TextBox;
    }
  };
  private InitializationCallback<MainEntryPoint> init_inj1924_MainEntryPoint = new InitializationCallback<MainEntryPoint>() {
    public void init(final MainEntryPoint obj) {
      _$704656636_init(obj);
    }
  };
  private final BeanProvider<MainEntryPoint> inj1925_MainEntryPoint_creational = new BeanProvider<MainEntryPoint>() {
    public MainEntryPoint getInstance(final CreationalContext context) {
      final MainEntryPoint inj1924_MainEntryPoint = new MainEntryPoint();
      context.addBean(context.getBeanReference(MainEntryPoint.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1924_MainEntryPoint);
      final DataBinder<Customer> var1 = DataBinder.forType(Customer.class);
      final DataBinder var2 = var1;
      _$704656636__$1775465013_customer(inj1924_MainEntryPoint, var1.getModel());
      _$704656636__$483175978_dataBinder(inj1924_MainEntryPoint, inj1908_DataBinderProvider.provide(new Class[] { Customer.class }, null));
      _$704656636__$1862661780_nameLabel(inj1924_MainEntryPoint, inj1926_Label_creational.getInstance(context));
      _$704656636__$371269162_nameTextBox(inj1924_MainEntryPoint, inj1927_TextBox_creational.getInstance(context));
      context.addInitializationCallback(inj1924_MainEntryPoint, init_inj1924_MainEntryPoint);
      context.addInitializationCallback(inj1924_MainEntryPoint, new InitializationCallback<MainEntryPoint>() {
        public void init(final MainEntryPoint obj) {
          DataBinder binder = var2;
          if (binder == null) {
            throw new RuntimeException("@AutoBound data binder for class org.yournamehere.client.MainEntryPoint has not been initialized. Either initialize or add @Inject!");
          }
          binder.bind(_$704656636__$1862661780_nameLabel(inj1924_MainEntryPoint), "nom", null);
          binder.bind(_$704656636__$371269162_nameTextBox(inj1924_MainEntryPoint), "nom", null);
        }
      });
      context.addDestructionCallback(inj1924_MainEntryPoint, new DestructionCallback<MainEntryPoint>() {
        public void destroy(final MainEntryPoint obj) {
          var2.unbind();
        }
      });
      return inj1924_MainEntryPoint;
    }
  };
  private final BeanProvider<StateChangeImpl> inj1929_StateChangeImpl_creational = new BeanProvider<StateChangeImpl>() {
    public StateChangeImpl getInstance(final CreationalContext context) {
      final StateChangeImpl inj1928_StateChangeImpl = new StateChangeImpl();
      context.addBean(context.getBeanReference(StateChangeImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1928_StateChangeImpl);
      return inj1928_StateChangeImpl;
    }
  };
  private final BeanProvider<InitBallotProvider> inj1930_InitBallotProvider_creational = new BeanProvider<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      final InitBallotProvider inj1894_InitBallotProvider = new InitBallotProvider();
      context.addBean(context.getBeanReference(InitBallotProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1894_InitBallotProvider);
      return inj1894_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1894_InitBallotProvider = inj1930_InitBallotProvider_creational.getInstance(context);
  private final BeanProvider<MessageBusProvider> inj1931_MessageBusProvider_creational = new BeanProvider<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      final MessageBusProvider inj1888_MessageBusProvider = new MessageBusProvider();
      context.addBean(context.getBeanReference(MessageBusProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1888_MessageBusProvider);
      return inj1888_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1888_MessageBusProvider = inj1931_MessageBusProvider_creational.getInstance(context);
  private final BeanProvider<RequestDispatcherProvider> inj1932_RequestDispatcherProvider_creational = new BeanProvider<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      final RequestDispatcherProvider inj1904_RequestDispatcherProvider = new RequestDispatcherProvider();
      context.addBean(context.getBeanReference(RequestDispatcherProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1904_RequestDispatcherProvider);
      return inj1904_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1904_RequestDispatcherProvider = inj1932_RequestDispatcherProvider_creational.getInstance(context);
  private final BeanProvider<DestructionImpl> inj1934_DestructionImpl_creational = new BeanProvider<DestructionImpl>() {
    public DestructionImpl getInstance(final CreationalContext context) {
      final DestructionImpl inj1933_DestructionImpl = new DestructionImpl();
      context.addBean(context.getBeanReference(DestructionImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1933_DestructionImpl);
      return inj1933_DestructionImpl;
    }
  };
  private final BeanProvider<RootPanelProvider> inj1935_RootPanelProvider_creational = new BeanProvider<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      final RootPanelProvider inj1890_RootPanelProvider = new RootPanelProvider();
      context.addBean(context.getBeanReference(RootPanelProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1890_RootPanelProvider);
      return inj1890_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1890_RootPanelProvider = inj1935_RootPanelProvider_creational.getInstance(context);
  private final BeanProvider<LifecycleListenerRegistrarImpl> inj1937_LifecycleListenerRegistrarImpl_creational = new BeanProvider<LifecycleListenerRegistrarImpl>() {
    public LifecycleListenerRegistrarImpl getInstance(final CreationalContext context) {
      final LifecycleListenerRegistrarImpl inj1936_LifecycleListenerRegistrarImpl = new LifecycleListenerRegistrarImpl();
      context.addBean(context.getBeanReference(LifecycleListenerRegistrarImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1936_LifecycleListenerRegistrarImpl);
      return inj1936_LifecycleListenerRegistrarImpl;
    }
  };
  private final LifecycleListenerRegistrarImpl inj1936_LifecycleListenerRegistrarImpl = inj1937_LifecycleListenerRegistrarImpl_creational.getInstance(context);
  private final BeanProvider<ListWidgetProvider> inj1938_ListWidgetProvider_creational = new BeanProvider<ListWidgetProvider>() {
    public ListWidgetProvider getInstance(final CreationalContext context) {
      final ListWidgetProvider inj1898_ListWidgetProvider = new ListWidgetProvider();
      context.addBean(context.getBeanReference(ListWidgetProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1898_ListWidgetProvider);
      return inj1898_ListWidgetProvider;
    }
  };
  private final ListWidgetProvider inj1898_ListWidgetProvider = inj1938_ListWidgetProvider_creational.getInstance(context);
  private final BeanProvider<LocaleListBox> inj1940_LocaleListBox_creational = new BeanProvider<LocaleListBox>() {
    public LocaleListBox getInstance(final CreationalContext context) {
      final LocaleListBox inj1939_LocaleListBox = new LocaleListBox();
      context.addBean(context.getBeanReference(LocaleListBox.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1939_LocaleListBox);
      _1350680564__$1232121576_selector(inj1939_LocaleListBox, inj1918_LocaleSelector);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          inj1939_LocaleListBox.init();
        }
      });
      return inj1939_LocaleListBox;
    }
  };
  private InitializationCallback<LessStyle> init_inj1941_LessStyle = new InitializationCallback<LessStyle>() {
    public void init(final LessStyle obj) {
      obj.init();
    }
  };
  private final BeanProvider<LessStyle> inj1942_LessStyle_creational = new BeanProvider<LessStyle>() {
    public LessStyle getInstance(final CreationalContext context) {
      final LessStyle inj1941_LessStyle = new LessStyle();
      context.addBean(context.getBeanReference(LessStyle.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1941_LessStyle);
      context.addInitializationCallback(inj1941_LessStyle, init_inj1941_LessStyle);
      return inj1941_LessStyle;
    }
  };
  private final LessStyle inj1941_LessStyle = inj1942_LessStyle_creational.getInstance(context);
  private final BeanProvider<DisposerProvider> inj1943_DisposerProvider_creational = new BeanProvider<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      final DisposerProvider inj1902_DisposerProvider = new DisposerProvider();
      context.addBean(context.getBeanReference(DisposerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1902_DisposerProvider);
      _$1300398733__$652658075_beanManager(inj1902_DisposerProvider, inj1906_IOCBeanManagerProvider.get());
      return inj1902_DisposerProvider;
    }
  };
  private final DisposerProvider inj1902_DisposerProvider = inj1943_DisposerProvider_creational.getInstance(context);
  private final BeanProvider<BatchCallerProvider> inj1944_BatchCallerProvider_creational = new BeanProvider<BatchCallerProvider>() {
    public BatchCallerProvider getInstance(final CreationalContext context) {
      final BatchCallerProvider inj1914_BatchCallerProvider = new BatchCallerProvider();
      context.addBean(context.getBeanReference(BatchCallerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1914_BatchCallerProvider);
      return inj1914_BatchCallerProvider;
    }
  };
  private final BatchCallerProvider inj1914_BatchCallerProvider = inj1944_BatchCallerProvider_creational.getInstance(context);
  private final BeanProvider<CreationImpl> inj1946_CreationImpl_creational = new BeanProvider<CreationImpl>() {
    public CreationImpl getInstance(final CreationalContext context) {
      final CreationImpl inj1945_CreationImpl = new CreationImpl();
      context.addBean(context.getBeanReference(CreationImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1945_CreationImpl);
      return inj1945_CreationImpl;
    }
  };
  private final BeanProvider<AccessImpl> inj1948_AccessImpl_creational = new BeanProvider<AccessImpl>() {
    public AccessImpl getInstance(final CreationalContext context) {
      final AccessImpl inj1947_AccessImpl = new AccessImpl();
      context.addBean(context.getBeanReference(AccessImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1947_AccessImpl);
      return inj1947_AccessImpl;
    }
  };
  private void declareBeans_0() {
    injContext.addBean(TranslationServiceProvider.class, TranslationServiceProvider.class, inj1915_TranslationServiceProvider_creational, inj1892_TranslationServiceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, TranslationServiceProvider.class, inj1915_TranslationServiceProvider_creational, inj1892_TranslationServiceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(DataBinderProvider.class, DataBinderProvider.class, inj1916_DataBinderProvider_creational, inj1908_DataBinderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, DataBinderProvider.class, inj1916_DataBinderProvider_creational, inj1908_DataBinderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1917_InstanceProvider_creational, inj1912_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1917_InstanceProvider_creational, inj1912_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LocaleSelector.class, LocaleSelector.class, inj1919_LocaleSelector_creational, inj1918_LocaleSelector, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(EventProvider.class, EventProvider.class, inj1920_EventProvider_creational, inj1896_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1920_EventProvider_creational, inj1896_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj1921_SenderProvider_creational, inj1910_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj1921_SenderProvider_creational, inj1910_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1922_IOCBeanManagerProvider_creational, inj1906_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1922_IOCBeanManagerProvider_creational, inj1906_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj1923_CallerProvider_creational, inj1900_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj1923_CallerProvider_creational, inj1900_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Label.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(HasDirectionalText.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasText.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDirection.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasClickHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDoubleClickHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SourcesClickEvents.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SourcesMouseEvents.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAllDragAndDropHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragEndHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragEnterHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragLeaveHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragOverHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragStartHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDropHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAllGestureHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasGestureStartHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasGestureChangeHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasGestureEndHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAllMouseHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseDownHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseUpHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseOutHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseOverHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseMoveHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseWheelHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAllTouchHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasTouchStartHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasTouchMoveHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasTouchEndHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasTouchCancelHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IsEditor.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LabelBase.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasWordWrap.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDirectionEstimator.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAutoHorizontalAlignment.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasHorizontalAlignment.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Widget.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(EventListener.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAttachHandlers.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IsWidget.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(UIObject.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasVisibility.class, Label.class, inj1926_Label_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(TextBox.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(TextBoxBase.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SourcesChangeEvents.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(ValueBoxBase.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasChangeHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasName.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDirectionEstimator.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasValue.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(TakesValue.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasValueChangeHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasText.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Target.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDirection.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasKeyUpHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IsEditor.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(FocusWidget.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SourcesClickEvents.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasClickHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDoubleClickHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasFocus.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Focusable.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SourcesFocusEvents.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SourcesKeyboardEvents.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasEnabled.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAllDragAndDropHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragEndHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragEnterHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragLeaveHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragOverHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDragStartHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasDropHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAllFocusHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasFocusHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasBlurHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAllGestureHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasGestureStartHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasGestureChangeHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasGestureEndHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAllKeyHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasKeyDownHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasKeyPressHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAllMouseHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseDownHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseUpHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseOutHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseOverHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseMoveHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasMouseWheelHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAllTouchHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasTouchStartHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasTouchMoveHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasTouchEndHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasTouchCancelHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SourcesMouseEvents.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Widget.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(EventListener.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAttachHandlers.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IsWidget.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(UIObject.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasVisibility.class, TextBox.class, inj1927_TextBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(MainEntryPoint.class, MainEntryPoint.class, inj1925_MainEntryPoint_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(EntryPoint.class, MainEntryPoint.class, inj1925_MainEntryPoint_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(StateChangeImpl.class, StateChangeImpl.class, inj1929_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(StateChange.class, StateChangeImpl.class, inj1929_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, StateChangeImpl.class, inj1929_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, StateChangeImpl.class, inj1929_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, StateChangeImpl.class, inj1929_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj1930_InitBallotProvider_creational, inj1894_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj1930_InitBallotProvider_creational, inj1894_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1931_MessageBusProvider_creational, inj1888_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1931_MessageBusProvider_creational, inj1888_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1932_RequestDispatcherProvider_creational, inj1904_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1932_RequestDispatcherProvider_creational, inj1904_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(DestructionImpl.class, DestructionImpl.class, inj1934_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Destruction.class, DestructionImpl.class, inj1934_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, DestructionImpl.class, inj1934_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, DestructionImpl.class, inj1934_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, DestructionImpl.class, inj1934_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj1935_RootPanelProvider_creational, inj1890_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj1935_RootPanelProvider_creational, inj1890_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleListenerRegistrarImpl.class, LifecycleListenerRegistrarImpl.class, inj1937_LifecycleListenerRegistrarImpl_creational, inj1936_LifecycleListenerRegistrarImpl, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(LifecycleListenerRegistrar.class, LifecycleListenerRegistrarImpl.class, inj1937_LifecycleListenerRegistrarImpl_creational, inj1936_LifecycleListenerRegistrarImpl, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(ListWidgetProvider.class, ListWidgetProvider.class, inj1938_ListWidgetProvider_creational, inj1898_ListWidgetProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, ListWidgetProvider.class, inj1938_ListWidgetProvider_creational, inj1898_ListWidgetProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LocaleListBox.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ValueListBox.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Focusable.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasConstrainedValue.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasValue.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(TakesValue.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasValueChangeHandlers.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasHandlers.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasEnabled.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IsEditor.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Composite.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IsRenderable.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Widget.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(EventListener.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAttachHandlers.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IsWidget.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(UIObject.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasVisibility.class, LocaleListBox.class, inj1940_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LessStyle.class, LessStyle.class, inj1942_LessStyle_creational, inj1941_LessStyle, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj1943_DisposerProvider_creational, inj1902_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj1943_DisposerProvider_creational, inj1902_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(BatchCallerProvider.class, BatchCallerProvider.class, inj1944_BatchCallerProvider_creational, inj1914_BatchCallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, BatchCallerProvider.class, inj1944_BatchCallerProvider_creational, inj1914_BatchCallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(CreationImpl.class, CreationImpl.class, inj1946_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Creation.class, CreationImpl.class, inj1946_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, CreationImpl.class, inj1946_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, CreationImpl.class, inj1946_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, CreationImpl.class, inj1946_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(AccessImpl.class, AccessImpl.class, inj1948_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Access.class, AccessImpl.class, inj1948_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, AccessImpl.class, inj1948_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, AccessImpl.class, inj1948_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, AccessImpl.class, inj1948_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
  }

  private native static void _1350680564__$1232121576_selector(LocaleListBox instance, LocaleSelector value) /*-{
    instance.@org.jboss.errai.ui.client.widget.LocaleListBox::selector = value;
  }-*/;

  private native static void _$1300398733__$652658075_beanManager(DisposerProvider instance, SyncBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static TextBox _$704656636__$371269162_nameTextBox(MainEntryPoint instance) /*-{
    return instance.@org.yournamehere.client.MainEntryPoint::nameTextBox;
  }-*/;

  private native static void _$704656636__$371269162_nameTextBox(MainEntryPoint instance, TextBox value) /*-{
    instance.@org.yournamehere.client.MainEntryPoint::nameTextBox = value;
  }-*/;

  private native static Label _$704656636__$1862661780_nameLabel(MainEntryPoint instance) /*-{
    return instance.@org.yournamehere.client.MainEntryPoint::nameLabel;
  }-*/;

  private native static void _$704656636__$1862661780_nameLabel(MainEntryPoint instance, Label value) /*-{
    instance.@org.yournamehere.client.MainEntryPoint::nameLabel = value;
  }-*/;

  private native static void _$704656636__$483175978_dataBinder(MainEntryPoint instance, DataBinder<Customer> value) /*-{
    instance.@org.yournamehere.client.MainEntryPoint::dataBinder = value;
  }-*/;

  private native static Customer _$704656636__$1775465013_customer(MainEntryPoint instance) /*-{
    return instance.@org.yournamehere.client.MainEntryPoint::customer;
  }-*/;

  private native static void _$704656636__$1775465013_customer(MainEntryPoint instance, Customer value) /*-{
    instance.@org.yournamehere.client.MainEntryPoint::customer = value;
  }-*/;

  public native static void _$704656636_init(MainEntryPoint instance) /*-{
    instance.@org.yournamehere.client.MainEntryPoint::init()();
  }-*/;

  // The main IOC bootstrap method.
  public SimpleInjectionContext bootstrapContainer() {
    declareBeans_0();
    return injContext;
  }
}