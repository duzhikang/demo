package com.zk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>ClassName: DomainManage</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/4
 * @since JDK 1.8
 */
@Entity
@Table(name = "t_domain_manage")
public class DomainManage {

    @Id
    private Long manageId;

    private Long memberId;

    private String encrypt;

    private String domain;

    private Integer recordStatus;

    private Integer iyongStatus;

    private Integer analysisStatus;

    private Integer authStatus;

    private Date registerDatetime;

    private Date expiresDatetime;

    private String certUrl;

    private String checkCert;

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

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
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

    public String getCertUrl() {
        return certUrl;
    }

    public void setCertUrl(String certUrl) {
        this.certUrl = certUrl;
    }

    public String getCheckCert() {
        return checkCert;
    }

    public void setCheckCert(String checkCert) {
        this.checkCert = checkCert;
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
        return "DomainManage{" +
                "manageId=" + manageId +
                ", memberId=" + memberId +
                ", encrypt='" + encrypt + '\'' +
                ", domain='" + domain + '\'' +
                ", recordStatus=" + recordStatus +
                ", iyongStatus=" + iyongStatus +
                ", analysisStatus=" + analysisStatus +
                ", authStatus=" + authStatus +
                ", registerDatetime=" + registerDatetime +
                ", expiresDatetime=" + expiresDatetime +
                ", certUrl='" + certUrl + '\'' +
                ", checkCert='" + checkCert + '\'' +
                ", originType='" + originType + '\'' +
                ", createDatetime=" + createDatetime +
                ", updateDatetime=" + updateDatetime +
                '}';
    }
}
