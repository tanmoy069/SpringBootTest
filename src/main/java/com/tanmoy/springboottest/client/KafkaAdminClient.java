package com.tanmoy.springboottest.client;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.Node;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public final class KafkaAdminClient {
    private final AdminClient client;

    public KafkaAdminClient() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.17:9092");
        properties.put("connections.max.idle.ms", 10000);
        properties.put("request.timeout.ms", 5000);

        this.client = AdminClient.create(properties);
    }

    public boolean verifyConnection() {
        try {
            Collection<Node> nodes = this.client.describeCluster()
                    .nodes()
                    .get();
            return nodes != null && !nodes.isEmpty();
        } catch (InterruptedException | ExecutionException e1) {
            return false;
        }
    }
}

