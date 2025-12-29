package com.example.Zapy.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Zapy.Repository.ZapRunOutboxRepo;
import com.example.Zapy.model.ZapRunOutbox;

@Service
public class outboxWorker {
    

    private final ZapRunOutboxRepo outboxRepo;
    private final KafkaProducerService producer;
     public static final String TOPIC_NAME = "zap-events";

    public outboxWorker(ZapRunOutboxRepo outboxRepo, KafkaProducerService producer) {
        this.outboxRepo = outboxRepo;
        this.producer = producer;
    }
    @Scheduled(fixedDelay = 3000) // runs every 3 seconds
    @Transactional
    public void process() {

       PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id"));
List<ZapRunOutbox> rows = outboxRepo.findAll(pageRequest).getContent();

        if (rows.isEmpty()) {
            return; // nothing to send
        }

        for (ZapRunOutbox row : rows) {
            String payload = String.format("{\"zapRunId\": %s, \"stage\": 0}", row.getZapRun().getId());
            producer.send(TOPIC_NAME, payload);
        }

        // delete processed rows
        List<String> ids = rows.stream().map(ZapRunOutbox::getId).toList();
        outboxRepo.deleteAllById(ids);

        System.out.println("Sent " + rows.size() + " event(s) to Kafka");
    }

}
