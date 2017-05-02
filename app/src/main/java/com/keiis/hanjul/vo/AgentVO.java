package com.keiis.hanjul.vo;

/**
 * Created by kyh on 2017-01-30.
 */

public class AgentVO {
    String select;
    String agentCd;
    String agentName;

    public AgentVO(String select, String agentCd, String agentName){
        this.select        = select;
        this.agentCd   = agentCd;
        this.agentName = agentName;
    }

    public String getSelect()          { return select; }
    public void setSelect(String select) { this.select = select; }
    public String getAgentCd()      { return agentCd; }
    public void setAgentCd(String agentCd) { this.agentCd = agentCd; }
    public String getAgentName() { return agentName; }
    public void setAgentName(String agentName) { this.agentName = agentName; }
}
