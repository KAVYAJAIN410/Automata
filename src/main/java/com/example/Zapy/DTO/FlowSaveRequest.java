package com.example.Zapy.DTO;

import java.util.List;

public class FlowSaveRequest {

    private List<NodeDTO> nodes;
    private List<EdgePostDTO> edges;

    public List<NodeDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeDTO> nodes) {
        this.nodes = nodes;
    }

    public List<EdgePostDTO> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgePostDTO> edges) {
        this.edges = edges;
    }
}
