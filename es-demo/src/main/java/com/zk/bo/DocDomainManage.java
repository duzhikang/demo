package com.zk.bo;

import java.util.Date;

/**
 * <p>ClassName: DocDomainManage</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/4
 * @since JDK 1.8
 */
public class DocDomainManage {

    private Long manageId;

    private Long memberId;

    private String domain;

    private Integer recordStatus;

    private Integer iyongStatus;

    private Integer analysisStatus;

    private Integer authStatus;

    private Date registerDatetime;

    private Date expiresDatetime;

    private Integer originType;

    private Date createDatetime;

    private Date updateDatetime;

    public Long getManageId() {
        return manageId;
    }

    public void setManageId(Long manageId) {
        this.manageId = manageId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Integer getIyongStatus() {
        return iyongStatus;
    }

    public void setIyongStatus(Integer iyongStatus) {
        this.iyongStatus = iyongStatus;
    }

    public Integer getAnalysisStatus() {
        return analysisStatus;
    }

    public void setAnalysisStatus(Integer analysisStatus) {
        this.analysisStatus = analysisStatus;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public Date getRegisterDatetime() {
        return registerDatetime;
    }

    public void setRegisterDatetime(Date registerDatetime) {
        this.registerDatetime = registerDatetime;
    }

    public Date getExpiresDatetime() {
        return expiresDatetime;
    }

    public void setExpiresDatetime(Date expiresDatetime) {
        this.expiresDatetime = expiresDatetime;
    }

    public Integer getOriginType() {
        return originType;
    }

    public void setOriginType(Integer originType) {
        this.originType = originType;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    @Override
    public String toString() {
        return "DocDomainManage{" +
                "manageId=" + manageId +
                ", memberId=" + memberId +
                ", domain='" + domain + '\'' +
                ", recordStatus=" + recordStatus +
                ", iyongStatus=" + iyongStatus +
                ", analysisStatus=" + analysisStatus +
                ", authStatus=" + authStatus +
                ", registerDatetime=" + registerDatetime +
                ", expiresDatetime=" + expiresDatetime +
                ", originType='" + originType + '\'' +
                ", createDatetime=" + createDatetime +
                ", updateDatetime=" + updateDatetime +
                '}';
    }
}
