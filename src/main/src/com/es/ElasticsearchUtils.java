package src.com.es;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class ElasticsearchUtils {
    /*客户端*/
    private final static Client client;
    /*ip地址*/
    private final static String ip="localhost";
    /*端口号*/
    private final static int port=9300;
    private final static Logger logger = LoggerFactory.getLogger(ElasticsearchUtils.class);
    static {
        // 通过setting对象指定集群配置信息, 配置的集群名
        Settings settings = Settings.settingsBuilder().put("cluster.name", "elasticsearch_wenbronk") // 设置集群名
//                .put("client.transport.sniff", true) // 开启嗅探 , 开启后会一直连接不上, 原因未知
                .put("network.host", "192.168.50.37")
                .put("client.transport.ignore_cluster_name", true) // 忽略集群名字验证, 打开后集群名字不对也能连接上
//                .put("client.transport.nodes_sampler_interval", 5) //报错,
//                .put("client.transport.ping_timeout", 5) // 报错, ping等待时间,
                .build();
        client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(ip, port)));
        // 默认5s
        // 多久打开连接, 默认5s
        logger.info("************************success connect*********************************");
    }

    public static Client getClient(){
        return client;
    }
}