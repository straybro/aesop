package ee.lis.driver;

import akka.actor.ActorContext;
import com.typesafe.config.Config;
import ee.lis.core.ConfMsg;
import ee.lis.core.RecipientConf;
import ee.lis.flow_component.astm_to_mylis.LIS2A2ToMyLabConverter;
import ee.lis.flow_component.astm_to_string.LIS2A2ToStringConverter;
import ee.lis.flow_component.astme138195.AstmE138195Controller;
import ee.lis.flow_component.astme138195.AstmE138195ControllerConf;
import ee.lis.flow_component.astm_to_mylis.MyLabToLIS2A2Converter;
import ee.lis.flow_component.astm_to_string.StringToLIS2A2Converter;
import ee.lis.flow_component.mylab_http.MyLabHttpClientConf;
import ee.lis.flow_component.socket.SocketClient;
import ee.lis.flow_component.socket.SocketClientConf;
import ee.lis.flow_component.socket.SocketServer;
import ee.lis.flow_component.socket.SocketServerConf;
import ee.lis.core.FlowComponent;
import java.util.function.BiFunction;
import ee.lis.flow_component.mylab_http.MyLabHttpClient;

public enum ComponentType {

    SocketClient(SocketClient.class, SocketClientConf::create),
    SocketServer(SocketServer.class, SocketServerConf::create),
    AstmE138195Controller(AstmE138195Controller.class, AstmE138195ControllerConf::create),
    LIS2A2ToMyLabConverter(LIS2A2ToMyLabConverter.class, RecipientConf::create),
    MyLabToLIS2A2Converter(MyLabToLIS2A2Converter.class, RecipientConf::create),
    LIS2A2ToStringConverter(LIS2A2ToStringConverter.class, RecipientConf::create),
    StringToLIS2A2Converter(StringToLIS2A2Converter.class, RecipientConf::create),
    MyLabHttpClient(MyLabHttpClient.class, MyLabHttpClientConf::create);

    public final Class<?> klass;
    public final BiFunction<Config, ActorContext, ConfMsg> configFuction;

    ComponentType(Class<? extends FlowComponent<?>> klass,
                          BiFunction<Config, ActorContext, ConfMsg> configFuction) {
        this.klass = klass;
        this.configFuction = configFuction;
    }
}
