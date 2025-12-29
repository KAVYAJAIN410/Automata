package com.example.Zapy.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Zapy.DTO.EdgePostDTO;
import com.example.Zapy.DTO.FlowSaveRequest;
import com.example.Zapy.DTO.NodeDTO;
import com.example.Zapy.Repository.ActionRepo;
import com.example.Zapy.Repository.EdgeRepo;
import com.example.Zapy.Repository.TriggerRepo;
import com.example.Zapy.Repository.ZapRepo;
import com.example.Zapy.model.Zap;

import jakarta.transaction.Transactional;



@Service
public class FlowService {
    public class NodeRef {

    private final String type;   // "Trigger" or "Action"
    private final String dbId;

    public NodeRef(String type, String dbId) {
        this.type = type;
        this.dbId = dbId;
    }

    public String getType() {
        return type;
    }

    public String getDbId() {
        return dbId;
    }
}

    // Inject repositories here
    private final TriggerRepo triggerRepo;
    private final ActionRepo actionRepo;
    private final EdgeRepo edgeRepo;
    private final ZapRepo zapRepo;
    private final TriggerService triggerService;
    private final ActionService actionService;
    private final EdgeService edgeService;

    public FlowService(
        TriggerRepo triggerRepo,
        ActionRepo actionRepo,
        EdgeRepo edgeRepo,
        ZapRepo zapRepo,
        TriggerService triggerService,
        ActionService actionService,
        EdgeService edgeService
    ) {
        this.triggerRepo = triggerRepo;
        this.actionRepo = actionRepo;
        this.edgeRepo = edgeRepo;
        this.zapRepo=zapRepo;
        this.triggerService = triggerService;
        this.actionService=actionService;
        this.edgeService=edgeService;
    }

     @Transactional
    public void replaceFlowByProjectId(String projectId,FlowSaveRequest request) {

        Zap zap = zapRepo.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("Zap not found"));

        String zapId = zap.getId();

        
        edgeRepo.deleteByZapId(zapId);
        actionRepo.deleteByZapId(zapId);
        triggerRepo.deleteByZapId(zapId);

        List<NodeDTO> nodes = request.getNodes();
        List<EdgePostDTO> edges = request.getEdges();


        HashMap<String, NodeRef> map = new HashMap<>();
        for (NodeDTO node : nodes) {
    if ("Trigger".equals(node.getType())) {
          String dbTriggerId = triggerService.createTrigger(
                projectId,
                node.getData().getTypeId(),
                node.getPosition().getX(),
                node.getPosition().getY()
        );

        // 🔑 Store mapping
        map.put(node.getId(),new NodeRef("Trigger", dbTriggerId));  
    }
     if ("Action".equals(node.getType())) {
        String dbActionId = actionService.createAction(
                projectId,
                node.getData().getTypeId(),
                node.getPosition().getX(),
                node.getPosition().getY()
        );
        map.put(node.getId(), new NodeRef("Action", dbActionId));
    }
  

}
for (EdgePostDTO edge : edges) {

   String sourceFrontendId = edge.getSource();
   String targetFrontendId = edge.getTarget();

   NodeRef source = map.get(sourceFrontendId);
    NodeRef target = map.get(targetFrontendId);

    if (source == null || target == null) {
        throw new RuntimeException("Invalid edge mapping");
    }

    if ("Trigger".equals(source.getType())) {
        edgeService.createFromTrigger(
            zap,
            source.getDbId(),
            target.getDbId()
        );
    } else {
        edgeService.createFromAction(
            zap,
            source.getDbId(),
            target.getDbId()
        );
    }
}
        
    }
}
