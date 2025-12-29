package com.example.Zapy.DTO;

public class NodeDTO {

    private String id;
    private String type;        // "Trigger" or "Action"
    private PositionDTO position;
    private NodeDataDTO data;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public PositionDTO getPosition() { return position; }
    public void setPosition(PositionDTO position) { this.position = position; }

    public NodeDataDTO getData() { return data; }
    public void setData(NodeDataDTO data) { this.data = data; }
}
