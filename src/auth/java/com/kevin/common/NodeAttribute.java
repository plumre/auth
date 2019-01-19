package com.kevin.common;

/*
 * Created by renhongjiang on 2019/1/19.
 */

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/19 12:02
 */
public class NodeAttribute {

    private String url;
    private Long nodeId;

    public NodeAttribute(String url, Long nodeId) {
        this.url = url;
        this.nodeId = nodeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }
}